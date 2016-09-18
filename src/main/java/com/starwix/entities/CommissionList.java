package com.starwix.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by starwix on 18.9.16.
 */
@XmlRootElement(name = "commissions")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommissionList {
    @XmlElement(name = "commission")
    private ArrayList<Commission> commissions;

    public CommissionList() {
    }

    public CommissionList(final ArrayList<Commission> commissions) {
        this.commissions = commissions;
    }

    public ArrayList<Commission> getCommissions() {
        return commissions;
    }

    @Override
    public String toString() {
        return "CommissionList{" +
                "commissions=" + commissions +
                '}';
    }
}
