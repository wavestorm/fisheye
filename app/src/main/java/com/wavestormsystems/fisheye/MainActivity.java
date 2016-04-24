// Main entry page
// Select the language and the region before proceeding to the Fish Cut selection

package com.wavestormsystems.fisheye;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/*
* FishEye v 0.1
* A proposed solution to Problem Statement #8 by the MarViva Foundation
* Submitted for Fishackathon 2016 by Dylan Francis Bailey, Port Elizabeth, South Africa
*
* This is my first ever Android application. I have never written one before.
* (You may notice some strange things going on in here...)
* My strengths lie in databases, environmental data and records management,
* so I leveraged my knowledge in SQL to tackle the task.
* I only write open source software. I do not develop professionally.
*
* I chose this problem statement as it may have use in my own country as part
* of the South African Sustainable Seafood Initiative (SASSI) by the WWF.
* I sincerely hope that the MarViva foundation finds this app useful, I intend
* to develop it further if possible.
*
* All of the data is contained in a structured database Data.db, in the assets directory.
* The database is accessed as read only directly from the assets directory.
* This makes installation simpler. The app can also be easily updated by simply
* updating the database.
*
* This Android App was purposefully designed to be as simple as possible,
* even a child should be able to use it.
*
*  The app has been designed with internationalization in mind, through the selection
*  of region and language. Language has been handled internally via the database,
*  rather than relying on Androids internal translation functions.
*
*  I only included the data that was supplied, so some selections will return no data.
*  There is an erronous entry in the supplied data: Onchorhycnus mykiss is a trout, not a tilapia.
*
*  I produced all artwork used in the cut selection menu and is free for use without restriction.
*  The application icon was created from one of my own underwater photos and is free
*  for use without restriction.
*
*  The Android "BACK" navigation button is used to jump back to start from the FISH CUT
*  menu to begin another selection. Pressing "BACK" a second time closes the app.
*/


/*
* The main class where the activity begins
* The language and region is selected here
*/

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FishData Fish_Data = new FishData();
        final TextView menutitle = (TextView) findViewById(R.id.menutitle);
        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearlayout);
        final DatabaseHandler FishDB = new DatabaseHandler(this);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        int i;
        for(i=0;i<FishDB.getItems("LANGUAGE");i++)
        {
            final Context current_context = this;
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            ll.setGravity(Gravity.CENTER);
            final Button btn = new Button(this);
            btn.setId(i+1);
            btn.setText(FishDB.getLanguageItem(i));
            btn.setLayoutParams(params);
            final int index_i = i+1;
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Fish_Data.Language = index_i;
                    LinearLayout old_ll = (LinearLayout)findViewById(R.id.linearlayout);
                    old_ll.removeAllViews();
                    String Menu_Title = FishDB.getMenuItem(2,Fish_Data.Language);
                    menutitle.setText(Menu_Title);
                    int j;
                    for(j=0;j<FishDB.getItems("REGION");j++)
                    {
                        LinearLayout ll = new LinearLayout(current_context);
                        ll.setOrientation(LinearLayout.HORIZONTAL);
                        ll.setGravity(Gravity.CENTER);
                        final Button btn = new Button(current_context);
                        btn.setId(j+1);
                        btn.setText(FishDB.getRegionItem(j));
                        btn.setLayoutParams(params);
                        final int index_j = j;
                        btn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Fish_Data.Region = index_j;
                                startActivity(new Intent(MainActivity.this, FishCut.class));
                                finish();
                            }
                        });
                        ll.addView(btn);
                        lm.addView(ll);
                    }
                }
            });
            ll.addView(btn);
            lm.addView(ll);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.wavestormsystems.fisheye/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.wavestormsystems.fisheye/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

}
