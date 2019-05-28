package com.mitrais.rms.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class provides MySQL datasource to be used to connect to database.
 * It implements singleton pattern See <a href="http://www.oodesign.com/singleton-pattern.html">Singleton Pattern</a>
 */
public class DataSourceFactory
{
    private final MysqlDataSource dataSource;

    DataSourceFactory()
    {
        MysqlDataSource dataSource = new MysqlDataSource();

        // TODO: make these database setting configurable by moving to properties file
        try {
            InputStream reader=DataSourceFactory.class.getClassLoader().getResourceAsStream("database.properties");
            Properties p=new Properties();
            p.load(reader);

            dataSource.setDatabaseName(p.getProperty("db.name"));
            dataSource.setServerName(p.getProperty("db.servername"));
            dataSource.setPort(Integer.parseInt(p.getProperty("db.port")));
            dataSource.setUser(p.getProperty("db.user"));
            dataSource.setPassword(p.getProperty("db.password"));
        }catch (Exception e){
            e.printStackTrace();
        }

        this.dataSource = dataSource;
    }

    /**
     * Get a data source to database
     *
     * @return DataSource object
     */
    public static Connection getConnection() throws SQLException
    {
        return (Connection) SingletonHelper.INSTANCE.dataSource.getConnection();
    }

    private static class SingletonHelper
    {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }
}
