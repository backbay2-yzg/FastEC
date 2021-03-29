package com.yzg.koala.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.yzg.koala.core.app.Koala;

public class DimenUtil {
    public static int getScreenWidth() {
        final Resources resources = Koala.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
  public static int getScreenHeight() {
        final Resources resources = Koala.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
