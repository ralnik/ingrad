package ru.ralnik.ingrad.camera;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;

public class CamerasButtonOnClick implements ImageView.OnClickListener {
    /** types of button parameters*/
    public final static int CLOSE = 5;

    private int buttonParametr;
    private Dialog dialog;

    public CamerasButtonOnClick(int buttonParametr) {
        this.buttonParametr = buttonParametr;
    }

    public CamerasButtonOnClick(Dialog dialog, int buttonParametr) {
        this.dialog = dialog;
        this.buttonParametr = buttonParametr;
    }

    @Override
    public void onClick(View view) {
        switch (buttonParametr) {
            case CLOSE:
                dialog.dismiss();
        }
    }
}
