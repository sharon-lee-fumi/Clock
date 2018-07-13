package pointclickcare.lish.clock.ui.Clock.Time;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClockDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "clock";
    private static final int DB_VERSION = 1;
    private    static final String TABLE_ZONE = "zones";

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

    private static void insertTime(SQLiteDatabase db, String zoneName)
    {
        ContentValues zoneValues = new ContentValues();
        zoneValues.put("ZONE_NAME", zoneName);
        db.insert(TABLE_ZONE, null, zoneValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE TABLE_ZONE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ZONE_NAME TEXT);");
            //insertZone(db, "America/Chicago");
            }
    }
}
