package ru.ralnik.ingrad;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.ralnik.ingrad.camera.CameraActivity;
import ru.ralnik.ingrad.config.myConfig;
import ru.ralnik.ingrad.customListView.listviewItemSelected;
import ru.ralnik.ingrad.customListView.myAdapter;
import ru.ralnik.ingrad.for3d.For3DActivity;
import ru.ralnik.ingrad.httpPlayer.HttpPlayerFactory;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;
import ru.ralnik.ingrad.permissions.MyPermissions;
import ru.ralnik.ingrad.sqlitedb.FlatRepository;

public class MainActivity extends AppCompatActivity {
//---------MAIN LAYOUT----------
    @BindView(R.id.leftPanel)LinearLayout leftPanel;
    @BindView(R.id.scrollViewRight)ScrollView scrollViewRight;
    @BindView(R.id.resultPanel)LinearLayout resultPanel;
    @BindView(R.id.settingsPanel)LinearLayout settingsPanel;

//---------Components------------
  //------leftPanel----------------
    @BindView(R.id.btnFull) ImageView btnFull;
    @BindView(R.id.btnLocation) ImageView btnLocation;
    @BindView(R.id.btnAbout) ImageView btnAbout;
    @BindView(R.id.btnTransport) ImageView btnTransport;
    @BindView(R.id.btnArea) ImageView btnArea;
    @BindView(R.id.btnNaturals) ImageView btnNaturales;
    @BindView(R.id.btnInfra) ImageView btnInfra;
    @BindView(R.id.btnOutsideInfra) ImageView btnOutsideInfra;
    @BindView(R.id.btnTorpedo) ImageView btnTorpedo;
    @BindView(R.id.btnSurround) ImageView btnSurround;
    @BindView(R.id.btnAdvantageProject) ImageView btnAdvantageProject;
    @BindView(R.id.btnGallary) ImageView btnGallary;
    @BindView(R.id.btnProcessBuildings) ImageView btnProcessBuildings;
    @BindView(R.id.btnAdvantageRiversky)ImageView btnAdvantageRiversky;
    @BindView(R.id.btnAdvantageForiver)ImageView btnAdvantageForiver;
    @BindView(R.id.btn3d) ImageView btn3d;
    @BindView(R.id.btnCamera) ImageView btnCamera;
  //---------Control panel-------------
    @BindView(R.id.btnPlayPause)ImageView btnPlayPause;
    @BindView(R.id.btnOptions)ImageView btnOptions;
    @BindView(R.id.btnVolume)ImageView btnVolume;
    @BindView(R.id.btnRiverSky)ImageView btnRiverSky;
    @BindView(R.id.btnFoRiver)ImageView btnFoRiver;
    @BindView(R.id.btnRTypePlan)ImageView btnTypePlan;

  //---------count room buttons-------
    @BindView(R.id.btnRoom1)ImageView btnRoom1;
    @BindView(R.id.btnRoom2)ImageView btnRoom2;
    @BindView(R.id.btnRoom3)ImageView btnRoom3;
    @BindView(R.id.btnRoom4)ImageView btnRoom4;
    @BindView(R.id.btnRoom5)ImageView btnRoom5;
    @BindView(R.id.btnRoomEvro)ImageView btnRoomEvro;

  //----------remont buttons------
    @BindView(R.id.btnWithoutRemont)ImageView btnWithoutRemont;
    @BindView(R.id.btnWithRemont)ImageView btnWithRemont;
    @BindView(R.id.btnWhiteBox)ImageView btnWhiteBox;

  //---------Additional Attributes-----
    @BindView(R.id.btnTownHouse)ImageView btnTownHouse;
    @BindView(R.id.btnPentHouse)ImageView btnPentHouse;
    @BindView(R.id.btnDoubleFloorHouse)ImageView btnDoubleFloorHouse;
    @BindView(R.id.btnTwoEnterHouse)ImageView btnTwoEnterHouse;
    @BindView(R.id.btnTerrasa)ImageView btnTerrasa;
    @BindView(R.id.btnWithFirePlace)ImageView btnWithFirePlace;
    @BindView(R.id.btnBathRoomWithWindow)ImageView btnBathRoomWithWindow;
    @BindView(R.id.btnBalcon)ImageView btnBalcon;

  // ---------- buttons period----------
    @BindView(R.id.btnSrok_4_2021)ImageView check_period_4_2021;
    @BindView(R.id.btnSrok_3_2022)ImageView check_period_3_2022;
    @BindView(R.id.btnSrok_4_2022)ImageView check_period_4_2022;

  //---------Buttons filter---------
    @BindView(R.id.btnClear)ImageView btnClear;
    @BindView(R.id.btnSearch)ImageView btnSearch;

 //--------Controll Settings--------
    private View viewSettingPanel;
    private SeekBar musicSeekBar;
    private SeekBar effectSeekBar;
    private EditText editWaitTime;
    private ImageView switcherTimer;
    private EditText editIP;
    private ImageView btnSave;

 //------- SEEKBAR-------------
    @BindView(R.id.seekBarFloor)CrystalRangeSeekbar seekbarFloor;
    @BindView(R.id.seekBarCost)CrystalRangeSeekbar seekbarCost;
    @BindView(R.id.seekBarSquare)CrystalRangeSeekbar seekbarSquare;

    @BindView(R.id.minFloorEdit)EditText minFloorEdit;
    @BindView(R.id.maxFloorEdit)EditText maxFloorEdit;

    @BindView(R.id.minCostEdit)EditText minCostEdit;
    @BindView(R.id.maxCostEdit)EditText maxCostEdit;

    @BindView(R.id.minSquareEdit)EditText minSquareEdit;
    @BindView(R.id.maxSquareEdit)EditText maxSquareEdit;

 //---------RESULT-----------
    @BindView(R.id.listview)ListView listview;
    @BindView(R.id.textNoRow)TextView textNoRow;

 //---------Column name of listview
    @BindView(R.id.colKorpus)TextView colKorpus;
    @BindView(R.id.colFlat)TextView colFlat;
    @BindView(R.id.colFloor)TextView colFloor;
    @BindView(R.id.colRooms)TextView colRooms;
    @BindView(R.id.colSquare)TextView colSquare;
    @BindView(R.id.colCost)TextView colCost;
    @BindView(R.id.colOther)TextView colOther;

  // -------BUILDs-------------

    @BindView(R.id.build_3_1)ImageView build_3_1;
    @BindView(R.id.build_3_2)ImageView build_3_2;
    @BindView(R.id.build_4)ImageView build_4;
    @BindView(R.id.build_5)ImageView build_5;
    @BindView(R.id.build_6)ImageView build_6;
    @BindView(R.id.build_7)ImageView build_7;
    @BindView(R.id.build_8)ImageView build_8;
    @BindView(R.id.build_9)ImageView build_9;
    @BindView(R.id.build_10)ImageView build_10;
    @BindView(R.id.build_11)ImageView build_11;
    @BindView(R.id.build_12)ImageView build_12;
    @BindView(R.id.build_13)ImageView build_13;
    @BindView(R.id.build_14)ImageView build_14;
    @BindView(R.id.build_15)ImageView build_15;
    @BindView(R.id.build_16)ImageView build_16;
    @BindView(R.id.build_17)ImageView build_17;
    @BindView(R.id.build_18)ImageView build_18;
    @BindView(R.id.build_19)ImageView build_19;
    @BindView(R.id.build_20)ImageView build_20;
    @BindView(R.id.build_21_1)ImageView build_21_1;
    @BindView(R.id.build_21_2)ImageView build_21_2;
    @BindView(R.id.build_21_3)ImageView build_21_3;
    @BindView(R.id.build_23)ImageView build_23;

    /**Корпуса 2 очереди foriver*/
    @BindView(R.id.buttonG2K1)ImageView buttonG2K1;
    @BindView(R.id.buttonG2K2)ImageView buttonG2K2;
    @BindView(R.id.buttonG2K3)ImageView buttonG2K3;
    @BindView(R.id.buttonG2K4)ImageView buttonG2K4;
    @BindView(R.id.buttonG2K5)ImageView buttonG2K5;
    @BindView(R.id.buttonG2K6)ImageView buttonG2K6;
    @BindView(R.id.buttonG2K7)ImageView buttonG2K7;
    @BindView(R.id.buttonG2K8)ImageView buttonG2K8;
    @BindView(R.id.buttonG2K9)ImageView buttonG2K9;
    @BindView(R.id.buttonG2K10)ImageView buttonG2K10;
    @BindView(R.id.buttonG2K11)ImageView buttonG2K11;

