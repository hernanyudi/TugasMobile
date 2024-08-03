package com.mobileprogamming.catering.order;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mobileprogamming.catering.database.DatabaseClient;
import com.mobileprogamming.catering.database.DatabaseModel;
import com.mobileprogamming.catering.database.dao.DatabaseDao;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OrderViewModel extends AndroidViewModel {

    LiveData<List<DatabaseModel>> modelDatabase;
    DatabaseDao databaseDao;

    // Inisialisasi databaseDao
    public OrderViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
    }

    // Mengambil data dari database
    public LiveData<List<DatabaseModel>> getDataIdUser() {
        modelDatabase = databaseDao.getAllOrder();
        return modelDatabase;
    }

    // Menambahkan data ke database
    public void addDataOrder(final String menuName, final int itemCount, final int totalPrice) {
        Completable.fromAction(() -> {
                    DatabaseModel databaseModel = new DatabaseModel();
                    databaseModel.nama_menu = menuName;
                    databaseModel.items = itemCount;
                    databaseModel.harga = totalPrice;
                    databaseDao.insertData(databaseModel);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
