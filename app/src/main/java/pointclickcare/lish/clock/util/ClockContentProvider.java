package pointclickcare.lish.clock.util;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import pointclickcare.lish.clock.util.ClockDatabaseHelper;

public class ClockContentProvider extends ContentProvider {
    private static final String PROVIDER_NAME = "pointclickcare.lish.clock.util.ClockContentProvider";
    private static final int PROVIDER_ZONE = 1;
    private static final int PROVIDER_ZONE_ID = 2;
    private static final int PROVIDER_ALARM = 3;
    private static final int PROVIDER_ALARM_ID = 4;
    private static final Uri PROVIDER_ZONE_URI = Uri.parse("content://" + PROVIDER_NAME + "/zones");
    private static final Uri PROVIDER_ALARM_URI = Uri.parse("content://" + PROVIDER_NAME + "/alarms");
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(PROVIDER_NAME, "zones", PROVIDER_ZONE);
        sUriMatcher.addURI(PROVIDER_NAME, "zones/#", PROVIDER_ZONE_ID);
        sUriMatcher.addURI(PROVIDER_NAME, "alarms", PROVIDER_ALARM);
        sUriMatcher.addURI(PROVIDER_NAME, "alarms/#", PROVIDER_ALARM_ID);
    }

    private ClockDatabaseHelper mDatabaseHelper;

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new ClockDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String id;
        switch (sUriMatcher.match(uri)) {
            case PROVIDER_ZONE:
                queryBuilder.setTables(ClockDatabaseHelper.TABLE_ZONE);
                break;
            case PROVIDER_ZONE_ID:
                id = uri.getPathSegments().get(1);
                selection = ClockDatabaseHelper.ZONE_ID + "=" + id
                        + (TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")");
                queryBuilder.setTables(ClockDatabaseHelper.TABLE_ZONE);
                break;
            case PROVIDER_ALARM:
                queryBuilder.setTables(ClockDatabaseHelper.TABLE_ALARM);
                break;
            case PROVIDER_ALARM_ID:
                id = uri.getPathSegments().get(3);
                selection = ClockDatabaseHelper.ALARM_ID + "=" + id
                        + (TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")");
                queryBuilder.setTables(ClockDatabaseHelper.TABLE_ALARM);
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case PROVIDER_ZONE:
                return "vnd.android.cursor.dir/vnd." + PROVIDER_NAME;
            case PROVIDER_ZONE_ID:
                return "vnd.android.cursor.item/vnd." + PROVIDER_NAME;
            case PROVIDER_ALARM:
                return "vnd.android.cursor.dir/vnd." + PROVIDER_NAME;
            case PROVIDER_ALARM_ID:
                return "vnd.android.cursor.item/vnd." + PROVIDER_NAME;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        Uri _uri = null;
        long id;
        switch (sUriMatcher.match(uri)) {
            case PROVIDER_ZONE:
                id = db.insert(ClockDatabaseHelper.TABLE_ZONE, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                _uri = Uri.parse(PROVIDER_ZONE_URI + "/" + id);
                break;
            case PROVIDER_ALARM:
                id = db.insert(ClockDatabaseHelper.TABLE_ALARM, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                _uri = Uri.parse(PROVIDER_ALARM_URI + "/" + id);
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        return _uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int deleteCount;
        String id;
        switch (sUriMatcher.match(uri)) {
            case PROVIDER_ZONE:
                deleteCount = db.delete(ClockDatabaseHelper.TABLE_ZONE, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            case PROVIDER_ZONE_ID:
                id = uri.getPathSegments().get(1);
                selection = ClockDatabaseHelper.ZONE_ID + "=" + id
                        + (TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")");
                deleteCount = db.delete(ClockDatabaseHelper.TABLE_ZONE, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            case PROVIDER_ALARM:
                deleteCount = db.delete(ClockDatabaseHelper.TABLE_ALARM, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            case PROVIDER_ALARM_ID:
                id = uri.getPathSegments().get(3);
                selection = ClockDatabaseHelper.ALARM_ID + "=" + id
                        + (TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")");
                deleteCount = db.delete(ClockDatabaseHelper.TABLE_ALARM, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        return deleteCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int updateCount;
        String id;
        switch (sUriMatcher.match(uri)) {
            case PROVIDER_ZONE:
                updateCount = db.update(ClockDatabaseHelper.TABLE_ZONE, values, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            case PROVIDER_ZONE_ID:
                id = uri.getPathSegments().get(1);
                selection = ClockDatabaseHelper.ZONE_ID + "=" + id
                        + (TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")");
                updateCount = db.update(ClockDatabaseHelper.TABLE_ZONE, values, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            case PROVIDER_ALARM:
                updateCount = db.update(ClockDatabaseHelper.TABLE_ALARM, values, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            case PROVIDER_ALARM_ID:
                id = uri.getPathSegments().get(3);
                selection = ClockDatabaseHelper.ALARM_ID + "=" + id
                        + (TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")");
                updateCount = db.update(ClockDatabaseHelper.TABLE_ALARM, values, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        return updateCount;
    }
}
