package edu.miracostacollege.cs134.sandiegomusicevents;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import edu.miracostacollege.cs134.sandiegomusicevents.model.JSONLoader;
import edu.miracostacollege.cs134.sandiegomusicevents.model.MusicEvent;

/**note the change in which class this extends */
public class MainActivity extends ListActivity {

    private ListView eventsListView;
    // Create a list of Music events loaded from JSON
    List<MusicEvent> allMusicEvents;

    protected void onListItemClick(ListView l, View v, int position, long id) {

        Intent detailsIntent = new Intent(this, EventDetailsActivity2.class);

        MusicEvent selectedEvent = allMusicEvents.get(position);
        detailsIntent.putExtra("artist", selectedEvent.getArtist());
        detailsIntent.putExtra("date", selectedEvent.getDate());
        detailsIntent.putExtra("day", selectedEvent.getDay());
        detailsIntent.putExtra("time", selectedEvent.getTime());
        detailsIntent.putExtra("venue", selectedEvent.getVenue());
        detailsIntent.putExtra("city", selectedEvent.getCity());
        detailsIntent.putExtra("state", selectedEvent.getState());
        detailsIntent.putExtra("imageName", selectedEvent.getImageName());

        startActivity(detailsIntent);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        /** Since layout is being inflated by ListView, don't setContentView */
        //setContentView(R.layout.activity_main);



        eventsListView = findViewById(R.id.eventsListView);

        try {
            allMusicEvents = JSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            Log.e("SD Music Events", "Error loading JSON" + e.getMessage());
        }

        /** Connect our ListView with an ArrayAdapter to fill out data */
        //ArrayAdapter<> eventsAdapter = new ArrayAdapter<String>(this, R.layout.music_event_list_item, allMusicEvents);
        setListAdapter(new ArrayAdapter<>(this, R.layout.music_event_list_item, allMusicEvents));
    }
}
