package kc.dcava.domain.managers.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public interface MyActivityDao<T> {

    LinkedList<T> query();
    @Nullable
    LinkedList<T> query(String where, String[] whereArgs, String orderBy);
    int numRecords();


    long insert(@NonNull final T element);
    void deleteAll();

}
