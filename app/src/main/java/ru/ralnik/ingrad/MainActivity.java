package ru.ralnik.ingrad;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
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

import ru.ralnik.ingrad.config.myConfig;
import ru.ralnik.ingrad.customListView.listviewItemSelected;
import ru.ralnik.ingrad.customListView.myAdapter;
import ru.ralnik.ingrad.httpPlayer.HttpPlayerFactory;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;
import ru.ralnik.ingrad.permissions.MyPermissions;
import ru.ralnik.ingrad.sqlitedb.FlatRepository;
import ru.ralnik.ingrad.xml.Parser;

public class MainActivity extends AppCompatActivity {
//---------MAIN LAYOUT----------
    LinearLayout leftPanel;
    ScrollView scrollViewRight;
    LinearLayout resultPanel;
    LinearLayout settingsPanel;

//---------Components------------
  //------leftPanel----------------
    private ImageView btnFull;
    private ImageView btnLocation;
    private ImageView btnAbout;
    private ImageView btnTransport;
    private ImageView btnArea;
    private ImageView btnNaturales;
    private ImageView btnInfra;
    private ImageView btnOutsideInfra;
    private ImageView btnTorpedo;
    private ImageView btnSurround;
    private ImageView btnAdvantageProject;
    private ImageView btnGallary;
    private ImageView btnProcessBuildings;
  //---------Control panel-------------
    private ImageView btnPlayPause;
    private ImageView btnOptions;
    private ImageView btnVolume;

  //---------count room buttons-------
    private ImageView btnRoom1;
    private ImageView btnRoom2;
    private ImageView btnRoom3;
    private ImageView btnRoom4;
    private ImageView btnRoom5;
    private ImageView btnRoomEvro;

  //----------remont buttons------
    private ImageView btnWithoutRemont;
    private ImageView btnWithRemont;
    private ImageView btnWhiteBox;

  //---------Additional Attributes-----
    private ImageView btnTownHouse;
    private ImageView btnPentHouse;
    private ImageView btnDoubleFloorHouse;
    private ImageView btnTwoEnterHouse;
    private ImageView btnTerrasa;
    private ImageView btnWithFirePlace;
    private ImageView btnBathRoomWithWindow;
    private ImageView btnBalcon;

  // ---------- buttons period----------
    private ImageView check_period_4_2021;
    private ImageView check_period_3_2022;
    private ImageView check_period_4_2022;

  //---------Buttons filter---------
    private ImageView btnClear;
    private ImageView btnSearch;

 //--------Controll Settings--------
    private View viewSettingPanel;
    private SeekBar musicSeekBar;
    private SeekBar effectSeekBar;
    private EditText editWaitTime;
    private ImageView switcherTimer;
    private EditText editIP;
    private ImageView btnSave;

 //------- SEEKBAR-------------
    private CrystalRangeSeekbar seekbarFloor;
    private CrystalRangeSeekbar seekbarCost;
    private CrystalRangeSeekbar seekbarSquare;

    private EditText minFloorEdit;
    private EditText maxFloorEdit;

    private EditText minCostEdit;
    private EditText maxCostEdit;

    private EditText minSquareEdit;
    private EditText maxSquareEdit;

 //---------RESULT-----------
    private ListView listview;
    private TextView textNoRow;
 //---------Column name of listview

    private TextView colKorpus;
    private TextView colFlat;
    private TextView colFloor;
    private TextView colRooms;
    private TextView colSquare;
    private TextView colCost;
    private TextView colOther;

  // -------BUILDs-------------

    private ImageView build_3_1;
    private ImageView build_3_2;
    private ImageView build_4;
    private ImageView build_5;
    private ImageView build_6;
    private ImageView build_7;
    private ImageView build_8;
    private ImageView build_9;
    private ImageView build_10;
    private ImageView build_11;
    private ImageView build_12;
    private ImageView build_13;
    private ImageView build_14;
    private ImageView build_15;
    private ImageView build_16;
    private ImageView build_17;
    private ImageView build_18;
    private ImageView build_19;
    private ImageView build_20;
    private ImageView build_21_1;
    private ImageView build_21_2;
    private ImageView build_21_3;
    private ImageView build_23;

    private TextView hint;

