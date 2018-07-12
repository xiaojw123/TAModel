package com.example.xiaojw.tamodel.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.common.baseframe.util.APPLOG;
import com.example.xiaojw.tamodel.APPLog;
import com.example.xiaojw.tamodel.MyApplication;
import com.example.xiaojw.tamodel.R;
import com.example.xiaojw.tamodel.bean.Person;
import com.example.xiaojw.tamodel.greenDb.PersonDao;
import java.util.List;

/**
 *GreenDao框架测试
 */
public class GreenDaoActiviy extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        Person person = new Person(1001, "18", "cr");
        PersonDao personDao = MyApplication.getInstance().getmDaoSession().getPersonDao();
        List<Person> items= personDao.loadAll();
        APPLog.printDebug("items size__"+items.size());
        for (Person p:items){
            APPLog.printDebug("name="+p.getName()+", id="+p.getId());
        }
//        personDao.insert(person);
    }
}
