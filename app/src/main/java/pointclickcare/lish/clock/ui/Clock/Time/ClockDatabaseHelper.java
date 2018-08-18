package pointclickcare.lish.clock.ui.Clock.Time;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClockDatabaseHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "CLOCK";
    static final int DB_VERSION = 1;
    static final String TABLE_ZONE = "ZONES";
    static final String ZONE_ID = "_ID";
    static final String ZONE_NAME = "ZONE_NAME";
    static final String GMT_OFFSET = "GMT_OFFSET";

    private static final String SQL_CREATE_ZONES = "CREATE TABLE " + TABLE_ZONE + " (" +
            ZONE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ZONE_NAME + " TEXT," +
            GMT_OFFSET + " INTEGER);";

    ClockDatabaseHelper (Context context) {
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

            insertTime(db, "Asia/Tokyo", 32400);
            insertTime(db, "Pacific/Chatham", 45900);
            insertTime(db, "America/New_York", -14400);
            }
    }

    private static void insertTime(SQLiteDatabase db, String zoneName, long gmtOffset)
    {
        ContentValues zoneValues = new ContentValues();
        zoneValues.put("ZONE_NAME", zoneName);
        zoneValues.put("GMT_OFFSET", gmtOffset);
        db.insert(TABLE_ZONE, null, zoneValues);
    }

}
