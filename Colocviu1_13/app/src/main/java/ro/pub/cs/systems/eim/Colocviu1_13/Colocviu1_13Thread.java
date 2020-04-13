package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Colocviu1_13Thread extends Thread {
    private Context context;
    private final String TAG = "my_practic_thread";
    private final int SLEEP_TIME = 5000;

    private String cards = "";

    public Colocviu1_13Thread(Context context, String commands) {
        this.cards = commands;
        this.context = context;
    }

    @Override
    public void run() {
        Log.d(TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        sleep();
        sendMessage();
    }

    private void sendMessage() {
        Intent intent = new Intent();
        String date = (new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime())).toString();
        Date time = Calendar.getInstance().getTime();

        String msg = "" + date.toString() + " | " + time.toString() + " | " + cards;

        intent.setAction(Constants.ACTION_TYPE);
        intent.putExtra(Constants.DATA, msg);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException interruptedException) {
            Log.e(TAG, interruptedException.getMessage());
            interruptedException.printStackTrace();
        }
    }
}