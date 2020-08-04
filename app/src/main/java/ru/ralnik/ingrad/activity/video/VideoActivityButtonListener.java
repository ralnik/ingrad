package ru.ralnik.ingrad.activity.video;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import ru.ralnik.ingrad.R;

import ru.ralnik.ingrad.httpPlayer.HttpPlayerFactory;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;

public class VideoActivityButtonListener implements ImageView.OnClickListener {
    private static final int VIDEO1 = 1;
    private static final int VIDEO2 = 2;
    private static final int VIDEO3 = 3;
    private static final int VIDEO4 = 4;
    private static final int VIDEO5 = 5;
    private static final int VIDEO6 = 6;
    private static final int VIDEO7 = 7;

    private PlayerCommands vvvv;

    public VideoActivityButtonListener(Context context) {
        vvvv = HttpPlayerFactory.getInstance(context).getCommand();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonVideo1:
                vvvv.playVideo(VIDEO1);
                break;
            case R.id.buttonVideo2:
                vvvv.playVideo(VIDEO2);
                break;
            case R.id.buttonVideo3:
                vvvv.playVideo(VIDEO3);
                break;
            case R.id.buttonVideo4:
                vvvv.playVideo(VIDEO4);
                break;
            case R.id.buttonVideo5:
                vvvv.playVideo(VIDEO5);
                break;
            case R.id.buttonVideo6:
                vvvv.playVideo(VIDEO6);
                break;
            case R.id.buttonVideo7:
                vvvv.playVideo(VIDEO7);
                break;
        }
    }
}
