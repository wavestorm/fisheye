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
* Selection of the colour of the fish meat
* In future, for selecting WHOLE and DRESSED fish cuts, the colour
* and texture of the skin also can be included here
*/

public class FishColour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_colour);

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        final FishData Fish_Data = new FishData();
        final TableLayout lm = (TableLayout) findViewById(R.id.tablelayout2);
        final DatabaseHandler FishDB = new DatabaseHandler(this);

        final String Menu_Title = FishDB.getMenuItem(4,Fish_Data.Language);
        TextView menutitle = (TextView) findViewById(R.id.menutitle3);
        menutitle.setText(Menu_Title);

        int i;
        TableRow tR = new TableRow(this);
        for(i=0;i<5;i++)
        {
            if((i%2)==0) {
                tR = new TableRow(this);
                tR.setPadding(35,35,35,35);
                tR.setOrientation(TableLayout.HORIZONTAL);
                tR.setGravity(Gravity.CENTER);
            }
            final byte[] image = Base64.decode(FishDB.getColourImage(i), Base64.DEFAULT);
            final ImageButton btn = new ImageButton(this);
            btn.setId(i+1);
            btn.setBackgroundColor(Color.TRANSPARENT);
            btn.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.length));
            final int index_i = i+1;
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Fish_Data.Colour = index_i;
                    startActivity(new Intent(getApplicationContext(), FishSelection.class));
                    finish();
                }
            });
            tR.addView(btn);
            if((i%2)==0)lm.addView(tR);
        }
    }
}
