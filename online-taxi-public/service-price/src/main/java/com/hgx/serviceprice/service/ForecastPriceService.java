package com.hgx.serviceprice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.dto.PriceRule;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.ForecastPriceDTO;
import com.hgx.internalcomm.response.DirectionResponse;
import com.hgx.internalcomm.response.ForecastPriceResponse;
import com.hgx.internalcomm.utils.BigDecimalUtils;
import com.hgx.serviceprice.mapper.PriceRuleMapper;
import com.hgx.serviceprice.remote.ServiceMapClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @Description  预估价格
 * @Author huogaoxu
 * @Date 2023-01-02 15:24
 **/
@Service
@Slf4j
public class ForecastPriceService {

    //注入service-map服务到service-price
    @Resource
    private ServiceMapClients serviceMapClients;

    @Resource
    private PriceRuleMapper priceRuleMapper;

    //获取经纬度，然后调用地图服务计算价格
    public ResponseResult forecasePrice(String depLongitude, String depLatitude, String destLongitude,
                                        String destLatitude, String cityCode, String vehicleType){
        log.info("出发地经度："+depLongitude);
        log.info("出发地纬度："+depLatitude);
        log.info("目的地经度："+destLongitude);
        log.info("目的地纬度："+destLatitude);

        log.info("调用地图服务，查询距离和时长");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        ResponseResult<DirectionResponse> direction = serviceMapClients.direction(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info("行驶距离"+distance+",行驶时长"+duration);

        log.info("读取计价规则");
//        Map<String, Object> map = new HashMap<>();
//        map.put("city_code","11000");
//        map.put("vehicle_type","1");

        QueryWrapper<PriceRule> queryWrap = new QueryWrapper();
        queryWrap.eq("city_code", cityCode)
                .eq("vehicle_type", vehicleType)
                .orderByDesc("fare_version");
        List<PriceRule> priceRuleList = priceRuleMapper.selectList(queryWrap);
        //如果跟据城市编号和车辆类型查不出来，返回一个错误信息
       if(priceRuleList.size() == 0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(),CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }

        PriceRule priceRule = priceRuleList.get(0);
        double price = getPrice(distance, duration, priceRule);


        log.info("根据距离，时长和计价规则，计算价格");
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        forecastPriceResponse.setCityCode(cityCode);
        forecastPriceResponse.setVehicleType(vehicleType);
        forecastPriceResponse.setFareType(priceRule.getFareType());
        forecastPriceResponse.setFareVersion(priceRule.getFareVersion());
        return ResponseResult.success(forecastPriceResponse);
    }

    /**
     * 根据距离，时长和计价规则，计算最终价格
     * @param distance
     * @param duration
     * @param priceRule
     * @return
     */
    private double getPrice(Integer distance, Integer duration, PriceRule priceRule){
        double price = 0;


        // 起步价
        double startFare = priceRule.getStartFare();
        price = BigDecimalUtils.add(price, startFare);
        // 里程费
        // 获取总里程 m
        double distanceMile = BigDecimalUtils.divide(distance, 1000);

        // 起步里程
        double startMile = priceRule.getStartMile();
        double distanceSubstract =BigDecimalUtils.substract(distanceMile, startMile);

        //最终收费的里程数 km
        Double mile = distanceSubstract < 0?0:distanceSubstract;
        //计程单价 元/km
        Double unitPricePerMile = priceRule.getUnitPricePerMile();

        //里程价格
        double mailFare = BigDecimalUtils.multiply(mile, unitPricePerMile);
        price = BigDecimalUtils.add(price,mailFare);

        // 时长的分钟数
        double timeMinute = BigDecimalUtils.divide(duration, 60);
        // 计时单价
        Double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        // 时长费用
        double timeFare = BigDecimalUtils.multiply(timeMinute, unitPricePerMinute);

        price = BigDecimalUtils.add(price, timeFare);
        BigDecimal priceBigDecimal = BigDecimal.valueOf(price);
        priceBigDecimal = priceBigDecimal.setScale(2, RoundingMode.HALF_UP);// 四舍五入

        return priceBigDecimal.doubleValue();
    }

}
