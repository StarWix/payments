package com.starwix.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by starwix on 18.9.16.
 */
@XmlRootElement(name = "commissions")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommissionList {
    @XmlElement(name = "commission")
    private List<Commission> commissions;

    public CommissionList() {
    }

    public CommissionList(final List<Commission> commissions) {
        this.commissions = commissions;
    }

    public List<Commission> getCommissions() {
        return commissions;
    }

    @Override
    public String toString() {
        return "CommissionList{" +
                "commissions=" + commissions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommissionList that = (CommissionList) o;

        return commissions.equals(that.commissions);

    }

    @Override
    public int hashCode() {
        return commissions.hashCode();
    }
}
