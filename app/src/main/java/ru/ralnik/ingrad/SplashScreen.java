package ru.ralnik.ingrad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

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
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
    }

    private void loadDataFromXML() {
        int progress = 0;
        try {
            Parser parser1 = new Parser(this);
            parser1.setUrl("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=D9AF2F75-A99F-E811-80F9-005056BA18B6&export=false");
            parser1.execute();

            Thread.sleep(1000);
            progress += 12.5;
            mProgress.setProgress(progress);

            Parser parser2 = new Parser(this);
            parser2.setUrl("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=517BA460-B39F-E811-80F9-005056BA18B6&export=false");
            parser2.execute();

            Thread.sleep(1000);
            progress += 12.5;
            mProgress.setProgress(progress);

            Parser parser3 = new Parser(this);
            parser3.setUrl("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=C545D97A-B39F-E811-80F9-005056BA18B6&export=false");
            parser3.execute();

            Thread.sleep(1000);
            progress += 12.5;
            mProgress.setProgress(progress);

            Parser parser4 = new Parser(this);
            parser4.setUrl("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=E8671A06-B49F-E811-80F9-005056BA18B6&export=false");
            parser4.execute();

            Thread.sleep(1000);
            progress += 12.5;
            mProgress.setProgress(progress);

            Parser parser5 = new Parser(this);
            parser5.setUrl("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=6F035221-B49F-E811-80F9-005056BA18B6&export=false");
            parser5.execute();

            Thread.sleep(1000);
            progress += 12.5;
            mProgress.setProgress(progress);

            Parser parser6 = new Parser(this);
            parser6.setUrl("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=739AB24A-B49F-E811-80F9-005056BA18B6&export=false");
            parser6.execute();

            Thread.sleep(1000);
            progress += 12.5;
            mProgress.setProgress(progress);

            Parser parser7 = new Parser(this);
            parser7.setUrl("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=B22CD269-B49F-E811-80F9-005056BA18B6&export=false");
            parser7.execute();

            Thread.sleep(1000);
            progress += 12.5;
            mProgress.setProgress(progress);

            Parser parser8 = new Parser(this);
            parser8.setUrl("https://crm.dmfs.ru/Service/ExportToSite.svc/ApartmentListWithsExportParam/xml?id=70FCF484-B49F-E811-80F9-005056BA18B6&export=false");
            parser8.execute();

            Thread.sleep(1000);
            progress += 12.5;
            mProgress.setProgress(progress);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
