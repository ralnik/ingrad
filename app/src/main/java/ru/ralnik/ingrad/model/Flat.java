package ru.ralnik.ingrad.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(tableName = "flats")
@Data
@NoArgsConstructor
public class Flat {
    public static final String RIVERSKY = "Riversky";
    public static final String FORIVER = "Foriver";
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "_id")
    @Getter
    private Long id;

    @ColumnInfo(name = "ArticleId")
    private String  ArticleId;              //ID квартиры / ID машино - места

    @ColumnInfo(name = "ArticleSubType")
    private String  ArticleSubType;         //Тип объекта недвижимости

    @ColumnInfo(name = "LayoutUrl")
    private String  LayoutUrl;              //Планировка квартиры (ссылка)

    @ColumnInfo(name = "BeforeBtiNumber")
    private int BeforeBtiNumber;        //№ квартиры / № машино - места

    @ColumnInfo(name = "BuildingGroup")
    private String buildingGroup;

    @ColumnInfo(name = "Category")
    private String Category;

    @ColumnInfo(name = "AddressId")
    private String  AddressId;              //Идентификатор корпуса: AddressId 

    @ColumnInfo(name = "AddressName")
    private String  AddressName;            //Наименование корпуса: AddressName

    @ColumnInfo(name = "AddressNumber")     //№ корпуса
    private int AddressNumber;

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

    @ColumnInfo(name = "DeliveryPeriod")
    private String DeliveryPeriod;      //Период ввода в эксплуатацию

    public void setBuildingGroup(String buildingGroup) {
        if (buildingGroup.toUpperCase().contains("FORIVER")) {
            this.buildingGroup = FORIVER;
        } else if (buildingGroup.toUpperCase().contains("RIVERSKY")) {
            this.buildingGroup = RIVERSKY;
        }
    }

    @Override
    public String toString() {
                return
                "LayoutUrl=" + LayoutUrl + "&" +
                "ArticleId=" + ArticleId + "&" +
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
                "CountTerrace=" + CountTerrace + "&" +
                "BeforeBtiNumber=" + BeforeBtiNumber + "&" +
                "AddressNumber=" + AddressNumber + "&" +
                "DeliveryPeriod=" + DeliveryPeriod + "&" +
                "buildingGroup=" + buildingGroup;
    }
}
