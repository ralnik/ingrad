package ru.ralnik.ingrad.httpPlayer;

import android.webkit.WebView;

import ru.ralnik.ingrad.model.Flat;

public class VVVVPlayer extends HttpPlayer implements PlayerCommands {

    private int playStop = 0;
    private int volumeOnOff = 1;
    private String lastLink = "";
    private int volume = 1;
    private int volEffect = 1;
    private int numberTrack;
    private int numberSubTrack;
    private Flat flat = null;


    public VVVVPlayer(String host) {
        super(host);
        if(flat == null ) {
            flat = new Flat();
        }
    }

    @Override
    public void setWebView(WebView webView) {
        super.setWebView(webView);
    }

    @Override
    public void setVolumeOnOff(int volumeOnOff) {
        this.volumeOnOff = volumeOnOff;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public void setVolEffect(int volEffect) {
        this.volEffect = volEffect;
    }

    @Override
    public void changeHost(String host) {
        setHost(host);
    }

    @Override
    public void play() {
        this.playStop = 1;
        executeCommand(getFullLink());
    }

    @Override
    public void pause() {}

    @Override
    public void stop() {
        this.playStop = 0;
        executeCommand(getFullLink());
    }

    @Override
    public void selectById(int id) {
        this.playStop = 1;
        this.numberTrack = id;
        if(id == 7){this.numberSubTrack = 0;}
        executeCommand(getFullLink());
    }

    @Override
    public void selectBySubId(int id) {
        this.playStop = 1;
        this.numberSubTrack = id;
        executeCommand(getFullLink());
    }

    @Override
    public void volume(int vol) {
        this.volume = vol;
        executeCommand(getFullLink());
    }

    @Override
    public void volEffect(int vol) {
        this.volEffect = vol;
        executeCommand(getFullLink());
    }

    @Override
    public void volumeOnOff() {
        volumeOnOff = (volumeOnOff == 1) ? 0: 1;
        executeCommand(getFullLink());
    }

    @Override
    public void toggleLoop() {}

    @Override
    public void repeat() {}

    @Override
    public void random() {}

    @Override
    public void nextTrack() {}

    @Override
    public void previousTrack() {}

    @Override
    public void fullscreen() {}

    @Override
    public void playlistEmpty() {}

    @Override
    public void deleteById(int id) {}

    @Override
    public void setFlatInfo(Flat flat){
        this.flat = flat;
    }

    @Override
    public void executeCommand(String url){

        if (!this.lastLink.equals(url)){
            this.lastLink = url;
            super.executeCommand(url);
        }

    }

    public String getFullLink(){
        return "vvvv?" +
               "track=" + this.numberTrack + "&" +
               "subTrack=" + this.numberSubTrack + "&" +
               "playStop=" + this.playStop + "&" +
               "volumeOnOff=" + this.volumeOnOff + "&" +
               "volume=" + this.volume + "&" +
               "volEffect=" + this.volEffect + "&" +
               flat.toString();
    }
}
