package com.hgx.serviceprice.service;

import com.hgx.internalcomm.constant.CommonStatusEnum;
import com.hgx.internalcomm.dto.PriceRule;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.internalcomm.request.ForecastPriceDTO;
import com.hgx.internalcomm.response.DirectionResponse;
import com.hgx.internalcomm.response.ForecastPriceResponse;
import com.hgx.serviceprice.mapper.PriceRuleMapper;
import com.hgx.serviceprice.remote.ServiceMapClients;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseResult forecasePrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
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
        Map<String, Object> map = new HashMap<>();
        map.put("city_code","11000");
        map.put("vehicle_type","1");
        List<PriceRule> priceRuleList = priceRuleMapper.selectByMap(map);
        //如果跟你城市编号和车辆类型查不出来，返回一个错误信息
       if(priceRuleList.size() == 0){
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_ERROR.getCode(),CommonStatusEnum.PRICE_RULE_ERROR.getValue());
        }
        PriceRule priceRule = priceRuleList.get(0);
        double price = getPrice(distance, duration, priceRule);


        log.info("根据距离，时长和计价规则，计算价格");
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
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
        BigDecimal price = new BigDecimal(0);

        // 起步价
        double startFare = priceRule.getStartFare();
        BigDecimal startFareDecimal = new BigDecimal(startFare);
        price = price.add(startFareDecimal);
        // 里程费
        // 获取总里程 m
        BigDecimal distanceDecimal = new BigDecimal(distance);
        // 总里程 km
        BigDecimal distanceMileDecimal = distanceDecimal.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
        // 起步里程
        Integer startMile = priceRule.getStartMile();
        BigDecimal startMileDecimal = new BigDecimal(startMile);
        double distanceSubstract = distanceMileDecimal.subtract(startMileDecimal).doubleValue();
        //最终收费的里程数 km
        Double mile = distanceSubstract < 0?0:distanceSubstract;
        BigDecimal mileDecimal =  new BigDecimal(mile);
        //计程单价 元/km
        Double unitPricePerMile = priceRule.getUnitPricePerMile();
        BigDecimal unitPricePerMileDecimal = new BigDecimal(unitPricePerMile);
        //里程价格
        BigDecimal mileFare = mileDecimal.multiply(unitPricePerMileDecimal).setScale(2, BigDecimal.ROUND_HALF_UP);
        price = price.add(mileFare);

        // 时长费
        BigDecimal time = new BigDecimal(duration);
        // 时长的分钟数
        BigDecimal timeDecimal = time.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
        // 计时单价
        Double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        BigDecimal unitPricePerMinuteDecimal = new BigDecimal(unitPricePerMinute);
        // 时长费用
        BigDecimal timeFare = timeDecimal.multiply(unitPricePerMinuteDecimal);
        price = price.add(timeFare).setScale(2, BigDecimal.ROUND_HALF_UP);

        return price.doubleValue();
    }

//    public static void main(String[] args) {//测试价格
//        PriceRule priceRule = new PriceRule();
//        priceRule.setStartMile(3);
//        priceRule.setStartFare(10.0);
//        priceRule.setUnitPricePerMile(1.8);
//        priceRule.setUnitPricePerMinute(0.5);
//        System.out.println(getPrice(6500,1800, priceRule));
//    }
}
