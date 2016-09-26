package com.asgar.tanggapbencana.sharedPrefs;

import android.content.Context;

import com.asgar.tanggapbencana.model.DataRelawan;

/**
 * Created by skday on 9/26/16.
 */

public class PrefRelawan {
    public static final String PREF_NAME = "Relawan_pref";
    public static final String RELAWAN = "Relawan_value";

    public static void setRelawan(DataRelawan objekRelawan, Context ctx ){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREF_NAME,0);
        complexPreferences.putObject(RELAWAN, objekRelawan);
        complexPreferences.commit();
    }

    public static DataRelawan getRelawan(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREF_NAME, 0);
        return complexPreferences.getObject(RELAWAN,DataRelawan.class);
    }

    public static String getJSON(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREF_NAME,0);
        return complexPreferences.getJSON(RELAWAN);
    }

    public static void clearRelawan(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREF_NAME, 0);
        complexPreferences.clearObject();
        complexPreferences.commit();
    }
}
