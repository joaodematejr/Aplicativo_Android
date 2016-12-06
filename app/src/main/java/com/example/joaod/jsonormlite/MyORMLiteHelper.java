package com.example.joaod.jsonormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by joaod on 04/11/2016.
 */

public class MyORMLiteHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "jsonOrmLite_db";
    private static final int DATABASE_VERSION = 3;
    private static MyORMLiteHelper mInstance = null;
    private Dao<Endereco, Integer> enderecoDao = null;
    private Dao<Moeda, Integer> moedaDao = null;

    public MyORMLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static MyORMLiteHelper getmInstance(Context context) {
        if (mInstance == null)
            mInstance = new MyORMLiteHelper(context);
        return mInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Endereco.class);
            TableUtils.createTable(connectionSource, Moeda.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Endereco.class, true);
            TableUtils.dropTable(connectionSource, Moeda.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Endereco, Integer> getEnderecoDao() throws SQLException {
        if (enderecoDao == null)
            enderecoDao = getDao(Endereco.class);
        return enderecoDao;
    }

    public Dao<Moeda, Integer> getMoedaDao() throws SQLException {
        if (moedaDao == null)
            moedaDao = getDao(Moeda.class);
        return moedaDao;
    }

}
