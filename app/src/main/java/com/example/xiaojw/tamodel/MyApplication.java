package com.example.xiaojw.tamodel;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.common.baseframe.util.LogUtils;
import com.example.xiaojw.tamodel.greenDb.DaoMaster;
import com.example.xiaojw.tamodel.greenDb.DaoSession;
import com.orhanobut.logger.Logger;

public class MyApplication extends Application {
    private DaoSession mDaoSession;

    private static MyApplication instance;
    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.logInit(true);
        instance=this;
        initDb();
    }

    private void initDb() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "model-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }
}
