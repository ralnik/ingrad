package ru.ralnik.ingrad.for3d;

import android.app.Activity;
import android.app.Dialog;

import androidx.appcompat.app.AlertDialog;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.activity.DialogButtonListener;

public class For3DActivity {
    private final Activity activity;
    private View rootView;

    @BindView(R.id.button_up)
    ImageView button_up;
    @BindView(R.id.button_down)
    ImageView button_down;
    @BindView(R.id.button_left)
    ImageView button_left;
    @BindView(R.id.button_right)
    ImageView button_right;
    @BindView(R.id.button_close)
    ImageView button_close;

    public For3DActivity(Activity activity) {
        this.activity = activity;
        rootView = activity.getLayoutInflater().inflate(R.layout.activity_for3d, null);
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
        button_close.setOnClickListener(new DialogButtonListener(dialog, DialogButtonListener.BUTTON_CLOSE));
    }

    private void init() {
        button_down.setOnTouchListener(new ButtonListener(ButtonListener.DOWN, activity.getApplicationContext()));
        button_up.setOnTouchListener(new ButtonListener(ButtonListener.UP, activity.getApplicationContext()));
        button_left.setOnTouchListener(new ButtonListener(ButtonListener.LEFT, activity.getApplicationContext()));
        button_right.setOnTouchListener(new ButtonListener(ButtonListener.RIGHT, activity.getApplicationContext()));
    }
}