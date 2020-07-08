package ru.ralnik.ingrad.for3d;

import android.app.Activity;
import android.app.Dialog;

import androidx.appcompat.app.AlertDialog;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.ralnik.ingrad.R;

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
        button_close.setOnClickListener(new ButtonListener(dialog, ButtonListener.CLOSE));
    }

    private void init() {
        button_down.setOnClickListener(new ButtonListener(ButtonListener.DOWN));
        button_up.setOnClickListener(new ButtonListener(ButtonListener.UP));
        button_left.setOnClickListener(new ButtonListener(ButtonListener.LEFT));
        button_right.setOnClickListener(new ButtonListener(ButtonListener.RIGHT));
    }
}