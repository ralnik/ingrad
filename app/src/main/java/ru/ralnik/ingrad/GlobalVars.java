package ru.ralnik.ingrad;

import android.os.Environment;
import android.webkit.WebView;

public class GlobalVars {
    public static String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/data/ru.ralnik.ingrad/";
    public static WebView webView;

}
