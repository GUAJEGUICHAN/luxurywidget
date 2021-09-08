package com.example.luxurywidget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    변수선언
    Chronometer chrono;
    RadioButton rdoCal, rdoTime;
    CalendarView calView;
    TimePicker tPicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute;
    int selectYear, selectMonth, selectDay;
    View bookedTime;
    Boolean isRunning = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간 예약");

        chrono = (Chronometer) findViewById(R.id.chronometer1);

        rdoCal = (RadioButton) findViewById(R.id.rdoCal);
        rdoTime = (RadioButton) findViewById(R.id.rdoTime);

        tvYear = (TextView) findViewById(R.id.tvYear);
        tvMonth = (TextView) findViewById(R.id.tvMonth);
        tvDay = (TextView) findViewById(R.id.tvDay);
        tvHour = (TextView) findViewById(R.id.tvHour);
        tvMinute = (TextView) findViewById(R.id.tvMinute);

        calView = (CalendarView) findViewById(R.id.calanderView1);
        tPicker = (TimePicker) findViewById(R.id.timePicker1);

        bookedTime = (View) findViewById(R.id.bookedTime);

        calView.setVisibility(View.INVISIBLE);
        tPicker.setVisibility(View.INVISIBLE);


        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRunning){
                    calView.setVisibility(View.VISIBLE);
                    tPicker.setVisibility(View.INVISIBLE);
                }
            }
        });
        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRunning){
                    calView.setVisibility(View.INVISIBLE);
                    tPicker.setVisibility(View.VISIBLE);
                }

            }
        });

        chrono.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.RED);
                isRunning=true;
                if(rdoCal.isChecked()){
                    calView.setVisibility(View.VISIBLE);
                }else if (rdoTime.isChecked()){
                    tPicker.setVisibility(View.VISIBLE);
                }

                return false;
            }
        });
        bookedTime.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(Integer.toString(selectYear).equals(Integer.toString(0))){
                    Toast.makeText(getApplicationContext(),"날짜를 선택해주세요",Toast.LENGTH_LONG).show();
                    return false;
                }
                Toast.makeText(getApplicationContext(),Integer.toString(selectYear),Toast.LENGTH_LONG).show();

                chrono.stop();
                chrono.setTextColor(Color.BLUE);
                isRunning=false;
                calView.setVisibility(View.INVISIBLE);
                tPicker.setVisibility(View.INVISIBLE);
                tvYear.setText(Integer.toString(selectYear));
                tvMonth.setText(Integer.toString(selectMonth));
                tvDay.setText(Integer.toString(selectDay));

                tvHour.setText(Integer.toString(tPicker.getCurrentHour()));
                tvMinute.setText(Integer.toString(tPicker.getCurrentMinute()));
                return false;
            }
        });

        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectYear = year;
                selectMonth = month;
                selectDay = dayOfMonth;
            }
        });




        //xml 과 연결
    }
}