    //------title cost and budget
    private ImageView titleCost;
    private ImageView titleBudget;

    //My variables
    AlertDialog alertDialog;
    String TAG = "myDebug";
    private myConfig cfg;
    private String query;
    //private VVVVPlayer vvvv;
    private PlayerCommands vvvv;
    private myTimer timer;

    ArrayList<Integer> ListClearFilter = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //initialize config
        cfg = new myConfig(this);

        //vvvv = new VVVVPlayer(cfg.getHost());
        vvvv = HttpPlayerFactory.getInstance(this).getCommand();

         //load data from web xml links
         //loadDataFromXML();



        //initialization all component
        init();
        initSettings();

        //verify permissions read/write
        if(new MyPermissions(this,this).verifyStoragePermissions()){
            leftPanel.setVisibility(View.VISIBLE);
            scrollViewRight.setVisibility(View.VISIBLE);
            resultPanel.setVisibility(View.GONE);
        }

        //AppDatabase db = AppDatabase.getInstance(this);
        /*
        new Thread(new Runnable() {
            public void run() {
                Log.d(TAG, "mas_size" + new FlatRepository(getApplicationContext()).findByIds(new String[]{"9f8f62ae-63a0-e811-80f9-005056ba18b6"}).size());
            }
        }).start();
        */



