package com.gy.just.VoltageMonitor.Model.Dao;

import com.gy.just.VoltageMonitor.Model.Bean.DB.NotifyDB;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

/**
 * Created by gy on 2016/5/4.
 */
public class NotifyDao {
    static DbManager.DaoConfig daoConfig;
    public static DbManager.DaoConfig getDaoConfig(){
        if(daoConfig==null){
            daoConfig=new DbManager.DaoConfig()
                    .setDbName("shiyan.db")
                    .setDbVersion(1)
                    .setAllowTransaction(true)
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                        }
                    });
        }
        return daoConfig;
    }
    public static void save(List<NotifyDB> list){
        DbManager dbManager = x.getDb(getDaoConfig());
        for (NotifyDB db:list) {
            try {
                dbManager.save(db);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }
    public static List<NotifyDB> getAll(){
        DbManager dbManager = x.getDb(getDaoConfig());
        List<NotifyDB> list = null;
        try {
            list = dbManager.findAll(NotifyDB.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void del(int id){
        DbManager dbManager = x.getDb(getDaoConfig());
        try {
            dbManager.deleteById(NotifyDB.class,id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void delAll(){
        DbManager dbManager = x.getDb(getDaoConfig());
        try {
            dbManager.delete(NotifyDB.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
