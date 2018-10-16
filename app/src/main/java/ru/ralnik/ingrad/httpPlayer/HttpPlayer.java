package ru.ralnik.ingrad.httpPlayer;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpPlayer {

    public String host = "192.168.1.103";
    public int port = 0;
    public String username = null;
    public String password = null;

    public HttpPlayer(String host, int port, String username, String password) {
        this.host = new String(host.split(":")[0]);
        //Log.d("myDebug","Чистый IP:"+this.host);
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public HttpPlayer(String host){
        this.host = new String(host.split(":")[0]);
    }

    public void executeCommand(String url){
        HttpCommandRun commandRun = new  HttpCommandRun(url);
        commandRun.execute();
    }

    class HttpCommandRun extends AsyncTask<Void, Void, Void>{

        private String url;
        public HttpCommandRun(String url) {
            this.url = url;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                CloseableHttpClient client = HttpClients.createDefault();
                HttpClientContext httpContext = HttpClientContext.create();

                if(username != null && password != null) {
                    CredentialsProvider credsProvider = new BasicCredentialsProvider();
                    credsProvider.setCredentials(
                            new AuthScope(host, port),
                            new UsernamePasswordCredentials(username, password));
                    //Log.d("myDebug", "login" + username + "_password" + password);
                    httpContext.setCredentialsProvider(credsProvider);
                }

                String url = (port != 0) ? "http://"+host+":"+port+"/"+this.url :  "http://"+host+"/"+this.url;

                HttpGet httpget = new HttpGet(url);
                //Log.d("myDebug",url);

                CloseableHttpResponse response = client.execute(httpget,httpContext);
                HttpEntity entity = response.getEntity();

                if(entity != null){
                    BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String inputLine;
                    StringBuffer buffer = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        buffer.append(inputLine);
                        //Log.d("myDebug",inputLine.toString());
                    }
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
