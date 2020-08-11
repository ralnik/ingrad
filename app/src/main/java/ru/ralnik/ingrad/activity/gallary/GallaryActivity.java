package ru.ralnik.ingrad.activity.gallary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.activity.DialogButtonListener;
import ru.ralnik.ingrad.activity.video.VideoActivityButtonListener;
import ru.ralnik.ingrad.for3d.ButtonListener;

public class GallaryActivity {
    private final Activity activity;
    private Context context;
    private View rootView;

    @BindView(R.id.button_close)
    ImageView buttonClose;

//    @BindView(R.id.buttonRiverskyArchitecture)ImageView buttonRiverskyArchitecture;
//    @BindView(R.id.buttonRiverskyEnvironment)ImageView buttonRiverskyEnvironment;
//    @BindView(R.id.buttonRiverskyImprovement)ImageView buttonRiverskyImprovement;
//    @BindView(R.id.buttonRiverskyMop)ImageView buttonRiverskyMop;
//    @BindView(R.id.buttonRiverskyTownhouse)ImageView buttonRiverskyTownhouse;
//
//    @BindView(R.id.buttonForiverArchitecture)ImageView buttonForiverArchitecture;
//    @BindView(R.id.buttonForiverEnvironment)ImageView buttonForiverEnvironment;
//    @BindView(R.id.buttonForiverImprovement)ImageView buttonForiverImprovement;
//    @BindView(R.id.buttonForiverMop)ImageView buttonForiverMop;
//
//    @BindView(R.id.buttonRemontModern)ImageView buttonRemontModern;
//    @BindView(R.id.buttonRemontClassic)ImageView buttonRemontClassic;
//    @BindView(R.id.buttonRemontArtDeco)ImageView buttonRemontArtDeco;
//
//    @BindView(R.id.buttonPlayPause)ImageView buttonPlayPause;
//    @BindView(R.id.buttonForward)ImageView buttonForward;
//    @BindView(R.id.buttonBack)ImageView buttonBack;

    @BindViews({R.id.buttonRiverskyArchitecture,
            R.id.buttonRiverskyEnvironment,
            R.id.buttonRiverskyImprovement,
            R.id.buttonRiverskyMop,
            R.id.buttonRiverskyTownhouse,
            R.id.buttonForiverArchitecture,
            R.id.buttonForiverEnvironment,
            R.id.buttonForiverImprovement,
            R.id.buttonForiverMop,
            R.id.buttonRemontModern,
            R.id.buttonRemontClassic,
            R.id.buttonRemontArtDeco
    })List<ImageView> gallaryButtons;

    @BindViews({R.id.buttonPlayPause,
            R.id.buttonForward,
            R.id.buttonBack})List<ImageView> gallaryMediaButtons;

    public GallaryActivity(Activity activity) {
        this.activity = activity;
        rootView = activity.getLayoutInflater().inflate(R.layout.activity_gallary, null);
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
        for (ImageView button : gallaryMediaButtons) {
            button.setTag(0);
            GallaryActivityButtonListener buttonListener = new GallaryActivityButtonListener(context);
            buttonListener.setGallaryMediaButtons(gallaryMediaButtons);
            buttonListener.initMediaButtons();
            button.setOnClickListener(buttonListener.getButtonsOnClick(GallaryActivityButtonListener.GALLARY_MEDIA_BUTTONS));
        }
        for (ImageView button : gallaryButtons) {
            GallaryActivityButtonListener buttonListener = new GallaryActivityButtonListener(context);
            buttonListener.setGallaryButtons(gallaryButtons);
            buttonListener.initButtons();
            button.setOnClickListener(buttonListener.getButtonsOnClick(GallaryActivityButtonListener.GALLARY_BUTTONS));
        }
    }
}