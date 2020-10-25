package ru.ralnik.ingrad;

import android.os.Environment;
import android.webkit.WebView;

public class GlobalVars {
    public static String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/data/ru.ralnik.ingrad/";
    public static final String YANDEX_DISK_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath() +"/Android/data/ru.yandex.disk/files/disk/soft/Server/NewPlan/";
    public static WebView webView;
    public final static String DEBUG = "myDebug";

}
