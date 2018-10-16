package ru.ralnik.ingrad.sqlitedb;

import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;
import android.database.Cursor;

import java.util.List;

import ru.ralnik.ingrad.model.Flat;

@Dao
public interface FlatDao {
    @RawQuery
   Cursor getFlatsByQuery(SupportSQLiteQuery query);

    @Query("SELECT * FROM flats")
    List<Flat> getAll();

    @Query("SELECT * FROM flats")
    Cursor getFlats();

    @Query("SELECT * FROM flats WHERE ArticleId IN (:ArticleId)")
    List<Flat> FindByIds(String[] ArticleId);

    @Query("SELECT * FROM flats WHERE ArticleId = :ArticleId")
    Flat findById(String ArticleId);

    @Insert
    void insertAll(Flat... flats);

    @Delete
    void delete(Flat flat);

    @Query("DELETE FROM flats")
    void deleteAll();

    //**************Floor
    @Query("SELECT min(Floor) FROM flats")
    int getMinFloor();

    @Query("SELECT max(Floor) FROM flats")
    int getMaxFloor();

    //**************SQUARE
    @Query("SELECT min(Quantity) FROM flats")
    Float getMinSquare();

    @Query("SELECT max(Quantity) FROM flats")
    Float getMaxSquare();

    //**************COST
    @Query("SELECT min(DiscountMax) FROM flats")
    Float getMinCost();

    @Query("SELECT max(DiscountMax) FROM flats")
    Float getMaxCost();
}
