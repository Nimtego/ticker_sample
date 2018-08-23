package com.nimtego.ticker_sample;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    float count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TickerView tickerView = findViewById(R.id.tickerView);
        tickerView.setCharacterLists(TickerUtils.provideNumberList());
        final EditText summ = findViewById(R.id.editTextSumm);
        tickerView.setText("TEST");
        final Handler handler = new Handler();
        findViewById(R.id.buttonUp).setOnClickListener((view) -> tickerView.post(new Runnable() {
            @Override
            public void run() {
                String debit = String.valueOf(summ.getText());
                if (debit != null && Float.valueOf(debit) < 1000000000f && Float.valueOf(debit) > 0f) {
                    float second = Float.valueOf(debit) / 576000f;
                    count += second;
                    DecimalFormat df = new DecimalFormat("#.##");
                    tickerView.setText(String.valueOf(df.format(count)));
                    handler.postDelayed(this, 1000);
                }
            }
        }));
    }
}
