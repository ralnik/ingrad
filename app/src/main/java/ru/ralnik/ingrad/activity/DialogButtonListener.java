package ru.ralnik.ingrad.activity;

import android.app.Dialog;
import android.view.View;

import ru.ralnik.ingrad.R;
/**
 * Класс для событий на кнопки диалога
 * 11.08.2020
 * ralni@mail.ru
 * */
public class DialogButtonListener implements View.OnClickListener {
    public static final int BUTTON_CLOSE = 1;
    private Dialog dialog;
    private int button;

    public DialogButtonListener(int button){
        this.button = button;
    }

    public DialogButtonListener(Dialog dialog, int button) {
        this.dialog = dialog;
        this.button = button;
    }

    @Override
    public void onClick(View view) {
        switch (button) {
            case BUTTON_CLOSE:
                dialog.dismiss();
                break;
        }
    }
}
