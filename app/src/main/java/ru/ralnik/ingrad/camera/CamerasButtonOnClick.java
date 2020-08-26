package ru.ralnik.ingrad.camera;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import ru.ralnik.clickablebutton.ClickableButton;
import ru.ralnik.clickablebutton.ClickableButtonOnClickListener;
import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.context.IngradContex;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;

public class CamerasButtonOnClick implements ClickableButtonOnClickListener {
    private List<ClickableButton> buttonCameraList;
    private PlayerCommands vvvv;
    private PlayerCommands vvvv2;

    public CamerasButtonOnClick(List<ClickableButton> buttonCameraList) {
        this.buttonCameraList = buttonCameraList;
        vvvv = IngradContex.getInstance(IngradContex.getAppContext()).getVvvv();
        vvvv2 = IngradContex.getInstance(IngradContex.getAppContext()).getVvvv2();
    }

    @Override
    public void onClick(View view) {
        for (ClickableButton button : buttonCameraList) {
            if (button.equals((ClickableButton) view)) {
                button.setStatus(true);
            } else {
                button.setStatus(false);
            }
        }
        switch (view.getId()) {
            case R.id.btnCamera1:
                vvvv.setCameraTrack(1);
                vvvv2.setCameraTrack(1);
                break;
            case R.id.btnCamera2:
                vvvv.setCameraTrack(2);
                vvvv2.setCameraTrack(2);
                break;
            case R.id.btnCamera3:
                vvvv.setCameraTrack(3);
                vvvv2.setCameraTrack(3);
                break;
        }
    }
}
