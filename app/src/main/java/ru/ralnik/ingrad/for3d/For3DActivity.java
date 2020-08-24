package ru.ralnik.ingrad.for3d;

import android.app.Activity;
import android.app.Dialog;

import androidx.appcompat.app.AlertDialog;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import ru.ralnik.clickablebutton.ClickableButton;
import ru.ralnik.clickablebutton.ClickableButtonOnClickListener;
import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.activity.DialogButtonListener;
import ru.ralnik.ingrad.context.IngradContex;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;

public class For3DActivity {
    private final Activity activity;
    private View rootView;
    private PlayerCommands vvvv;
    private PlayerCommands vvvv2;

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

    @BindView(R.id.buttonBack) ImageView buttonBack;
    @BindView(R.id.buttonForward) ImageView buttonForward;
    @BindView(R.id.buttonHome) ImageView buttonHome;

    @BindView(R.id.buttonLeftHidden) FrameLayout buttonLeftHidden;
    @BindView(R.id.buttonRightHidden) FrameLayout buttonRightHidden;
    @BindView(R.id.buttonUpHidden) FrameLayout buttonUpHidden;
    @BindView(R.id.buttonDownHidden) FrameLayout buttonDownHidden;

    @BindViews({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7
            , R.id.btn8, R.id.btn9, R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14
            , R.id.btn15, R.id.btn16, R.id.btn17, R.id.btn18, R.id.btn19, R.id.btn20, R.id.btn21
            , R.id.btn22, R.id.btn23, R.id.btn24, R.id.btn25, R.id.btn26, R.id.btn27, R.id.btn28
            , R.id.btn29, R.id.btn30, R.id.btn31, R.id.btn32, R.id.btn33, R.id.btn34, R.id.btn35
            , R.id.btn36, R.id.btn37, R.id.btn38, R.id.btn39}) List<ClickableButton> listButtons3d;

    public For3DActivity(Activity activity) {
        this.activity = activity;
        rootView = activity.getLayoutInflater().inflate(R.layout.activity_for3d, null);
        ButterKnife.bind(this,rootView);
        vvvv = IngradContex.getInstance(IngradContex.getAppContext()).getVvvv();
        vvvv2 = IngradContex.getInstance(IngradContex.getAppContext()).getVvvv2();

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
        buttonDownHidden.setOnTouchListener(new ButtonListener(ButtonListener.DOWN, activity.getApplicationContext(), button_down));
        buttonUpHidden.setOnTouchListener(new ButtonListener(ButtonListener.UP, activity.getApplicationContext(), button_up));
        buttonLeftHidden.setOnTouchListener(new ButtonListener(ButtonListener.LEFT, activity.getApplicationContext(), button_left));
        buttonRightHidden.setOnTouchListener(new ButtonListener(ButtonListener.RIGHT, activity.getApplicationContext(), button_right));

        buttonBack.setOnTouchListener(new ButtonListener(ButtonListener.BACK, activity.getApplicationContext(), buttonBack));
        buttonForward.setOnTouchListener(new ButtonListener(ButtonListener.FORWARD, activity.getApplicationContext(), buttonForward));
        buttonHome.setOnTouchListener(new ButtonListener(ButtonListener.HOME, activity.getApplicationContext(), buttonHome));

        int index = 1;
        for(ClickableButton button : listButtons3d) {
            button.setTag(index);
            button.setOnDemonstrationButtonClickListener(new ClickableButtonOnClickListener() {
                @Override
                public void onClick(View view) {
                   for (ClickableButton buttonTemp : listButtons3d) {
                       buttonTemp.setStatus(false);
                   }
                   vvvv.for3dVideoTrack((Integer) button.getTag());
                   vvvv2.for3dVideoTrack((Integer) button.getTag());
                   button.setStatus(true);
                }
            });
            index++;
        }
    }
}