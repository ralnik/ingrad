package ru.ralnik.ingrad.activity.exception;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.activity.DialogButtonListener;

public class ExceptionActivity {
    private final Activity activity;
    private final String message;
    private View rootView;

    @BindView(R.id.textViewError)
    TextView textViewError;
    @BindView(R.id.button_close)
    ImageView button_close;

    public ExceptionActivity(Activity activity, String message) {
        this.activity = activity;
        this.message = message;
        rootView = activity.getLayoutInflater().inflate(R.layout.activity_exception, null);
        ButterKnife.bind(this,rootView);
        show();
    }
    private void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setView(this.rootView);
        builder.setCancelable(true);
        builder.create();
        Dialog dialog = builder.show();
        textViewError.setText(this.message);
        button_close.setOnClickListener(new DialogButtonListener(dialog, DialogButtonListener.BUTTON_CLOSE));
    }
}