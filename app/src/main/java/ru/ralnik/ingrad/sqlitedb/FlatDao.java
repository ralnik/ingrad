package ru.ralnik.ingrad.sqlitedb;


import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

import ru.ralnik.ingrad.model.Flat;

@Dao
public interface FlatDao {
    @RawQuery
    Cursor getFlatsByQuery(SupportSQLiteQuery query);

    @RawQuery
    Cursor countTypeFlats(SupportSQLiteQuery query);

    @Query("SELECT * FROM flats")
    List<Flat> getAll();

    @Query("SELECT * FROM flats")
    Cursor getFlats();

    @Query("SELECT * FROM flats WHERE ArticleId IN (:ArticleId)")
    List<Flat> FindByIds(String[] ArticleId);

    @Query("SELECT * FROM flats WHERE ArticleId = :ArticleId")
    Flat findById(String ArticleId);

    //   В режиме REPLACE старая запись будет заменена новой. Этот режим хорошо подходит, если вам надо вставить запись, если ее еще нет в таблице или обновить запись, если она уже есть.
//   Также есть режим IGNORE. В этом режиме будет оставлена старая запись и операция вставки не будет выполнена.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Flat... flats);

    @Delete
    void delete(Flat flat);

    @Query("DELETE FROM flats")
    void deleteAll();

    //**************Floor
    @Query("SELECT min(Floor) FROM flats where (discountmax/Quantity) > 1")
    int getMinFloor();

    @Query("SELECT max(Floor) FROM flats where (discountmax/Quantity) > 1")
    int getMaxFloor();

    //**************SQUARE
    @Query("SELECT min(Quantity) FROM flats where (discountmax/Quantity) > 1")
    Float getMinSquare();

    @Query("SELECT max(Quantity) FROM flats where (discountmax/Quantity) > 1")
    Float getMaxSquare();

    //**************COST
    @Query("SELECT min(DiscountMax/Quantity) FROM flats where (discountmax/Quantity) > 1")
    Float getMinCost();

    @Query("SELECT max(DiscountMax/Quantity) FROM flats where (discountmax/Quantity) > 1")
    Float getMaxCost();

    //**************Budget
    @Query("SELECT min(DiscountMax) FROM flats where (discountmax/Quantity) > 1")
    Float getMinBudget();

    @Query("SELECT max(DiscountMax) FROM flats where (discountmax/Quantity) > 1")
    Float getMaxBudget();

    @Query("UPDATE flats set ArticleSubType = :ArticleSubType, LayoutUrl = :LayoutUrl, BeforeBtiNumber = :BeforeBtiNumber, Category = :Category, AddressId = :AddressId, AddressName = :AddressName, AddressNumber = :AddressNumber, SectionNumber = :SectionNumber, Floor = :Floor, Rooms = :Rooms, Quantity = :Quantity, DiscountMax = :DiscountMax,FinishTypeId = :FinishTypeId, StatusCodeName = :StatusCodeName, TownHouse = :TownHouse, PentHouse = :PentHouse, TwoLevel = :TwoLevel, SeparateEntrance = :SeparateEntrance, WithWindow = :WithWindow, FirePlace = :FirePlace, Terrace = :Terrace, CountBalcony = :CountBalcony, CountLoggia = :CountLoggia, CountTerrace = :CountTerrace, DeliveryPeriod = :DeliveryPeriod where ArticleId = :ArticleId")
    void update(String ArticleId, String ArticleSubType, String LayoutUrl, int BeforeBtiNumber, String Category, String AddressId, String AddressName, int AddressNumber, String SectionNumber, int Floor, int Rooms, Float Quantity, Float DiscountMax, String FinishTypeId, String StatusCodeName, String TownHouse, String PentHouse, String TwoLevel, String SeparateEntrance, String WithWindow, String FirePlace, String Terrace, int CountBalcony, int CountLoggia, int CountTerrace, String DeliveryPeriod);
}
