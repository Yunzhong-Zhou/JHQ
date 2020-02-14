package com.ofc.ofc.utils.changelanguage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 保存语言类型
 */
public class SpUtil {

    public static final String LANGUAGE = "language";
    private static final String SP_NAME = "poemTripSpref";
    private static SpUtil spUtil;
    private static SharedPreferences hmSpref;
    private static SharedPreferences.Editor editor;

    private SpUtil(Context context) {
        hmSpref = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = hmSpref.edit();
    }

    public static SpUtil getInstance(Context context) {
        if (spUtil == null) {
            synchronized (SpUtil.class) {
                if (spUtil == null) {
                    spUtil = new SpUtil(context);
                }
            }
        }
        return spUtil;
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return hmSpref.getString(key,"");
    }

}
