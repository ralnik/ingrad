package ru.ralnik.ingrad.activity.gallary;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ru.ralnik.ingrad.config.myConfig;
import ru.ralnik.ingrad.context.IngradContex;
import ru.ralnik.ingrad.httpPlayer.HttpPlayerFactory;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;
import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.httpPlayer.VVVVPlayer;

public class GallaryActivityButtonListener {
    private static final int RIVERSKY_ARCHITECTURE = 1;
    private static final int RIVERSKY_ENVIRONMENT = 2;
    private static final int RIVERSKY_MOP = 3;
    private static final int RIVERSKY_TOWNHOUSE = 4;
    private static final int RIVERSKY_IMPROVEMENT = 5;
    private static final int FORIVER_ARCHITECTURE = 6;
    private static final int FORIVER_IMPROVEMENT = 7;
    private static final int FORIVER_ENVIRONMENT = 8;
    private static final int FORIVER_MOP = 9;

    private static final int REMONT_MODERN = 10;
    private static final int REMONT_CLASSIC = 11;
    private static final int REMONT_ARTDECO = 12;

    private static final int PAUSE = 0;
    private static final int PLAY = 1;
    private static final int BACK_ENABLE = 1;
    private static final int BACK_DISABLE = 0;
    private static final int FORWARD_ENABLE = 1;
    private static final int FORWARD_DISABLE = 0;

    public static final int GALLARY_MEDIA_BUTTONS = 1;
    public static final int GALLARY_BUTTONS = 2;

    @Setter
    private List<ImageView> gallaryButtons;
    @Setter
    private List<ImageView> gallaryMediaButtons;
    @Getter
    ImageView.OnClickListener buttonsMediaOnClick;
    @Getter
    ImageView.OnClickListener buttonsOnClick;

    private ImageView buttonRiverskyArchitecture;
    private ImageView buttonRiverskyEnvironment;
    private ImageView buttonRiverskyImprovement;
    private ImageView buttonRiverskyMop;
    private ImageView buttonRiverskyTownhouse;

    private ImageView buttonForiverArchitecture;
    private ImageView buttonForiverEnvironment;
    private ImageView buttonForiverImprovement;
    private ImageView buttonForiverMop;

    private ImageView buttonRemontModern;
    private ImageView buttonRemontClassic;
    private ImageView buttonRemontArtDeco;

    private ImageView buttonBack;
    private ImageView buttonForward;
    private ImageView buttonPlayPause;


    private Context context;
    private PlayerCommands vvvv;
    private PlayerCommands vvvv2;

    public GallaryActivityButtonListener(Context context) {
        this.context = context;
        vvvv =  IngradContex.getInstance(context).getVvvv();
        vvvv2 = IngradContex.getInstance(context).getVvvv2();
        //vvvv = new VVVVPlayer(cfg.getHost1());
        //vvvv2 = new VVVVPlayer(cfg.getHost2());
    }

    public void initMediaButtons() {
        for (ImageView button : gallaryMediaButtons) {
            if (button.getId() == R.id.buttonBack) buttonBack = button;
            if (button.getId() == R.id.buttonForward) buttonForward = button;
            if (button.getId() == R.id.buttonPlayPause) buttonPlayPause = button;
        }
    }

    public void initButtons() {
        for (ImageView button : gallaryButtons) {
            if (button.getId() == R.id.buttonRiverskyArchitecture) buttonRiverskyArchitecture = button;
            if (button.getId() == R.id.buttonRiverskyImprovement) buttonRiverskyImprovement = button;
            if (button.getId() == R.id.buttonRiverskyEnvironment) buttonRiverskyEnvironment = button;
            if (button.getId() == R.id.buttonRiverskyMop) buttonRiverskyMop = button;
            if (button.getId() == R.id.buttonRiverskyTownhouse) buttonRiverskyTownhouse = button;

            if (button.getId() == R.id.buttonForiverArchitecture) buttonForiverArchitecture = button;
            if (button.getId() == R.id.buttonForiverImprovement) buttonForiverImprovement = button;
            if (button.getId() == R.id.buttonForiverEnvironment) buttonForiverEnvironment = button;
            if (button.getId() == R.id.buttonForiverMop) buttonForiverMop = button;

            if (button.getId() == R.id.buttonRemontModern) buttonRemontModern = button;
            if (button.getId() == R.id.buttonRemontClassic) buttonRemontClassic = button;
            if (button.getId() == R.id.buttonRemontArtDeco) buttonRemontArtDeco = button;
        }
    }

    public ImageView.OnClickListener getButtonsOnClick(int gallaryButtons) {
        switch (gallaryButtons) {
            case GALLARY_MEDIA_BUTTONS:
                return new GallaryMediaButtonsOnClick();
            case GALLARY_BUTTONS:
                return new GallaryButtonsOnCLick();
            default: return null;
        }
    }

