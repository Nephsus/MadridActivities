package kc.dcava.domain.managers.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import kc.dcava.domain.managers.network.parsers.MyActivitiesMapperFromEntities;
import kc.dcava.domain.model.MyActivity;

import static kc.dcava.domain.managers.db.DBConstants.ALL_COLUMNS;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_ADDRESS;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_DESCRIPTION_EN;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_DESCRIPTION_ES;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_ID;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_IMAGE_URL;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_LATITUDE;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_LOGO_IMAGE_URL;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_LONGITUDE;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_NAME;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_URL;
import static kc.dcava.domain.managers.db.DBConstants.TABLE_MY_ACTIVITIES;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public class MyActivityDaoImpl implements  MyActivityDao<MyActivity> {

    private SQLiteDatabase dbReadConnection;
    private SQLiteDatabase dbWriteConnection;

    public MyActivityDaoImpl(Context context) {
        this(new DBHelper(context));
    }

    public MyActivityDaoImpl(DBHelper dbHelper) {
        dbReadConnection = dbHelper.getReadableDatabase();
        dbWriteConnection = dbHelper.getWritableDatabase();
    }


    @Override
    public LinkedList<MyActivity> query() {
        return query(null, null, KEY_MYACTIVITY_ID);
    }

    @Nullable
    @Override
    public LinkedList<MyActivity> query(String where, String[] whereArgs, String orderBy) {
        Cursor c = dbReadConnection.query(TABLE_MY_ACTIVITIES, ALL_COLUMNS,
                                                where,
                                                whereArgs,
                                                orderBy,
                                                null,
                                                null);

        if (c == null || c.getCount() == 0) {
            return null;
        }

        LinkedList<MyActivity> myActivities = new LinkedList<>();

        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(KEY_MYACTIVITY_ID));
            String name = c.getString(c.getColumnIndex(KEY_MYACTIVITY_NAME));
            String address = c.getString(c.getColumnIndex(KEY_MYACTIVITY_ADDRESS));
            String description_es = c.getString(c.getColumnIndex(KEY_MYACTIVITY_DESCRIPTION_ES));
            String description_en = c.getString(c.getColumnIndex(KEY_MYACTIVITY_DESCRIPTION_EN));
            String imageUrl = c.getString(c.getColumnIndex(KEY_MYACTIVITY_IMAGE_URL));
            String logoImageUrl = c.getString(c.getColumnIndex(KEY_MYACTIVITY_LOGO_IMAGE_URL));
            //String url = c.getString(c.getColumnIndex(KEY_MYACTIVITY_URL));
            float latitude = c.getFloat(c.getColumnIndex(KEY_MYACTIVITY_LATITUDE));
            float longitude = c.getFloat(c.getColumnIndex(KEY_MYACTIVITY_LONGITUDE));

            MyActivity myActivity = new MyActivity().setId( id ).setName(name)
                    .setAddress(address)
                    .setDescription_es(description_es)
                    .setDescription_es(description_en)
                    .setFront_image(imageUrl)
                    .setLogo(logoImageUrl)
                    .setLatitude(latitude)
                    .setLongitude(longitude);

            myActivities.add(myActivity);
        }

        return myActivities;
    }

    @Override
    public int numRecords() {
        return query(null, null, KEY_MYACTIVITY_ID).size();
    }

    @Override
    public long insert(@NonNull MyActivity element) {
        dbWriteConnection.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id = dbWriteConnection.insert(TABLE_MY_ACTIVITIES, null, MyActivitiesMapperFromEntities.mapperToDB( element ) );
            element.setId(id);

            dbWriteConnection.setTransactionSuccessful();
        } finally {
            dbWriteConnection.endTransaction();
        }

        return id;
    }


    @Override
    public void deleteAll() {
        int deletedRegs = 0;
        dbWriteConnection.beginTransaction();
        try {
            deletedRegs = dbWriteConnection.delete(TABLE_MY_ACTIVITIES, null, null);

            dbWriteConnection.setTransactionSuccessful();
        } finally {
            dbWriteConnection.endTransaction();
        }

    }
}
