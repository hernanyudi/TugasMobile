package com.mobileprogamming.catering.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mobileprogamming.catering.database.dao.DatabaseDao;

//untuk entitas model database
@Database(entities = {DatabaseModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatabaseDao databaseDao();
}
