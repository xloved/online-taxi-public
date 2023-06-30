package com.hgx.serviceprice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.dto.PriceRule;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.serviceprice.mapper.PriceRuleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 计价规格
 * @Author huogaoxu
 * @Date 2023-06-14 9:36
 * @Version 1.0
 **/
@Service
public class PriceRuleService {

    @Resource
    private PriceRuleMapper priceRuleMapper;

    /**
     * 添加计价规格
     * @param priceRule
     * @return
     */
    public ResponseResult addPriceRule(PriceRule priceRule){
        // 获取priceRule，然后拼接traceType
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode + "$" + vehicleType;
        priceRule.setFareType(fareType);

        // 插入默认版本号,先根据城市编码和车辆类型，由版本号信息分组降序查出当前版本的价格信息
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code",cityCode)
                .eq("vehicle_type",vehicleType)
                .orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        // 指定默认版本号
        int fareVersion = 1;
        /**
         * 判断查出的数据是否有值,有值存在则返回警告信息，
         * 不然数据库会存在除了版本号不一样之外其他数据都一样的多条记录，容易造成大量废数据
         */
        if (priceRules.size() > 0) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EXISTS.getCode(),
                    CommonStatusEnum.PRICE_RULE_EXISTS.getValue());
        }
        priceRule.setFareVersion(fareVersion);
        // 添加数据
        priceRuleMapper.insert(priceRule);

        return ResponseResult.success();
    }

    /**
     * 编辑计价规则
     * @param priceRule
     * @return
     */
    public ResponseResult edit(PriceRule priceRule){
        // 获取priceRule，然后拼接traceType
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        String fareType = cityCode + "$" + vehicleType;
        priceRule.setFareType(fareType);

        // 插入默认版本号,先根据城市编码和车辆类型，由版本号信息分组降序查出当前版本的价格信息
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code",cityCode)
                .eq("vehicle_type",vehicleType)
                .orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        // 指定默认版本号
        int fareVersion = 1;

        // 判断查出的数据是否有值
        if (priceRules.size() > 0) {

            PriceRule rule = priceRules.get(0);
            Integer startMile = rule.getStartMile();
            Double startFare = rule.getStartFare();
            Double unitPricePerMile = rule.getUnitPricePerMile();
            Double unitPricePerMinute = rule.getUnitPricePerMinute();
            // 判断数据库的值与前端传来的值是否相等，若相等，则提示错误信息，否则更新数据库
            if (startMile.intValue() == priceRule.getStartMile().intValue()
            && startFare.doubleValue() == priceRule.getStartFare().doubleValue()
            && unitPricePerMile.doubleValue() == priceRule.getUnitPricePerMile().doubleValue()
            && unitPricePerMinute.doubleValue() == priceRule.getUnitPricePerMinute().doubleValue()
            ){
                return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_NOT_EDIT.getCode(),
                        CommonStatusEnum.PRICE_RULE_NOT_EDIT.getValue());
            }

            fareVersion = rule.getFareVersion() + 1;
        }
        priceRule.setFareVersion(fareVersion);
        // 添加数据
        priceRuleMapper.insert(priceRule);

        return ResponseResult.success(" ");
    }

    /**
     * 查询最新版本的计价规则
     *
     * @param fareType
     * @return
     */
    public ResponseResult getNewestVersion(String fareType){

        QueryWrapper<PriceRule> queryWrap = new QueryWrapper<>();
        queryWrap.eq("fare_type",fareType)
                .orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrap);
        // 判断价格是否为null
        if(priceRules.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),
                    CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        return ResponseResult.success(priceRules.get(0));
    }

    /**
     * 判断当前的计价规则是否是最新的
     *
     * @param fareType
     * @param fareVersion
     * @return
     */
    public ResponseResult<Boolean> isNewVersion(String fareType, Integer fareVersion){
        ResponseResult newestVersion = getNewestVersion(fareType);
        if(newestVersion.getCode() == CommonStatusEnum.PRICE_RULE_EMPTY.getCode()){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),
                    CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        PriceRule data = (PriceRule) newestVersion.getData();
        Integer versionDB = data.getFareVersion();
        if(versionDB > fareVersion){
            return ResponseResult.success(false);
        }
            return ResponseResult.success(true);
    }

    /**
     * 判断当前城市的车辆类型和计价规则是否存在
     * @param priceRule
     * @return
     */
    public ResponseResult<Boolean> ifExits(PriceRule priceRule){
        String cityCode = priceRule.getCityCode();
        String vehicleType = priceRule.getVehicleType();
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code",cityCode)
                .eq("vehicle_Type",vehicleType)
                .orderByDesc("fare_version");
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        if (priceRules.size() > 0) {
            return ResponseResult.success(true);
        } else {
          return ResponseResult.success(false);
        }
    }
}
