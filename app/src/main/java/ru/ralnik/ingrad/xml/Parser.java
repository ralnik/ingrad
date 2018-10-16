package ru.ralnik.ingrad.xml;

import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

import ru.ralnik.ingrad.model.Flat;
import ru.ralnik.ingrad.sqlitedb.FlatRepository;

public class Parser extends Thread implements Runnable {

    private Context context;
    private String url;
    String TAG = "myDebug";

    public Parser(Context context,String url) {
        this.url = url;
    }

    public Parser(Context context){
        this.context = context;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void execute(){
        start();
    }

    @Override
    public void run() {
        super.run();
        try {
            //если файл брать по HTTP ссылке
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //factory.setNamespaceAware(true); //если надо
            XmlPullParser parser = factory.newPullParser();
            URL input = new URL(url); //url удаленного документа

            parser.setInput(input.openStream(), null);

            // Log.d(TAG, url);

            String nameTAG = null;
            Flat flat = null;
            //  AppDatabase db = AppDatabase.getInstance(context);
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                // Log.d(TAG, parser.getText());

                switch (parser.getEventType()){
                    case XmlPullParser.START_TAG:
                        nameTAG = parser.getName();
                        // Log.d(TAG,"<"+nameTAG+">");
                        if(nameTAG.equals("a:Apartment")){
                            flat = new Flat();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        // Log.d(TAG, parser.getText());
                        if(nameTAG.equals("a:ArticleId")) {
                            flat.setArticleId(parser.getText());
                        }

                        if(nameTAG.equals("a:ArticleSubType")) {
                            flat.setArticleSubType(parser.getText());
                        }

                        if(nameTAG.equals("a:LayoutUrl")) {
                            flat.setLayoutUrl(parser.getText());
                        }

                        if(nameTAG.equals("a:BeforeBtiNumber")) {
                            flat.setBeforeBtiNumber(Integer.valueOf(parser.getText()));
                        }

                        if(nameTAG.equals("a:AddressId")) {
                            flat.setAddressId(parser.getText());
                        }

                        if(nameTAG.equals("a:AddressName")) {
                            flat.setAddressName(parser.getText());
                        }

                        if(nameTAG.equals("a:SectionNumber")) {
                            flat.setSectionNumber(parser.getText());
                        }

                        if(nameTAG.equals("a:Floor")) {
                            flat.setFloor(Integer.valueOf(parser.getText()));
                        }

                        if(nameTAG.equals("a:Rooms")) {
                            flat.setRooms(Integer.valueOf(parser.getText()));
                        }

                        if(nameTAG.equals("a:Quantity")) {
                            flat.setQuantity(Float.valueOf(parser.getText()));
                        }

                        if(nameTAG.equals("a:DiscountMax")) {
                            flat.setDiscountMax(Float.valueOf(parser.getText()));
                        }

                        if(nameTAG.equals("a:FinishTypeId")) {
                            flat.setFinishTypeId(parser.getText());
                        }

                        if(nameTAG.equals("a:StatusCodeName")) {
                            flat.setStatusCodeName(parser.getText());
                        }

                        if(nameTAG.equals("a:TownHouse")) {
                            flat.setTownHouse(parser.getText());
                        }

                        if(nameTAG.equals("a:PentHouse")) {
                            flat.setPentHouse(parser.getText());
                        }

                        if(nameTAG.equals("a:TwoLevel")) {
                            flat.setTwoLevel(parser.getText());
                        }

                        if(nameTAG.equals("a:SeparateEntrance")) {
                            flat.setSeparateEntrance(parser.getText());
                        }

                        if(nameTAG.equals("a:WithWindow")) {
                            flat.setWithWindow(parser.getText());
                        }

                        if(nameTAG.equals("a:FirePlace")) {
                            flat.setFirePlace(parser.getText());
                        }

                        if(nameTAG.equals("a:Terrace")) {
                            flat.setTerrace(parser.getText());
                        }

                        if(nameTAG.equals("a:CountBalcony")) {
                            flat.setCountBalcony(Integer.valueOf(parser.getText()));
                        }

                        if(nameTAG.equals("a:CountLoggia")) {
                            flat.setCountLoggia(Integer.valueOf(parser.getText()));
                        }

                        if(nameTAG.equals("a:CountTerrace")) {
                            flat.setCountTerrace(Integer.valueOf(parser.getText()));
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        //Log.d(TAG,"</"+parser.getName()+">");

                        if(parser.getName().equals("a:Apartment")){
                            int f = new FlatRepository(context).findByIds(new String[]{flat.getArticleId()}).size();
                            if(f == 0) {
                                new FlatRepository(context).insert(flat);
                            }

                        }
                        break;

                    default:
                        break;
                }
                parser.next();
            }
        } catch (XmlPullParserException e){
            Log.d(TAG,"Ошибка еще какая то : " + e.toString());
        } catch (Throwable t) {
            t.printStackTrace();
            //Log.d(TAG,"Ошибка при загрузке XML-документа: " + t.);
        }
    }








}
