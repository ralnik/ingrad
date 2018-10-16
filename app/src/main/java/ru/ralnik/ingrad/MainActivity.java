package ru.ralnik.ingrad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import java.util.ArrayList;
import java.util.List;

import ru.ralnik.ingrad.config.myConfig;
import ru.ralnik.ingrad.customListView.listviewItemSelected;
import ru.ralnik.ingrad.customListView.myAdapter;
import ru.ralnik.ingrad.httpPlayer.VVVVPlayer;
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

  // ---------- buttons period----------
    private ImageView check_period_2_2022;
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

 //---------LISTVIEW-----------
    private ListView listview;
 //---------Column name of listview

    private TextView colKorpus;
    private TextView colFlat;
    private TextView colFloor;
    private TextView colRooms;
    private TextView colSquare;
    private TextView colCost;
    private TextView colOther;

    //My variables
    AlertDialog alertDialog;
    String TAG = "myDebug";
    private myConfig cfg;
    private String query;
    private VVVVPlayer vvvv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //initialize config
        cfg = new myConfig(this);

        vvvv = new VVVVPlayer(cfg.getHost());

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

        check_period_2_2022 = (ImageView) findViewById(R.id.btnSrok_2_2022);
        check_period_3_2022 = (ImageView) findViewById(R.id.btnSrok_3_2022);
        check_period_4_2022 = (ImageView) findViewById(R.id.btnSrok_4_2022);
        check_period_2_2022.setTag(0);
        check_period_3_2022.setTag(0);
        check_period_4_2022.setTag(0);

        btnTownHouse = (ImageView) findViewById(R.id.btnTownHouse);
        btnPentHouse = (ImageView) findViewById(R.id.btnPentHouse);
        btnDoubleFloorHouse = (ImageView) findViewById(R.id.btnDoubleFloorHouse);
        btnTwoEnterHouse = (ImageView) findViewById(R.id.btnTwoEnterHouse);
        btnTerrasa = (ImageView) findViewById(R.id.btnTerrasa);
        btnWithFirePlace = (ImageView) findViewById(R.id.btnWithFirePlace);
        btnBathRoomWithWindow = (ImageView) findViewById(R.id.btnBathRoomWithWindow);
        btnTownHouse.setTag(0);
        btnPentHouse.setTag(0);
        btnDoubleFloorHouse.setTag(0);
        btnTwoEnterHouse.setTag(0);
        btnTerrasa.setTag(0);
        btnWithFirePlace.setTag(0);
        btnBathRoomWithWindow.setTag(0);

        btnClear = (ImageView) findViewById(R.id.btnClear);
        btnSearch = (ImageView) findViewById(R.id.btnSearch);

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

        colKorpus = (TextView) findViewById(R.id.colKorpus);
        colFlat  = (TextView) findViewById(R.id.colFlat);
        colFloor = (TextView) findViewById(R.id.colFloor);
        colRooms = (TextView) findViewById(R.id.colRooms);
        colSquare = (TextView) findViewById(R.id.colSquare);
        colCost = (TextView) findViewById(R.id.colCost);
        colOther = (TextView) findViewById(R.id.colOther);
    }

    private void setValuesToSeekBar(){
//    try {
        cfg.setMinCost(Float.valueOf(new FlatRepository(this).getMin("Cost").toString()));
        cfg.setMaxCost(Float.valueOf(new FlatRepository(this).getMax("Cost").toString()));
        cfg.setMinFloor((int) new FlatRepository(this).getMin("Floor"));
        cfg.setMaxFloor((int) new FlatRepository(this).getMax("Floor"));
        cfg.setMinSquare((float) new FlatRepository(this).getMin("Square"));
        cfg.setMaxSquare((float) new FlatRepository(this).getMax("Square"));
//    }catch (Exception e){
//        e.printStackTrace();
//    }

        seekbarCost.setMinValue(cfg.getMinCost());
        seekbarCost.setMaxValue(cfg.getMaxCost());
        minCostEdit.setText(String.valueOf(cfg.getMinCost()));
        maxCostEdit.setText(String.valueOf(cfg.getMaxCost()));

        seekbarFloor.setMinValue(cfg.getMinFloor());
        seekbarFloor.setMaxValue(cfg.getMaxFloor());
        minFloorEdit.setText(String.valueOf(cfg.getMinFloor()));
        maxFloorEdit.setText(String.valueOf(cfg.getMaxFloor()));

        seekbarSquare.setMinValue(cfg.getMinSquare());
        seekbarSquare.setMaxValue(cfg.getMaxSquare());
        minSquareEdit.setText(String.valueOf(cfg.getMinSquare()));
        maxSquareEdit.setText(String.valueOf(cfg.getMaxSquare()));

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
                //clearFilterActivate(true);
            }
        });
    }

    public void SeekbarCostOnChange(){
        seekbarCost.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {

                float min = Float.valueOf(minValue.toString()) / 1000000 ;
                float max = Float.valueOf(maxValue.toString()) / 1000000 ;
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
                //clearFilterActivate(true);
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
                //clearFilterActivate(true);
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
                Log.d(TAG,String.valueOf(musicSeekBar.getProgress()));
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
        //Log.d(TAG,"save config");
        if(switcherTimer.getTag().equals("on")) {
            cfg.setDisableTimer(true);
        }else{
            cfg.setDisableTimer(false);
        }

        cfg.setVolumeProgress(String.valueOf(musicSeekBar.getProgress()));
        cfg.setEffectProgress(String.valueOf(effectSeekBar.getProgress()));

        cfg.setTimer(String.valueOf(editWaitTime.getText()));
        cfg.setHost(editIP.getText().toString());
        settingsPanel.setVisibility(View.GONE);
        scrollViewRight.setVisibility(View.VISIBLE);
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
                vvvv.selectById(1);
                break;
            case R.id.btnLocation:
                btnLocation.setImageResource(R.drawable.button_location_down);
                vvvv.selectById(2);
                break;
            case R.id.btnAbout:
                btnAbout.setImageResource(R.drawable.button_about_down);
                vvvv.selectById(3);
                break;
            case R.id.btnTransport:
                btnTransport.setImageResource(R.drawable.button_transport_down);
                vvvv.selectById(4);
                break;
            case R.id.btnArea:
                btnArea.setImageResource(R.drawable.button_area_down);
                vvvv.selectById(5);
                break;
            case R.id.btnNaturals:
                btnNaturales.setImageResource(R.drawable.button_naturales_down);
                vvvv.selectById(6);
                break;
            case R.id.btnInfra:
                btnInfra.setImageResource(R.drawable.button_infra_down);
                vvvv.selectById(7);
                break;
            case R.id.btnOutsideInfra:
                btnOutsideInfra.setImageResource(R.drawable.button_outside_infra_down);
                //showMyWindow();
                new OuterInfraActivity(this);
                break;
            case R.id.btnTorpedo:
                btnTorpedo.setImageResource(R.drawable.button_torpedo_down);
                vvvv.selectById(8);
                break;
            case R.id.btnSurround:
                btnSurround.setImageResource(R.drawable.button_surround_down);
                vvvv.selectById(9);
                break;
            case R.id.btnAdvantageProject:
                btnAdvantageProject.setImageResource(R.drawable.button_advantage_project_down);
                vvvv.selectById(10);
                break;
            case R.id.btnGallary:
                btnGallary.setImageResource(R.drawable.button_gallary_down);
                vvvv.selectById(11);
                break;
            case R.id.btnProcessBuildings:
                btnProcessBuildings.setImageResource(R.drawable.button_process_buildings_down);
                vvvv.selectById(12);
                break;
        }
    }

    public void conlrolPanelButtonOnClick(View view){
        switch (view.getId()){
            case R.id.btnPlayPause:
                if(btnPlayPause.getTag() == (Object) 1 ){
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
                if(btnVolume.getTag() == (Object) 1 ){
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
                if(btnRoom1.getTag() == (Object) 1 ){
                    btnRoom1.setImageResource(R.drawable.button_room1);
                    btnRoom1.setTag(0);
                }else{
                    btnRoom1.setImageResource(R.drawable.button_room1_down);
                    btnRoom1.setTag(1);
                }
                break;
            case R.id.btnRoom2:
                if(btnRoom2.getTag() == (Object) 1 ){
                    btnRoom2.setImageResource(R.drawable.button_room2);
                    btnRoom2.setTag(0);
                }else{
                    btnRoom2.setImageResource(R.drawable.button_room2_down);
                    btnRoom2.setTag(2);
                }
                break;
            case R.id.btnRoom3:
                if(btnRoom3.getTag() == (Object) 1 ){
                    btnRoom3.setImageResource(R.drawable.button_room3);
                    btnRoom3.setTag(0);
                }else{
                    btnRoom3.setImageResource(R.drawable.button_room3_down);
                    btnRoom3.setTag(3);
                }
                break;
            case R.id.btnRoom4:
                if(btnRoom4.getTag() == (Object) 1 ){
                    btnRoom4.setImageResource(R.drawable.button_room4);
                    btnRoom4.setTag(0);
                }else{
                    btnRoom4.setImageResource(R.drawable.button_room4_down);
                    btnRoom4.setTag(4);
                }
                break;
            case R.id.btnRoom5:
                if(btnRoom5.getTag() == (Object) 1 ){
                    btnRoom5.setImageResource(R.drawable.button_room5);
                    btnRoom5.setTag(0);
                }else{
                    btnRoom5.setImageResource(R.drawable.button_room5_down);
                    btnRoom5.setTag(5);
                }
                break;
            case R.id.btnRoomEvro:
                if(btnRoomEvro.getTag() == (Object) 1 ){
                    btnRoomEvro.setImageResource(R.drawable.button_room_evro);
                    btnRoomEvro.setTag(0);
                }else{
                    btnRoomEvro.setImageResource(R.drawable.button_room_evro_down);
                    btnRoomEvro.setTag(6);
                }
                break;
        }
    }

    public void periodOnClick(View view){
        switch (view.getId()){
            case R.id.btnSrok_2_2022:
                if(check_period_2_2022.getTag() == (Object) 1 ){
                    check_period_2_2022.setImageResource(R.drawable.checkbox_srok_2_2022);
                    check_period_2_2022.setTag(0);
                }else{
                    check_period_2_2022.setImageResource(R.drawable.checkbox_srok_2_2022_down);
                    check_period_2_2022.setTag(1);
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
                if(btnWithoutRemont.getTag() == (Object) 1 ){
                    btnWithoutRemont.setImageResource(R.drawable.button_without_remont);
                    btnWithoutRemont.setTag(0);
                }else{
                    btnWithoutRemont.setImageResource(R.drawable.button_without_remont_down);
                    btnWithoutRemont.setTag(1);
                }
                break;
            case R.id.btnWithRemont:
                if(btnWithRemont.getTag() == (Object) 1 ){
                    btnWithRemont.setImageResource(R.drawable.button_with_remont);
                    btnWithRemont.setTag(0);
                }else{
                    btnWithRemont.setImageResource(R.drawable.button_with_remont_down);
                    btnWithRemont.setTag(1);
                }
                break;
            case R.id.btnWhiteBox:
                if(btnWhiteBox.getTag() == (Object) 1 ){
                    btnWhiteBox.setImageResource(R.drawable.button_remont_whitebox);
                    btnWhiteBox.setTag(0);
                }else{
                    btnWhiteBox.setImageResource(R.drawable.button_remont_whitebox_down);
                    btnWhiteBox.setTag(1);
                }
                break;
        }

    }


    public void addAttrButtonsOnClick(View view){
        switch (view.getId()){
            case R.id.btnTownHouse:
                if(btnTownHouse.getTag() == (Object) 1 ){
                    btnTownHouse.setImageResource(R.drawable.button_townhouse);
                    btnTownHouse.setTag(0);
                }else{
                    btnTownHouse.setImageResource(R.drawable.button_townhouse_down);
                    btnTownHouse.setTag(1);
                }
                break;
            case R.id.btnPentHouse:
                if(btnPentHouse.getTag() == (Object) 1 ){
                    btnPentHouse.setImageResource(R.drawable.button_penthouse);
                    btnPentHouse.setTag(0);
                }else{
                    btnPentHouse.setImageResource(R.drawable.button_penthouse_down);
                    btnPentHouse.setTag(1);
                }
                break;
            case R.id.btnDoubleFloorHouse:
                if(btnDoubleFloorHouse.getTag() == (Object) 1 ){
                    btnDoubleFloorHouse.setImageResource(R.drawable.button_doublefloorhouse);
                    btnDoubleFloorHouse.setTag(0);
                }else{
                    btnDoubleFloorHouse.setImageResource(R.drawable.button_doublefloorhouse_down);
                    btnDoubleFloorHouse.setTag(1);
                }
                break;
            case R.id.btnTwoEnterHouse:
                if(btnTwoEnterHouse.getTag() == (Object) 1 ){
                    btnTwoEnterHouse.setImageResource(R.drawable.button_twoenterhouse);
                    btnTwoEnterHouse.setTag(0);
                }else{
                    btnTwoEnterHouse.setImageResource(R.drawable.button_twoenterhouse_down);
                    btnTwoEnterHouse.setTag(1);
                }
                break;
            case R.id.btnTerrasa:
                if(btnTerrasa.getTag() == (Object) 1 ){
                    btnTerrasa.setImageResource(R.drawable.button_terrasa);
                    btnTerrasa.setTag(0);
                }else{
                    btnTerrasa.setImageResource(R.drawable.button_terrasa_down);
                    btnTerrasa.setTag(1);
                }
                break;
            case R.id.btnWithFirePlace:
                if(btnWithFirePlace.getTag() == (Object) 1 ){
                    btnWithFirePlace.setImageResource(R.drawable.button_with_fireplace);
                    btnWithFirePlace.setTag(0);
                }else{
                    btnWithFirePlace.setImageResource(R.drawable.button_with_fireplace_down);
                    btnWithFirePlace.setTag(1);
                }
                break;
            case R.id.btnBathRoomWithWindow:
                if(btnBathRoomWithWindow.getTag() == (Object) 1 ){
                    btnBathRoomWithWindow.setImageResource(R.drawable.button_bathroom_with_window);
                    btnBathRoomWithWindow.setTag(0);
                }else{
                    btnBathRoomWithWindow.setImageResource(R.drawable.button_bathroom_with_window_down);
                    btnBathRoomWithWindow.setTag(1);
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
                //order = " order by ";
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
                //order = " order by ";
                break;
            case R.id.colOther:
                //order = " order by ";
                break;
        }
        loadFromSqlite(query + order);
    }

    private void loadFromSqlite(String sql){
        //Log.d(TAG, "mas_size" + new FlatRepository(getApplicationContext()).getAll().size());
        myAdapter adapter = new myAdapter(this, new FlatRepository(this).getFlatsByQuery(sql),0);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new listviewItemSelected(this));

        scrollViewRight.setVisibility(View.GONE);
        settingsPanel.setVisibility(View.GONE);
        resultPanel.setVisibility(View.VISIBLE);
    }

    private void applyFilter() {
        query = "select * from flats where ";

        query = query + " (floor >= "+seekbarFloor.getSelectedMinValue() + " and floor <= "+seekbarFloor.getSelectedMaxValue()+") ";
        query = query + " and (Quantity >= " + seekbarSquare.getSelectedMinValue() + " and Quantity <= "+seekbarSquare.getSelectedMaxValue() + ") ";
        query = query + " and (DiscountMax >= " + seekbarCost.getSelectedMinValue() + " and DiscountMax <= " + seekbarCost.getSelectedMaxValue() + ") ";

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



        //remont

        //additional parameter
        if(Integer.valueOf(btnTownHouse.getTag().toString())>0){query = query + " and TownHouse > 0"; }
        if(Integer.valueOf(btnPentHouse.getTag().toString())>0){query = query + " and PentHouse > 0"; }
        if(Integer.valueOf(btnDoubleFloorHouse.getTag().toString())>0){query = query + " and TwoLevel > 0"; }
        if(Integer.valueOf(btnTwoEnterHouse.getTag().toString())>0){query = query + " and SeparateEntrance > 0"; }
        if(Integer.valueOf(btnTwoEnterHouse.getTag().toString())>0){query = query + " and SeparateEntrance > 0"; }
        if(Integer.valueOf(btnTerrasa.getTag().toString())>0){ query = query + " and Terrace > 0";}
        if(Integer.valueOf(btnWithFirePlace.getTag().toString())>0){ query = query + " and FirePlace > 0";}
        if(Integer.valueOf(btnBathRoomWithWindow.getTag().toString())>0){ query = query + " and WithWindow > 0";}

        Log.d(TAG, query);
        loadFromSqlite(query);
    }

    private void clearFilter() {
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

        check_period_2_2022.setImageResource(R.drawable.checkbox_srok_2_2022);
        check_period_3_2022.setImageResource(R.drawable.checkbox_srok_3_2022);
        check_period_4_2022.setImageResource(R.drawable.checkbox_srok_4_2022);
        check_period_2_2022.setTag(0);
        check_period_3_2022.setTag(0);
        check_period_4_2022.setTag(0);

        btnTownHouse.setImageResource(R.drawable.button_townhouse);
        btnPentHouse.setImageResource(R.drawable.button_penthouse);
        btnDoubleFloorHouse.setImageResource(R.drawable.button_doublefloorhouse);
        btnTwoEnterHouse.setImageResource(R.drawable.button_twoenterhouse);
        btnTerrasa.setImageResource(R.drawable.button_terrasa);
        btnWithFirePlace.setImageResource(R.drawable.button_with_fireplace);
        btnBathRoomWithWindow.setImageResource(R.drawable.button_bathroom_with_window);
        btnTownHouse.setTag(0);
        btnPentHouse.setTag(0);
        btnDoubleFloorHouse.setTag(0);
        btnTwoEnterHouse.setTag(0);
        btnTerrasa.setTag(0);
        btnWithFirePlace.setTag(0);
        btnBathRoomWithWindow.setTag(0);
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
}
