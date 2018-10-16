package ru.ralnik.ingrad.httpPlayer;

public class VLCPlayer extends HttpPlayer implements PlayerCommands {

    public VLCPlayer(String host, int port, String username, String password) {
        super(host, port, username, password);
    }

    @Override
    public void play() {
        executeCommand("requests/status.xml?command=pl_play");
    }

    @Override
    public void pause() {
        executeCommand("requests/status.xml?command=pl_pause");
    }

    @Override
    public void stop() {
        executeCommand("requests/status.xml?command=pl_stop");
    }

    @Override
    public void selectById(int id) {
        executeCommand("requests/status.xml?command=pl_play&id=" + id);
    }

    @Override
    public void volume(int vol) {
        executeCommand("requests/status.xml?command=volume&val="+vol);
    }

    @Override
    public void volEffect(int vol) {}

    @Override
    public void volumeOnOff() {}

    @Override
    public void toggleLoop() {
        executeCommand("requests/status.xml?command=pl_loop");
    }

    @Override
    public void repeat() {
        executeCommand("requests/status.xml?command=pl_repeat");
    }

    @Override
    public void random() {
        executeCommand("requests/status.xml?command=pl_random");
    }

    @Override
    public void nextTrack() {
        executeCommand("requests/status.xml?command=pl_next");
    }

    @Override
    public void previousTrack() {
        executeCommand("requests/status.xml?command=pl_previous");
    }

    @Override
    public void fullscreen() {
        executeCommand("requests/status.xml?command=fullscreen");
    }

    @Override
    public void playlistEmpty() {
        executeCommand("requests/status.xml?command=pl_empty");
    }

    @Override
    public void deleteById(int id) {
        executeCommand("requests/status.xml?command=pl_delete&id="+id);
    }
}
