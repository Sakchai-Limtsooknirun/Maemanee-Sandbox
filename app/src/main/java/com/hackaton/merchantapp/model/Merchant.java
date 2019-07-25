package com.hackaton.merchantapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Merchant implements Serializable {

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getBillerId() {
        return billerId;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getBillerName() {
        return billerName;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public String getTerminalPrefix() {
        return terminalPrefix;
    }

    public void setTerminalPrefix(String terminalPrefix) {
        this.terminalPrefix = terminalPrefix;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Promotion> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    private int mId ;

    private String merchantId ;

    private String merchantName ;

    private String terminalId  ;

    private String billerId  ;

    private String billerName ;

    private String terminalPrefix ;

    private double latitude;

    private double longitude;

    private List<Promotion> promotionList = new ArrayList<>();
}
