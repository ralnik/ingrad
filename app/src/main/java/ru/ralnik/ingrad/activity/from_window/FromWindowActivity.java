package ru.ralnik.ingrad.activity.from_window;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import ru.ralnik.clickablebutton.ClickableButton;
import ru.ralnik.ingrad.GlobalVars;
import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.activity.DialogButtonListener;
import ru.ralnik.ingrad.context.IngradContex;
import ru.ralnik.ingrad.enums.TypeGK;
import ru.ralnik.ingrad.for3d.ButtonListener;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;

public class FromWindowActivity {
    private final static int FORIVER1 = 1;
    private final static int FORIVER2 = 2;
    private final static int FORIVER3 = 7;
    private final static int FORIVER4 = 3;
    private final static int FORIVER5 = 4;
    private final static int FORIVER6 = 6;
    //private final static int FORIVER_EMPTY = 0;

    private final static int RIVERSKY1 = 11;
    private final static int RIVERSKY2 = 12;
    private final static int RIVERSKY3 = 13;
    private final static int RIVERSKY4 = 14;
    private final static int RIVERSKY5 =15;
   //private final static int RIVERSKY_EMPTY = 0;

    private final static int FLOOR5 = 5;
    private final static int FLOOR6 = 6;
    private final static int FLOOR10 = 10;
    private final static int FLOOR12 = 12;
    private final static int FLOOR15 = 15;
    private final static int FLOOR16 = 16;
    private final static int FLOOR17 = 17;
    private final static int FLOOR19 = 19;
    private final static int FLOOR20 = 20;
    private final static int FLOOR21 = 21;
    private final static int FLOOR25 = 25;
    private final static int FLOOR27 = 27;
    private final static int FLOOR29 = 29;
    private final static int FLOOR_EMPTY = 0;


    private final Activity activity;
    private final Context context;
    private final View rootView;
    private PlayerCommands vvvv;
    private PlayerCommands vvvv2;
    private final Map<Integer, Integer[]> riverskyButtonsSelector = new HashMap<>();
    private final Map<Integer, Integer[]> foriverButtonsSelector = new HashMap<>();
    private int currentFloor = 0;

    @BindView(R.id.button_close)
    ImageView buttonClose;
    @BindView(R.id.switcherPlans)ClickableButton switcherPlans;
    @BindView(R.id.switcherDayNight)ClickableButton switcherDayNight;
    @BindView(R.id.planForiverFromWindowLayout)
    FrameLayout planForiverFromWindowLayout;
    @BindView(R.id.planRiverskyFromWindowLayout)
    FrameLayout planRiverskyFromWindowLayout;
    @BindView(R.id.buttonsForiverLayout)
    ImageView buttonsForiverLayout;
    @BindView(R.id.buttonsRiverskyLayout)
    ImageView buttonsRiverskyLayout;

    @BindViews({R.id.buttonFloor5, R.id.buttonFloor6, R.id.buttonFloor10, R.id.buttonFloor12,
            R.id.buttonFloor15, R.id.buttonFloor16, R.id.buttonFloor17, R.id.buttonFloor19,
            R.id.buttonFloor20, R.id.buttonFloor21, R.id.buttonFloor25, R.id.buttonFloor27,
            R.id.buttonFloor29})
    List<ImageView> buttonFloorList;

    @BindView(R.id.button_up)
    ImageView button_up;
    @BindView(R.id.button_down)
    ImageView button_down;
    @BindView(R.id.button_left)
    ImageView button_left;
    @BindView(R.id.button_right)
    ImageView button_right;

    @BindView(R.id.buttonLeftHidden)
    FrameLayout buttonLeftHidden;
    @BindView(R.id.buttonRightHidden)
    FrameLayout buttonRightHidden;
    @BindView(R.id.buttonUpHidden)
    FrameLayout buttonUpHidden;
    @BindView(R.id.buttonDownHidden)
    FrameLayout buttonDownHidden;

    @BindViews({R.id.hiddenButtonForiverBuild1
            , R.id.hiddenButtonForiverBuild2
            , R.id.hiddenButtonForiverBuild3
            , R.id.hiddenButtonForiverBuild4
            , R.id.hiddenButtonForiverBuild5
            , R.id.hiddenButtonForiverBuild6})
    List<FrameLayout> hiddenForiverButtons;

