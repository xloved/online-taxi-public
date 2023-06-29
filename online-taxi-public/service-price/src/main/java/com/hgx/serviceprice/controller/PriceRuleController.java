package com.hgx.serviceprice.controller;

import com.hgx.internalcomm.dto.PriceRule;
import com.hgx.internalcomm.dto.ResponseResult;
import com.hgx.serviceprice.service.PriceRuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description 计价规格
 * @Author huogaoxu
 * @Date 2023-06-14 9:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/price-rule")
public class PriceRuleController {

    @Resource
    private PriceRuleService priceRuleService;

    /**
     * 添加计价规格
     * @param priceRule
     * @return
     */
    @PostMapping("/add")
    public ResponseResult addPriceRule(@RequestBody PriceRule priceRule){

        return priceRuleService.addPriceRule(priceRule);
    }

    /**
     * 编辑计价规格
     * @param priceRule
     * @return
     */
    @PostMapping("/edit")
    public ResponseResult editPriceRule(@RequestBody PriceRule priceRule){

        return priceRuleService.edit(priceRule);
    }


    /**
     * 查询最新的计价规则
     * @param fareType
     * @return
     */
    @GetMapping("/getNewestVersion")
    public ResponseResult getNewestVersion(@RequestParam String fareType){

        return priceRuleService.getNewestVersion(fareType);
    }

    /**
     * 判断是否是最新的计价规则
     * @param fareType
     * @param fareVersion
     * @return
     */
    @GetMapping("/isNewVersion")
    public ResponseResult isNewVersion(@RequestParam String fareType, @RequestParam Integer fareVersion){

        return priceRuleService.isNewVersion(fareType, fareVersion);

    }
}
