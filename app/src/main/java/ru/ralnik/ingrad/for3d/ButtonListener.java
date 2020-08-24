package ru.ralnik.ingrad.for3d;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.context.IngradContex;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;

public class ButtonListener implements FrameLayout.OnTouchListener {
    public final static int LEFT = 1;
    public final static int RIGHT = 2;
    public final static int UP = 3;
    public final static int DOWN = 4;
    public final static int CLOSE = 5;
    public final static int EMPTY = 0;
    public final static int BACK = 6;
    public final static int FORWARD = 7;
    public final static int HOME = 8;

    private int pathTo;
    private Dialog dialog;
    private Context context;
    private ImageView button;

    private PlayerCommands vvvv;
    private PlayerCommands vvvv2;

    public ButtonListener(int pathTo, Context context, ImageView buttonArrow){
        this.pathTo = pathTo;
        this.context = context;
        this.button = buttonArrow;
        init();
    }

    public ButtonListener(Dialog dialog, int pathTo, Context context) {
        this.dialog = dialog;
        this.pathTo = pathTo;
        this.context = context;
        init();
    }

    private void init() {
        vvvv = IngradContex.getInstance(IngradContex.getAppContext()).getVvvv();
        vvvv2 = IngradContex.getInstance(IngradContex.getAppContext()).getVvvv2();
    }

    //действия при нажатии на кнопку
    private void onClickActionDown(View view) {
        switch (pathTo) {
            case LEFT:
                vvvv.for3d(LEFT);
                vvvv2.for3d(LEFT);
                break;
            case RIGHT:
                vvvv.for3d(RIGHT);
                vvvv2.for3d(RIGHT);
                break;
            case UP:
                vvvv.for3d(UP);
                vvvv2.for3d(UP);
                break;
            case DOWN:
                vvvv.for3d(DOWN);
                vvvv2.for3d(DOWN);
                break;
            case BACK:
                vvvv.for3d(BACK);
                vvvv2.for3d(BACK);
                break;
            case FORWARD:
                vvvv.for3d(FORWARD);
                vvvv2.for3d(FORWARD);
                break;
            case HOME:
                vvvv.for3d(HOME);
                vvvv2.for3d(HOME);
                break;
            case CLOSE:
                dialog.dismiss();
                break;
        }
    }
    //действия при отпускании кнопки
    private void onClickActionUp(View view) {
        switch (pathTo) {
            case LEFT:
                vvvv.for3d(EMPTY);
                vvvv2.for3d(EMPTY);
                break;
            case RIGHT:
                vvvv.for3d(EMPTY);
                vvvv2.for3d(EMPTY);
                break;
            case UP:
                vvvv.for3d(EMPTY);
                vvvv2.for3d(EMPTY);
                break;
            case DOWN:
                vvvv.for3d(EMPTY);
                vvvv2.for3d(EMPTY);
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int image = EMPTY;
        int imagePressed = EMPTY;
        switch (pathTo) {
            case LEFT:
                image = R.drawable.left;
                imagePressed = R.drawable.left_pressed;
                break;
            case RIGHT:
                image = R.drawable.right;
                imagePressed = R.drawable.right_pressed;
                break;
            case UP:
                image = R.drawable.up;
                imagePressed = R.drawable.up_pressed;
                break;
            case DOWN:
                image = R.drawable.down;
                imagePressed = R.drawable.down_pressed;
                break;
            case BACK:
                image = R.drawable.button_back_3d;
                imagePressed = R.drawable.button_back_3d_pressed;
                break;
            case FORWARD:
                image = R.drawable.button_forward_3d;
                imagePressed = R.drawable.button_forward_3d_pressed;
                break;
            case HOME:
                image = R.drawable.home;
                imagePressed = R.drawable.home_pressed;
                break;
        }
        switch (event.getAction()) {
            //Нажатие
            case MotionEvent.ACTION_DOWN:
                button.setImageResource(imagePressed);
                onClickActionDown(view);
                break;
            //Отпускание
            case MotionEvent.ACTION_UP:
                button.setImageResource(image);
                onClickActionUp(view);
                break;
        }
        return true;
    }
}
