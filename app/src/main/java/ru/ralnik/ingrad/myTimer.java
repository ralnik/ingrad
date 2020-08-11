package  ru.ralnik.ingrad;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import ru.ralnik.ingrad.config.myConfig;
import ru.ralnik.ingrad.context.IngradContex;
import ru.ralnik.ingrad.httpPlayer.HttpPlayerFactory;
import ru.ralnik.ingrad.httpPlayer.PlayerCommands;
import ru.ralnik.ingrad.httpPlayer.VVVVPlayer;

/**
 * Created by ralnik on 08.11.17.
 */

public class myTimer extends CountDownTimer {

    private Context context;
    private PlayerCommands vvvv;
    private PlayerCommands vvvv2;

    private int timerTrack;
    String TAG = "myDebug";

    public myTimer(Context context, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.context = context;
        vvvv = IngradContex.getInstance(context).getVvvv();
        vvvv2 = IngradContex.getInstance(context).getVvvv2();
    }

    public void setTimerTrack(int timerTrack){
        this.timerTrack = timerTrack;

    }

    @Override
    public void onTick(long millisUntilFinished) {
        Log.d(TAG,"Осталось секунд: " + millisUntilFinished / 1000);
    }

    @Override
    public void onFinish() {
        //запускаем трек по таймеру
        vvvv.selectById(timerTrack);
        vvvv2.selectById(timerTrack);

    }
}
