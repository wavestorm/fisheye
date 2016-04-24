package com.wavestormsystems.fisheye;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/*
* This class is the core function that uses the selection criteria to determine the final species
* A selection of the meat cuts as determined by the REGION, CUT and COLOUR criteria is filtered and displayed
* for final selection
*
* In future, for selecting WHOLE or DRESSED fish cuts, picture
* of the whole fish can also be included here
*/


public class FishSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_selection);

        final String TAG = FishSelection.class.getSimpleName();
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        final FishData Fish_Data = new FishData();
        final TableLayout lm = (TableLayout) findViewById(R.id.tablelayout3);
        final DatabaseHandler FishDB = new DatabaseHandler(this);

        String Menu_Title = FishDB.getMenuItem(5,Fish_Data.Language);
        TextView menutitle = (TextView) findViewById(R.id.menutitle4);
        if(FishDB.getFinalFishCounts()==0) Menu_Title = "Nothing in the database"; // Should be pulled from the language db
        menutitle.setText(Menu_Title);

        Log.d(TAG, "REGION: " + String.valueOf(Fish_Data.Region));
        Log.d(TAG, "CUT: " + String.valueOf(Fish_Data.Cut));
        Log.d(TAG, "COLOUR: " + String.valueOf(Fish_Data.Colour));
        Log.d(TAG, "Final fish counts: " + String.valueOf(FishDB.getFinalFishCounts()));

        int i;
        TableRow tR = new TableRow(this);
        for(i=0;i<FishDB.getFinalFishCounts();i++)
        {
            tR = new TableRow(this);
            tR.setPadding(35,35,35,35);
            tR.setOrientation(TableLayout.HORIZONTAL);
            tR.setGravity(Gravity.CENTER);

            final byte[] image = Base64.decode(FishDB.getFishImage(i), Base64.DEFAULT);
            final ImageButton btn = new ImageButton(this);
            btn.setId(i+1);
            btn.setBackgroundColor(Color.TRANSPARENT);
            btn.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.length));
            final int index_i = i;
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Fish_Data.Species = FishDB.getFishSpecies(index_i);
                    Log.d(TAG, "Species: " + String.valueOf(Fish_Data.Species));
                    startActivity(new Intent(getApplicationContext(), FishSpecies.class));
                    finish();
                }
            });
            tR.addView(btn);
            lm.addView(tR);
        }
    }
}
