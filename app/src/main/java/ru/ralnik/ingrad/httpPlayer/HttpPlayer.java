package ru.ralnik.ingrad.httpPlayer;

import android.net.http.SslError;
import android.util.Log;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ru.ralnik.ingrad.GlobalVars;

public class HttpPlayer {

    public String host = "192.168.1.103";
    public int port = 0;
    public String username = null;
    public String password = null;
    private WebView webView;

    public HttpPlayer(String host, int port, String username, String password) {
        this.host = new String(host.split(":")[0]);
        //Log.d("myDebug","Чистый IP:"+this.host);
        this.port = port;
        this.username = username;
        this.password = password;
    }



    public HttpPlayer(String host){
        this.host = new String(host.split(":")[0]);
        this.webView = GlobalVars.webView;
        connectToServer();
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
        connectToServer();
    }

    public void executeCommand(String url){
        String current_link = (port != 0) ? "http://"+host+":"+port+"/"+url :  "http://"+host+"/"+url;
        Log.d("myDebug",current_link);
        this.webView.loadUrl(current_link);
    }

    private void connectToServer() {

        // включаем поддержку JavaScript
        this.webView.getSettings().setJavaScriptEnabled(true);
        // указываем страницу загрузки+

        // mwebView.setHttpAuthUsernamePassword(HOST, "realest", null, PASSWORD);
        this.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed(null, password);
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
    }
}