    @BindViews({R.id.hiddenButtonRiverskyBuild1
            , R.id.hiddenButtonRiverskyBuild2
            , R.id.hiddenButtonRiverskyBuild3
            , R.id.hiddenButtonRiverskyBuild4
            , R.id.hiddenButtonRiverskyBuild5})
    List<FrameLayout> hiddenRiverskyButtons;

    @BindViews({R.id.buttonFloor5
            , R.id.buttonFloor6
            , R.id.buttonFloor10
            , R.id.buttonFloor12
            , R.id.buttonFloor15
            , R.id.buttonFloor16
            , R.id.buttonFloor17
            , R.id.buttonFloor19
            , R.id.buttonFloor20
            , R.id.buttonFloor21
            , R.id.buttonFloor25
            , R.id.buttonFloor27
            , R.id.buttonFloor29})
    List<ImageView> floorButtons;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public FromWindowActivity(Activity activity) {
        this.activity = activity;
        rootView = activity.getLayoutInflater().inflate(R.layout.activity_from_window, null);
        this.context = activity.getApplicationContext();
        ButterKnife.bind(this, rootView);
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
        buttonClose.setOnClickListener(new DialogButtonListener(dialog, DialogButtonListener.BUTTON_CLOSE));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        vvvv = IngradContex.getInstance(IngradContex.getAppContext()).getVvvv();
        vvvv2 = IngradContex.getInstance(IngradContex.getAppContext()).getVvvv2();

        //корпуса 7 и 8
        riverskyButtonsSelector.put(RIVERSKY1, new Integer[]{5, 10, 16, 20, 27});
        //корпуса 4 и 5
        riverskyButtonsSelector.put(RIVERSKY2, new Integer[]{6, 12, 15, 20, 25, 29});
        //корпус 1
        riverskyButtonsSelector.put(RIVERSKY3, new Integer[]{5, 10, 15, 20, 25, 29});
        //корпуса 6
        riverskyButtonsSelector.put(RIVERSKY4, new Integer[]{5, 10, 15, 21});
        //корпуса 2 и 3
        riverskyButtonsSelector.put(RIVERSKY5, new Integer[]{5, 10, 17, 20, 27});
        //формирование кнопок для разных корпусов
        //корпус 10;
        foriverButtonsSelector.put(FORIVER1, new Integer[]{5, 10, 15, 19});
        //корпус 4;
        foriverButtonsSelector.put(FORIVER2, new Integer[]{5, 10, 15, 19});
        //корпус 9;
        foriverButtonsSelector.put(FORIVER3, new Integer[]{5, 10, 15});
        //корпус 3;
        foriverButtonsSelector.put(FORIVER4, new Integer[]{5, 10, 15});
        //корпус 2;
        foriverButtonsSelector.put(FORIVER5, new Integer[]{5, 10, 15});
        //корпус 1;
        foriverButtonsSelector.put(FORIVER6, new Integer[]{5, 10, 15, 19});

        //Делаем кнопки по умолчанию скрытими
        visibleButtonsFloor(foriverButtonsSelector, floorButtons, 0);

        switcherPlans.setStatus(false);
        switcherPlans.setOnDemonstrationButtonClickListener(this::switcherOnListener);

        switcherDayNight.setOnDemonstrationButtonClickListener(this::switcherDayNightOnListener);

        planRiverskyFromWindowLayout.setVisibility(View.VISIBLE);
        planForiverFromWindowLayout.setVisibility(View.GONE);

        for (ImageView button : buttonFloorList) {
            button.setOnClickListener(this::buttonFloorListener);
        }

        for (FrameLayout button : hiddenForiverButtons) {
            button.setOnClickListener(this::hiddenButtonForiverListener);
        }

        for (FrameLayout button : hiddenRiverskyButtons) {
            button.setOnClickListener(this::hiddenButtonsRiverskyListener);
        }

        for (ImageView button : floorButtons) {
            button.setOnClickListener(this::floorButtonsListener);
        }

        buttonDownHidden.setOnTouchListener(new FromWindowActivityButtonListener(ButtonListener.DOWN, activity.getApplicationContext(), button_down));
        buttonUpHidden.setOnTouchListener(new FromWindowActivityButtonListener(ButtonListener.UP, activity.getApplicationContext(), button_up));
        buttonLeftHidden.setOnTouchListener(new FromWindowActivityButtonListener(ButtonListener.LEFT, activity.getApplicationContext(), button_left));
        buttonRightHidden.setOnTouchListener(new FromWindowActivityButtonListener(ButtonListener.RIGHT, activity.getApplicationContext(), button_right));
    }

