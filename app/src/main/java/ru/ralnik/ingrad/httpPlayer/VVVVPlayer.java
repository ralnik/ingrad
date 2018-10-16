package ru.ralnik.ingrad.httpPlayer;

import ru.ralnik.ingrad.model.Flat;

public class VVVVPlayer extends HttpPlayer implements PlayerCommands {

    private int playStop = 0;
    private int volumeOnOff = 1;
    private String lastLink = "";
    private int volume = 1;
    private int volEffect = 1;
    private int numberTrack;
    private Flat flat = null;


    public VVVVPlayer(String host) {
        super(host);
        if(flat == null ) {
            flat = new Flat();
        }
    }


    @Override
    public void play() {
        this.playStop = 1;
        super.executeCommand(getFullLink());
    }

    @Override
    public void pause() {}

    @Override
    public void stop() {
        this.playStop = 0;
        super.executeCommand(getFullLink());
    }

    @Override
    public void selectById(int id) {
        this.numberTrack = id;
        super.executeCommand(getFullLink());
    }

    @Override
    public void volume(int vol) {
        this.volume = vol;
        super.executeCommand(getFullLink());
    }

    @Override
    public void volEffect(int vol) {
        this.volEffect = vol;
        super.executeCommand(getFullLink());
    }

    @Override
    public void volumeOnOff() {
        volumeOnOff = (volumeOnOff == 1) ? 0: 1;
        super.executeCommand(getFullLink());
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

    public String getFullLink(){
        String currentLink = "vvvv?" +
                "track=" + this.numberTrack + "&" +
                "playStop=" + this.playStop + "&" +
                "volumeOnOff=" + this.volumeOnOff + "&" +
                "volume=" + this.volume + "&" +
                "volEffect=" + this.volEffect + "&" +
                flat.toString();

        if (this.lastLink.equals(currentLink)){
            return lastLink;
        }
        this.lastLink = currentLink;
        return currentLink;
    }
}
