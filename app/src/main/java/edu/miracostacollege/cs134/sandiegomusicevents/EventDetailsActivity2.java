package edu.miracostacollege.cs134.sandiegomusicevents;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;

import edu.miracostacollege.cs134.sandiegomusicevents.R;

public class EventDetailsActivity2 extends AppCompatActivity {

    private ImageView eventImageView;
    private TextView eventTitleTextView;
    private TextView eventDetailsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        eventImageView = findViewById(R.id.eventImageView);
        eventTitleTextView = findViewById(R.id.eventTitleTextView);
        eventDetailsTextView = findViewById(R.id.eventDetailsTextView);

        // Retrieve the intent
        Intent mainIntent = getIntent();

        // extract the artist and details from the Intent
        String artist = mainIntent.getStringExtra("Artist");
        String details = mainIntent.getStringExtra("Details");
        String imageName = artist.replaceAll(" ", "") + ".png";

        // fill TVs with respective text
        eventTitleTextView.setText(artist);
        eventDetailsTextView.setText(details);


        // use AssetManager to load the correct image
        AssetManager am = getAssets();

        // Define an input stream to the selected image
        try {
            InputStream stream = am.open(imageName);
            // Create a drawable object to display
            Drawable eventImage = Drawable.createFromStream(stream, artist);
            eventImageView.setImageDrawable(eventImage);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
