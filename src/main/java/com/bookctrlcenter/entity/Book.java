package com.bookctrlcenter.entity;

public class Book {
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    private String bookName;
    private int id;
    private int supplierID;
    private Suppliers suppliers;
    private Printerss printerss;


    public Suppliers getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }

    public Printerss getPrinterss() {
        return printerss;
    }

    public void setPrinterss(Printerss printerss) {
        this.printerss = printerss;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    private float price;
    private String author;
    private String departmentID;
    private int status;

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    private int status2;

    public int getStatus2() {
        return status2;
    }

    public void setStatus2(int status2) {
        this.status2 = status2;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public int getUserID2() {
        return userID2;
    }

    public void setUserID2(int userID2) {
        this.userID2 = userID2;
    }

    private ClassSchedule classSchedule;

    public ClassSchedule getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(ClassSchedule classSchedule) {
        this.classSchedule = classSchedule;
    }

    private String time;
    private String time2;
    private String time3;
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private int count;
    private int warehousing;
    private int classID;
    private Util util;
    private float totalPrice;

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getIsSB() {
        return isSB;
    }

    public void setIsSB(String isSB) {
        this.isSB = isSB;
    }

    private String isSB;



    private ClassInfo classInfo;

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public Util getUtil() {
        return util;
    }

    public void setUtil(Util util) {
        this.util = util;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getWarehousing() {
        return warehousing;
    }

    public void setWarehousing(int warehousing) {
        this.warehousing = warehousing;
    }

    public int getRecount() {
        return recount;
    }

    public void setRecount(int recount) {
        this.recount = recount;
    }

    private int recount;
    private int stuCount;

    public int getStuCount() {
        return stuCount;
    }

    public void setStuCount(int stuCount) {
        this.stuCount = stuCount;
    }

    public int getCountStock() {
        return countStock;
    }

    public void setCountStock(int countStock) {
        this.countStock = countStock;
    }

    private int countStock;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private String userID;
    private int userID2;
    public float getPrice() {
        return price;
    }
    private String usType;

    public String getUsType() {
        return usType;
    }

    public void setUsType(String usType) {
        this.usType = usType;
    }

    public Book(String bookName, int id, int supplierID, Suppliers supplier, Printerss printers, float price, String author, String departmentID, int status, int status2, ClassSchedule classSchedule, String time, String time2, String time3, int count, int warehousing, int classID, Util util, int totalPrice, String isSB, ClassInfo classInfo, int recount, int stuCount, int countStock, String userID, int userID2, String publisher, String isbn, int start, int size, int needCount, User user) {
        this.bookName = bookName;
        this.id = id;
        this.supplierID = supplierID;
        this.suppliers = supplier;
        this.printerss = printers;
        this.price = price;
        this.author = author;
        this.departmentID = departmentID;
        this.status = status;
        this.status2 = status2;
        this.classSchedule = classSchedule;
        this.time = time;
        this.time2 = time2;
        this.time3 = time3;
        this.count = count;
        this.warehousing = warehousing;
        this.classID = classID;
        this.util = util;
        this.totalPrice = totalPrice;
        this.isSB = isSB;
        this.classInfo = classInfo;
        this.recount = recount;
        this.stuCount = stuCount;
        this.countStock = countStock;
        this.userID = userID;
        this.userID2 = userID2;
        this.publisher = publisher;
        this.isbn = isbn;
        this.start = start;
        this.size = size;
        this.needCount = needCount;
        this.user = user;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    private String publisher;
    private String isbn;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    private int start;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int size;
    private int needCount;

    public int getNeedCount() {
        return needCount;
    }

    public void setNeedCount(int needCount) {
        this.needCount = needCount;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
