package com.bookctrlcenter.entity;

public class ClassInfo {
    private int classID;
    private String department;
    private int stuCount;
    private int realCount;

    public int getRealCount() {
        return realCount;
    }

    public void setRealCount(int realCount) {
        this.realCount = realCount;
    }

    private int size;
    private int start;
    private int level;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private String issuingUserID;
    private String issuingTime;

    public String getIssuingTime() {
        return issuingTime;
    }

    public void setIssuingTime(String issuingTime) {
        this.issuingTime = issuingTime;
    }

    public String getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
    }

    private String settlementTime;

    private float totalPrice;
    private int mStatus;

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    private float money;
    private String settlementUserID;
    private int bmStatus;

    public int getBmStatus() {
        return bmStatus;
    }

    public void setBmStatus(int bmStatus) {
        this.bmStatus = bmStatus;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getBookMoney() {
        return bookMoney;
    }

    public void setBookMoney(float bookMoney) {
        this.bookMoney = bookMoney;
    }

    public String getIssuingUserID() {
        return issuingUserID;
    }

    public void setIssuingUserID(String issuingUserID) {
        this.issuingUserID = issuingUserID;
    }

    public String getSettlementUserID() {
        return settlementUserID;
    }

    public void setSettlementUserID(String settlementUserID) {
        this.settlementUserID = settlementUserID;
    }

    private String username;
    private int settlement;
    private float bookMoney;
    public int getSettlement() {
        return settlement;
    }

    public void setSettlement(int settlement) {
        this.settlement = settlement;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    private int issuing;

    public int getIssuing() {
        return issuing;
    }

    public void setIssuing(int issuing) {
        this.issuing = issuing;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getDepartments() {
        return department;
    }

    public void setDepartments(String departments) {
        this.department = departments;
    }

    public int getStuCount() {
        return stuCount;
    }

    public void setStuCount(int stuCount) {
        this.stuCount = stuCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
