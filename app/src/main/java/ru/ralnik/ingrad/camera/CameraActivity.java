package ru.ralnik.ingrad.camera;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import ru.ralnik.clickablebutton.ClickableButton;
import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.activity.DialogButtonListener;
import ru.ralnik.ingrad.for3d.ButtonListener;

public class CameraActivity {
    private final Activity activity;
    @BindView(R.id.button_close)
    ImageView button_close;
    private View rootView;
    @BindViews({R.id.btnCamera1, R.id.btnCamera2, R.id.btnCamera3})
    List<ClickableButton> buttonCameraList;

    public CameraActivity(Activity activity) {
        this.activity = activity;
        rootView = activity.getLayoutInflater().inflate(R.layout.activity_camera, null);
        ButterKnife.bind(this, rootView);

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
        button_close.setOnClickListener(new DialogButtonListener(dialog, DialogButtonListener.BUTTON_CLOSE));
    }

    private void init() {
        for (ClickableButton button : buttonCameraList) {
            button.setOnDemonstrationButtonClickListener(new CamerasButtonOnClick(buttonCameraList));
        }
    }

}