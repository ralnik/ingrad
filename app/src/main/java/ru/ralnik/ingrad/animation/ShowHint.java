package ru.ralnik.ingrad.animation;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import ru.ralnik.ingrad.R;

public class ShowHint implements View.OnLongClickListener, View.OnTouchListener {


    private final Activity activity;
    private TextView hint;
    private boolean isMoving;

    public ShowHint(Activity activity) {
        this.activity = activity;
        this.hint = (TextView) this.activity.findViewById(R.id.hint);

    }

    @Override
    public boolean onLongClick(View view) {
        hint.setText("test test test");
        hint.setVisibility(View.VISIBLE);
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            isMoving = true;

        }
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            isMoving=false;
            hint.setVisibility(View.GONE);
        }
        return false;
    }
}
