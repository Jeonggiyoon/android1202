package com.example.jgy.myapplication;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1, b2;
    RadioButton r1, r2;
    RadioGroup rg;
    Chronometer cro1;
    CalendarView cal;
    TimePicker tp;
    TextView t1;

    int year = -1;
    int mon = -1;
    int day = -1;
    int hour = -1;
    int min = -1;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        cal= (CalendarView) findViewById(R.id.calendarView1);
        tp = (TimePicker) findViewById(R.id.timePicker1);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        r1 = (RadioButton) findViewById(R.id.radioButton1);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        cro1 = (Chronometer) findViewById(R.id.chronometer1);
        t1 = (TextView) findViewById(R.id.textView1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0) flag = 1;
                else if (flag == 1) {
                    Toast.makeText(getApplicationContext(),"예약을 해주세요 :)", Toast.LENGTH_SHORT).show(); return;
                }
                cro1.setBase(SystemClock.elapsedRealtime());
                cro1.start();
                cro1.setTextColor(Color.RED);  //빨간색으로
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radioButton1) {
                    cal.setVisibility(View.VISIBLE); tp.setVisibility(View.GONE);
                }
                else if (checkedId == R.id.radioButton2) {
                    tp.setVisibility(View.VISIBLE); cal.setVisibility(View.GONE);
                }
            }
        });

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                year = i; mon = i1; day = i2;
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0) {
                    Toast.makeText(getApplicationContext(), "예약을 해주세요 :)", Toast.LENGTH_SHORT).show(); return;
                }

                if (year == -1 || min == -1) {
                    Toast.makeText(getApplicationContext(), "예약시간을 입력해 주세요 :)", Toast.LENGTH_SHORT).show(); return;
                }
                cro1.stop();
                cro1.setTextColor(0xff0099cc);
                t1.setText(year+ "년" + mon + "월" + day + "일" + hour + "시" + min +"분 예약됨 ");
                flag = 0;
            }
        });

    }
}
