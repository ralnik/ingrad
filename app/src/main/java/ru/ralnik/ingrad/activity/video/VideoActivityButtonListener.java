package ru.ralnik.ingrad.activity.video;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import lombok.Setter;
import ru.ralnik.ingrad.R;

import ru.ralnik.ingrad.config.myConfig;
import ru.ralnik.ingrad.context.IngradContex;
import ru.ralnik.ingrad.httpPlayer.HttpPlayerFactory;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;
import ru.ralnik.ingrad.httpPlayer.VVVVPlayer;

public class VideoActivityButtonListener implements ImageView.OnClickListener {
    private static final int VIDEO1 = 1;
    private static final int VIDEO2 = 2;
    private static final int VIDEO3 = 3;
    private static final int VIDEO4 = 4;
    private static final int VIDEO5 = 5;
    private static final int VIDEO6 = 6;
    private static final int VIDEO7 = 7;

    private PlayerCommands vvvv;
    private PlayerCommands vvvv2;
    @Setter
    private List<ImageView> videoButtons;
    private ImageView buttonVideo1;
    private ImageView buttonVideo2;
    private ImageView buttonVideo3;
    private ImageView buttonVideo4;
    private ImageView buttonVideo5;
    private ImageView buttonVideo6;
    private ImageView buttonVideo7;

    public VideoActivityButtonListener(Context context) {
        vvvv = IngradContex.getInstance(context).getVvvv();
        vvvv2 = IngradContex.getInstance(context).getVvvv2();
    }

    public  void initButtons() {
        for (ImageView button : videoButtons) {
            if (button.getId() == R.id.buttonVideo1) buttonVideo1 = button;
            if (button.getId() == R.id.buttonVideo2) buttonVideo2 = button;
            if (button.getId() == R.id.buttonVideo3) buttonVideo3 = button;
            if (button.getId() == R.id.buttonVideo4) buttonVideo4 = button;
            if (button.getId() == R.id.buttonVideo5) buttonVideo5 = button;
            if (button.getId() == R.id.buttonVideo6) buttonVideo6 = button;
            if (button.getId() == R.id.buttonVideo7) buttonVideo7 = button;
        }
    }

    @Override
    public void onClick(View view) {
        buttonVideo1.setImageResource(R.drawable.video_1);
        buttonVideo2.setImageResource(R.drawable.video_2);
        buttonVideo3.setImageResource(R.drawable.video_3);
        buttonVideo4.setImageResource(R.drawable.video_4);
        buttonVideo5.setImageResource(R.drawable.video_5);
        buttonVideo6.setImageResource(R.drawable.video_6);
        buttonVideo7.setImageResource(R.drawable.video_7);

        switch (view.getId()) {
            case R.id.buttonVideo1:
                vvvv.playVideo(VIDEO1);
                vvvv2.playVideo(VIDEO1);
                buttonVideo1.setImageResource(R.drawable.video_1_down);
                break;
            case R.id.buttonVideo2:
                vvvv.playVideo(VIDEO2);
                vvvv2.playVideo(VIDEO2);
                buttonVideo2.setImageResource(R.drawable.video_2_down);
                break;
            case R.id.buttonVideo3:
                vvvv.playVideo(VIDEO3);
                vvvv2.playVideo(VIDEO3);
                buttonVideo3.setImageResource(R.drawable.video_3_down);
                break;
            case R.id.buttonVideo4:
                vvvv.playVideo(VIDEO4);
                vvvv2.playVideo(VIDEO4);
                buttonVideo4.setImageResource(R.drawable.video_4_down);
                break;
            case R.id.buttonVideo5:
                vvvv.playVideo(VIDEO5);
                vvvv2.playVideo(VIDEO5);
                buttonVideo5.setImageResource(R.drawable.video_5_down);
                break;
            case R.id.buttonVideo6:
                vvvv.playVideo(VIDEO6);
                vvvv2.playVideo(VIDEO6);
                buttonVideo6.setImageResource(R.drawable.video_6_down);
                break;
            case R.id.buttonVideo7:
                vvvv.playVideo(VIDEO7);
                vvvv2.playVideo(VIDEO7);
                buttonVideo7.setImageResource(R.drawable.video_7_down);
                break;
        }
    }
}
