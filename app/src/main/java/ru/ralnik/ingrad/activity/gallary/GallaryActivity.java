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

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.activity.video.VideoActivityButtonListener;
import ru.ralnik.ingrad.for3d.ButtonListener;

public class GallaryActivity {
    private final Activity activity;
    private Context context;
    private View rootView;

    @BindView(R.id.btnClose)
    ImageView buttonClose;

    @BindView(R.id.buttonRiverskyArchitecture)ImageView buttonRiverskyArchitecture;
    @BindView(R.id.buttonRiverskyEnvironment)ImageView buttonRiverskyEnvironment;
    @BindView(R.id.buttonRiverskyImprovement)ImageView buttonRiverskyImprovement;
    @BindView(R.id.buttonRiverskyMop)ImageView buttonRiverskyMop;
    @BindView(R.id.buttonRiverskyTownhouse)ImageView buttonRiverskyTownhouse;

    @BindView(R.id.buttonForiverArchitecture)ImageView buttonForiverArchitecture;
    @BindView(R.id.buttonForiverEnvironment)ImageView buttonForiverEnvironment;
    @BindView(R.id.buttonForiverImprovement)ImageView buttonForiverImprovement;
    @BindView(R.id.buttonForiverMop)ImageView buttonForiverMop;

    @BindView(R.id.buttonPlayPause)ImageView buttonPlayPause;
    @BindView(R.id.buttonForward)ImageView buttonForward;
    @BindView(R.id.buttonBack)ImageView buttonBack;

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
        buttonClose.setOnClickListener(new ButtonListener(dialog, ButtonListener.CLOSE));
    }

    private void init() {
        buttonRiverskyArchitecture.setOnClickListener(new GallaryActivityButtonListener(context));
        buttonRiverskyEnvironment.setOnClickListener(new GallaryActivityButtonListener(context));
        buttonRiverskyImprovement.setOnClickListener(new GallaryActivityButtonListener(context));
        buttonRiverskyMop.setOnClickListener(new GallaryActivityButtonListener(context));
        buttonRiverskyTownhouse.setOnClickListener(new GallaryActivityButtonListener(context));

        buttonForiverArchitecture.setOnClickListener(new GallaryActivityButtonListener(context));
        buttonForiverImprovement.setOnClickListener(new GallaryActivityButtonListener(context));
        buttonForiverEnvironment.setOnClickListener(new GallaryActivityButtonListener(context));
        buttonForiverMop.setOnClickListener(new GallaryActivityButtonListener(context));

        buttonPlayPause.setOnClickListener(new GallaryActivityButtonListener(context));
        buttonBack.setOnClickListener(new GallaryActivityButtonListener(context));
        buttonForward.setOnClickListener(new GallaryActivityButtonListener(context));
    }
}