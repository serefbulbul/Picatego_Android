package co.twinkly.picatego.utils.managers;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by serefbulbul on 19.12.2017.
 */

public class SharedPreferencesManager {

    private final String PREF_NAME = "co.twinkly.picatego.PicategoSharedPreferences";

    private static SharedPreferencesManager mInstance;
    private SharedPreferences mSharedPreferences;

    private SharedPreferencesManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (mInstance == null)
            mInstance = new SharedPreferencesManager(context);
        return mInstance;
    }

    public static synchronized SharedPreferencesManager newInstance(Context context) {
        return new SharedPreferencesManager(context);
    }

    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    public void put(String key, Integer value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putInt(key, value);
        editor.commit();
    }

    public void put(String key, Boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putBoolean(key, value);
        editor.commit();
    }

    public void put(String key, Float value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putFloat(key, value);
        editor.commit();
    }

    public void put(String key, Long value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putLong(key, value);
        editor.commit();
    }

    public void put(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString(key, value);
        editor.commit();
    }

    public void put(Map<String, ?> editMap) {
        for (Map.Entry<String, ?> entry : editMap.entrySet()) {
            if (entry.getValue() instanceof Integer)
                put(entry.getKey(), ((Integer) entry.getValue()));
            else if (entry.getValue() instanceof Boolean)
                put(entry.getKey(), ((Boolean) entry.getValue()));
            else if (entry.getValue() instanceof Float)
                put(entry.getKey(), ((Float) entry.getValue()));
            else if (entry.getValue() instanceof Long)
                put(entry.getKey(), ((Long) entry.getValue()));
            else
                put(entry.getKey(), ((String) entry.getValue()));
        }
    }

    public Integer getIntegerValue(String key) {
        if (mSharedPreferences.contains(key))
            return mSharedPreferences.getInt(key, -1);
        return null;
    }

    public Boolean getBooleanValue(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public Float getFloatValue(String key) {
        if (mSharedPreferences.contains(key))
            return mSharedPreferences.getFloat(key, -1);
        return null;
    }

    public Long getLongValue(String key) {
        if (mSharedPreferences.contains(key))
            return mSharedPreferences.getLong(key, -1);
        return null;
    }

    public String getStringValue(String key) {
        if (mSharedPreferences.contains(key))
            return mSharedPreferences.getString(key, null);
        return null;
    }

    public Map<String, ?> getAllValues() {
        return mSharedPreferences.getAll();
    }

    public void remove(String... key) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        for (String k : key)
            editor.remove(k);
        editor.commit();
    }

    public void removeAll() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        for (Map.Entry<String, ?> entry : getAllValues().entrySet()) {
            editor.remove(entry.getKey());
        }
        editor.commit();
    }
}
