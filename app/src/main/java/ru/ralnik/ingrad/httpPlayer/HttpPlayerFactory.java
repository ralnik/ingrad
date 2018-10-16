package ru.ralnik.ingrad.httpPlayer;

import android.content.Context;

import ru.ralnik.ingrad.config.myConfig;

public class HttpPlayerFactory {
    private static PlayerCommands playerCommands = null;
    private static HttpPlayerFactory instance = null;
    private Context context;
    private myConfig cfg;

    public HttpPlayerFactory(Context context){
        this.context = context;
        cfg = new myConfig(context);
    }

    public static synchronized HttpPlayerFactory getInstance(Context context) {
        if (instance == null) {
            instance = new HttpPlayerFactory(context);
        }
        return instance;
    }


    public PlayerCommands getCommand() {
        if (playerCommands == null) {
            playerCommands = new VVVVPlayer(cfg.getHost());
        }
        return playerCommands;
    }

}
