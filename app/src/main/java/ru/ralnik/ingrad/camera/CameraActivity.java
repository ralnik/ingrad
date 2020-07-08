package ru.ralnik.ingrad.camera;

import androidx.appcompat.app.AlertDialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.for3d.ButtonListener;

public class CameraActivity {
    private final Activity activity;
    private View rootView;

    @BindView(R.id.button_close)ImageView button_close;

    public CameraActivity(Activity activity) {
        this.activity = activity;
        rootView = activity.getLayoutInflater().inflate(R.layout.activity_camera, null);
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
        button_close.setOnClickListener(new CamerasButtonOnClick(dialog, ButtonListener.CLOSE));
    }

    private void init() {

    }

}