    private void switcherOnListener(View view) {

        if (switcherPlans.getStatus()) {
            planForiverFromWindowLayout.setVisibility(View.VISIBLE);
            planRiverskyFromWindowLayout.setVisibility(View.GONE);
        } else {
            planForiverFromWindowLayout.setVisibility(View.GONE);
            planRiverskyFromWindowLayout.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void switcherDayNightOnListener(View view) {
        if (switcherDayNight.getStatus()) {
            vvvv.actionFloor360(FLOOR_EMPTY);
            vvvv2.actionFloor360(FLOOR_EMPTY);
        } else {
            vvvv.actionFloor360(currentFloor);
            vvvv2.actionFloor360(currentFloor);
        }
    }

    private void buttonFloorListener(View view) {
        for (ImageView button : buttonFloorList) {
            button.setVisibility(View.GONE);
        }
        //Если switcherPlans.status = true - значит foriver включен, иначе riversky
        if (switcherPlans.getStatus()) {
            // здесь идут кнопки для foriver
        } else {
            // здесь идут кнопки для riversky
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void hiddenButtonsRiverskyListener(View view) {
        switch (view.getId()) {
            case R.id.hiddenButtonRiverskyBuild1:
                visibleButtonsFloor(riverskyButtonsSelector, floorButtons, RIVERSKY1);
                buttonsRiverskyLayout.setImageResource(R.drawable.riversky_plan1_from_window);
                vvvv.corpus360(RIVERSKY1);
                Log.d(GlobalVars.DEBUG, "VVVV_corpus: " + RIVERSKY1);
                vvvv2.corpus360(RIVERSKY1);
                Log.d(GlobalVars.DEBUG, "VVVV2_corpus: " + RIVERSKY1);
                break;
            case R.id.hiddenButtonRiverskyBuild2:
                visibleButtonsFloor(riverskyButtonsSelector, floorButtons, RIVERSKY2);
                buttonsRiverskyLayout.setImageResource(R.drawable.riversky_plan2_from_window);
                vvvv.corpus360(RIVERSKY2);
                vvvv2.corpus360(RIVERSKY2);
                break;
            case R.id.hiddenButtonRiverskyBuild3:
                visibleButtonsFloor(riverskyButtonsSelector, floorButtons, RIVERSKY3);
                buttonsRiverskyLayout.setImageResource(R.drawable.riversky_plan3_from_window);
                vvvv.corpus360(RIVERSKY3);
                vvvv2.corpus360(RIVERSKY3);
                break;
            case R.id.hiddenButtonRiverskyBuild4:
                visibleButtonsFloor(riverskyButtonsSelector, floorButtons, RIVERSKY4);
                buttonsRiverskyLayout.setImageResource(R.drawable.riversky_plan4_from_window);
                vvvv.corpus360(RIVERSKY4);
                vvvv2.corpus360(RIVERSKY4);
                break;
            case R.id.hiddenButtonRiverskyBuild5:
                visibleButtonsFloor(riverskyButtonsSelector, floorButtons, RIVERSKY5);
                buttonsRiverskyLayout.setImageResource(R.drawable.riversky_plan5_from_window);
                vvvv.corpus360(RIVERSKY5);
                vvvv2.corpus360(RIVERSKY5);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void hiddenButtonForiverListener(View view) {
        switch (view.getId()) {
            case R.id.hiddenButtonForiverBuild1:
                visibleButtonsFloor(foriverButtonsSelector, floorButtons, FORIVER1);
                buttonsForiverLayout.setImageResource(R.drawable.foriver_plan1_from_window);
                vvvv.corpus360(FORIVER1);
                vvvv2.corpus360(FORIVER1);
                break;
            case R.id.hiddenButtonForiverBuild2:
                visibleButtonsFloor(foriverButtonsSelector, floorButtons, FORIVER2);
                buttonsForiverLayout.setImageResource(R.drawable.foriver_plan2_from_window);
                vvvv.corpus360(FORIVER2);
                vvvv2.corpus360(FORIVER2);
                break;
            case R.id.hiddenButtonForiverBuild3:
                visibleButtonsFloor(foriverButtonsSelector, floorButtons, FORIVER3);
                buttonsForiverLayout.setImageResource(R.drawable.foriver_plan3_from_window);
                vvvv.corpus360(FORIVER3);
                vvvv2.corpus360(FORIVER3);
                break;
            case R.id.hiddenButtonForiverBuild4:
                visibleButtonsFloor(foriverButtonsSelector, floorButtons, FORIVER4);
                buttonsForiverLayout.setImageResource(R.drawable.foriver_plan4_from_window);
                vvvv.corpus360(FORIVER4);
                vvvv2.corpus360(FORIVER4);
                break;
            case R.id.hiddenButtonForiverBuild5:
                visibleButtonsFloor(foriverButtonsSelector, floorButtons, FORIVER5);
                buttonsForiverLayout.setImageResource(R.drawable.foriver_plan5_from_window);
                vvvv.corpus360(FORIVER5);
                vvvv2.corpus360(FORIVER5);
                break;
            case R.id.hiddenButtonForiverBuild6:
                visibleButtonsFloor(foriverButtonsSelector, floorButtons, FORIVER6);
                buttonsForiverLayout.setImageResource(R.drawable.foriver_plan6_from_window);
                vvvv.corpus360(FORIVER6);
                vvvv2.corpus360(FORIVER6);
                break;
        }
    }

    private void floorButtonsListener(View view) {
        ImageView tempButton = null;
        for (ImageView button : floorButtons) {
            if (button.equals((ImageView) view)) {
                tempButton = button;
            }
            if (button.getId() == R.id.buttonFloor5) button.setImageResource(R.drawable.btn_floor5);
            if (button.getId() == R.id.buttonFloor6) button.setImageResource(R.drawable.btn_floor6);
            if (button.getId() == R.id.buttonFloor10)
                button.setImageResource(R.drawable.btn_floor10);
            if (button.getId() == R.id.buttonFloor12)
                button.setImageResource(R.drawable.btn_floor12);
            if (button.getId() == R.id.buttonFloor15)
                button.setImageResource(R.drawable.btn_floor15);
            if (button.getId() == R.id.buttonFloor16)
                button.setImageResource(R.drawable.btn_floor16);
            if (button.getId() == R.id.buttonFloor17)
                button.setImageResource(R.drawable.btn_floor17);
            if (button.getId() == R.id.buttonFloor19)
                button.setImageResource(R.drawable.btn_floor19);
            if (button.getId() == R.id.buttonFloor20)
                button.setImageResource(R.drawable.btn_floor20);
            if (button.getId() == R.id.buttonFloor21)
                button.setImageResource(R.drawable.btn_floor21);
            if (button.getId() == R.id.buttonFloor25)
                button.setImageResource(R.drawable.btn_floor25);
            if (button.getId() == R.id.buttonFloor27)
                button.setImageResource(R.drawable.btn_floor27);
            if (button.getId() == R.id.buttonFloor29)
                button.setImageResource(R.drawable.btn_floor29);
        }

        switch (view.getId()) {
            case R.id.buttonFloor5:
                vvvv.actionFloor360(FLOOR5);
                vvvv2.actionFloor360(FLOOR5);
                tempButton.setImageResource(R.drawable.btn_floor5_down);
                break;
            case R.id.buttonFloor6:
                vvvv.actionFloor360(FLOOR6);
                vvvv2.actionFloor360(FLOOR6);
                tempButton.setImageResource(R.drawable.btn_floor6_down);
                break;
            case R.id.buttonFloor10:
                vvvv.actionFloor360(FLOOR10);
                vvvv2.actionFloor360(FLOOR10);
                tempButton.setImageResource(R.drawable.btn_floor10_down);
                break;
            case R.id.buttonFloor12:
                vvvv.actionFloor360(FLOOR12);
                vvvv2.actionFloor360(FLOOR12);
                tempButton.setImageResource(R.drawable.btn_floor12_down);
                break;
            case R.id.buttonFloor15:
                vvvv.actionFloor360(FLOOR15);
                vvvv2.actionFloor360(FLOOR15);
                tempButton.setImageResource(R.drawable.btn_floor15_down);
                break;
            case R.id.buttonFloor16:
                vvvv.actionFloor360(FLOOR16);
                vvvv2.actionFloor360(FLOOR16);
                tempButton.setImageResource(R.drawable.btn_floor16_down);
                break;
            case R.id.buttonFloor17:
                vvvv.actionFloor360(FLOOR17);
                vvvv2.actionFloor360(FLOOR17);
                tempButton.setImageResource(R.drawable.btn_floor17_down);
                break;
            case R.id.buttonFloor19:
                vvvv.actionFloor360(FLOOR19);
                vvvv2.actionFloor360(FLOOR19);
                tempButton.setImageResource(R.drawable.btn_floor19_down);
                break;
            case R.id.buttonFloor20:
                vvvv.actionFloor360(FLOOR20);
                vvvv2.actionFloor360(FLOOR20);
                tempButton.setImageResource(R.drawable.btn_floor20_down);
                break;
            case R.id.buttonFloor21:
                vvvv.actionFloor360(FLOOR21);
                vvvv2.actionFloor360(FLOOR21);
                tempButton.setImageResource(R.drawable.btn_floor21_down);
                break;
            case R.id.buttonFloor25:
                vvvv.actionFloor360(FLOOR25);
                vvvv2.actionFloor360(FLOOR25);
                tempButton.setImageResource(R.drawable.btn_floor25_down);
                break;
            case R.id.buttonFloor27:
                vvvv.actionFloor360(FLOOR27);
                vvvv2.actionFloor360(FLOOR27);
                tempButton.setImageResource(R.drawable.btn_floor27_down);
                break;
            case R.id.buttonFloor29:
                vvvv.actionFloor360(FLOOR29);
                vvvv2.actionFloor360(FLOOR29);
                tempButton.setImageResource(R.drawable.btn_floor29_down);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int getMaxFloor(Map<Integer, Integer[]> map, Integer corpus) {
        Integer[] buildings = map.get((Integer) corpus);

        //устанавливаем максимальное значение этажа
        int maxFloor = 0;
        if (buildings != null) {
            maxFloor = Arrays.stream(buildings)
                    .max(Comparator.comparing(Integer::valueOf))
                    .orElse(0);
        }
        return maxFloor;
    }

    /**
     * Отображение кнопок с номером этажа в зависимости от выбора корпуса на плане
     * @param map - правило, какие кнопки для какого корпуса
     * @param buttons - список кнопок с номером этажа
     * @param corpus - номер корпуса
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void visibleButtonsFloor(Map<Integer, Integer[]> map, List<ImageView> buttons, Integer corpus) {
        Integer[] buildings = map.get((Integer) corpus);

        //устанавливаем максимальное значение этажа
        int maxFloor = 0;
        if (buildings != null) {
            maxFloor = Arrays.stream(buildings)
                    .max(Comparator.comparing(Integer::valueOf))
                    .orElse(0);
        }
        currentFloor = maxFloor;
        vvvv.setActionFloor360(maxFloor);
        vvvv2.setActionFloor360(maxFloor);

        for (ImageView button : buttons) {
            if (buildings == null) {
                button.setVisibility(View.GONE);
            } else if (Arrays.stream(buildings).anyMatch(x -> x.equals(Integer.valueOf((String) button.getTag())))) {
                button.setVisibility(View.VISIBLE);
            } else {
                button.setVisibility(View.GONE);
            }
        }
    }
}
