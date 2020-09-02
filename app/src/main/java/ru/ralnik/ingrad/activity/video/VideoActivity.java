package ru.ralnik.ingrad.activity.video;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.activity.DialogButtonListener;
import ru.ralnik.ingrad.for3d.ButtonListener;

public class VideoActivity {
    private final Activity activity;
    private View rootView;
    private Context context;

    @BindView(R.id.button_close)
    ImageView buttonClose;

    @BindViews({R.id.buttonVideo1,
            R.id.buttonVideo2,
            R.id.buttonVideo3,
            R.id.buttonVideo4})List<ImageView> videoButtons;

    public VideoActivity(Activity activity) {
        this.activity = activity;
        rootView = activity.getLayoutInflater().inflate(R.layout.activity_video, null);
        this.context = activity.getApplicationContext();
        ButterKnife.bind(this,rootView);
        /**Инициализация активити*/
        init();
        /**вывести на активити*/
        show();
    }

    private void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setView(this.rootView);
        builder.setCancelable(true);
        builder.create();
        Dialog dialog = builder.show();
        buttonClose.setOnClickListener(new DialogButtonListener(dialog, DialogButtonListener.BUTTON_CLOSE));
    }

    private void init() {
        for (ImageView button : videoButtons) {
            VideoActivityButtonListener buttonListener = new VideoActivityButtonListener(context);
            buttonListener.setVideoButtons(videoButtons);
            buttonListener.initButtons();
            button.setOnClickListener(buttonListener);
        }
    }

}