package com.northbrain.base.common.model.vo.atom;

import com.northbrain.base.common.model.vo.basic.BasicVO;

public class RegistryVO extends BasicVO
{
    private Integer registryId;

    private Integer partyId;

    public Integer getRegistryId() {
        return registryId;
    }

    public void setRegistryId(Integer registryId) {
        this.registryId = registryId;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }
}
