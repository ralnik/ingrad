package ru.ralnik.ingrad.httpPlayer;

import android.content.Context;

import ru.ralnik.ingrad.config.myConfig;

@Deprecated
public class HttpPlayerFactory {
    private static PlayerCommands playerCommands = null;
    private static HttpPlayerFactory instance = null;
    private Context context;
    private myConfig cfg;
    private String host;

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

    public HttpPlayerFactory selectHost(EnumHost host) {
        if (host == EnumHost.SERVER_1) {
            this.host = cfg.getHost1();
        } else if (host == EnumHost.SERVER_2) {
            this.host = cfg.getHost2();
        }
        return this;
    }

    public PlayerCommands getCommand() {
        if (host.isEmpty()) {
            host = cfg.getHost();
        }
        if (playerCommands == null) {
            playerCommands = new VVVVPlayer(host);
        }
        return playerCommands;
    }

}