    @BindView(R.id.hint)TextView hint;
    @BindView(R.id.hint2)TextView hint2;
    @BindView(R.id.genPlanLayout) FrameLayout genPlanRiverSky;
    @BindView(R.id.genPlanLayout2) FrameLayout genPlanFoRiver;

    //------title cost and budget
    @BindView(R.id.titleCost)ImageView titleCost;
    @BindView(R.id.titleBudget)ImageView titleBudget;

    //My variables
    AlertDialog alertDialog;
    String TAG = "myDebug";
    private myConfig cfg;
    private String query;
    //private VVVVPlayer vvvv;
    private PlayerCommands vvvv;
    @BindView(R.id.webView)WebView webView;
    private myTimer timer;

    ArrayList<Integer> ListClearFilter = new ArrayList<>();
    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //initialize config
        cfg = new myConfig(this);

        //vvvv = new VVVVPlayer(cfg.getHost());
        GlobalVars.webView = webView;
        vvvv = HttpPlayerFactory.getInstance(this).getCommand();
        vvvv.setVolume(cfg.getVolumeProgress());
        vvvv.setVolEffect(cfg.getEffectProgress());

        //initialization all component
        init();
        initSettings();

        //verify permissions read/write
        if(new MyPermissions(this,this).verifyStoragePermissions()){
            leftPanel.setVisibility(View.VISIBLE);
            scrollViewRight.setVisibility(View.VISIBLE);
            resultPanel.setVisibility(View.GONE);
        }
    }

    private void init(){
        leftPanel.setVisibility(View.INVISIBLE);
        scrollViewRight.setVisibility(View.INVISIBLE);
        resultPanel.setVisibility(View.GONE);
        settingsPanel.setVisibility(View.GONE);

        btnPlayPause.setTag(0);
        btnVolume.setTag(0);

        btnRoom1.setTag(0);
        btnRoom2.setTag(0);
        btnRoom3.setTag(0);
        btnRoom4.setTag(0);
        btnRoom5.setTag(0);
        btnRoomEvro.setTag(0);

        btnWhiteBox.setTag(0);
        btnWithoutRemont.setTag(0);
        btnWithRemont.setTag(0);

        check_period_4_2021.setTag(0);
        check_period_3_2022.setTag(0);
        check_period_4_2022.setTag(0);

        btnTownHouse.setTag(0);
        btnPentHouse.setTag(0);
        btnDoubleFloorHouse.setTag(0);
        btnTwoEnterHouse.setTag(0);
        btnTerrasa.setTag(0);
        btnWithFirePlace.setTag(0);
        btnBathRoomWithWindow.setTag(0);
        btnBalcon.setTag(0);

        titleCost.setTag(1);
        titleBudget.setTag(0);
        titleBudget.setImageResource(R.drawable.title_budget);
        titleCost .setImageResource(R.drawable.title_cost_down);

        SeekbarSquareOnChange();
        SeekbarFloorOnChange();
        SeekbarCostOnChange();
        setValuesToSeekBar();

        build_3_1.setImageResource(R.drawable.empty);
        build_3_2.setImageResource(R.drawable.empty);
        build_4.setImageResource(R.drawable.empty);
        build_5.setImageResource(R.drawable.empty);
        build_6.setImageResource(R.drawable.empty);
        build_7.setImageResource(R.drawable.empty);
        build_8.setImageResource(R.drawable.empty);
        build_9.setImageResource(R.drawable.empty);
        build_10.setImageResource(R.drawable.empty);
        build_11.setImageResource(R.drawable.empty);
        build_12.setImageResource(R.drawable.empty);
        build_13.setImageResource(R.drawable.empty);
        build_14.setImageResource(R.drawable.empty);
        build_15.setImageResource(R.drawable.empty);
        build_16.setImageResource(R.drawable.empty);
        build_17.setImageResource(R.drawable.empty);
        build_18.setImageResource(R.drawable.empty);
        build_19.setImageResource(R.drawable.empty);
        build_20.setImageResource(R.drawable.empty);
        build_21_1.setImageResource(R.drawable.empty);
        build_21_2.setImageResource(R.drawable.empty);
        build_21_3.setImageResource(R.drawable.empty);
        build_23.setImageResource(R.drawable.empty);

        build_3_1.setTag(0);
        build_3_2.setTag(0);
        build_4.setTag(0);
        build_5.setTag(0);
        build_6.setTag(0);
        build_7.setTag(0);
        build_8.setTag(0);
        build_9.setTag(0);
        build_10.setTag(0);
        build_11.setTag(0);
        build_12.setTag(0);
        build_13.setTag(0);
        build_14.setTag(0);
        build_15.setTag(0);
        build_16.setTag(0);
        build_17.setTag(0);
        build_18.setTag(0);
        build_19.setTag(0);
        build_20.setTag(0);
        build_21_1.setTag(0);
        build_21_2.setTag(0);
        build_21_3.setTag(0);
        build_23.setTag(0);

        buttonG2K1.setTag(0);
        buttonG2K2.setTag(0);
        buttonG2K3.setTag(0);
        buttonG2K4.setTag(0);
        buttonG2K5.setTag(0);
        buttonG2K6.setTag(0);
        buttonG2K7.setTag(0);
        buttonG2K8.setTag(0);
        buttonG2K9.setTag(0);
        buttonG2K10.setTag(0);
        buttonG2K11.setTag(0);

        /**устанавка генплана по умолчанию*/
        setGenPlan(1);

        hint.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/panroman.ttf"));
        hint.setText("корпус 3 / секция 1");
        hint.setVisibility(View.INVISIBLE);

        hint2.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/panroman.ttf"));
        hint2.setText("корпус 1");
        hint2.setVisibility(View.INVISIBLE);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hint_alpha);

