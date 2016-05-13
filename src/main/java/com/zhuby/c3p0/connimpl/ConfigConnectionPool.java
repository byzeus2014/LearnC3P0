package com.zhuby.c3p0.connimpl;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 1.C3P0的学习程序
 * 2.使用了单态模式，注释中有单态模式的使用注意事项。
 * Created by zhuby on 2016/5/4.
 */
public class ConfigConnectionPool {
    private ComboPooledDataSource ds = null;
    private static ConfigConnectionPool connectionPool= null;
    private String propertyFileName = "myc3p0";

    //单态模式，一般是private的构造方法
    //使用了 “懒实例化”的方式，第一次使用的时候，会实例化。
    private ConfigConnectionPool(){
        ds = new ComboPooledDataSource( propertyFileName );
    }

    // synchronized 关键字必须得增加，否则在多线程并发的情况下，会实例化出多个实例
    public static synchronized ConfigConnectionPool getInstance(){
        if(connectionPool == null){
            return (new ConfigConnectionPool());
        }else{
            return connectionPool;
        }
    }

    public Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printConnectionDetail( ){
        System.out.println("URL: "+ds.getJdbcUrl());
        System.out.println("maxPoolSize: "+ds.getMaxPoolSize());
        System.out.println("maxIdelTime: "+ds.getMaxIdleTime());
    }

    public static void  main(String[] args){
        ConfigConnectionPool pool = ConfigConnectionPool.getInstance();
        pool.printConnectionDetail();
    }

}
