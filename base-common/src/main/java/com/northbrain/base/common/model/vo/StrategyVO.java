package com.northbrain.base.common.model.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 类名：策略值对象类
 * 用途：实现domain层以上策略对象的传递
 * @author Jiakun
 * @version 1.0
 */
public class StrategyVO extends BasicVO
{
    //策略编号
    private Integer strategyId;

    //策略名称
    private String name;

    private List<StrategyDetailVO> strategyDetailVOS;

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<StrategyDetailVO> getStrategyDetailVOS() {
        return strategyDetailVOS;
    }

    public void setStrategyDetailVOS(List<StrategyDetailVO> strategyDetailVOS) {
        this.strategyDetailVOS = strategyDetailVOS;
    }

    public boolean addStrategyDetail(StrategyDetailVO newStrategyDetailVO)
    {
        if(newStrategyDetailVO == null)
            return false;

        if(this.strategyDetailVOS == null)
            this.strategyDetailVOS = new ArrayList<>();

        boolean isContain = false;

        for(StrategyDetailVO strategyDetailVO: this.strategyDetailVOS)
            if(Objects.equals(strategyDetailVO.getStrategyDetailId(), newStrategyDetailVO.getStrategyDetailId()))
                isContain = true;

        if(!isContain)
            this.strategyDetailVOS.add(newStrategyDetailVO);

        return true;
    }

    //策略明细值对象类
    public static class StrategyDetailVO extends BasicDetailVO
    {
        //策略明细编号
        private Integer strategyDetailId;

        public Integer getStrategyDetailId() {
            return strategyDetailId;
        }

        public void setStrategyDetailId(Integer strategyDetailId) {
            this.strategyDetailId = strategyDetailId;
        }
    }
}