//        build_3_1.setOnTouchListener(new ShowHint(this));
//        build_3_1.setOnLongClickListener(new ShowHint(this));

    }

    private void setValuesToSeekBar(){
//    try {
        cfg.setMinBudget(Float.valueOf(new FlatRepository(this).getMin("Budget").toString()));
        cfg.setMaxBudget(Float.valueOf(new FlatRepository(this).getMax("Budget").toString()));
        cfg.setMinCost(Float.valueOf(new FlatRepository(this).getMin("Cost").toString()));
        cfg.setMaxCost(Float.valueOf(new FlatRepository(this).getMax("Cost").toString()));
        cfg.setMinFloor((int) new FlatRepository(this).getMin("Floor"));
        cfg.setMaxFloor((int) new FlatRepository(this).getMax("Floor"));
        cfg.setMinSquare((float) new FlatRepository(this).getMin("Square"));
        cfg.setMaxSquare((float) new FlatRepository(this).getMax("Square"));
//    }catch (Exception e){
//        e.printStackTrace();
//    }

        setDataToCostSeekBar();

        seekbarFloor.setMinValue(cfg.getMinFloor());
        seekbarFloor.setMaxValue(cfg.getMaxFloor());
        minFloorEdit.setText(String.valueOf(cfg.getMinFloor()));
        maxFloorEdit.setText(String.valueOf(cfg.getMaxFloor()));

        seekbarSquare.setMinValue(cfg.getMinSquare());
        seekbarSquare.setMaxValue(cfg.getMaxSquare());
        minSquareEdit.setText(String.valueOf(cfg.getMinSquare()));
        maxSquareEdit.setText(String.valueOf(cfg.getMaxSquare()));

    }

    private void setDataToCostSeekBar() {
        float min = 0;
        float max = 0;
        if((Integer) titleCost.getTag() == 1) {
            seekbarCost.setMinValue(cfg.getMinCost());
            seekbarCost.setMaxValue(cfg.getMaxCost());
            min = cfg.getMinCost() / 1000;
            max = cfg.getMaxCost() / 1000;
        }
        if((Integer) titleBudget.getTag() == 1){
            seekbarCost.setMinValue(cfg.getMinBudget());
            seekbarCost.setMaxValue(cfg.getMaxBudget());
            min = cfg.getMinBudget() / 1000000;
            max = cfg.getMaxBudget() / 1000000;
        }

        //Log.d(TAG,"min="+min);
        String formattedDoubleMin = String.valueOf(min).substring(0,String.valueOf(min).indexOf(".")+2);
        String formattedDoubleMax = String.valueOf(max).substring(0,String.valueOf(max).indexOf(".")+2);
        //Log.d(TAG,"minSork="+formattedDouble);
        minCostEdit.setText(formattedDoubleMin.replace(",","."));
        maxCostEdit.setText(formattedDoubleMax.replace(",","."));
//        minCostEdit.setText(seekbarCost.getSelectedMinValue().toString());
//        maxCostEdit.setText(seekbarCost.getSelectedMaxValue().toString());
    }

    public void SeekbarSquareOnChange(){
        seekbarSquare.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {

                /*заменяем в строке запятую на точку, т.к. по неизвестным мне причинам
                на разных версиях андроид в EditView почему то тип float конвертируется с точкой а в каком то андроиде запятая.
                а нужно обязательно точка т.к. данные из этого Edit конвертируется обратно во float и передается другому activity
                и если стоит запятая конвертация не проходит, вываливается ошибка.
                */
                minSquareEdit.setText(String.format("%.2f", minValue).replace(",","."));
                maxSquareEdit.setText(String.format("%.2f", maxValue).replace(",","."));

            }
        });
        seekbarSquare.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                clearFilterActivate(true);
            }
        });
    }

    public void SeekbarCostOnChange(){
        seekbarCost.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                float min = 0;
                float max = 0;
                if((Integer) titleCost.getTag() == 1) {
                    min = Float.valueOf(minValue.toString()) / 1000;
                    max = Float.valueOf(maxValue.toString()) / 1000;
                }
                if((Integer) titleBudget.getTag() == 1){
                    min = Float.valueOf(minValue.toString()) / 1000000;
                    max = Float.valueOf(maxValue.toString()) / 1000000;
                }
                //Log.d(TAG,"min="+min);
                String formattedDoubleMin = String.valueOf(min).substring(0,String.valueOf(min).indexOf(".")+2);
                String formattedDoubleMax = String.valueOf(max).substring(0,String.valueOf(max).indexOf(".")+2);
                //Log.d(TAG,"minSork="+formattedDouble);
                minCostEdit.setText(formattedDoubleMin.replace(",","."));
                maxCostEdit.setText(formattedDoubleMax.replace(",","."));

            }
        });
        seekbarCost.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                clearFilterActivate(true);
            }
        });
    }

    public void SeekbarFloorOnChange(){
        seekbarFloor.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                minFloorEdit.setText(String.valueOf(minValue));
                maxFloorEdit.setText(String.valueOf(maxValue));
            }
        });
        seekbarFloor.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                clearFilterActivate(true);
            }
        });
    }

    private void initSettings(){
        viewSettingPanel = this.getLayoutInflater().inflate(R.layout.activity_settings, null);
        musicSeekBar = (SeekBar) viewSettingPanel.findViewById(R.id.musicSeekBar);
        effectSeekBar = (SeekBar) viewSettingPanel.findViewById(R.id.effectSeekBar);
        editWaitTime = (EditText) viewSettingPanel.findViewById(R.id.editWaitTime);
        switcherTimer = (ImageView) viewSettingPanel.findViewById(R.id.switcherTimer);
        editIP = (EditText) viewSettingPanel.findViewById(R.id.editIP);
        btnSave = (ImageView) viewSettingPanel.findViewById(R.id.btnSave);
        settingsPanel.addView(viewSettingPanel);
        musicSeekBar.setProgress(cfg.getVolumeProgress());
        effectSeekBar.setProgress(cfg.getEffectProgress());
        editWaitTime.setText(String.valueOf(cfg.getTimer()));
        editIP.setText(cfg.getHost());

        if(cfg.getDisableTimer()) {
            switcherTimer.setTag("on");
            switcherTimer.setImageResource(R.drawable.button_on_wait);
        }else {
            switcherTimer.setTag("off");
            switcherTimer.setImageResource(R.drawable.button_off_wait);
        }

        musicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Log.d(TAG,String.valueOf(musicSeekBar.getProgress()));
                //GlobalVar.volume = String.valueOf(VolumeSeekBar.getProgress());
                //GlobalVar.lastLink = VVVV.fullLink();
                vvvv.volume(seekBar.getProgress());
            }
        });

        effectSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Log.d(TAG,String.valueOf(effectSeekBar.getProgress()));
                //GlobalVar.volEffects = String.valueOf(EffectSeekBar.getProgress());
                //GlobalVar.lastLink = VVVV.fullLink();
                vvvv.volEffect(seekBar.getProgress());
            }
        });
        switcherTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switcherTimer.getTag().equals("on")){
                    switcherTimer.setTag("off");
                    switcherTimer.setImageResource(R.drawable.button_off_wait);
                }else{
                    switcherTimer.setTag("on");
                    switcherTimer.setImageResource(R.drawable.button_on_wait);
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings();
            }
        });
    }

    private void saveSettings() {
        if(!editIP.getText().toString().equals(cfg.getHost())) {
            showPasswordWindow();
        }

        //Log.d(TAG,"save config");
        if(switcherTimer.getTag().equals("on")) {
            cfg.setDisableTimer(true);
        }else{
            cfg.setDisableTimer(false);
        }

        cfg.setVolumeProgress(String.valueOf(musicSeekBar.getProgress()));
        cfg.setEffectProgress(String.valueOf(effectSeekBar.getProgress()));

        cfg.setTimer(String.valueOf(editWaitTime.getText()));
        settingsPanel.setVisibility(View.GONE);
        scrollViewRight.setVisibility(View.VISIBLE);
        setTimer(cfg.getTimer());

    }

    private void showPasswordWindow(){
        //Получаем вид с файла prompt.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompt, null);

        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);

        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);

        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:
                                if(String.valueOf(userInput.getText()).equals("realred34")){
                                    cfg.setHost(editIP.getText().toString());
                                    vvvv.changeHost(cfg.getHost());
                                }else
                                {
                                    Toast toast2 = Toast.makeText(getApplicationContext(), "Неверный пароль!", Toast.LENGTH_LONG);
                                    toast2.setGravity(Gravity.CENTER, 0, 0);
                                    toast2.show();
                                }

                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();


    }


    public void leftPanelButtonOnCLick(View view){
        btnFull.setImageResource(R.drawable.button_full);
        btnLocation.setImageResource(R.drawable.button_location);
        btnAbout.setImageResource(R.drawable.button_about);
        btnTransport.setImageResource(R.drawable.button_transport);
        btnArea.setImageResource(R.drawable.button_area);
        btnNaturales.setImageResource(R.drawable.button_naturales);
        btnInfra.setImageResource(R.drawable.button_infra);
        btnOutsideInfra.setImageResource(R.drawable.button_outside_infra);
        btnTorpedo.setImageResource(R.drawable.button_torpedo);
        btnSurround.setImageResource(R.drawable.button_surround);
        btnAdvantageProject.setImageResource(R.drawable.button_advantage_project);
        btnGallary.setImageResource(R.drawable.button_gallary);
        btnProcessBuildings.setImageResource(R.drawable.button_process_buildings);
        btnAdvantageRiversky.setImageResource(R.drawable.button_advantage_riversky);
        btnAdvantageForiver.setImageResource(R.drawable.button_advantage_foriver);
        btn3d.setImageResource(R.drawable.button_3d);
        btnCamera.setImageResource(R.drawable.button_camera);
        switch (view.getId()){
            case R.id.btnFull:
                btnFull.setImageResource(R.drawable.button_full_down);
                vvvv.selectById(0);
                play();
                break;
            case R.id.btnLocation:
                btnLocation.setImageResource(R.drawable.button_location_down);
                vvvv.selectById(1);
                play();
                break;
            case R.id.btnAbout:
                btnAbout.setImageResource(R.drawable.button_about_down);
                vvvv.selectById(2);
                play();
                break;
            case R.id.btnTransport:
                btnTransport.setImageResource(R.drawable.button_transport_down);
                vvvv.selectById(3);
                play();
                break;
            case R.id.btnArea:
                btnArea.setImageResource(R.drawable.button_area_down);
                vvvv.selectById(4);
                play();
                break;
            case R.id.btnNaturals:
                btnNaturales.setImageResource(R.drawable.button_naturales_down);
                vvvv.selectById(5);
                play();
                break;
            case R.id.btnInfra:
                btnInfra.setImageResource(R.drawable.button_infra_down);
                vvvv.selectById(6);
                play();
                break;
            case R.id.btnOutsideInfra:
                btnOutsideInfra.setImageResource(R.drawable.button_outside_infra_down);
                vvvv.selectById(7);
                play();
                new OuterInfraActivity(this);
                break;
            case R.id.btnTorpedo:
                btnTorpedo.setImageResource(R.drawable.button_torpedo_down);
                vvvv.selectById(8);
                play();
                break;
            case R.id.btnSurround:
                btnSurround.setImageResource(R.drawable.button_surround_down);
                vvvv.selectById(9);
                play();
                break;
            case R.id.btnAdvantageProject:
                btnAdvantageProject.setImageResource(R.drawable.button_advantage_project_down);
                vvvv.selectById(10);
                play();
                break;
            case R.id.btnGallary:
                btnGallary.setImageResource(R.drawable.button_gallary_down);
                vvvv.selectById(11);
                play();
                break;
            case R.id.btnProcessBuildings:
                btnProcessBuildings.setImageResource(R.drawable.button_process_buildings_down);
                vvvv.selectById(12);
                play();
                break;
            case R.id.btnAdvantageRiversky:
                btnAdvantageRiversky.setImageResource(R.drawable.button_advantage_riversky_down);
                //vvvv.selectById(13);
                //play();
                break;
            case R.id.btnAdvantageForiver:
                btnAdvantageForiver.setImageResource(R.drawable.button_advantage_foriver_down);
                //vvvv.selectById(14);
                //play();
                break;
            case R.id.btn3d:
                btn3d.setImageResource(R.drawable.button_3d_down);
                new For3DActivity(this);
                break;
            case R.id.btnCamera:
                btnCamera.setImageResource(R.drawable.button_camera_down);
                new CameraActivity(this);
                break;
        }
    }

    private void play(){
        btnPlayPause.setImageResource(R.drawable.button_playpause_down);
        btnPlayPause.setTag(1);
        setTimer(cfg.getTimer());
    }
    public void conlrolPanelButtonOnClick(View view){
        switch (view.getId()){
            case R.id.btnPlayPause:
                if((Integer) btnPlayPause.getTag() == 1 ){
                    btnPlayPause.setImageResource(R.drawable.button_playpause);
                    btnPlayPause.setTag(0);
                    vvvv.stop();
                }else{
                    btnPlayPause.setImageResource(R.drawable.button_playpause_down);
                    btnPlayPause.setTag(1);
                    vvvv.play();
                }
                break;
            case R.id.btnVolume:
                if((Integer) btnVolume.getTag() == 1 ){
                    btnVolume.setImageResource(R.drawable.button_volume);
                    btnVolume.setTag(0);
                    vvvv.volumeOnOff();
                }else{
                    btnVolume.setImageResource(R.drawable.button_volume_down);
                    btnVolume.setTag(1);
                    vvvv.volumeOnOff();
                }
                break;
            case R.id.btnOptions:
                scrollViewRight.setVisibility(View.GONE);
                resultPanel.setVisibility(View.GONE);
                settingsPanel.setVisibility(View.VISIBLE);
                break;
            case R.id.btnRiverSky:
                setGenPlan(1);
                break;
            case R.id.btnFoRiver:
                setGenPlan(2);
                break;
            case R.id.btnRTypePlan:
                btnTypePlan.setImageResource(R.drawable.button_typeplan_down);
                break;
        }

    }

    private void setGenPlan(int genPlan) {
        if (genPlan > 2) {
            genPlan = 1;
        }
        clearFilter();
        switch (genPlan) {
            case 1:
                btnRiverSky.setImageResource(R.drawable.button_riversky_down);
                btnFoRiver.setImageResource(R.drawable.button_foriver);
                genPlanRiverSky.setVisibility(View.VISIBLE);
                genPlanFoRiver.setVisibility(View.GONE);
                break;
            case 2:
                btnRiverSky.setImageResource(R.drawable.button_riversky);
                btnFoRiver.setImageResource(R.drawable.button_foriver_down);
                genPlanRiverSky.setVisibility(View.GONE);
                genPlanFoRiver.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void countRoomButtonOnClick(View view){
        switch (view.getId()){
            case R.id.btnRoom1:
                if((Integer) btnRoom1.getTag() == 1 ){
                    btnRoom1.setImageResource(R.drawable.button_room1);
                    btnRoom1.setTag(0);
                    clearFilterActivate(false);

                }else{
                    btnRoom1.setImageResource(R.drawable.button_room1_down);
                    btnRoom1.setTag(1);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnRoom2:
                if((Integer) btnRoom2.getTag() == 2 ){
                    btnRoom2.setImageResource(R.drawable.button_room2);
                    btnRoom2.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnRoom2.setImageResource(R.drawable.button_room2_down);
                    btnRoom2.setTag(2);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnRoom3:
                if((Integer) btnRoom3.getTag() == 3 ){
                    btnRoom3.setImageResource(R.drawable.button_room3);
                    btnRoom3.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnRoom3.setImageResource(R.drawable.button_room3_down);
                    btnRoom3.setTag(3);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnRoom4:
                if((Integer) btnRoom4.getTag() == 4 ){
                    btnRoom4.setImageResource(R.drawable.button_room4);
                    btnRoom4.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnRoom4.setImageResource(R.drawable.button_room4_down);
                    btnRoom4.setTag(4);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnRoom5:
                if((Integer) btnRoom5.getTag() == 5 ){
                    btnRoom5.setImageResource(R.drawable.button_room5);
                    btnRoom5.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnRoom5.setImageResource(R.drawable.button_room5_down);
                    btnRoom5.setTag(5);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnRoomEvro:
                if((Integer) btnRoomEvro.getTag() == 1 ){
                    btnRoomEvro.setImageResource(R.drawable.button_room_evro);
                    btnRoomEvro.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnRoomEvro.setImageResource(R.drawable.button_room_evro_down);
                    btnRoomEvro.setTag(1);
                    clearFilterActivate(true);
                }
                break;
        }
    }

    public void periodOnClick(View view){
        switch (view.getId()){
            case R.id.btnSrok_4_2021:
                if((Integer) check_period_4_2021.getTag() == 1 ){
                    check_period_4_2021.setImageResource(R.drawable.checkbox_srok_4_2021);
                    check_period_4_2021.setTag(0);
                    clearFilterActivate(false);
                }else{
                    check_period_4_2021.setImageResource(R.drawable.checkbox_srok_4_2021_down);
                    check_period_4_2021.setTag(1);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnSrok_3_2022:
                break;

            case R.id.btnSrok_4_2022:
                break;
        }
    }

    public void remontButtonOnClick(View view){
        switch (view.getId()){
            case R.id.btnWithoutRemont:
                if((Integer) btnWithoutRemont.getTag() == 1 ){
                    btnWithoutRemont.setImageResource(R.drawable.button_without_remont);
                    btnWithoutRemont.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnWithoutRemont.setImageResource(R.drawable.button_without_remont_down);
                    btnWithoutRemont.setTag(1);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnWithRemont:
                if((Integer) btnWithRemont.getTag() == 1 ){
                    btnWithRemont.setImageResource(R.drawable.button_with_remont);
                    btnWithRemont.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnWithRemont.setImageResource(R.drawable.button_with_remont_down);
                    btnWithRemont.setTag(1);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnWhiteBox:
                if((Integer) btnWhiteBox.getTag() == 1 ){
                    btnWhiteBox.setImageResource(R.drawable.button_remont_whitebox);
                    btnWhiteBox.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnWhiteBox.setImageResource(R.drawable.button_remont_whitebox_down);
                    btnWhiteBox.setTag(1);
                    clearFilterActivate(true);
                }
                break;
        }

    }


    public void addAttrButtonsOnClick(View view){
        switch (view.getId()){
            case R.id.btnTownHouse:
                if((Integer) btnTownHouse.getTag() == 1 ){
                    btnTownHouse.setImageResource(R.drawable.button_townhouse);
                    btnTownHouse.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnTownHouse.setImageResource(R.drawable.button_townhouse_down);
                    btnTownHouse.setTag(1);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnPentHouse:
                if((Integer) btnPentHouse.getTag() == 1 ){
                    btnPentHouse.setImageResource(R.drawable.button_penthouse);
                    btnPentHouse.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnPentHouse.setImageResource(R.drawable.button_penthouse_down);
                    btnPentHouse.setTag(1);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnDoubleFloorHouse:
                if((Integer) btnDoubleFloorHouse.getTag() == 1 ){
                    btnDoubleFloorHouse.setImageResource(R.drawable.button_doublefloorhouse);
                    btnDoubleFloorHouse.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnDoubleFloorHouse.setImageResource(R.drawable.button_doublefloorhouse_down);
                    btnDoubleFloorHouse.setTag(1);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnTwoEnterHouse:
                if((Integer) btnTwoEnterHouse.getTag() == 1 ){
                    btnTwoEnterHouse.setImageResource(R.drawable.button_twoenterhouse);
                    btnTwoEnterHouse.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnTwoEnterHouse.setImageResource(R.drawable.button_twoenterhouse_down);
                    btnTwoEnterHouse.setTag(1);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnTerrasa:
                if((Integer) btnTerrasa.getTag() == 1 ){
                    btnTerrasa.setImageResource(R.drawable.button_terrasa);
                    btnTerrasa.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnTerrasa.setImageResource(R.drawable.button_terrasa_down);
                    btnTerrasa.setTag(1);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnWithFirePlace:
                if((Integer) btnWithFirePlace.getTag() == 1 ){
                    btnWithFirePlace.setImageResource(R.drawable.button_with_fireplace);
                    btnWithFirePlace.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnWithFirePlace.setImageResource(R.drawable.button_with_fireplace_down);
                    btnWithFirePlace.setTag(1);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnBathRoomWithWindow:
                if((Integer) btnBathRoomWithWindow.getTag() == 1 ){
                    btnBathRoomWithWindow.setImageResource(R.drawable.button_bathroom_with_window);
                    btnBathRoomWithWindow.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnBathRoomWithWindow.setImageResource(R.drawable.button_bathroom_with_window_down);
                    btnBathRoomWithWindow.setTag(1);
                    clearFilterActivate(true);
                }
                break;
            case R.id.btnBalcon:
                if((Integer) btnBalcon.getTag() == 1 ){
                    btnBalcon.setImageResource(R.drawable.button_balcon);
                    btnBalcon.setTag(0);
                    clearFilterActivate(false);
                }else{
                    btnBalcon.setImageResource(R.drawable.button_balcon_down);
                    btnBalcon.setTag(1);
                    clearFilterActivate(true);
                }
                break;
        }

    }


    public void buttonsSearchOnClick(View view){
        switch (view.getId()){
            case R.id.btnClear:
                clearFilter();
                break;
            case R.id.btnSearch:
                applyFilter();
                break;
        }
    }

    public void titleOfListviewOnClick(View view){
        String order = null;
        switch (view.getId()){
            case R.id.colKorpus:
                order = " order by category,sectionNumber";
                break;
            case R.id.colFlat:
                order = " order by BeforeBtiNumber";
                break;
            case R.id.colFloor:
                order = " order by Floor";
                break;
            case R.id.colRooms:
                order = " order by Rooms";
                break;
            case R.id.colSquare:
                order = " order by Quantity";
                break;
            case R.id.colCost:
                order = " order by DiscountMax";
                break;
            case R.id.colSrok:
                order = " order by DeliveryPeriod";
                break;
        }
        loadFromSqlite(query + order);
    }

    private void loadFromSqlite(String sql){
        //Log.d(TAG, "mas_size" + new FlatRepository(getApplicationContext()).getAll().size());
        myAdapter adapter = new myAdapter(this, new FlatRepository(this).getFlatsByQuery(sql),0);
        //Log.d("myDebug","adapter.size="+adapter.getCount());
        if(adapter.getCount() == 0 ){
            textNoRow.setVisibility(View.VISIBLE);
        }else{
            textNoRow.setVisibility(View.GONE);
        }
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new listviewItemSelected(this));

        scrollViewRight.setVisibility(View.GONE);
        settingsPanel.setVisibility(View.GONE);
        resultPanel.setVisibility(View.VISIBLE);
    }

    private void applyFilter() {
        query = "select * from flats where (StatusCodeName='Свободно' or StatusCodeName='Ус. Бронь') and ";

        query = query + " (floor >= "+seekbarFloor.getSelectedMinValue() + " and floor <= "+seekbarFloor.getSelectedMaxValue()+") ";
        query = query + " and (Quantity >= " + seekbarSquare.getSelectedMinValue() + " and Quantity <= "+((Float) seekbarSquare.getSelectedMaxValue()+0.1) + ") ";
        if((Integer) titleCost.getTag() == 1) {
            query = query + " and ((DiscountMax/Quantity) >= " + minCostEdit.getText() + "*1000" + " and (DiscountMax/Quantity) <= " + maxCostEdit.getText() + "*1000" + ") ";
        }
        if((Integer) titleBudget.getTag() == 1){
            query = query + " and (DiscountMax >= " + minCostEdit.getText() + "*1000000" + " and DiscountMax <= " + maxCostEdit.getText() + "*1000000" + ") ";
        }

        //Count ROOMS;
        List<Integer> countRoom = new ArrayList<>();
        if(Integer.valueOf(btnRoom1.getTag().toString()) > 0) { countRoom.add(Integer.valueOf(btnRoom1.getTag().toString())); }
        if(Integer.valueOf(btnRoom2.getTag().toString()) > 0) { countRoom.add(Integer.valueOf(btnRoom2.getTag().toString())); }
        if(Integer.valueOf(btnRoom3.getTag().toString()) > 0) { countRoom.add(Integer.valueOf(btnRoom3.getTag().toString())); }
        if(Integer.valueOf(btnRoom4.getTag().toString()) > 0) { countRoom.add(Integer.valueOf(btnRoom4.getTag().toString())); }
        if(Integer.valueOf(btnRoom5.getTag().toString()) > 0) { countRoom.add(Integer.valueOf(btnRoom5.getTag().toString())); }


        if(countRoom.size()>0){
            String countRoomString = "";
            for(int i = 0 ; i<countRoom.size();i++){
                if (countRoomString.length() == 0) {
                    countRoomString = countRoom.get(i).toString();
                } else {
                    countRoomString = countRoomString + "," + countRoom.get(i).toString();
                }
            }
            countRoomString = " and rooms in (" + countRoomString + ")";
            query = query + countRoomString;
        }

        //evro %E% - cyrillic symbol
        if((Integer) btnRoomEvro.getTag() == 1){
            query = query + " and Category like '%Е%' ";
        }

        //remont


        //additional parameter
        if(Integer.valueOf(btnTownHouse.getTag().toString())>0){query = query + " and TownHouse = 'True'"; }
        if(Integer.valueOf(btnPentHouse.getTag().toString())>0){query = query + " and PentHouse = 'True'"; }
        if(Integer.valueOf(btnDoubleFloorHouse.getTag().toString())>0){query = query + " and TwoLevel = 'True'"; }
        if(Integer.valueOf(btnTwoEnterHouse.getTag().toString())>0){query = query + " and SeparateEntrance = 'True'"; }
        if(Integer.valueOf(btnTerrasa.getTag().toString())>0){ query = query + " and Terrace = 'True'";}
        if(Integer.valueOf(btnWithFirePlace.getTag().toString())>0){ query = query + " and FirePlace = 'True'";}
        if(Integer.valueOf(btnBathRoomWithWindow.getTag().toString()) > 0){ query = query + " and WithWindow = 'True'";}
        //if(Integer.valueOf(btnBalcon.getTag().toString()) > 0){ query = query + " and Balcon = 'True'";}

        String buildQuery;
        List<String> countBuild = new ArrayList<>();
        if((Integer) build_3_1.getTag() == 1 ){countBuild.add("(SectionNumber=1 and AddressNumber=8)");}
        if((Integer) build_3_2.getTag() == 1 ){countBuild.add("(SectionNumber=2 and AddressNumber=8)");}
        if((Integer) build_4.getTag() == 1 ){countBuild.add("(SectionNumber=3 and AddressNumber=8)");}
        if((Integer) build_5.getTag() == 1 ){countBuild.add("(SectionNumber=4 and AddressNumber=8)");}
        if((Integer) build_6.getTag() == 1 ){countBuild.add("(SectionNumber=5 and AddressNumber=8)");}
        //if((Integer) build_7.getTag() == 1 ){countBuild.add("(SectionNumber=1 and AddressNumber=8)");}
        if((Integer) build_8.getTag() == 1 ){countBuild.add("(SectionNumber=1 and AddressNumber=5)");}
        if((Integer) build_9.getTag() == 1 ){countBuild.add("(SectionNumber=1 and AddressNumber=4)");}
        if((Integer) build_10.getTag() == 1 ){countBuild.add("(SectionNumber=2 and AddressNumber=4)");}
        if((Integer) build_11.getTag() == 1 ){countBuild.add("(SectionNumber=3 and AddressNumber=4)");}
        if((Integer) build_12.getTag() == 1 ){countBuild.add("(SectionNumber=1 and AddressNumber=1)");}
        //if((Integer) build_13.getTag() == 1 ){countBuild.add("(SectionNumber=3 and AddressNumber=8)");}
        if((Integer) build_14.getTag() == 1 ){countBuild.add("(SectionNumber=5 and AddressNumber=2)");}
        if((Integer) build_15.getTag() == 1 ){countBuild.add("(SectionNumber=4 and AddressNumber=2)");}
        if((Integer) build_16.getTag() == 1 ){countBuild.add("(SectionNumber=3 and AddressNumber=2)");}
        if((Integer) build_17.getTag() == 1 ){countBuild.add("(SectionNumber=2 and AddressNumber=2)");}
        if((Integer) build_18.getTag() == 1 ){countBuild.add("(SectionNumber=1 and AddressNumber=2)");}
        //if((Integer) build_19.getTag() == 1 ){countBuild.add("(SectionNumber=3 and AddressNumber=8)");}
        if((Integer) build_20.getTag() == 1 ){countBuild.add("(SectionNumber=1 and AddressNumber=3)");}
        if((Integer) build_21_1.getTag() == 1 ){countBuild.add("(SectionNumber=1 and AddressNumber=6)");}
        if((Integer) build_21_2.getTag() == 1 ){countBuild.add("(SectionNumber=2 and AddressNumber=6)");}
        if((Integer) build_21_3.getTag() == 1 ){countBuild.add("(SectionNumber=3 and AddressNumber=6)");}
        if((Integer) build_23.getTag() == 1 ){countBuild.add("(SectionNumber=1 and AddressNumber=7)");}

        if(countBuild.size()>0){
            String countBuildString = "";
            for(int i = 0 ; i<countBuild.size();i++){
                if (countBuildString.length() == 0) {
                    countBuildString = countBuild.get(i);
                } else {
                    countBuildString = countBuildString + " or " + countBuild.get(i);
                }
            }
            countBuildString = " and (" + countBuildString + ") ";
            query = query + countBuildString;
        }

        if((Integer) check_period_4_2021.getTag() == 1){query = query + " and DeliveryPeriod = '4к2021'";}
        if((Integer) check_period_3_2022.getTag() == 1){query = query + " and DeliveryPeriod = '3к2022'";}
        if((Integer) check_period_4_2022.getTag() == 1){query = query + " and DeliveryPeriod = '4к2022'";}

        //выводить только тех у кого цена за кв.метр не равна 1
        query += " and (discountmax/Quantity) > 1";

        Log.d(TAG, query + " order by DiscountMax");
        loadFromSqlite(query + " order by DiscountMax");
    }

    private void clearFilter() {
        hint.setVisibility(View.INVISIBLE);
        hint2.setVisibility(View.INVISIBLE);
        btnClear.setImageResource(R.drawable.button_clear);
        //btnClear.setEnabled(flag);
        ListClearFilter.clear();

        seekbarSquare.setMinStartValue(cfg.getMinSquare());
        seekbarSquare.setMaxStartValue(cfg.getMaxSquare());
        seekbarSquare.apply();

        seekbarCost.setMinStartValue(cfg.getMinCost());
        seekbarCost.setMaxStartValue(cfg.getMaxCost());
        seekbarCost.apply();

        seekbarFloor.setMinStartValue(cfg.getMinFloor());
        seekbarFloor.setMaxStartValue(cfg.getMaxFloor());
        seekbarFloor.apply();

        btnRoom1.setImageResource(R.drawable.button_room1);
        btnRoom2.setImageResource(R.drawable.button_room2);
        btnRoom3.setImageResource(R.drawable.button_room3);
        btnRoom4.setImageResource(R.drawable.button_room4);
        btnRoom5.setImageResource(R.drawable.button_room5);
        btnRoomEvro.setImageResource(R.drawable.button_room_evro);
        btnRoom1.setTag(0);
        btnRoom2.setTag(0);
        btnRoom3.setTag(0);
        btnRoom4.setTag(0);
        btnRoom5.setTag(0);
        btnRoomEvro.setTag(0);

        btnWithoutRemont.setImageResource(R.drawable.button_without_remont);
        btnWithRemont.setImageResource(R.drawable.button_with_remont);
        btnWhiteBox.setImageResource(R.drawable.button_remont_whitebox);
        btnWhiteBox.setTag(0);
        btnWithoutRemont.setTag(0);
        btnWithRemont.setTag(0);

        check_period_4_2021.setImageResource(R.drawable.checkbox_srok_4_2021);
        check_period_3_2022.setImageResource(R.drawable.checkbox_srok_3_2022);
        check_period_4_2022.setImageResource(R.drawable.checkbox_srok_4_2022);
        check_period_4_2021.setTag(0);
        check_period_3_2022.setTag(0);
        check_period_4_2022.setTag(0);

        btnTownHouse.setImageResource(R.drawable.button_townhouse);
        btnPentHouse.setImageResource(R.drawable.button_penthouse);
        btnDoubleFloorHouse.setImageResource(R.drawable.button_doublefloorhouse);
        btnTwoEnterHouse.setImageResource(R.drawable.button_twoenterhouse);
        btnTerrasa.setImageResource(R.drawable.button_terrasa);
        btnWithFirePlace.setImageResource(R.drawable.button_with_fireplace);
        btnBathRoomWithWindow.setImageResource(R.drawable.button_bathroom_with_window);
        btnBalcon.setImageResource(R.drawable.button_balcon);
        btnTownHouse.setTag(0);
        btnPentHouse.setTag(0);
        btnDoubleFloorHouse.setTag(0);
        btnTwoEnterHouse.setTag(0);
        btnTerrasa.setTag(0);
        btnWithFirePlace.setTag(0);
        btnBathRoomWithWindow.setTag(0);
        btnBalcon.setTag(0);

        build_3_1.setTag(0);
        build_3_1.setImageResource(R.drawable.empty);
        build_3_2.setTag(0);
        build_3_2.setImageResource(R.drawable.empty);
        build_4.setTag(0);
        build_4.setImageResource(R.drawable.empty);
        build_5.setTag(0);
        build_5.setImageResource(R.drawable.empty);
        build_6.setTag(0);
        build_6.setImageResource(R.drawable.empty);
        build_7.setTag(0);
        build_7.setImageResource(R.drawable.empty);
        build_8.setTag(0);
        build_8.setImageResource(R.drawable.empty);
        build_9.setTag(0);
        build_9.setImageResource(R.drawable.empty);
        build_10.setTag(0);
        build_10.setImageResource(R.drawable.empty);
        build_11.setTag(0);
        build_11.setImageResource(R.drawable.empty);
        build_12.setTag(0);
        build_12.setImageResource(R.drawable.empty);
        build_13.setTag(0);
        build_13.setImageResource(R.drawable.empty);
        build_14.setTag(0);
        build_14.setImageResource(R.drawable.empty);
        build_15.setTag(0);
        build_15.setImageResource(R.drawable.empty);
        build_16.setTag(0);
        build_16.setImageResource(R.drawable.empty);
        build_17.setTag(0);
        build_17.setImageResource(R.drawable.empty);
        build_18.setTag(0);
        build_18.setImageResource(R.drawable.empty);
        build_19.setTag(0);
        build_19.setImageResource(R.drawable.empty);
        build_20.setTag(0);
        build_20.setImageResource(R.drawable.empty);
        build_21_1.setTag(0);
        build_21_1.setImageResource(R.drawable.empty);
        build_21_2.setTag(0);
        build_21_2.setImageResource(R.drawable.empty);
        build_21_3.setTag(0);
        build_21_3.setImageResource(R.drawable.empty);
        build_23.setTag(0);
        build_23.setImageResource(R.drawable.empty);

        buttonG2K1.setTag(0);
        buttonG2K1.setImageResource(R.drawable.empty);
        buttonG2K2.setTag(0);
        buttonG2K2.setImageResource(R.drawable.empty);
        buttonG2K3.setTag(0);
        buttonG2K3.setImageResource(R.drawable.empty);
        buttonG2K4.setTag(0);
        buttonG2K4.setImageResource(R.drawable.empty);
        buttonG2K5.setTag(0);
        buttonG2K5.setImageResource(R.drawable.empty);
        buttonG2K6.setTag(0);
        buttonG2K6.setImageResource(R.drawable.empty);
        buttonG2K7.setTag(0);
        buttonG2K7.setImageResource(R.drawable.empty);
        buttonG2K8.setTag(0);
        buttonG2K8.setImageResource(R.drawable.empty);
        buttonG2K9.setTag(0);
        buttonG2K9.setImageResource(R.drawable.empty);
        buttonG2K10.setTag(0);
        buttonG2K10.setImageResource(R.drawable.empty);
        buttonG2K11.setTag(0);
        buttonG2K11.setImageResource(R.drawable.empty);
    }

    public void genplan2KorpusesOnClick(View view) {
        if ((Integer) view.getTag() == 0) {
            view.setTag(1);
            clearFilterActivate(true);
            if (view.getId() == R.id.buttonG2K1) {buttonG2K1.setImageResource(R.drawable.genplan2_k1); ShowHint(view,true, getString(R.string.G2K1), hint2);}
            if (view.getId() == R.id.buttonG2K2) {buttonG2K2.setImageResource(R.drawable.genplan2_k2); ShowHint(view,true, getString(R.string.G2K2), hint2);}
            if (view.getId() == R.id.buttonG2K3) {buttonG2K3.setImageResource(R.drawable.genplan2_k3); ShowHint(view,true, getString(R.string.G2K3), hint2);}
            if (view.getId() == R.id.buttonG2K4) {buttonG2K4.setImageResource(R.drawable.genplan2_k4); ShowHint(view,true, getString(R.string.G2K4), hint2);}
            if (view.getId() == R.id.buttonG2K5) {buttonG2K5.setImageResource(R.drawable.genplan2_k5); ShowHint(view,true, getString(R.string.G2K5), hint2);}
            if (view.getId() == R.id.buttonG2K6) {buttonG2K6.setImageResource(R.drawable.genplan2_k6); ShowHint(view,true, getString(R.string.G2K6), hint2);}
            if (view.getId() == R.id.buttonG2K7) {buttonG2K7.setImageResource(R.drawable.genplan2_k7); ShowHint(view,true, getString(R.string.G2K7), hint2);}
            if (view.getId() == R.id.buttonG2K8) {buttonG2K8.setImageResource(R.drawable.genplan2_k8); ShowHint(view,true, getString(R.string.G2K8), hint2);}
            if (view.getId() == R.id.buttonG2K9) {buttonG2K9.setImageResource(R.drawable.genplan2_k9); ShowHint(view,true, getString(R.string.G2K9), hint2);}
            if (view.getId() == R.id.buttonG2K10) {buttonG2K10.setImageResource(R.drawable.genplan2_k10); ShowHint(view,true, getString(R.string.G2K10), hint2);}
            if (view.getId() == R.id.buttonG2K11) {buttonG2K11.setImageResource(R.drawable.genplan2_k11); ShowHint(view,true, getString(R.string.G2K11), hint2);}
        } else {
            view.setTag(0);
            clearFilterActivate(false);
            if (view.getId() == R.id.buttonG2K1) {buttonG2K1.setImageResource(R.drawable.empty); ShowHint(view,false, getString(R.string.G2K1), hint2);}
            if (view.getId() == R.id.buttonG2K2) {buttonG2K2.setImageResource(R.drawable.empty); ShowHint(view,false, getString(R.string.G2K2), hint2);}
            if (view.getId() == R.id.buttonG2K3) {buttonG2K3.setImageResource(R.drawable.empty); ShowHint(view,false, getString(R.string.G2K3), hint2);}
            if (view.getId() == R.id.buttonG2K4) {buttonG2K4.setImageResource(R.drawable.empty); ShowHint(view,false, getString(R.string.G2K4), hint2);}
            if (view.getId() == R.id.buttonG2K5) {buttonG2K5.setImageResource(R.drawable.empty); ShowHint(view,false, getString(R.string.G2K5), hint2);}
            if (view.getId() == R.id.buttonG2K6) {buttonG2K6.setImageResource(R.drawable.empty); ShowHint(view,false, getString(R.string.G2K6), hint2);}
            if (view.getId() == R.id.buttonG2K7) {buttonG2K7.setImageResource(R.drawable.empty); ShowHint(view,false, getString(R.string.G2K7), hint2);}
            if (view.getId() == R.id.buttonG2K8) {buttonG2K8.setImageResource(R.drawable.empty); ShowHint(view,false, getString(R.string.G2K8), hint2);}
            if (view.getId() == R.id.buttonG2K9) {buttonG2K9.setImageResource(R.drawable.empty); ShowHint(view,false, getString(R.string.G2K9), hint2);}
            if (view.getId() == R.id.buttonG2K10) {buttonG2K10.setImageResource(R.drawable.empty); ShowHint(view,false, getString(R.string.G2K10), hint2);}
            if (view.getId() == R.id.buttonG2K11) {buttonG2K11.setImageResource(R.drawable.empty); ShowHint(view,false, getString(R.string.G2K11), hint2);}
        }
    }

    public void buildsOnClick(View v){
        switch (v.getId()){
            case R.id.build_3_1:
                if((Integer) build_3_1.getTag() == 0){
                   build_3_1.setTag(1);
                   build_3_1.setImageResource(R.drawable.build_3_1);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build3_1), hint);
                } else{
                    build_3_1.setTag(0);
                    build_3_1.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build3_1), hint);
                }
            break;
            case R.id.build_3_2:
                if((Integer) build_3_2.getTag() == 0){
                    build_3_2.setTag(1);
                    build_3_2.setImageResource(R.drawable.build_3_2);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build3_2), hint);
                } else{
                    build_3_2.setTag(0);
                    build_3_2.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build3_2), hint);
                }
                break;
            case R.id.build_4:
                if((Integer) build_4.getTag() == 0){
                    build_4.setTag(1);
                    build_4.setImageResource(R.drawable.build_4);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build4), hint);
                } else{
                    build_4.setTag(0);
                    build_4.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build4), hint);
                }
                break;
            case R.id.build_5:
                if((Integer) build_5.getTag() == 0){
                    build_5.setTag(1);
                    build_5.setImageResource(R.drawable.build_5);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build5), hint);
                } else{
                    build_5.setTag(0);
                    build_5.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build5), hint);
                }
                break;
            case R.id.build_6:
                if((Integer) build_6.getTag() == 0){
                    build_6.setTag(1);
                    build_6.setImageResource(R.drawable.build_6);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build6), hint);
                } else{
                    build_6.setTag(0);
                    build_6.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build6), hint);
                }
                break;
            case R.id.build_7:
                if((Integer) build_7.getTag() == 0){
                    build_7.setTag(1);
                    build_7.setImageResource(R.drawable.build_7);
                    clearFilterActivate(true);
                } else{
                    build_7.setTag(0);
                    build_7.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                }
                break;
            case R.id.build_8:
                if((Integer) build_8.getTag() == 0){
                    build_8.setTag(1);
                    build_8.setImageResource(R.drawable.build_8);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build8), hint);
                } else{
                    build_8.setTag(0);
                    build_8.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build8), hint);
                }
                break;
            case R.id.build_9:
                if((Integer) build_9.getTag() == 0){
                    build_9.setTag(1);
                    build_9.setImageResource(R.drawable.build_9);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build9), hint);
                } else{
                    build_9.setTag(0);
                    build_9.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build9), hint);
                }
                break;
            case R.id.build_10:
                if((Integer) build_10.getTag() == 0){
                    build_10.setTag(1);
                    build_10.setImageResource(R.drawable.build_10);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build10), hint);
                } else{
                    build_10.setTag(0);
                    build_10.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build10), hint);
                }
                break;
            case R.id.build_11:
                if((Integer) build_11.getTag() == 0){
                    build_11.setTag(1);
                    build_11.setImageResource(R.drawable.build_11);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build11), hint);
                } else{
                    build_11.setTag(0);
                    build_11.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build11), hint);
                }
                break;
            case R.id.build_12:
                if((Integer) build_12.getTag() == 0){
                    build_12.setTag(1);
                    build_12.setImageResource(R.drawable.build_12);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build12), hint);
                } else{
                    build_12.setTag(0);
                    build_12.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build12), hint);
                }
                break;
            case R.id.build_13:
                if((Integer) build_13.getTag() == 0){
                    build_13.setTag(1);
                    build_13.setImageResource(R.drawable.build_13);
                    clearFilterActivate(true);

                } else{
                    build_13.setTag(0);
                    build_13.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                }
                break;
            case R.id.build_14:
                if((Integer) build_14.getTag() == 0){
                    build_14.setTag(1);
                    build_14.setImageResource(R.drawable.build_14);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build14), hint);
                } else{
                    build_14.setTag(0);
                    build_14.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build14), hint);
                }
                break;
            case R.id.build_15:
                if((Integer) build_15.getTag() == 0){
                    build_15.setTag(1);
                    build_15.setImageResource(R.drawable.build_15);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build15), hint);
                } else{
                    build_15.setTag(0);
                    build_15.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build15), hint);
                }
                break;
            case R.id.build_16:
                if((Integer) build_16.getTag() == 0){
                    build_16.setTag(1);
                    build_16.setImageResource(R.drawable.build_16);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build16), hint);
                } else{
                    build_16.setTag(0);
                    build_16.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build16), hint);
                }
                break;
            case R.id.build_17:
                if((Integer) build_17.getTag() == 0){
                    build_17.setTag(1);
                    build_17.setImageResource(R.drawable.build_17);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build17), hint);
                } else{
                    build_17.setTag(0);
                    build_17.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build17), hint);
                }
                break;
            case R.id.build_18:
                if((Integer) build_18.getTag() == 0){
                    build_18.setTag(1);
                    build_18.setImageResource(R.drawable.build_18);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build18), hint);
                } else{
                    build_18.setTag(0);
                    build_18.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build18), hint);
                }
                break;
            case R.id.build_19:
                if((Integer) build_19.getTag() == 0){
                    build_19.setTag(1);
                    build_19.setImageResource(R.drawable.build_19);
                    clearFilterActivate(true);

                } else{
                    build_19.setTag(0);
                    build_19.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                }
                break;
            case R.id.build_20:
                if((Integer) build_20.getTag() == 0){
                    build_20.setTag(1);
                    build_20.setImageResource(R.drawable.build_20);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build20), hint);
                } else{
                    build_20.setTag(0);
                    build_20.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build20), hint);
                }
                break;
            case R.id.build_21_1:
                if((Integer) build_21_1.getTag() == 0){
                    build_21_1.setTag(1);
                    build_21_1.setImageResource(R.drawable.build_21_1);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build21_1), hint);
                } else{
                    build_21_1.setTag(0);
                    build_21_1.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build21_1), hint);
                }
                break;
            case R.id.build_21_2:
                if((Integer) build_21_2.getTag() == 0){
                    build_21_2.setTag(1);
                    build_21_2.setImageResource(R.drawable.build_21_2);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build21_2), hint);
                } else{
                    build_21_2.setTag(0);
                    build_21_2.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build21_2), hint);
                }
                break;
            case R.id.build_21_3:
                if((Integer) build_21_3.getTag() == 0){
                    build_21_3.setTag(1);
                    build_21_3.setImageResource(R.drawable.build_21_3);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build21_3), hint);
                } else{
                    build_21_3.setTag(0);
                    build_21_3.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build21_3), hint);
                }
                break;
            case R.id.build_23:
                if((Integer) build_23.getTag() == 0){
                    build_23.setTag(1);
                    build_23.setImageResource(R.drawable.build_23);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build23), hint);
                } else{
                    build_23.setTag(0);
                    build_23.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build23), hint);
                }
                break;
        }
    }

    public void titleCost_BudgetOnClick(View v){
        switch (v.getId()){
            case R.id.titleCost:
                if((Integer) titleCost.getTag() == 0){
                    titleCost.setTag(1);
                    titleCost.setImageResource(R.drawable.title_cost_down);
                    titleBudget.setTag(0);
                    titleBudget.setImageResource(R.drawable.title_budget);
                    setDataToCostSeekBar();
                }
                break;
            case R.id.titleBudget:
                if((Integer) titleBudget.getTag() == 0){
                    titleBudget.setTag(1);
                    titleBudget.setImageResource(R.drawable.title_budget_down);
                    titleCost.setTag(0);
                    titleCost.setImageResource(R.drawable.title_cost);
                    setDataToCostSeekBar();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        if(settingsPanel.getVisibility() == View.VISIBLE || resultPanel.getVisibility() == View.VISIBLE) {
            if(settingsPanel.getVisibility() == View.VISIBLE){saveSettings();}
            scrollViewRight.setVisibility(View.VISIBLE);
            settingsPanel.setVisibility(View.GONE);
            resultPanel.setVisibility(View.GONE);
        }else {
            //если будет нажата кнопка назад на самом планшете, то программы свернется и не закроется
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    }

    public void setTimer(int minute) {

        if (timer != null) {
            timer.cancel();
        }
        timer = new myTimer(this, minute * 60000, 1000);
        //номер трека в плейлисте на время простоя
        timer.setTimerTrack(20);
        if (cfg.getDisableTimer() == false) {
            timer.start();
        }
    }

    private void ShowHint(View view, boolean activateHint, String text, TextView vHint){
        if (activateHint == true) {
            TextView vvHint = new TextView(getApplicationContext());
            vvHint = vHint;
            vvHint.setVisibility(View.VISIBLE);

            vvHint.setText(text);

            //animation.cancel();
//            vHint.setVisibility(View.INVISIBLE);
//
//            vHint.setVisibility(View.VISIBLE);

            vHint.setTranslationY(view.getTranslationY() - 50);
            if(view.getTranslationX()+vvHint.getWidth() > genPlanRiverSky.getWidth()){
                vvHint.setTranslationX(genPlanRiverSky.getWidth() - vvHint.getWidth());
            }else {
                vvHint.setTranslationX(view.getTranslationX());
            }
            hideHint(vvHint);
        }/**else{
            vHint.setVisibility(View.INVISIBLE);
        }*/
    }

    private void hideHint(TextView vHint){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        vHint.setVisibility(View.INVISIBLE);


                       // Log.d("myDebug","скрылся hint");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                if(vHint.getVisibility() == View.VISIBLE) {
                    vHint.startAnimation(animation);
                }
            }
        }, 1000);
    }

    private void clearFilterActivate(Boolean flag) {

        if (flag == true) {
            btnClear.setImageResource(R.drawable.button_clear_ifhas);
            //btnClear.setEnabled(flag);
            ListClearFilter.add(1);
           // Log.d("myDebug","count="+ListClearFilter.size());
        } else {
            int count = ListClearFilter.size();
            /*
                если кол-во = 1, значит всего одна кнопка была нажата, и при ее повторном нажатии можно кнопку фильтр делаеть не активной,
                также (или) если сам массив пустой, то также делать можно неактивной. перед этим методом обязательно нужно отчистить массив
                в тех местах где это необходимо.
                если кол-во > 1, и массив не пустой, а кто то послал запрос на то, чтобы сделать кнопку неактивной, то ничего не произойдет.
                такая ситуация может быть например если нажата кнопка 38 корпус, и следом 39. а потом пользователь подумал,а
                зачем мне 39, и отжал эту кнопку, а поскольку кнопка 38 еще нажата, и при проверка в массиве тоже будет больше 1,
                то кнопка не станет неактивной
            */

            if (count == 1 || count == 0) {
                btnClear.setImageResource(R.drawable.button_clear);
                //btnClear.setEnabled(flag);
                ListClearFilter.clear();
            }
//            if(count > 1){
//                btnClear.setImageResource(R.drawable.button_clear);
//                //btnClear.setEnabled(flag);
//                ListClearFilter.clear();
//            }
           // Log.d("myDebug","count="+ListClearFilter.size());
        }

    }
}
