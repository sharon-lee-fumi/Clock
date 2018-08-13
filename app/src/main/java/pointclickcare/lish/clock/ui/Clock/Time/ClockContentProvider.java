package pointclickcare.lish.clock.ui.Clock.Time;

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

public class ClockContentProvider  extends ContentProvider {
    private static final String PROVIDER_NAME = "pointclickcare.lish.clock.ui.Clock.Time.ClockContentProvider";
    private static final int PROVIDER_ZONE = 1;
    private static final int PROVIDER_ZONE_ID = 2;
    private static final Uri PROVIDER_URI = Uri.parse("content://" + PROVIDER_NAME + "/zones");
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(PROVIDER_NAME, "zones", PROVIDER_ZONE);
        sUriMatcher.addURI(PROVIDER_NAME, "zones/#", PROVIDER_ZONE_ID);
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
        switch (sUriMatcher.match(uri)) {
            case PROVIDER_ZONE:
                break;
            case PROVIDER_ZONE_ID:
                String id = uri.getPathSegments().get(1);
                selection = ClockDatabaseHelper.ZONE_ID + "=" + id
                        + (TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")");
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }

        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(ClockDatabaseHelper.TABLE_ZONE);
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
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (sUriMatcher.match(uri)) {
            case PROVIDER_ZONE:
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }

        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        long id = db.insert(ClockDatabaseHelper.TABLE_ZONE, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(PROVIDER_URI + "/" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case PROVIDER_ZONE:
                break;
            case PROVIDER_ZONE_ID:
                String id = uri.getPathSegments().get(1);
                selection = ClockDatabaseHelper.ZONE_ID + "=" + id
                        + (TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")");
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }

        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int deleteCount = db.delete(ClockDatabaseHelper.TABLE_ZONE, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return deleteCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case PROVIDER_ZONE:
                break;
            case PROVIDER_ZONE_ID:
                String id = uri.getPathSegments().get(1);
                selection = ClockDatabaseHelper.ZONE_ID + "=" + id
                        + (TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")");
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }

        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int updateCount = db.update(ClockDatabaseHelper.TABLE_ZONE, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return updateCount;
    }
}
