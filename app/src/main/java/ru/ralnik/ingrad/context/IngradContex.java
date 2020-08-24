package ru.ralnik.ingrad.context;

import android.app.Activity;
import android.content.Context;

import lombok.Getter;
import lombok.Setter;
import ru.ralnik.ingrad.config.myConfig;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;
import ru.ralnik.ingrad.httpPlayer.VVVVPlayer;

/*
* контекст для приложения
* */
public class IngradContex {
    private static IngradContex instance;
    private static Context context;
    @Setter
    @Getter
    private static Activity mainActivity;
    @Getter
    private PlayerCommands vvvv;
    @Getter
    private PlayerCommands vvvv2;
    private myConfig cfg;

    private IngradContex(Context context) {
        this.context = context;
        init();
    }

    public static synchronized IngradContex getInstance(Context context) {
        if (instance == null) {
            instance = new IngradContex(context);
        }
        return instance;
    }

    private void init() {
        cfg = new myConfig(context);
        if (vvvv == null) {
            vvvv = new VVVVPlayer(cfg.getHost1());
        }
        if (vvvv2 == null) {
            vvvv2 = new VVVVPlayer(cfg.getHost2());
        }
    }

    public static Context getAppContext() {
        return context;
    }
}
