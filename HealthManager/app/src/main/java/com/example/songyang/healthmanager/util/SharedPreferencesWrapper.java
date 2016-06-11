package com.example.songyang.healthmanager.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;
import java.util.Set;

public class SharedPreferencesWrapper {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Gson mGson;

    public SharedPreferencesWrapper(Context context, String db) {
        mSharedPreferences = context.getSharedPreferences(db, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mGson = new Gson();
    }

    public Map<String, ?> getAll() {
        return mSharedPreferences.getAll();
    }

    @Nullable
    public String getString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    @Nullable
    public Set<String> getStringSet(String key, Set<String> defValues) {
        return mSharedPreferences.getStringSet(key, defValues);
    }

    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return mSharedPreferences.getFloat(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 获取Object
     *
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public <T> T getValue(String key, Class<T> type) {
        return mGson.fromJson(getString(key, null), type);
    }

    /**
     * 获取Object
     *
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public <T> T getValue(String key, TypeToken<T> type) {
        return mGson.fromJson(getString(key, null), type.getType());
    }

    public boolean contains(String key) {
        return mSharedPreferences.contains(key);
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mSharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public boolean putString(String key, String value) {
        return mEditor.putString(key, value).commit();
    }

    public boolean putStringSet(String key, Set<String> values) {
        return mEditor.putStringSet(key, values).commit();
    }

    public boolean putInt(String key, int value) {
        return mEditor.putInt(key, value).commit();
    }

    public boolean putLong(String key, long value) {
        return mEditor.putLong(key, value).commit();
    }

    public boolean putFloat(String key, float value) {
        return mEditor.putFloat(key, value).commit();
    }

    public boolean putBoolean(String key, boolean value) {
        return mEditor.putBoolean(key, value).commit();
    }

    /**
     * 保存Object
     *
     * @param key
     * @param value
     * @return
     */
    public boolean putValue(String key, Object value) {
        return putString(key, mGson.toJson(value));
    }

    public boolean remove(String key) {
        return mEditor.remove(key).commit();
    }

    public boolean clear() {
        return mEditor.clear().commit();
    }
}
