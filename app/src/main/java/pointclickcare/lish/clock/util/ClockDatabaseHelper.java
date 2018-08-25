package pointclickcare.lish.clock.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClockDatabaseHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "CLOCK";
    static final int DB_VERSION = 1;

    static final String TABLE_ZONE = "ZONES";
    static final String ZONE_ID = "_ZONE_ID";
    static final String ZONE_NAME = "ZONE_NAME";
    static final String GMT_OFFSET = "GMT_OFFSET";

    static final String TABLE_ALARM = "ALARMS";
    static final String ALARM_ID = "_ALARM_ID";
    static final String ALARM_TIME = "ALARM_TIME";
    static final String ALARM_DAYS = "ALARM_DAYS";
    static final String ALARM_STATUS = "ALARM_STATUS";

    private static final String SQL_CREATE_ZONES = "CREATE TABLE " + TABLE_ZONE + " (" +
            ZONE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ZONE_NAME + " TEXT," +
            GMT_OFFSET + " INTEGER);";

    private static final String SQL_CREATE_ALARMS = "CREATE TABLE " + TABLE_ALARM + " (" +
            ALARM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ALARM_TIME + " TEXT," +
            ALARM_DAYS + " TEXT," +
            ALARM_STATUS + " INTEGER);";

    ClockDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL(SQL_CREATE_ZONES);
            db.execSQL(SQL_CREATE_ALARMS);
        }
    }

}
