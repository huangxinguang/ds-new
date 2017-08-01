package com.ectrip.api.model;

import java.io.Serializable;

public class StsschecktabKey implements Serializable {
    private Long szsoldticketsubid;

    private Long szsoldticketid;

    private Long isalesvoucherdetailsid;

    private Long isalesvoucherid;

    private Long iticketstationid;

    public Long getSzsoldticketsubid() {
        return szsoldticketsubid;
    }

    public void setSzsoldticketsubid(Long szsoldticketsubid) {
        this.szsoldticketsubid = szsoldticketsubid;
    }

    public Long getSzsoldticketid() {
        return szsoldticketid;
    }

    public void setSzsoldticketid(Long szsoldticketid) {
        this.szsoldticketid = szsoldticketid;
    }

    public Long getIsalesvoucherdetailsid() {
        return isalesvoucherdetailsid;
    }

    public void setIsalesvoucherdetailsid(Long isalesvoucherdetailsid) {
        this.isalesvoucherdetailsid = isalesvoucherdetailsid;
    }

    public Long getIsalesvoucherid() {
        return isalesvoucherid;
    }

    public void setIsalesvoucherid(Long isalesvoucherid) {
        this.isalesvoucherid = isalesvoucherid;
    }

    public Long getIticketstationid() {
        return iticketstationid;
    }

    public void setIticketstationid(Long iticketstationid) {
        this.iticketstationid = iticketstationid;
    }
}