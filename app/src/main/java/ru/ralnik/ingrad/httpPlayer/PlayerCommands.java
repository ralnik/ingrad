package ru.ralnik.ingrad.httpPlayer;

import ru.ralnik.ingrad.model.Flat;

public interface PlayerCommands {

    void play();
    default void pause() {}
    void stop();
    void selectById(int id);
    void selectBySubId(int id);
    void volume(int vol);
    void volEffect(int vol);
    void volumeOnOff();
    default void toggleLoop() {}
    default void repeat() {}     //repeat or not repeat
    default void random() {}    //random
    default void nextTrack() {}
    default void previousTrack() {}
    default void fullscreen() {}
    default void playlistEmpty() {}
    default void deleteById(int id) {}

    void setFlatInfo(Flat flat);
    void changeHost(String host);
    default void setVolumeOnOff(int volumeOnOff) {}
    void setVolume(int volume);
    void setVolEffect(int volEffect);
    void playVideo(int videoTrack);
    void showGallary(int gallary);
    void gallaryBack(int back);
    void gallaryForward(int forward);
    void gallaryPlay(int play);
    void for3d(int value);
    void for3dVideoTrack(int value);
    void setTrackTv(int value);

}
