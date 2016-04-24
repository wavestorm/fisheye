package com.wavestormsystems.fisheye;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/*
* This class displays the final species information.
* Localization of species name currently uses the selected language.
* In future, the region can also be considered.
*/

public class FishSpecies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_species);

        final String TAG = FishSelection.class.getSimpleName();
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        final FishData Fish_Data = new FishData();
        final TableLayout lm = (TableLayout) findViewById(R.id.tablelayout4);
        final ImageView speciesIMG = (ImageView) findViewById(R.id.imageview1);
        final ImageView statusIMG = (ImageView) findViewById(R.id.statusimage);
        final DatabaseHandler FishDB = new DatabaseHandler(this);
        //Log.d(TAG, "Species: " + String.valueOf(Fish_Data.Species));

        String Menu_Title = FishDB.getSpeciesCommonName();
        TextView menutitle = (TextView) findViewById(R.id.menutitle5);
        menutitle.setText(Menu_Title);

        String Menu_Species = FishDB.getSpeciesScientificName();
        TextView menuspecies = (TextView) findViewById(R.id.species);
        menuspecies.setText(Menu_Species);

        FishDB.updateSpeciesStatus();

        final byte[] image = Base64.decode(FishDB.getSpeciesImage(), Base64.DEFAULT);
        speciesIMG.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.length));

        final byte[] image2 = Base64.decode(FishDB.getStatusImage(), Base64.DEFAULT);
        statusIMG.setImageBitmap(BitmapFactory.decodeByteArray(image2,0,image2.length));

        TableRow tR = new TableRow(this);
        tR.setOrientation(TableLayout.HORIZONTAL);
        tR.setGravity(Gravity.CENTER);
        TextView txt = new TextView(this);
        String Description = FishDB.getSpeciesDescription();
        txt.setText(Description);
        tR.addView(txt);
        txt.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        lm.addView(tR);
    }
}
