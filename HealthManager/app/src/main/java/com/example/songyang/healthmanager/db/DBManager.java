package com.example.songyang.healthmanager.db;

import android.support.annotation.WorkerThread;

import com.example.songyang.healthmanager.HealthManagerApplication;
import com.example.songyang.healthmanager.bean.ClinicBean;
import com.example.songyang.healthmanager.bean.ClinicBeanStorIOSQLiteDeleteResolver;
import com.example.songyang.healthmanager.bean.ClinicBeanStorIOSQLiteGetResolver;
import com.example.songyang.healthmanager.bean.ClinicBeanStorIOSQLitePutResolver;
import com.example.songyang.healthmanager.bean.ExaminationBean;
import com.example.songyang.healthmanager.bean.ExaminationBeanStorIOSQLiteDeleteResolver;
import com.example.songyang.healthmanager.bean.ExaminationBeanStorIOSQLiteGetResolver;
import com.example.songyang.healthmanager.bean.ExaminationBeanStorIOSQLitePutResolver;
import com.example.songyang.healthmanager.bean.HospitalBean;
import com.example.songyang.healthmanager.bean.HospitalBeanStorIOSQLiteDeleteResolver;
import com.example.songyang.healthmanager.bean.HospitalBeanStorIOSQLiteGetResolver;
import com.example.songyang.healthmanager.bean.HospitalBeanStorIOSQLitePutResolver;
import com.example.songyang.healthmanager.bean.UserBean;
import com.example.songyang.healthmanager.bean.UserBeanStorIOSQLiteDeleteResolver;
import com.example.songyang.healthmanager.bean.UserBeanStorIOSQLiteGetResolver;
import com.example.songyang.healthmanager.bean.UserBeanStorIOSQLitePutResolver;
import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;

import java.io.IOException;
import java.util.List;

/**
 * Created by SongYang on 2016/4/11.
 */
public class DBManager {
    private static DBManager instance;
    private StorIOSQLite healthStorIOSQLite;

    private DBManager() {
        healthStorIOSQLite = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(new HealthSQLiteOpenHelper(HealthManagerApplication.getContext().getApplicationContext(), "health", 1))
                .addTypeMapping(UserBean.class, SQLiteTypeMapping.<UserBean>builder()
                        .putResolver(new UserBeanStorIOSQLitePutResolver())
                        .getResolver(new UserBeanStorIOSQLiteGetResolver())
                        .deleteResolver(new UserBeanStorIOSQLiteDeleteResolver())
                        .build())
                .addTypeMapping(ClinicBean.class, SQLiteTypeMapping.<ClinicBean>builder()
                        .putResolver(new ClinicBeanStorIOSQLitePutResolver())
                        .getResolver(new ClinicBeanStorIOSQLiteGetResolver())
                        .deleteResolver(new ClinicBeanStorIOSQLiteDeleteResolver())
                        .build())
                .addTypeMapping(HospitalBean.class, SQLiteTypeMapping.<HospitalBean>builder()
                        .putResolver(new HospitalBeanStorIOSQLitePutResolver())
                        .getResolver(new HospitalBeanStorIOSQLiteGetResolver())
                        .deleteResolver(new HospitalBeanStorIOSQLiteDeleteResolver())
                        .build())
                .addTypeMapping(ExaminationBean.class, SQLiteTypeMapping.<ExaminationBean>builder()
                        .putResolver(new ExaminationBeanStorIOSQLitePutResolver())
                        .getResolver(new ExaminationBeanStorIOSQLiteGetResolver())
                        .deleteResolver(new ExaminationBeanStorIOSQLiteDeleteResolver())
                        .build())
                .build();
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public void close() throws IOException {
        healthStorIOSQLite.close();
    }

    @WorkerThread
    public UserBean getUser(int id) {
        return healthStorIOSQLite
                .get()
                .object(UserBean.class)
                .withQuery(Query.builder()
                        .table("user")
                        .where("id = " + id)
                        .build())
                .withGetResolver(new UserBeanStorIOSQLiteGetResolver())
                .prepare()
                .executeAsBlocking();
    }

    @WorkerThread
    public List<UserBean> getUsers() {
        return healthStorIOSQLite
                .get()
                .listOfObjects(UserBean.class)
                .withQuery(Query.builder()
                        .table("user")
                        .build())
                .withGetResolver(new UserBeanStorIOSQLiteGetResolver())
                .prepare()
                .executeAsBlocking();
    }

    @WorkerThread
    public void putUsers(List<UserBean> userBeans) {
        healthStorIOSQLite
                .put()
                .objects(userBeans)
                .withPutResolver(new UserBeanStorIOSQLitePutResolver())
                .prepare()
                .executeAsBlocking();
    }

    @WorkerThread
    public void putUser(UserBean userBean) {
        healthStorIOSQLite
                .put()
                .object(userBean)
                .withPutResolver(new UserBeanStorIOSQLitePutResolver())
                .prepare()
                .executeAsBlocking();
    }
}
