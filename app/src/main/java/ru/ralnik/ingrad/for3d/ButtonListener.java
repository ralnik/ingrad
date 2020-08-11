package ru.ralnik.ingrad.for3d;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import ru.ralnik.ingrad.context.IngradContex;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;

public class ButtonListener implements ImageView.OnTouchListener {
    public final static int LEFT = 1;
    public final static int RIGHT = 2;
    public final static int UP = 3;
    public final static int DOWN = 4;
    public final static int CLOSE = 5;
    private final static int EMPTY = 0;

    private int pathTo;
    private Dialog dialog;
    private Context context;

    private PlayerCommands vvvv;
    private PlayerCommands vvvv2;

    public ButtonListener(int pathTo, Context context){
        this.pathTo = pathTo;
        this.context = context;
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
        switch (event.getAction()) {
            //Нажатие
            case MotionEvent.ACTION_DOWN:
                onClickActionDown(view);
                break;
            //Отпускание
            case MotionEvent.ACTION_UP:
                onClickActionUp(view);
                break;
        }
        return true;
    }
}
