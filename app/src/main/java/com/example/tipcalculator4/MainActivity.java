package com.example.tipcalculator4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private EditText textBase;
    private SeekBar mSeekBar;
    private TextView textTipPercent;
    private TextView textTipAmount;
    private TextView textTotalAmount;
    private double base = 0.0;
    private double tipPercentage = 0.15;
    private double tip = 0.0;
    private double total = 0.0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBase = findViewById(R.id.etBase);

        mSeekBar = findViewById(R.id.seekBar);
        textTipPercent = findViewById(R.id.tvTipPecent);
        textTipAmount = findViewById(R.id.tvTipAmount);
        textTotalAmount = findViewById(R.id.tvTotalAmount);

        mSeekBar.setProgress(15);
        textTipPercent.setText(String.valueOf(mSeekBar.getProgress())+"%");

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textTipPercent.setText(String.valueOf(mSeekBar.getProgress())+"%");
                tipPercentage = (double) seekBar.getProgress()/100;
                calculator();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tipPercentage = (double) seekBar.getProgress() /100;
                calculator();
            }
        });
        textBase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    base = Double.parseDouble(s.toString());
                }
                catch (NumberFormatException e){
                    textTotalAmount.setText("");
                    base=0.0;
                }
                calculator();
            }
        });


    }

    private void calculator(){
        tip = tipPercentage * base;
        total = base + tip;
        textTipAmount.setText(String.format("%.02f",tip));
        textTotalAmount.setText(String.format("%.02f",total));
    }
}