    private class GallaryMediaButtonsOnClick implements ImageView.OnClickListener {
        @Override
        public void onClick(View view) {
            ImageView button = (ImageView) view;
            switch (view.getId()) {
                case R.id.buttonPlayPause:
                    if ((Integer) button.getTag() == 0) {
                        vvvv.gallaryPlay(PLAY);
                        vvvv2.gallaryPlay(PLAY);
                        button.setImageResource(R.drawable.pause);
                        button.setTag(1);
                    } else {
                        vvvv.gallaryPlay(PAUSE);
                        vvvv2.gallaryPlay(PAUSE);
                        button.setImageResource(R.drawable.play);
                        button.setTag(0);
                    }
                    break;
                case R.id.buttonForward:
                    if ((Integer) button.getTag() == 0) {
                        vvvv.gallaryForward(FORWARD_ENABLE);
                        vvvv2.gallaryForward(FORWARD_ENABLE);
                        button.setTag(1);
                    } else {
                        vvvv.gallaryForward(FORWARD_DISABLE);
                        vvvv2.gallaryForward(FORWARD_DISABLE);
                        button.setTag(0);
                    }
                    break;
                case R.id.buttonBack:
                    if ((Integer) button.getTag() == 0) {
                        vvvv.gallaryBack(BACK_ENABLE);
                        vvvv2.gallaryBack(BACK_ENABLE);
                        button.setTag(1);
                    } else {
                        vvvv.gallaryBack(BACK_DISABLE);
                        vvvv2.gallaryBack(BACK_DISABLE);
                        button.setTag(0);
                    }
                    break;
            }
        }
    }

    private class GallaryButtonsOnCLick implements ImageView.OnClickListener {
        @Override
        public void onClick(View view) {
            ImageView button = (ImageView) view;
            buttonRiverskyArchitecture.setImageResource(R.drawable.riversky_architecture);
            buttonRiverskyEnvironment.setImageResource(R.drawable.riversky_environment);
            buttonRiverskyImprovement.setImageResource(R.drawable.riversky_improvement);
            buttonRiverskyMop.setImageResource(R.drawable.riversky_mop);
            buttonRiverskyTownhouse.setImageResource(R.drawable.riversky_townhouses);
            buttonForiverArchitecture.setImageResource(R.drawable.foriver_architecture);
            buttonForiverEnvironment.setImageResource(R.drawable.foriver_environment);
            buttonForiverImprovement.setImageResource(R.drawable.foriver_improvement);
            buttonForiverMop.setImageResource(R.drawable.foriver_mop);
            buttonRemontModern.setImageResource(R.drawable.remont_modern);
            buttonRemontClassic.setImageResource(R.drawable.remont_classic);
            buttonRemontArtDeco.setImageResource(R.drawable.remont_artdeco);
            switch (view.getId()) {
                case R.id.buttonRiverskyArchitecture:
                    vvvv.showGallary(RIVERSKY_ARCHITECTURE);
                    vvvv2.showGallary(RIVERSKY_ARCHITECTURE);
                    button.setImageResource(R.drawable.riversky_architecture_down);
                    break;
                case R.id.buttonRiverskyEnvironment:
                    vvvv.showGallary(RIVERSKY_ENVIRONMENT);
                    vvvv2.showGallary(RIVERSKY_ENVIRONMENT);
                    button.setImageResource(R.drawable.riversky_environment_down);
                    break;
                case R.id.buttonRiverskyMop:
                    vvvv.showGallary(RIVERSKY_MOP);
                    vvvv2.showGallary(RIVERSKY_MOP);
                    button.setImageResource(R.drawable.riversky_mop_down);
                    break;
                case R.id.buttonRiverskyImprovement:
                    vvvv.showGallary(RIVERSKY_IMPROVEMENT);
                    vvvv2.showGallary(RIVERSKY_IMPROVEMENT);
                    button.setImageResource(R.drawable.riversky_improvement_down);
                    break;
                case R.id.buttonRiverskyTownhouse:
                    vvvv.showGallary(RIVERSKY_TOWNHOUSE);
                    vvvv2.showGallary(RIVERSKY_TOWNHOUSE);
                    button.setImageResource(R.drawable.riversky_townhouses_down);
                    break;
                case R.id.buttonForiverArchitecture:
                    vvvv.showGallary(FORIVER_ARCHITECTURE);
                    vvvv2.showGallary(FORIVER_ARCHITECTURE);
                    button.setImageResource(R.drawable.foriver_architecture_down);
                    break;
                case R.id.buttonForiverImprovement:
                    vvvv.showGallary(FORIVER_IMPROVEMENT);
                    vvvv2.showGallary(FORIVER_IMPROVEMENT);
                    button.setImageResource(R.drawable.foriver_improvement_down);
                    break;
                case R.id.buttonForiverEnvironment:
                    vvvv.showGallary(FORIVER_ENVIRONMENT);
                    vvvv2.showGallary(FORIVER_ENVIRONMENT);
                    button.setImageResource(R.drawable.foriver_environment_down);
                    break;
                case R.id.buttonForiverMop:
                    vvvv.showGallary(FORIVER_MOP);
                    vvvv2.showGallary(FORIVER_MOP);
                    button.setImageResource(R.drawable.foriver_mop_down);
                    break;
                case R.id.buttonRemontModern:
                    vvvv.showGallary(REMONT_MODERN);
                    vvvv2.showGallary(REMONT_MODERN);
                    button.setImageResource(R.drawable.remont_modern_down);
                    break;
                case R.id.buttonRemontClassic:
                    vvvv.showGallary(REMONT_CLASSIC);
                    vvvv2.showGallary(REMONT_CLASSIC);
                    button.setImageResource(R.drawable.remont_classic_down);
                    break;
                case R.id.buttonRemontArtDeco:
                    vvvv.showGallary(REMONT_ARTDECO);
                    vvvv2.showGallary(REMONT_ARTDECO);
                    button.setImageResource(R.drawable.remont_artdeco_down);
                    break;
            }
        }
    }
}
