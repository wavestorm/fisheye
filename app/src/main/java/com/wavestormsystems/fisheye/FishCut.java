package com.wavestormsystems.fisheye;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/*
* Selection of the cut of meat
*/

public class FishCut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_cut);

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        final FishData Fish_Data = new FishData();
        final TableLayout lm = (TableLayout) findViewById(R.id.tablelayout1);
        final DatabaseHandler FishDB = new DatabaseHandler(this);

        final String Menu_Title = FishDB.getMenuItem(3,Fish_Data.Language);
        TextView menutitle = (TextView) findViewById(R.id.menutitle2);
        menutitle.setText(Menu_Title);

        int i;
        TableRow tR = new TableRow(this);
        for(i=0;i<6;i++)
        {
            if((i%2)==0) {
                tR = new TableRow(this);
                tR.setPadding(35,35,35,35);
                tR.setOrientation(TableLayout.HORIZONTAL);
                tR.setGravity(Gravity.CENTER);
            }
            final byte[] image = Base64.decode(FishDB.getCutImage(i), Base64.DEFAULT);
            final ImageButton btn = new ImageButton(this);
            btn.setId(i+1);
            btn.setBackgroundColor(Color.TRANSPARENT);
            btn.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.length));
            final int index_i = i+1;
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Fish_Data.Cut = index_i;
                    startActivity(new Intent(getApplicationContext(), FishColour.class));
                    // There is no Finish() here, because we want users to be able
                    // to jump back to this point to start over with another selection
                    // as quickly as possible
                }
            });
            tR.addView(btn);
            if((i%2)==0)lm.addView(tR);
        }

    }

}
