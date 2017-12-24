package co.twinkly.picatego.utils.services.database;

/**
 * Created by serefbulbul on 19.12.2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.twinkly.picatego.utils.services.database.models.Category;

public class DatabaseService extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = DatabaseService.class.getName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Picatego";

    // Table Names
    private static final String TABLE_CATEGORIES = "categories";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_INTEREST_COUNT = "interest_count";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE "
            + TABLE_CATEGORIES + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
            + " TEXT," + KEY_INTEREST_COUNT + " INTEGER" + ")";

    public DatabaseService(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_CATEGORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);

        // create new tables
        onCreate(db);
    }

    public long createCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, category.getName());
        values.put(KEY_INTEREST_COUNT, category.getInterestCount());

        // insert row
        long categoryId = db.insert(TABLE_CATEGORIES, null, values);

        return categoryId;
    }

    public Category getCategory(long categoryId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES + " WHERE "
                + KEY_ID + " = " + categoryId;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) {
            c.moveToFirst();
        }

        Category category = new Category();
        category.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        category.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        category.setInterestCount(c.getInt(c.getColumnIndex(KEY_INTEREST_COUNT)));

        return category;
    }

    public Category getMostInterestedCategory() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_CATEGORIES + " ORDER BY " + KEY_INTEREST_COUNT + " DESC LIMIT 1";
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) {
            c.moveToFirst();
        }

        Category category = new Category();
        category.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        category.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        category.setInterestCount(c.getInt(c.getColumnIndex(KEY_INTEREST_COUNT)));

        return category;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                category.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                category.setInterestCount(c.getInt(c.getColumnIndex(KEY_INTEREST_COUNT)));

                // adding to todo list
                categories.add(category);
            } while (c.moveToNext());
        }

        return categories;
    }

    public List<Category> getAllCategoriesInDescendingInterest() {
        List<Category> categories = new ArrayList<Category>();
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES + " ORDER BY " + KEY_INTEREST_COUNT + " DESC";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                category.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                category.setInterestCount(c.getInt(c.getColumnIndex(KEY_INTEREST_COUNT)));

                // adding to todo list
                categories.add(category);
            } while (c.moveToNext());
        }

        return categories;
    }

    public int getCategoryCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CATEGORIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public int updateCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, category.getName());
        values.put(KEY_INTEREST_COUNT, category.getInterestCount());

        // updating row
        return db.update(TABLE_CATEGORIES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(category.getId()) });
    }

    public int increaseInterestForCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INTEREST_COUNT, category.getInterestCount() + 1);

        // updating row
        return db.update(TABLE_CATEGORIES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(category.getId()) });
    }

    public void deleteCategory(long categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORIES, KEY_ID + " = ?",
                new String[] { String.valueOf(categoryId) });
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}