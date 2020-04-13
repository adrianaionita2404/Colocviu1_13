package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_13MainActivity extends AppCompatActivity {
    private Integer countCard = 0;

    public Colocviu1_13BcastRcv bcastr;
    public IntentFilter startedServiceIntentFilter;

    public boolean ok = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.CARD_TEXT)) {
                TextView allTextView = (TextView) findViewById(R.id.text_dir);
                allTextView.setText(savedInstanceState.getString(Constants.CARD_TEXT));
            }

            if (savedInstanceState.containsKey(Constants.CARD_CNT)) {
                countCard = Integer.parseInt(savedInstanceState.getString(Constants.CARD_CNT));
                Toast.makeText(this, "Restored state: " + countCard + " (card count)", Toast.LENGTH_LONG).show();
            }
        }

        if (countCard == 4) {
            TextView allTextView = (TextView) findViewById(R.id.text_dir);

            Intent intent = new Intent();
            intent.setComponent(new ComponentName("ro.pub.cs.systems.eim.Colocviu1_13", "ro.pub.cs.systems.eim.Colocviu1_13.Colocviu1_13Service"));
            intent.putExtra("ro.pub.cs.systems.eim.Colocviu1_13.allCards", allTextView.getText().toString());
            startService(intent);
            Toast.makeText(this, "Started service", Toast.LENGTH_LONG).show();

            bcastr = new Colocviu1_13BcastRcv();

            startedServiceIntentFilter = new IntentFilter();
            startedServiceIntentFilter.addAction(Constants.ACTION_TYPE);
            ok = true;
        }

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (ok == true) {
            registerReceiver(bcastr, startedServiceIntentFilter);
        }
    }

    protected void onPause() {
        if (ok == true) {
            unregisterReceiver(bcastr);
        }

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (ok == true) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("ro.pub.cs.systems.eim.Colocviu1_13", "ro.pub.cs.systems.eim.Colocviu1_13.Colocviu1_13Service"));
            stopService(intent);
            ok = false;
        }

        super.onDestroy();
    }

    public void concatDir(View view) {
        TextView allTextView = (TextView) findViewById(R.id.text_dir);
        Button currButton = (Button) view;

        String newStr = currButton.getText().toString();
        String allStr = allTextView.getText().toString();

        if (allStr.isEmpty())
            allStr = allStr + newStr;
        else
            allStr = allStr + "," + newStr;

        allTextView.setText(allStr);

        countCard++;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        TextView allTextView = (TextView) findViewById(R.id.text_dir);

        savedInstanceState.putString(Constants.CARD_TEXT, allTextView.getText().toString());
        savedInstanceState.putString(Constants.CARD_CNT, countCard.toString());
    }

    public void gotoNextAct(View view) {
        TextView allTextView = (TextView) findViewById(R.id.text_dir);

        Intent intent = new Intent(this, Colocviu1_13SecondaryActivity.class);
        intent.putExtra("ro.pub.cs.systems.eim.Colocviu1_13.TEXT_CARD", allTextView.getText().toString());
        startActivityForResult(intent, Constants.REQ_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case Constants.REQ_CODE:
                TextView allTextView = (TextView) findViewById(R.id.text_dir);

                allTextView.setText("");
                countCard = 0;

                if (resultCode == Activity.RESULT_OK)
                    Toast.makeText(this, "Returned ok", Toast.LENGTH_LONG).show();
                else if (resultCode == Activity.RESULT_CANCELED)
                    Toast.makeText(this, "Canceled", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
