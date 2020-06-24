package ru.ralnik.ingrad.sqlitedb;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import androidx.sqlite.db.SimpleSQLiteQuery;

import java.util.List;
import lombok.SneakyThrows;
import ru.ralnik.ingrad.model.Flat;

public class FlatRepository {
    private FlatDao mFlatDao;
    public FlatRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        mFlatDao = db.flatDao();
    }

    @SneakyThrows
    public Cursor getFlatsByQuery(String query){
        return new getFlatsByQueryAsyncTask(mFlatDao).execute(query).get();
    }

    public void insert(Flat flat){
        new insertAsyncTask(mFlatDao).execute(flat);
    }

    public void update(Flat flat){
        new updateAsyncTask(mFlatDao).execute(flat);
    }

    @SneakyThrows
    public List<Flat> findByIds(String[] strings){
        return new findByIdsAsyncTask(mFlatDao).execute(strings).get();
    }
    
    @SneakyThrows
    public Flat findById(String ArticleId){
        return new findByIdAsyncTask(mFlatDao).execute(ArticleId).get();
    }

    @SneakyThrows
    public List<Flat> getAll(){
        return new getAllAsyncTask(mFlatDao).execute().get();
    }

    @SneakyThrows
    public Cursor getFlats(){
        return new getFlatsAsyncTask(mFlatDao).execute().get();
    }

    public void delete(Flat flat){
        new deleteAsyncTask(mFlatDao).execute(flat);
    }

    public void deleteAll(){
        new deleteAllAsyncTask(mFlatDao).execute();
    }

    @SneakyThrows
    public Object getMin(String param){
        return new getMinAsyncTask(mFlatDao).execute(param).get();
    }

    @SneakyThrows
    public Object getMax(String param){
        return new getMaxAsyncTask(mFlatDao).execute(param).get();
    }

    //************--------============ PRIVATE CLASSES ==========-------------------*****************//
    private class getFlatsByQueryAsyncTask extends AsyncTask<String,Void, Cursor>{
        private FlatDao mAsyncFlatDao;
        public getFlatsByQueryAsyncTask(FlatDao mAsyncFlatDao) {
            this.mAsyncFlatDao = mAsyncFlatDao;
        }

        @Override
        protected Cursor doInBackground(String... param) {
            SimpleSQLiteQuery query = new SimpleSQLiteQuery(param[0]);
            return mAsyncFlatDao.getFlatsByQuery(query);
        }
    }


    private class getMinAsyncTask extends AsyncTask<String,Void,Object>{
        private FlatDao mAsyncFlatDao;

        public getMinAsyncTask(FlatDao mAsyncFlatDao) {
            this.mAsyncFlatDao = mAsyncFlatDao;
        }
        @Override
        protected Object doInBackground(String... param) {
            if(param[0].equals("Floor")){
                return mAsyncFlatDao.getMinFloor();
            }
            if(param[0].equals("Cost")){
                return mAsyncFlatDao.getMinCost();
            }
            if(param[0].equals("Square")){
                return mAsyncFlatDao.getMinSquare();
            }
            if(param[0].equals("Budget")){
                return mAsyncFlatDao.getMinBudget();
            }
            return null;
        }
    }

    private class getMaxAsyncTask extends AsyncTask<String,Void,Object>{
        private FlatDao mAsyncFlatDao;

        public getMaxAsyncTask(FlatDao mAsyncFlatDao) {
            this.mAsyncFlatDao = mAsyncFlatDao;
        }
        @Override
        protected Object doInBackground(String... param) {
            if(param[0].equals("Floor")){
                return mAsyncFlatDao.getMaxFloor();
            }
            if(param[0].equals("Cost")){
                return mAsyncFlatDao.getMaxCost();
            }
            if(param[0].equals("Square")){
                return mAsyncFlatDao.getMaxSquare();
            }
            if(param[0].equals("Budget")){
                return mAsyncFlatDao.getMaxBudget();
            }
            return null;
        }
    }

    private class getFlatsAsyncTask extends AsyncTask<Void,Void,Cursor>{
        private FlatDao mAsyncFlatDao;

        public getFlatsAsyncTask(FlatDao mAsyncFlatDao) {
            this.mAsyncFlatDao = mAsyncFlatDao;
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mAsyncFlatDao.getFlats();
        }
    }

    private class insertAsyncTask extends AsyncTask<Flat, Void, Void>{
        private FlatDao mAsyncFlatDao;

        public insertAsyncTask(FlatDao mAsyncFlatDao) {
            this.mAsyncFlatDao = mAsyncFlatDao;
        }

        @Override
        protected Void doInBackground(final Flat... param) {
            mAsyncFlatDao.insertAll(param[0]);
            return null;
        }
    }

    private class updateAsyncTask extends AsyncTask<Flat, Void, Void>{
        private FlatDao mAsyncFlatDao;

        public updateAsyncTask(FlatDao mAsyncFlatDao) {
            this.mAsyncFlatDao = mAsyncFlatDao;
        }

        @Override
        protected Void doInBackground(final Flat... param) {
            Flat flat = param[0];
            mAsyncFlatDao.update(
                    flat.getArticleId(),
                    flat.getArticleSubType(),
                    flat.getLayoutUrl(),
                    flat.getBeforeBtiNumber(),
                    flat.getCategory(),
                    flat.getAddressId(),
                    flat.getAddressName(),
                    flat.getAddressNumber(),
                    flat.getSectionNumber(),
                    flat.getFloor(),
                    flat.getRooms(),
                    flat.getQuantity(),
                    flat.getDiscountMax(),
                    flat.getFinishTypeId(),
                    flat.getStatusCodeName(),
                    flat.getTownHouse(),
                    flat.getPentHouse(),
                    flat.getTwoLevel(),
                    flat.getSeparateEntrance(),
                    flat.getWithWindow(),
                    flat.getFirePlace(),
                    flat.getTerrace(),
                    flat.getCountBalcony(),
                    flat.getCountLoggia(),
                    flat.getCountTerrace(),
                    flat.getDeliveryPeriod()
            );
            return null;
        }
    }

    private class findByIdsAsyncTask extends AsyncTask<String[], Void, List<Flat>> {
        private FlatDao mAsyncFlatDao;

        public findByIdsAsyncTask(FlatDao mAsyncFlatDao) {
            this.mAsyncFlatDao = mAsyncFlatDao;
        }

        @Override
        protected List<Flat> doInBackground(String[]... param) {
            return mAsyncFlatDao.FindByIds(param[0]);
        }
    }

    private class findByIdAsyncTask extends AsyncTask<String, Void, Flat> {
        private FlatDao mAsyncFlatDao;

        public findByIdAsyncTask(FlatDao mAsyncFlatDao) {
            this.mAsyncFlatDao = mAsyncFlatDao;
        }

        @Override
        protected Flat doInBackground(String... param) {
            return mAsyncFlatDao.findById(param[0]);
        }
    }

    private class getAllAsyncTask extends AsyncTask<Void, Void, List<Flat>>{
        private FlatDao mAsyncFlatDao;

        public getAllAsyncTask(FlatDao mAsyncFlatDao) {
            this.mAsyncFlatDao = mAsyncFlatDao;
        }

        @Override
        protected List<Flat> doInBackground(Void... voids) {
            return mAsyncFlatDao.getAll();
        }
    }

    private class deleteAsyncTask extends AsyncTask<Flat, Void, Void>{
        private FlatDao mAsyncFlatDao;

        public deleteAsyncTask(FlatDao mAsyncFlatDao) {
            this.mAsyncFlatDao = mAsyncFlatDao;
        }

        @Override
        protected Void doInBackground(Flat... param) {
            this.mAsyncFlatDao.delete(param[0]);
            return null;
        }
    }

    private class deleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private FlatDao mAsyncFlatDao;

        public deleteAllAsyncTask(FlatDao mAsyncFlatDao) {
            this.mAsyncFlatDao = mAsyncFlatDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            this.mAsyncFlatDao.deleteAll();
            return null;
        }
    }
}
