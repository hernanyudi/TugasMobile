package com.mobileprogamming.catering.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mobileprogamming.catering.database.DatabaseClient;
import com.mobileprogamming.catering.database.DatabaseModel;
import com.mobileprogamming.catering.database.dao.DatabaseDao;

import java.util.List;


public class LoginViewModel extends AndroidViewModel {

    LiveData<List<DatabaseModel>> modelDatabase;
    DatabaseDao databaseDao;

    //untuk inisialisasi databaseDao
    public LoginViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
    }

    //untuk cek login mengambil data dari database
    public LiveData<List<DatabaseModel>> getDataUser(String username, String password) {
        modelDatabase = databaseDao.getUserByName(username, password);
        return modelDatabase;
    }

}