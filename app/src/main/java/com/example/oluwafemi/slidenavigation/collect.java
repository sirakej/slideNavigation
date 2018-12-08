package com.example.oluwafemi.slidenavigation;

import android.content.Context;
import android.content.SharedPreferences;

public class collect {
    SharedPreferences real;
    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    public collect(Context context){
        this._context = context;
        real = _context.getSharedPreferences("savedData" ,PRIVATE_MODE);
        editor = real.edit();
    }

    public void setFirstLaunch(boolean firstTime){
        editor.putBoolean("myText" , firstTime);
        editor.commit();
    }

    public boolean firstTime(){
        return real.getBoolean("myText" , true);
    }
}
