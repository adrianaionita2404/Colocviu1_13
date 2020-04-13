package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_13Service extends Service {
    public final String TAG = "my_practic_service";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() method was invoked");
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() method was invoked");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() method was invoked");

        //take str from intent
        String allCard = intent.getStringExtra("ro.pub.cs.systems.eim.Colocviu1_13.allCards");

        Colocviu1_13Thread processingThread = new Colocviu1_13Thread(this, allCard);
        processingThread.start();


        return START_REDELIVER_INTENT;
    }

}