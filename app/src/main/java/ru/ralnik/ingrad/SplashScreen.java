package ru.ralnik.ingrad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ru.ralnik.ingrad.context.IngradContex;
import ru.ralnik.ingrad.xml.Parser;

public class SplashScreen extends AppCompatActivity {
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash_screen);
        mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                //doWork();
                loadDataFromXML();
                startApp();
                finish();
            }
        }).start();

    }

    private void startApp() {
        /** первоначальная инизиализация контекста
         *  и запись в него самого главного контекста программы
         *  теперь applicationContext можно вызывать в любом месте программы
         */
        IngradContex.getInstance(getApplicationContext());
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
    }

    private void loadDataFromXML() {
        List<String> urls = new ArrayList<>();
        urls.add("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=D9AF2F75-A99F-E811-80F9-005056BA18B6&export=false");
        urls.add("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=517BA460-B39F-E811-80F9-005056BA18B6&export=false");
        urls.add("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=C545D97A-B39F-E811-80F9-005056BA18B6&export=false");
        urls.add("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=C545D97A-B39F-E811-80F9-005056BA18B6&export=false");
        urls.add("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=E8671A06-B49F-E811-80F9-005056BA18B6&export=false");
        urls.add("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=6F035221-B49F-E811-80F9-005056BA18B6&export=false");
        urls.add("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=739AB24A-B49F-E811-80F9-005056BA18B6&export=false");
        urls.add("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=B22CD269-B49F-E811-80F9-005056BA18B6&export=false");
        urls.add("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=70FCF484-B49F-E811-80F9-005056BA18B6&export=false");

        //новый корпуса
        urls.add("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=EF6BF3A0-F8AA-EA11-8129-005056BA18B6&export=false");
        urls.add("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=6F6F2DC9-F9AA-EA11-8129-005056BA18B6&export=false");
        urls.add("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=D1238044-DCB9-EA11-812B-005056BA18B6&export=false");
        urls.add(" https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=51B7E73A-9ABA-EA11-812B-005056BA18B6&export=false");
        int progress = 0;
        try {
            for (String url : urls) {
                Parser parser = new Parser(this);
                parser.setUrl(url);
                parser.execute();

                Thread.sleep(1000);
                progress += (100/urls.size());
                mProgress.setProgress(progress);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
