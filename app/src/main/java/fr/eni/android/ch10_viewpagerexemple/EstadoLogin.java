package fr.eni.android.ch10_viewpagerexemple;

import android.content.Context;
import android.content.SharedPreferences;

public class EstadoLogin {


    private final String SHARED_PREFS_FILE = "HMPrefs";
    private final String loged = "";
    private Context mContext;

    public EstadoLogin(Context context){
        mContext = context;
    }

    private SharedPreferences getSettings(){
        return mContext.getSharedPreferences(SHARED_PREFS_FILE, 0);
    }

    public boolean getLog(){
        return getSettings().getBoolean(loged,false);
    }

    public void setLog(Boolean log){
        SharedPreferences.Editor editor = getSettings().edit();
        editor.putBoolean(loged, log );
        editor.commit();
    }

}
