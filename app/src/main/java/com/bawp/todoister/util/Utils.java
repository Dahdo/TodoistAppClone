package com.bawp.todoister.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public String formatDate(Date date){
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getInstance();
        simpleDateFormat.applyLocalizedPattern("EEE, MMM, d");

        return simpleDateFormat.format(date);
    }

    public static void hideSoftKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
