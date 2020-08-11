package ru.ralnik.ingrad.customListView;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import ru.ralnik.ingrad.R;
import ru.ralnik.ingrad.config.myConfig;
import ru.ralnik.ingrad.context.IngradContex;
import ru.ralnik.ingrad.httpPlayer.HttpPlayerFactory;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;
import ru.ralnik.ingrad.httpPlayer.VVVVPlayer;
import ru.ralnik.ingrad.model.Flat;
import ru.ralnik.ingrad.sqlitedb.FlatRepository;

public class listviewItemSelected implements AdapterView.OnItemClickListener {

    private String ArticleID = null;
    public static myAdapter.ViewHolder SELECTED_LISTVIEW_ITEM ; //предыдущий выделенный элемент
    public static long ID = 0;

    private Context context;
    private PlayerCommands vvvv;
    private PlayerCommands vvvv2;
    private myConfig cfg;

    public listviewItemSelected(Context context) {
        this.context = context;
        cfg = new myConfig(context);
//        vvvv = new VVVVPlayer(cfg.getHost1());
//        vvvv2 = new VVVVPlayer(cfg.getHost2());
        vvvv = IngradContex.getInstance(context).getVvvv();
        vvvv2 = IngradContex.getInstance(context).getVvvv2();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //текущий выделенный элемент
        myAdapter.ViewHolder currentViewHolder = (myAdapter.ViewHolder) view.getTag();

        //если предедущего элемента еще нет, то присваеваем ему текущий,
        //иначе с предыдущего снимаем выделение
        if(ID != 0) {

            //String fname = "pic_"+GlobalVar.razdelFilter.toLowerCase() + SELECTED_LISTVIEW_ITEM.column2.getText() + "_" + SELECTED_LISTVIEW_ITEM.column4.getText();SELECTED_LISTVIEW_ITEM.column1.setImageResource(context.getResources().getIdentifier(fname, "drawable", context.getPackageName()));
            SELECTED_LISTVIEW_ITEM.column1.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column2.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column3.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column4.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column5.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column6.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
            SELECTED_LISTVIEW_ITEM.column7.setTextColor(context.getResources().getColor(R.color.colorTextListitems));
        }


            SELECTED_LISTVIEW_ITEM = currentViewHolder;
            ID = currentViewHolder.classID;
            ArticleID = currentViewHolder.ArticleId;

        Flat flat = new FlatRepository(context).findById(ArticleID);
//        HttpPlayerFactory.getInstance(context).getCommand().setFlatInfo(flat);
//        HttpPlayerFactory.getInstance(context).getCommand().selectById(13);
        vvvv.setFlatInfo(flat);
        vvvv.selectById(13);

        vvvv2.setFlatInfo(flat);
        vvvv2.selectById(13);



    }
}
