package com.ssh.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/28.
 */
@Embeddable
public class Address implements Serializable {
    private String province;
    private String city;
    private String area;
    private String detial;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }
}
