package ru.ralnik.ingrad.viewElements.flatPlans;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.util.List;

import ru.ralnik.ingrad.GlobalVars;
import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.context.IngradContex;
import ru.ralnik.ingrad.customListView.listviewItemSelected;
import ru.ralnik.ingrad.customListView.myAdapter;
import ru.ralnik.ingrad.model.FlatPlanBean;
import ru.ralnik.ingrad.sqlitedb.DbManager;
import ru.ralnik.ingrad.sqlitedb.FlatRepository;

public class FlatPlansAdapter extends ArrayAdapter<FlatPlanBean> {
    private List<FlatPlanBean> list;
    private final Context context;
    private LayoutInflater mInflater;
    int resource;
    TextView textNoRow;
    ScrollView scrollViewRight;
    LinearLayout settingsPanel;
    LinearLayout resultPanel;
    ListView listview;

    public FlatPlansAdapter(@NonNull Context context, int resource, List<FlatPlanBean> list, List<View> listView) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;

        textNoRow = (TextView) listView.get(0);
        scrollViewRight = (ScrollView) listView.get(1);
        settingsPanel = (LinearLayout) listView.get(2);
        resultPanel = (LinearLayout) listView.get(3);
        listview = (ListView) listView.get(4);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root = mInflater.from(context).inflate(resource, parent, false);

        ImageView button = (ImageView) root.findViewById(R.id.buttonFlatPlan);
        String picture_file = GlobalVars.YANDEX_DISK_FOLDER + list.get(position).getId() + "-0.png";
        if (new File(GlobalVars.YANDEX_DISK_FOLDER).exists()) {
            Log.d("myDebug", "GlobalVars.YANDEX_DISK_FOLDER: " + GlobalVars.YANDEX_DISK_FOLDER + " is exist");
        } else {
            Log.d("myDebug", "GlobalVars.YANDEX_DISK_FOLDER: " + GlobalVars.YANDEX_DISK_FOLDER + " is not exist");
        }
        Log.d("myDebug", picture_file);
        button.setImageDrawable(Drawable.createFromPath(picture_file));

        TextView textTitle = (TextView) root.findViewById(R.id.title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbManager dbManager = new DbManager();
                dbManager.setQuery("SELECT * from flats where (StatusCodeName='Свободно' or StatusCodeName='Ус. Бронь') \n" +
                        " :rooms \n" +
                        " :squareMin \n" +
                        " :squareMax ");
                String[] paramsValue = {
                        " and rooms = " + list.get(position).getCountRooms()
                        , " and CAST(Quantity as INTEGER) >= " + list.get(position).getSquare()
                        , " and CAST(Quantity as INTEGER) < " + list.get(position).getSquare() + "+1"
                };

                dbManager.where(" and BuildingGroup = '" + list.get(position).getBuildingGroup() + "'");
                dbManager.whereParams(new String[] {":rooms", ":squareMin", ":squareMax"}, paramsValue);

                Log.d("myDebug", dbManager.getQuery());
                myAdapter adapter = new myAdapter(IngradContex.getAppContext(),
                        new FlatRepository(IngradContex.getAppContext()).getFlatsByQuery(dbManager.getQuery()), 0);

                if (adapter.getCount() == 0) {
                    textNoRow.setVisibility(View.VISIBLE);
                } else {
                    textNoRow.setVisibility(View.GONE);
                }
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(new listviewItemSelected(IngradContex.getAppContext()));

                scrollViewRight.setVisibility(View.GONE);
                settingsPanel.setVisibility(View.GONE);
                resultPanel.setVisibility(View.VISIBLE);
            }
        });

        textTitle.setText(list.get(position).getRealSquare().toString());
        return root;
    }

    @Nullable
    @Override
    public FlatPlanBean getItem(int position) {
        return list.get(position);
    }
}
