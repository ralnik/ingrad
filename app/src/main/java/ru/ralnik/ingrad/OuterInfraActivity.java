package ru.ralnik.ingrad;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import ru.ralnik.ingrad.config.myConfig;
import ru.ralnik.ingrad.httpPlayer.VVVVPlayer;

public class OuterInfraActivity {
    private final Activity activity;
    private View view;
    private AlertDialog alertDialog;
    private ImageView btnChildGarden;
    private ImageView btnSchools;
    private ImageView btnMedicine;
    private ImageView btnSport;
    private ImageView btnObjectOfCreation;
    private ImageView btnBusiness;
    private ImageView btnShops;

    private myConfig cfg;
    private VVVVPlayer vvvv;

    public OuterInfraActivity(Activity activity) {
        this.activity = activity;
        view = activity.getLayoutInflater().inflate(R.layout.outer_infra_layout, null); // Получаем layout по его ID
        show();
        //alertDialog.show();
        init();
        cfg = new myConfig(activity.getApplicationContext());

        vvvv = new VVVVPlayer(cfg.getHost());
    }

    private void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setView(this.view);
        builder.setCancelable(true);
        builder.create();
        builder.show();
        /*
        //Если нужно добавить снизу диалогового окна 2 кнопки ОК и Отмена
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
            public void onClick(DialogInterface dialog, int whichButton) {
               // MainActivity.doSaveSettings(); // Переход в сохранение настроек MainActivity
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // Кнопка Отмена
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        */
    }

    private void init() {
        // Определяем SeekBar и привязываем к нему дельты настроек
        btnChildGarden = (ImageView) view.findViewById(R.id.btnChildgarden);
        btnSchools = (ImageView) view.findViewById(R.id.btnSchools);
        btnMedicine = (ImageView) view.findViewById(R.id.btnMedicine);
        btnSport = (ImageView) view.findViewById(R.id.btnSport);
        btnObjectOfCreation = (ImageView) view.findViewById(R.id.btnObjectOfCreation);
        btnBusiness = (ImageView) view.findViewById(R.id.btnBusinessCenter);
        btnShops = (ImageView) view.findViewById(R.id.btnShops);

        btnChildGarden.setOnClickListener(new onClick());
        btnSchools.setOnClickListener(new onClick());
        btnMedicine.setOnClickListener(new onClick());
        btnSport.setOnClickListener(new onClick());
        btnObjectOfCreation.setOnClickListener(new onClick());
        btnBusiness.setOnClickListener(new onClick());
        btnShops.setOnClickListener(new onClick());
    }

    private class onClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            btnChildGarden.setImageResource(R.drawable.button_childgarden);
            btnSchools.setImageResource(R.drawable.button_schools);
            btnMedicine.setImageResource(R.drawable.button_medicine);
            btnSport.setImageResource(R.drawable.button_sport);
            btnObjectOfCreation.setImageResource(R.drawable.button_object_of_creation);
            btnBusiness.setImageResource(R.drawable.button_business_center);
            btnShops.setImageResource(R.drawable.button_shops);
            switch (v.getId()){
                case R.id.btnChildgarden:
                    btnChildGarden.setImageResource(R.drawable.button_childgarden_down);
                    vvvv.selectById(13);
                    break;
                case R.id.btnSchools:
                    btnSchools.setImageResource(R.drawable.button_schools_down);
                    vvvv.selectById(14);
                    break;
                case R.id.btnMedicine:
                    btnMedicine.setImageResource(R.drawable.button_medicine_down);
                    vvvv.selectById(15);
                    break;
                case R.id.btnSport:
                    btnSport.setImageResource(R.drawable.button_sport_down);
                    vvvv.selectById(16);
                    break;
                case R.id.btnObjectOfCreation:
                    btnObjectOfCreation.setImageResource(R.drawable.button_object_of_creation_down);
                    vvvv.selectById(17);
                    break;
                case R.id.btnBusinessCenter:
                    btnBusiness.setImageResource(R.drawable.button_business_center_down);
                    vvvv.selectById(18);
                    break;
                case R.id.btnShops:
                    btnShops.setImageResource(R.drawable.button_shops_down);
                    vvvv.selectById(19);
                    break;
                default:
                    break;
            }
        }
    }
}
