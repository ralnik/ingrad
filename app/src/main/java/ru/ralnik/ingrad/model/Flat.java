package ru.ralnik.ingrad.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "flats")
public class Flat {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "_id")
    private Long id;

    @ColumnInfo(name = "ArticleId")
    private String  ArticleId;              //ID квартиры / ID машино - места

    @ColumnInfo(name = "ArticleSubType")
    private String  ArticleSubType;         //Тип объекта недвижимости

    @ColumnInfo(name = "LayoutUrl")
    private String  LayoutUrl;              //Планировка квартиры (ссылка)

    @ColumnInfo(name = "BeforeBtiNumber")
    private int BeforeBtiNumber;        //№ квартиры / № машино - места

    @ColumnInfo(name = "Category")
    private String Category;

    @ColumnInfo(name = "AddressId")
    private String  AddressId;              //Идентификатор корпуса: AddressId 

    @ColumnInfo(name = "AddressName")
    private String  AddressName;            //Наименование корпуса: AddressName

    @ColumnInfo(name = "SectionNumber")
    private String  SectionNumber;          //№ секции

    @ColumnInfo(name = "Floor")
    private int Floor;                  //Этаж

    @ColumnInfo(name = "Rooms")
    private int Rooms;                  //Количество комнат

    @ColumnInfo(name = "Quantity")
    private Float   Quantity;               //Общая площадь / Площадь машиноместа

    @ColumnInfo(name = "DiscountMax")
    private Float   DiscountMax;            //Общая стоимость (с учетом максимальной скидки)

    @ColumnInfo(name = "FinishTypeId")
    private String  FinishTypeId;           //Отделка

    @ColumnInfo(name = "StatusCodeName")
    private String  StatusCodeName;         //Статус квартиры (уточнить у РОП)

    @ColumnInfo(name = "TownHouse")
    private String  TownHouse;              //TownHouse

    @ColumnInfo(name = "PentHouse")
    private String  PentHouse;              //PentHouse

    @ColumnInfo(name = "TwoLevel")
    private String  TwoLevel;               //TwoLevel

    @ColumnInfo(name = "SeparateEntrance")
    private String  SeparateEntrance;       //SeparateEntrance ; TwoEnter

    @ColumnInfo(name = "WithWindow")
    private String  WithWindow;             //WithWingow

    @ColumnInfo(name = "FirePlace")
    private String  FirePlace;              //С камином

    @ColumnInfo(name = "Terrace")
    private String  Terrace;                //Terrace;

    @ColumnInfo(name = "CountBalcony")
    private int CountBalcony;           //CountBalcony

    @ColumnInfo(name = "CountLoggia")
    private int CountLoggia;            //CountLoggia

    @ColumnInfo(name = "CountTerrace")
    private int CountTerrace;           //CountTerrace

    public Flat(){
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public String getArticleId() {
        return ArticleId;
    }

    public void setArticleId(String articleId) {
        ArticleId = articleId;
    }

    public String getArticleSubType() {
        return ArticleSubType;
    }

    public void setArticleSubType(String articleSubType) {
        ArticleSubType = articleSubType;
    }

    public String getLayoutUrl() {
        return LayoutUrl;
    }

    public void setLayoutUrl(String layoutUrl) {
        LayoutUrl = layoutUrl;
    }

    public Integer getBeforeBtiNumber() {
        return BeforeBtiNumber;
    }

    public void setBeforeBtiNumber(Integer beforeBtiNumber) {
        BeforeBtiNumber = beforeBtiNumber;
    }

    public String getAddressId() {
        return AddressId;
    }

    public void setAddressId(String addressId) {
        AddressId = addressId;
    }

    public String getAddressName() {
        return AddressName;
    }

    public void setAddressName(String addressName) {
        AddressName = addressName;
    }

    public String getSectionNumber() {
        return SectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        SectionNumber = sectionNumber;
    }

    public Integer getFloor() {
        return Floor;
    }

    public void setFloor(Integer floor) {
        Floor = floor;
    }

    public Integer getRooms() {
        return Rooms;
    }

    public void setRooms(Integer rooms) {
        Rooms = rooms;
    }

    public Float getQuantity() {
        return Quantity;
    }

    public void setQuantity(Float quantity) {
        Quantity = quantity;
    }

    public Float getDiscountMax() {
        return DiscountMax;
    }

    public void setDiscountMax(Float discountMax) {
        DiscountMax = discountMax;
    }

    public String getFinishTypeId() {
        return FinishTypeId;
    }

    public void setFinishTypeId(String finishTypeId) {
        FinishTypeId = finishTypeId;
    }

    public String getStatusCodeName() {
        return StatusCodeName;
    }

    public void setStatusCodeName(String statusCodeName) {
        StatusCodeName = statusCodeName;
    }

    public String getTownHouse() {
        return TownHouse;
    }

    public void setTownHouse(String townHouse) {
        TownHouse = townHouse;
    }

    public String getPentHouse() {
        return PentHouse;
    }

    public void setPentHouse(String pentHouse) {
        PentHouse = pentHouse;
    }

    public String getTwoLevel() {
        return TwoLevel;
    }

    public void setTwoLevel(String twoLevel) {
        TwoLevel = twoLevel;
    }

    public String getSeparateEntrance() {
        return SeparateEntrance;
    }

    public void setSeparateEntrance(String separateEntrance) {
        SeparateEntrance = separateEntrance;
    }

    public String getWithWindow() {
        return WithWindow;
    }

    public void setWithWindow(String withWindow) {
        WithWindow = withWindow;
    }

    public String getFirePlace() {
        return FirePlace;
    }

    public void setFirePlace(String firePlace) {
        FirePlace = firePlace;
    }

    public String getTerrace() {
        return Terrace;
    }

    public void setTerrace(String terrace) {
        Terrace = terrace;
    }

    public Integer getCountBalcony() {
        return CountBalcony;
    }

    public void setCountBalcony(Integer countBalcony) {
        CountBalcony = countBalcony;
    }

    public Integer getCountLoggia() {
        return CountLoggia;
    }

    public void setCountLoggia(Integer countLoggia) {
        CountLoggia = countLoggia;
    }

    public Integer getCountTerrace() {
        return CountTerrace;
    }

    public void setCountTerrace(Integer countTerrace) {
        CountTerrace = countTerrace;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    @Override
    public String toString() {
//        return  "ArticleId='" + ArticleId + "&" +
//                "ArticleSubType='" + ArticleSubType + "&" +
//                "LayoutUrl='" + LayoutUrl + "&" +
//                "BeforeBtiNumber=" + BeforeBtiNumber +
//                "AddressId='" + AddressId + "&" +
//                "AddressName='" + AddressName + "&" +
//                "SectionNumber='" + SectionNumber + "&" +
//                "Floor=" + Floor +
//                "Rooms=" + Rooms +
//                "Quantity=" + Quantity +
//                "DiscountMax=" + DiscountMax +
//                "FinishTypeId='" + FinishTypeId + "&" +
//                "StatusCodeName='" + StatusCodeName + "&" +
//                "TownHouse='" + TownHouse + "&" +
//                "PentHouse='" + PentHouse + "&" +
//                "TwoLevel='" + TwoLevel + "&" +
//                "SeparateEntrance='" + SeparateEntrance + "&" +
//                "WithWindow='" + WithWindow + "&" +
//                "FirePlace='" + FirePlace + "&" +
//                "Terrace='" + Terrace + "&" +
//                "CountBalcony=" + CountBalcony + "&" +
//                "CountLoggia=" + CountLoggia + "&" +
//                "CountTerrace=" + CountTerrace;
                return
                "LayoutUrl=" + LayoutUrl + "&" +
                "AddressId=" + AddressId + "&" +
                "SectionNumber=" + SectionNumber + "&" +
                "Floor=" + Floor + "&" +
                "Rooms=" + Rooms + "&" +
                "Quantity=" + Quantity + "&" +
                "DiscountMax=" + DiscountMax + "&" +
                "FinishTypeId=" + FinishTypeId + "&" +
                "TownHouse=" + TownHouse + "&" +
                "PentHouse=" + PentHouse + "&" +
                "TwoLevel=" + TwoLevel + "&" +
                "SeparateEntrance=" + SeparateEntrance + "&" +
                "WithWindow=" + WithWindow + "&" +
                "FirePlace=" + FirePlace + "&" +
                "Terrace=" + Terrace + "&" +
                "CountBalcony=" + CountBalcony + "&" +
                "CountLoggia=" + CountLoggia + "&" +
                "CountTerrace=" + CountTerrace;
    }
}
