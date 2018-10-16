package ru.ralnik.ingrad.httpPlayer;

import ru.ralnik.ingrad.model.Flat;

public interface PlayerCommands {

    void play();
    void pause();
    void stop();
    void selectById(int id);
    void volume(int vol);
    void volEffect(int vol);
    void volumeOnOff();
    void toggleLoop();
    void repeat();     //repeat or not repeat
    void random();    //random
    void nextTrack();
    void previousTrack();
    void fullscreen();
    void playlistEmpty();
    void deleteById(int id);

    void setFlatInfo(Flat flat);

}
