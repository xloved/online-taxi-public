package com.hgx.servicemap.service;

import com.hgx.internalcomm.constant.AmapConfigConstants;
import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.dto.DicDistrict;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.servicemap.mapper.DicdistrictMapper;
import com.hgx.servicemap.remote.MapDicDistrictClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author huogaoxu
 * @Date 2023-01-31 23:06
 **/
@Service
public class DicDistrictService {

    @Resource
    private MapDicDistrictClient mapDicDistrictClient;
    @Resource
    private DicdistrictMapper dicdistrictMapper;

    /**
     * 根据关键字查询行政区域
     * @param keywords
     * @return
     */
    public ResponseResult initDistrict(String keywords){

        // 拼装请求的URL
        String dicDistrict = mapDicDistrictClient.dicDistrict(keywords);
        System.out.println(dicDistrict);
        //解析结果
        JSONObject dicDistrictJsonObject = JSONObject.fromObject(dicDistrict);
        int status = dicDistrictJsonObject.getInt(AmapConfigConstants.STATUS);
        if (status != 1) {
                return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERROR.getCode(),
                        CommonStatusEnum.MAP_DISTRICT_ERROR.getValue());
        }
        //获取国家
        JSONArray countryJsonArray = dicDistrictJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
        for (int country = 0; country < countryJsonArray.size(); country++) {
            JSONObject countryJsonObject = countryJsonArray.getJSONObject(country);
            String countryAddressCode = countryJsonObject.getString(AmapConfigConstants.ADCODE);
            String countryAddressName = countryJsonObject.getString(AmapConfigConstants.NAME);
            String countryParentAddressCode = "0";
            String countryLevel = countryJsonObject.getString(AmapConfigConstants.LEVEL);
            insertDicDistrict(countryAddressCode, countryAddressName, countryParentAddressCode, countryLevel);

            //获取省份
           JSONArray provinceJsonArray =  countryJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
            for (int pro = 0; pro < provinceJsonArray.size(); pro++) {
                JSONObject provinceJsonObject = provinceJsonArray.getJSONObject(pro);
                String provinceAddressCode = provinceJsonObject.getString(AmapConfigConstants.ADCODE);
                String provinceAddressName = provinceJsonObject.getString(AmapConfigConstants.NAME);
                String provinceParentAddressCode = countryAddressCode;
                String provinceLevel = provinceJsonObject.getString(AmapConfigConstants.LEVEL);
                insertDicDistrict(provinceAddressCode, provinceAddressName, provinceParentAddressCode, provinceLevel);

                //获取市
                JSONArray cityJsonArray = provinceJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                for (int city = 0; city < cityJsonArray.size(); city++) {
                    JSONObject cityJsonObject = cityJsonArray.getJSONObject(city);
                    String cityAddressCode = cityJsonObject.getString(AmapConfigConstants.ADCODE);
                    String cityAddressName = cityJsonObject.getString(AmapConfigConstants.NAME);
                    String cityParentAddressCode = provinceAddressCode;
                    String cityLevel = cityJsonObject.getString(AmapConfigConstants.LEVEL);
                    insertDicDistrict(cityAddressCode, cityAddressName, cityParentAddressCode, cityLevel);

                    //获取区县街道
                    JSONArray districtArray = cityJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                    for (int d = 0; d < districtArray.size(); d++) {
                        JSONObject districtJsonObject = districtArray.getJSONObject(d);
                        String districtAddressCode = districtJsonObject.getString(AmapConfigConstants.ADCODE);
                        String districtAddressName = districtJsonObject.getString(AmapConfigConstants.NAME);
                        String districtParentAddressCode = cityAddressCode;
                        String districtLevel = districtJsonObject.getString(AmapConfigConstants.LEVEL);
                        //判断是否是街道
                        if (districtLevel.equals(AmapConfigConstants.STREET)) {
                            continue;
                        }
                        insertDicDistrict(districtAddressCode, districtAddressName, districtParentAddressCode, districtLevel);
                    }
                }
            }

        }
        //插入数据库
        return ResponseResult.success();
    }

    // 插入数据库对象方法
    public void insertDicDistrict(String addressCode, String addressName, String parentAdressCode, String level){
        DicDistrict dicDistrict = new DicDistrict();
        dicDistrict.setAddressCode(addressCode);
        dicDistrict.setAddressName(addressName);
        dicDistrict.setParentAddressCode(parentAdressCode);
        int levelInt = generatLevel(level);
        dicDistrict.setLevel(levelInt);
        dicdistrictMapper.insert(dicDistrict);
    }

    //省市级别判断
    public int generatLevel(String level){
        int levelInt = 0;
        if (level.trim().equals("country")) {
           levelInt = 0;
        } else if (level.trim().equals("province")) {
           levelInt = 1;
        } else if (level.trim().equals("city")) {
            levelInt = 2;
        } else if (level.trim().equals("district")) {
            levelInt = 3;
        }
        return levelInt;
    }
}
