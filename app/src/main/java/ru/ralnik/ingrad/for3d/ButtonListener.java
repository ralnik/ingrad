package ru.ralnik.ingrad.for3d;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;

public class ButtonListener implements ImageView.OnClickListener {
    public final static int LEFT = 1;
    public final static int RIGHT = 2;
    public final static int UP = 3;
    public final static int DOWN = 4;
    public final static int CLOSE = 5;

    private int pathTo;
    private Dialog dialog;

    public ButtonListener(int pathTo){
        this.pathTo = pathTo;
    }

    public ButtonListener(Dialog dialog, int pathTo) {
        this.dialog = dialog;
        this.pathTo = pathTo;
    }

    @Override
    public void onClick(View view) {
        switch (pathTo) {
            case LEFT:
                break;
            case RIGHT:
                break;
            case UP:
                break;
            case DOWN:
                break;
            case CLOSE:
                dialog.dismiss();
                break;
        }
    }
}
