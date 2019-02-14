package edu.miracostacollege.cs134.sandiegomusicevents;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.miracostacollege.cs134.sandiegomusicevents.R;
import edu.miracostacollege.cs134.sandiegomusicevents.model.MusicEvent;

/**note the change in which class this extends */
public class MainActivity extends ListActivity {

    private ListView eventsListView;

    protected void onListItemClick(ListView l, View v, int position, long id) {

        //Extract info we need
        String artist = MusicEvent.titles[position];
        String details = MusicEvent.details[position];

        // make the Intent
        // navigate from this class to the EventDetailsActivity class
        Intent detailsIntent = new Intent(this, EventDetailsActivity2.class);
        // fill Intent with data
        detailsIntent.putExtra("Artist", artist);
        detailsIntent.putExtra("Details", details);

        // start the activity
        startActivity(detailsIntent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Since layout is being inflated by ListView, don't setContentView */
        //setContentView(R.layout.activity_main);

        eventsListView = findViewById(R.id.eventsListView);

        /** Connect our ListView with an ArrayAdapter to fill out data */
        ArrayAdapter<String> eventsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MusicEvent.titles);
        setListAdapter(eventsAdapter);
    }
}