       //scrollViewRight.setVisibility(View.GONE);
        //resultPanel.setVisibility(View.VISIBLE);
        //settingsPanel.setVisibility(View.GONE);
    }

    private void loadDataFromXML() {
            Parser parser1 = new Parser(this);
            parser1.setUrl("http://crm.dmfs.ru:8080/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=D9AF2F75-A99F-E811-80F9-005056BA18B6&export=false");
            parser1.execute();

            Parser parser2 = new Parser(this);
            parser2.setUrl("http://crm.dmfs.ru:8080/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=517BA460-B39F-E811-80F9-005056BA18B6&export=false");
            parser2.execute();

            Parser parser3 = new Parser(this);
            parser3.setUrl("http://crm.dmfs.ru:8080/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=C545D97A-B39F-E811-80F9-005056BA18B6&export=false");
            parser3.execute();

            Parser parser4= new Parser(this);
            parser4.setUrl("http://crm.dmfs.ru:8080/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=E8671A06-B49F-E811-80F9-005056BA18B6&export=false");
            parser4.execute();

            Parser parser5 = new Parser(this);
            parser5.setUrl("http://crm.dmfs.ru:8080/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=6F035221-B49F-E811-80F9-005056BA18B6&export=false");
            parser5.execute();

            Parser parser6 = new Parser(this);
            parser6.setUrl("http://crm.dmfs.ru:8080/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=739AB24A-B49F-E811-80F9-005056BA18B6&export=false");
            parser6.execute();

            Parser parser7 = new Parser(this);
            parser7.setUrl("http://crm.dmfs.ru:8080/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=B22CD269-B49F-E811-80F9-005056BA18B6&export=false");
            parser7.execute();

            Parser parser8 = new Parser(this);
            parser8.setUrl("http://crm.dmfs.ru:8080/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=70FCF484-B49F-E811-80F9-005056BA18B6&export=false");
            parser8.execute();

    }

    private void init(){
        leftPanel = (LinearLayout) findViewById(R.id.leftPanel);
        scrollViewRight = (ScrollView) findViewById(R.id.scrollViewRight);
        resultPanel = (LinearLayout) findViewById(R.id.resultPanel);
        settingsPanel = (LinearLayout) findViewById(R.id.settingsPanel);
        leftPanel.setVisibility(View.INVISIBLE);
        scrollViewRight.setVisibility(View.INVISIBLE);
        resultPanel.setVisibility(View.GONE);
        settingsPanel.setVisibility(View.GONE);

        btnFull = (ImageView) findViewById(R.id.btnFull);
        btnLocation = (ImageView) findViewById(R.id.btnLocation);
        btnAbout = (ImageView) findViewById(R.id.btnAbout);
        btnTransport = (ImageView) findViewById(R.id.btnTransport);
        btnArea = (ImageView) findViewById(R.id.btnArea);
        btnNaturales = (ImageView) findViewById(R.id.btnNaturals);
        btnInfra = (ImageView) findViewById(R.id.btnInfra);
        btnOutsideInfra = (ImageView) findViewById(R.id.btnOutsideInfra);
        btnTorpedo = (ImageView) findViewById(R.id.btnTorpedo);
        btnSurround = (ImageView) findViewById(R.id.btnSurround);
        btnAdvantageProject = (ImageView) findViewById(R.id.btnAdvantageProject);
        btnGallary = (ImageView) findViewById(R.id.btnGallary);
        btnProcessBuildings = (ImageView) findViewById(R.id.btnProcessBuildings);

        btnPlayPause = (ImageView) findViewById(R.id.btnPlayPause);
        btnVolume = (ImageView) findViewById(R.id.btnVolume);
        btnOptions = (ImageView) findViewById(R.id.btnOptions);
        btnPlayPause.setTag(0);
        btnVolume.setTag(0);

        btnRoom1 = (ImageView) findViewById(R.id.btnRoom1);
        btnRoom2 = (ImageView) findViewById(R.id.btnRoom2);
        btnRoom3 = (ImageView) findViewById(R.id.btnRoom3);
        btnRoom4 = (ImageView) findViewById(R.id.btnRoom4);
        btnRoom5 = (ImageView) findViewById(R.id.btnRoom5);
        btnRoomEvro = (ImageView) findViewById(R.id.btnRoomEvro);
        btnRoom1.setTag(0);
        btnRoom2.setTag(0);
        btnRoom3.setTag(0);
        btnRoom4.setTag(0);
        btnRoom5.setTag(0);
        btnRoomEvro.setTag(0);

        btnWithoutRemont = (ImageView) findViewById(R.id.btnWithoutRemont);
        btnWithRemont = (ImageView) findViewById(R.id.btnWithRemont);
        btnWhiteBox = (ImageView) findViewById(R.id.btnWhiteBox);
        btnWhiteBox.setTag(0);
        btnWithoutRemont.setTag(0);
        btnWithRemont.setTag(0);

        check_period_4_2021 = (ImageView) findViewById(R.id.btnSrok_4_2021);
        check_period_3_2022 = (ImageView) findViewById(R.id.btnSrok_3_2022);
        check_period_4_2022 = (ImageView) findViewById(R.id.btnSrok_4_2022);
        check_period_4_2021.setTag(0);
        check_period_3_2022.setTag(0);
        check_period_4_2022.setTag(0);

        btnTownHouse = (ImageView) findViewById(R.id.btnTownHouse);
        btnPentHouse = (ImageView) findViewById(R.id.btnPentHouse);
        btnDoubleFloorHouse = (ImageView) findViewById(R.id.btnDoubleFloorHouse);
        btnTwoEnterHouse = (ImageView) findViewById(R.id.btnTwoEnterHouse);
        btnTerrasa = (ImageView) findViewById(R.id.btnTerrasa);
        btnWithFirePlace = (ImageView) findViewById(R.id.btnWithFirePlace);
        btnBathRoomWithWindow = (ImageView) findViewById(R.id.btnBathRoomWithWindow);
        btnBalcon = (ImageView) findViewById(R.id.btnBalcon);
        btnTownHouse.setTag(0);
        btnPentHouse.setTag(0);
        btnDoubleFloorHouse.setTag(0);
        btnTwoEnterHouse.setTag(0);
        btnTerrasa.setTag(0);
        btnWithFirePlace.setTag(0);
        btnBathRoomWithWindow.setTag(0);
        btnBalcon.setTag(0);

        btnClear = (ImageView) findViewById(R.id.btnClear);
        btnSearch = (ImageView) findViewById(R.id.btnSearch);

        titleCost = (ImageView) findViewById(R.id.titleCost);
        titleBudget = (ImageView) findViewById(R.id.titleBudget);
        titleCost.setTag(1);
        titleBudget.setTag(0);
        titleBudget.setImageResource(R.drawable.title_budget);
        titleCost .setImageResource(R.drawable.title_cost_down);

        seekbarFloor  = (CrystalRangeSeekbar) findViewById(R.id.seekBarFloor);
        seekbarCost   = (CrystalRangeSeekbar) findViewById(R.id.seekBarCost);
        seekbarSquare = (CrystalRangeSeekbar) findViewById(R.id.seekBarSquare);
        minFloorEdit = (EditText) findViewById(R.id.minFloorEdit);
        maxFloorEdit = (EditText) findViewById(R.id.maxFloorEdit);;
        minCostEdit = (EditText) findViewById(R.id.minCostEdit);
        maxCostEdit = (EditText) findViewById(R.id.maxCostEdit);
        minSquareEdit = (EditText) findViewById(R.id.minSquareEdit);
        maxSquareEdit = (EditText) findViewById(R.id.maxSquareEdit);
        SeekbarSquareOnChange();
        SeekbarFloorOnChange();
        SeekbarCostOnChange();
        setValuesToSeekBar();

        listview = (ListView) findViewById(R.id.listview);
        textNoRow = (TextView) findViewById(R.id.textNoRow);

        colKorpus = (TextView) findViewById(R.id.colKorpus);
        colFlat  = (TextView) findViewById(R.id.colFlat);
        colFloor = (TextView) findViewById(R.id.colFloor);
        colRooms = (TextView) findViewById(R.id.colRooms);
        colSquare = (TextView) findViewById(R.id.colSquare);
        colCost = (TextView) findViewById(R.id.colCost);
        colOther = (TextView) findViewById(R.id.colOther);

        build_3_1 = (ImageView) findViewById(R.id.build_3_1);
        build_3_2 = (ImageView) findViewById(R.id.build_3_2);
        build_4   = (ImageView) findViewById(R.id.build_4);
        build_5   = (ImageView) findViewById(R.id.build_5);
        build_6   = (ImageView) findViewById(R.id.build_6);
        build_7   = (ImageView) findViewById(R.id.build_7);
        build_8   = (ImageView) findViewById(R.id.build_8);
        build_9   = (ImageView) findViewById(R.id.build_9);
        build_10  = (ImageView) findViewById(R.id.build_10);
        build_11  = (ImageView) findViewById(R.id.build_11);
        build_12  = (ImageView) findViewById(R.id.build_12);
        build_13  = (ImageView) findViewById(R.id.build_13);
        build_14  = (ImageView) findViewById(R.id.build_14);
        build_15  = (ImageView) findViewById(R.id.build_15);
        build_16  = (ImageView) findViewById(R.id.build_16);
        build_17  = (ImageView) findViewById(R.id.build_17);
        build_18  = (ImageView) findViewById(R.id.build_18);
        build_19  = (ImageView) findViewById(R.id.build_19);
        build_20  = (ImageView) findViewById(R.id.build_20);
        build_21_1 = (ImageView) findViewById(R.id.build_21_1);
        build_21_2 = (ImageView) findViewById(R.id.build_21_2);
        build_21_3 = (ImageView) findViewById(R.id.build_21_3);
        build_23  = (ImageView) findViewById(R.id.build_23);

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

        hint = (TextView) findViewById(R.id.hint);
        hint.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/panroman.ttf"));
        hint.setVisibility(View.GONE);

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
                Log.d(TAG,String.valueOf(effectSeekBar.getProgress()));
                //GlobalVar.volEffects = String.valueOf(EffectSeekBar.getProgress());
                //GlobalVar.lastLink = VVVV.fullLink();
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
        query = "select * from flats where ";

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

        if((Integer) build_3_1.getTag() == 1 ){query = query + " and (SectionNumber=1 and AddressNumber=8)";}
        if((Integer) build_3_2.getTag() == 1 ){query = query + " and (SectionNumber=2 and AddressNumber=8)";}
        if((Integer) build_4.getTag() == 1 ){query = query + " and (SectionNumber=3 and AddressNumber=8)";}
        if((Integer) build_5.getTag() == 1 ){query = query + " and (SectionNumber=4 and AddressNumber=8)";}
        if((Integer) build_6.getTag() == 1 ){query = query + " and (SectionNumber=5 and AddressNumber=8)";}
        //if((Integer) build_7.getTag() == 1 ){query = query + " and (SectionNumber=1 and AddressNumber=8)";}
        if((Integer) build_8.getTag() == 1 ){query = query + " and (SectionNumber=1 and AddressNumber=5)";}
        if((Integer) build_9.getTag() == 1 ){query = query + " and (SectionNumber=1 and AddressNumber=4)";}
        if((Integer) build_10.getTag() == 1 ){query = query + " and (SectionNumber=2 and AddressNumber=4)";}
        if((Integer) build_11.getTag() == 1 ){query = query + " and (SectionNumber=3 and AddressNumber=4)";}
        if((Integer) build_12.getTag() == 1 ){query = query + " and (SectionNumber=1 and AddressNumber=1)";}
        //if((Integer) build_13.getTag() == 1 ){query = query + " and (SectionNumber=3 and AddressNumber=8)";}
        if((Integer) build_14.getTag() == 1 ){query = query + " and (SectionNumber=5 and AddressNumber=2)";}
        if((Integer) build_15.getTag() == 1 ){query = query + " and (SectionNumber=4 and AddressNumber=2)";}
        if((Integer) build_16.getTag() == 1 ){query = query + " and (SectionNumber=3 and AddressNumber=2)";}
        if((Integer) build_17.getTag() == 1 ){query = query + " and (SectionNumber=2 and AddressNumber=2)";}
        if((Integer) build_18.getTag() == 1 ){query = query + " and (SectionNumber=1 and AddressNumber=2)";}
        //if((Integer) build_19.getTag() == 1 ){query = query + " and (SectionNumber=3 and AddressNumber=8)";}
        if((Integer) build_20.getTag() == 1 ){query = query + " and (SectionNumber=1 and AddressNumber=3)";}
        if((Integer) build_21_1.getTag() == 1 ){query = query + " and (SectionNumber=1 and AddressNumber=6)";}
        if((Integer) build_21_2.getTag() == 1 ){query = query + " and (SectionNumber=2 and AddressNumber=6)";}
        if((Integer) build_21_3.getTag() == 1 ){query = query + " and (SectionNumber=3 and AddressNumber=6)";}
        if((Integer) build_23.getTag() == 1 ){query = query + " and (SectionNumber=1 and AddressNumber=7)";}


        if((Integer) check_period_4_2021.getTag() == 1){query = query + " and DeliveryPeriod = '4к2021'";}
        if((Integer) check_period_3_2022.getTag() == 1){query = query + " and DeliveryPeriod = '3к2022'";}
        if((Integer) check_period_4_2022.getTag() == 1){query = query + " and DeliveryPeriod = '4к2022'";}

        //выводить только тех у кого цена за кв.метр не равна 1
        query += " and (discountmax/Quantity) > 1";

        Log.d(TAG, query + " order by DiscountMax");
        loadFromSqlite(query + " order by DiscountMax");
    }

    private void clearFilter() {
        hint.setVisibility(View.GONE);
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
    }


    public void buildsOnClick(View v){
        switch (v.getId()){
            case R.id.build_3_1:

                if((Integer) build_3_1.getTag() == 0){
                   build_3_1.setTag(1);
                   build_3_1.setImageResource(R.drawable.build_3_1);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build3_1));
                } else{
                    build_3_1.setTag(0);
                    build_3_1.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build3_1));
                }
            break;
            case R.id.build_3_2:
                if((Integer) build_3_2.getTag() == 0){
                    build_3_2.setTag(1);
                    build_3_2.setImageResource(R.drawable.build_3_2);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build3_2));
                } else{
                    build_3_2.setTag(0);
                    build_3_2.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build3_2));
                }
                break;
            case R.id.build_4:
                if((Integer) build_4.getTag() == 0){
                    build_4.setTag(1);
                    build_4.setImageResource(R.drawable.build_4);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build4));
                } else{
                    build_4.setTag(0);
                    build_4.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build4));
                }
                break;
            case R.id.build_5:
                if((Integer) build_5.getTag() == 0){
                    build_5.setTag(1);
                    build_5.setImageResource(R.drawable.build_5);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build5));
                } else{
                    build_5.setTag(0);
                    build_5.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build5));
                }
                break;
            case R.id.build_6:
                if((Integer) build_6.getTag() == 0){
                    build_6.setTag(1);
                    build_6.setImageResource(R.drawable.build_6);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build6));
                } else{
                    build_6.setTag(0);
                    build_6.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build6));
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
                    ShowHint(v,true, getString(R.string.build8));
                } else{
                    build_8.setTag(0);
                    build_8.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build8));
                }
                break;
            case R.id.build_9:
                if((Integer) build_9.getTag() == 0){
                    build_9.setTag(1);
                    build_9.setImageResource(R.drawable.build_9);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build9));
                } else{
                    build_9.setTag(0);
                    build_9.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build9));
                }
                break;
            case R.id.build_10:
                if((Integer) build_10.getTag() == 0){
                    build_10.setTag(1);
                    build_10.setImageResource(R.drawable.build_10);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build10));
                } else{
                    build_10.setTag(0);
                    build_10.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build10));
                }
                break;
            case R.id.build_11:
                if((Integer) build_11.getTag() == 0){
                    build_11.setTag(1);
                    build_11.setImageResource(R.drawable.build_11);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build11));
                } else{
                    build_11.setTag(0);
                    build_11.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build11));
                }
                break;
            case R.id.build_12:
                if((Integer) build_12.getTag() == 0){
                    build_12.setTag(1);
                    build_12.setImageResource(R.drawable.build_12);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build12));
                } else{
                    build_12.setTag(0);
                    build_12.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build12));
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
                    ShowHint(v,true, getString(R.string.build14));
                } else{
                    build_14.setTag(0);
                    build_14.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build14));
                }
                break;
            case R.id.build_15:
                if((Integer) build_15.getTag() == 0){
                    build_15.setTag(1);
                    build_15.setImageResource(R.drawable.build_15);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build15));
                } else{
                    build_15.setTag(0);
                    build_15.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build15));
                }
                break;
            case R.id.build_16:
                if((Integer) build_16.getTag() == 0){
                    build_16.setTag(1);
                    build_16.setImageResource(R.drawable.build_16);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build16));
                } else{
                    build_16.setTag(0);
                    build_16.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build16));
                }
                break;
            case R.id.build_17:
                if((Integer) build_17.getTag() == 0){
                    build_17.setTag(1);
                    build_17.setImageResource(R.drawable.build_17);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build17));
                } else{
                    build_17.setTag(0);
                    build_17.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build17));
                }
                break;
            case R.id.build_18:
                if((Integer) build_18.getTag() == 0){
                    build_18.setTag(1);
                    build_18.setImageResource(R.drawable.build_18);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build18));
                } else{
                    build_18.setTag(0);
                    build_18.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build18));
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
                    ShowHint(v,true, getString(R.string.build20));
                } else{
                    build_20.setTag(0);
                    build_20.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build20));
                }
                break;
            case R.id.build_21_1:
                if((Integer) build_21_1.getTag() == 0){
                    build_21_1.setTag(1);
                    build_21_1.setImageResource(R.drawable.build_21_1);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build21_1));
                } else{
                    build_21_1.setTag(0);
                    build_21_1.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build21_1));
                }
                break;
            case R.id.build_21_2:
                if((Integer) build_21_2.getTag() == 0){
                    build_21_2.setTag(1);
                    build_21_2.setImageResource(R.drawable.build_21_2);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build21_2));
                } else{
                    build_21_2.setTag(0);
                    build_21_2.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build21_2));
                }
                break;
            case R.id.build_21_3:
                if((Integer) build_21_3.getTag() == 0){
                    build_21_3.setTag(1);
                    build_21_3.setImageResource(R.drawable.build_21_3);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build21_3));
                } else{
                    build_21_3.setTag(0);
                    build_21_3.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build21_3));
                }
                break;
            case R.id.build_23:
                if((Integer) build_23.getTag() == 0){
                    build_23.setTag(1);
                    build_23.setImageResource(R.drawable.build_23);
                    clearFilterActivate(true);
                    ShowHint(v,true, getString(R.string.build23));
                } else{
                    build_23.setTag(0);
                    build_23.setImageResource(R.drawable.empty);
                    clearFilterActivate(false);
                    ShowHint(v,false, getString(R.string.build23));
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
        if(cfg.getDisableTimer() == false){
            timer.start();
        }
    }

    private void ShowHint(View view, boolean activateHint, String text){
        if (activateHint == true) {
            hint.setText(text);
            hint.setVisibility(View.VISIBLE);
            hint.setTranslationX(view.getTranslationX());
            hint.setTranslationY(view.getTranslationY() - 50);
        }else{
            hint.setVisibility(View.GONE);
        }

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
