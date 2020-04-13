package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_13SecondaryActivity extends AppCompatActivity {
    private Integer countCard = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_secondary);

        Intent intent = getIntent();
        if (intent != null) {
            TextView allTextView = (TextView) findViewById(R.id.text_card);
            String allText = intent.getStringExtra("ro.pub.cs.systems.eim.Colocviu1_13.TEXT_CARD");
            if (allText != null) {
                allTextView.setText(allText);
            }
        }
    }

    public void endIntent(View view) {
        int id = view.getId();

        if (id == R.id.button_ok) {
            setResult(Activity.RESULT_OK, new Intent());
            finish();
        } else if (id == R.id.button_cancel) {
            setResult(Activity.RESULT_CANCELED, new Intent());
            finish();
        }
    }
}