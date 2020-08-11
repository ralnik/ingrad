package ru.ralnik.ingrad.httpPlayer;

import lombok.Getter;
import lombok.Setter;
import ru.ralnik.ingrad.model.Flat;

@Getter
@Setter
public class  VVVVPlayer extends HttpPlayer {

    private int playStop = 0;
    private int volumeOnOff = 1;
    private String lastLink = "";
    private int volume = 1;
    private int volEffect = 1;
    private int numberTrack;
    private int numberSubTrack;
    private Flat flat = null;
    private int videoTrack;
    private int gallary;
    private int gallaryPlay = 0;
    private int gallaryForward = 0;
    private int gallaryBack = 0;
    private int for3d = 0;


    public VVVVPlayer(String host) {
        super(host);
        if(flat == null ) {
            flat = new Flat();
        }
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

    @Override
    public void playVideo(int videoTrack) {
        this.videoTrack = videoTrack;
        super.executeCommand(getFullLink());
    }

    @Override
    public void showGallary(int gallary) {
        this.gallary = gallary;
        super.executeCommand(getFullLink());
    }

    @Override
    public void gallaryBack(int back) {
        this.gallaryBack = back;
        super.executeCommand(getFullLink());
    }

    @Override
    public void gallaryForward(int forward) {
        this.gallaryForward = forward;
        super.executeCommand(getFullLink());
    }

    @Override
    public void gallaryPlay(int play) {
        this.gallaryPlay = play;
        super.executeCommand(getFullLink());
    }

    @Override
    public void for3d(int value) {
        this.for3d = value;
        super.executeCommand(getFullLink());
    }

    public String getFullLink(){
        return "vvvv?" +
               "track=" + this.numberTrack + "&" +
               "subTrack=" + this.numberSubTrack + "&" +
               "playStop=" + this.playStop + "&" +
               "volumeOnOff=" + this.volumeOnOff + "&" +
               "volume=" + this.volume + "&" +
               "volEffect=" + this.volEffect + "&" +
               flat.toString() + "&" +
                "gallary=" + this.gallary + "&" +
                "gallaryPlay=" + this.gallaryPlay + "&" +
                "gallaryForward=" + this.gallaryForward + "&" +
                "gallaryBack=" + this.gallaryBack + "&" +
                "video=" + this.videoTrack  + "&" +
                "3d=" + this.for3d;
    }
}
