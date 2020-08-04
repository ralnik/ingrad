package ru.ralnik.ingrad.activity.gallary;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import ru.ralnik.ingrad.httpPlayer.HttpPlayerFactory;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;
import ru.ralnik.ingrad.R;

public class GallaryActivityButtonListener implements ImageView.OnClickListener {
    private static final int RIVERSKY_ARCHITECTURE = 1;
    private static final int RIVERSKY_ENVIRONMENT = 2;
    private static final int RIVERSKY_MOP = 3;
    private static final int RIVERSKY_TOWNHOUSE = 4;
    private static final int RIVERSKY_IMPROVEMENT = 5;
    private static final int FORIVER_IMPROVEMENT = 6;
    private static final int FORIVER_ENVIRONMENT = 7;
    private static final int FORIVER_MOP = 8;

    private static final int PAUSE = 0;
    private static final int PLAY = 1;
    private static final int BACK = 2;
    private static final int FORWARD = 3;

    private final Context context;
    private PlayerCommands vvvv;

    public GallaryActivityButtonListener(Context context) {
        this.context = context;
        vvvv = HttpPlayerFactory.getInstance(context).getCommand();
    }

    @Override
    public void onClick(View view) {
//        buttonRiverskyArchitecture
//        buttonRiverskyImprovement
//        buttonRiverskyMop
//        buttonRiverskyTownhouse
//        buttonForiverArchitecture
//        buttonForiverImprovement
//        buttonForiverEnvironment
//        buttonForiverMop
        switch (view.getId()) {
            case R.id.buttonRiverskyArchitecture:
                vvvv.showGallary(RIVERSKY_ARCHITECTURE);
                break;
            case R.id.buttonRiverskyEnvironment:
                vvvv.showGallary(RIVERSKY_ENVIRONMENT);
                break;
            case R.id.buttonRiverskyMop:
                vvvv.showGallary(RIVERSKY_MOP);
                break;
            case R.id.buttonRiverskyImprovement:
                vvvv.showGallary(RIVERSKY_IMPROVEMENT);
                break;
            case R.id.buttonRiverskyTownhouse:
                vvvv.showGallary(RIVERSKY_TOWNHOUSE);
                break;
            case R.id.buttonForiverImprovement:
                vvvv.showGallary(FORIVER_IMPROVEMENT);
                break;
            case R.id.buttonForiverEnvironment:
                vvvv.showGallary(FORIVER_ENVIRONMENT);
                break;
            case R.id.buttonForiverMop:
                vvvv.showGallary(FORIVER_MOP);
                break;
            case R.id.buttonPlayPause:
                vvvv.gallaryControl(PLAY);
                break;
            case R.id.buttonForward:
                vvvv.gallaryControl(FORWARD);
                break;
            case R.id.buttonBack:
                vvvv.gallaryControl(BACK);
                break;
        }
    }
}
