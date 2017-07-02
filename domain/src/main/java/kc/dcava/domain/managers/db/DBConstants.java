package kc.dcava.domain.managers.db;

public class DBConstants {
    public static final String TABLE_MY_ACTIVITIES = "ACTIVITIES";

    // Table field constantsf
    public static final String KEY_MYACTIVITY_ID = "_id";
    public static final String KEY_MYACTIVITY_NAME = "NAME";
    public static final String KEY_MYACTIVITY_IMAGE_URL = "IMAGE_URL";
    public static final String KEY_MYACTIVITY_LOGO_IMAGE_URL = "LOGO_IMAGE_URL";
    public static final String KEY_MYACTIVITY_ADDRESS = "ADDRESS";
    public static final String KEY_MYACTIVITY_URL = "URL";
    public static final String KEY_MYACTIVITY_DESCRIPTION_ES = "DESCRIPTION_ES";
    public static final String KEY_MYACTIVITY_DESCRIPTION_EN = "DESCRIPTION_EN";
    public static final String KEY_MYACTIVITY_LATITUDE = "latitude";
    public static final String KEY_MYACTIVITY_LONGITUDE = "longitude";


    public static final String[] ALL_COLUMNS = {
            KEY_MYACTIVITY_ID,
            KEY_MYACTIVITY_NAME,
            KEY_MYACTIVITY_IMAGE_URL,
            KEY_MYACTIVITY_LOGO_IMAGE_URL,
            KEY_MYACTIVITY_ADDRESS,
            KEY_MYACTIVITY_URL,
            KEY_MYACTIVITY_DESCRIPTION_ES,
            KEY_MYACTIVITY_DESCRIPTION_EN,
            KEY_MYACTIVITY_LATITUDE,
            KEY_MYACTIVITY_LONGITUDE
    };

    public static final String SQL_SCRIPT_CREATE_MYACTIVITY_TABLE =
            "create table " + TABLE_MY_ACTIVITIES
                    + "( "
                    + KEY_MYACTIVITY_ID + " integer primary key autoincrement, "
                    + KEY_MYACTIVITY_NAME + " text not null,"
                    + KEY_MYACTIVITY_IMAGE_URL + " text, "
                    + KEY_MYACTIVITY_LOGO_IMAGE_URL + " text, "
                    + KEY_MYACTIVITY_ADDRESS + " text,"
                    + KEY_MYACTIVITY_URL + " text,"
                    + KEY_MYACTIVITY_LATITUDE + " real,"
                    + KEY_MYACTIVITY_LONGITUDE + " real, "
                    + KEY_MYACTIVITY_DESCRIPTION_ES + " text, "
                    + KEY_MYACTIVITY_DESCRIPTION_EN + " text "
                    + ");";

    public static final String DROP_DATABASE_SCRIPTS = "";
    public static final String UPDATE_DATABASE_SCRIPTS = "";

    public static final String[] CREATE_DATABASE_SCRIPTS = {
            SQL_SCRIPT_CREATE_MYACTIVITY_TABLE
    };
}
