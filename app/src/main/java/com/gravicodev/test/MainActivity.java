package com.gravicodev.test;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.DayItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CalendarPickerController {

    private static final String LOG_TAG = "JAM" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.set(Calendar.MONTH, 0);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 5);

        List<CalendarEvent> eventList = new ArrayList<>();
        mockList(eventList);

        AgendaCalendarView mAgendaCalendarView = (AgendaCalendarView) findViewById(R.id.agenda_calendar_view);

        mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void mockList(List<CalendarEvent> eventList) {
        Calendar startTime1 = Calendar.getInstance();
        Calendar endTime1 = Calendar.getInstance();
//        endTime1.add(Calendar.MONTH, 1);
        startTime1.set(Calendar.MONTH,0);
        startTime1.set(Calendar.DATE,7);
        endTime1 = startTime1;
        BaseCalendarEvent event1 = new BaseCalendarEvent("Thibault travels in Iceland", "A wonderful journey!", "Iceland",
                ContextCompat.getColor(this, R.color.orange_dark), startTime1, endTime1, true);
        eventList.add(event1);

        Calendar startTime2 = Calendar.getInstance();
        startTime2.add(Calendar.DAY_OF_YEAR, 1);
        Calendar endTime2 = Calendar.getInstance();
        endTime2.add(Calendar.DAY_OF_YEAR, 3);
        BaseCalendarEvent event2 = new BaseCalendarEvent("Visit to Dalvík", "A beautiful small town", "Dalvík",
                ContextCompat.getColor(this, R.color.yellow), startTime2, endTime2, true);
        eventList.add(event2);

        Calendar startTime3 = Calendar.getInstance();
        Calendar endTime3 = Calendar.getInstance();
        startTime3.set(Calendar.HOUR_OF_DAY, 14);
        startTime3.set(Calendar.MINUTE, 0);
        endTime3.set(Calendar.HOUR_OF_DAY, 15);
        endTime3.set(Calendar.MINUTE, 0);
        DrawableCalendarEvent event3 = new DrawableCalendarEvent("Visit of Harpa", "", "Dalvík",
                ContextCompat.getColor(this, R.color.blue_dark), startTime3, endTime3, false, android.R.drawable.ic_dialog_info);
        eventList.add(event3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDaySelected(DayItem dayItem) {
        Log.d(LOG_TAG, String.format("Selected day: %s", dayItem));
    }

    @Override
    public void onEventSelected(CalendarEvent event) {
        Log.d(LOG_TAG, String.format("Selected event: %s", event));
    }

    @Override
    public void onScrollToDate(Calendar calendar) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        }
    }
}
