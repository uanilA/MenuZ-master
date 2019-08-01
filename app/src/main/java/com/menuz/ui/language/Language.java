package com.menuz.ui.language;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class Language {

    public static void SetLanguage(Context _context, String code) {

        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        Resources resources = _context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());


    }
}
