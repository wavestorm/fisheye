package com.wavestormsystems.fisheye;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by DyZ on 2016/04/23.
 * This class handles all of the database transactions
 */

public class DatabaseHandler extends SQLiteAssetHelper
{
    private static String DB_NAME = "Data.db";

    public DatabaseHandler(Context context)
    {
        super(context, DB_NAME, null, 1);
    }

    // Functions to retrieve the necessary information from the database.
    // We let SQL do all the data related work here, and Android handles the UI.
    // The functions are self explanatory

    public String getMenuItem(int id, int language)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  =   "SELECT * FROM MENUS";
        Cursor cursor = db.rawQuery(sql, new String[] {});
        int count = cursor.getCount();
        cursor.moveToPosition(id);
        String data = cursor.getString(language);
        db.close();
        cursor.close();
        return data;
    }

    public String getLanguageItem(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  =   "SELECT * FROM LANGUAGE";
        Cursor cursor = db.rawQuery(sql, new String[] {});
        int count = cursor.getCount();
        cursor.moveToPosition(id);
        String data = cursor.getString(1);
        db.close();
        cursor.close();
        return data;
    }

    public String getRegionItem(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  =   "SELECT * FROM REGION";
        Cursor cursor = db.rawQuery(sql, new String[] {});
        int count = cursor.getCount();
        cursor.moveToPosition(id);
        String data = cursor.getString(1);
        db.close();
        cursor.close();
        return data;
    }

    public byte[] getFishImage(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        final FishData Fish_Data = new FishData();
        String Colour = String.valueOf(Fish_Data.Colour);
        String Cut = String.valueOf(Fish_Data.Cut);
        String Region = String.valueOf(Fish_Data.Region);
        String sql  =   "SELECT * FROM APPEARANCE WHERE REGION = " + Region + " AND CUT = " + Cut + " AND COLOUR = " + Colour;
        Cursor cursor = db.rawQuery(sql, new String[] {});
        cursor.moveToPosition(id);
        byte[] data = cursor.getBlob(4);
        db.close();
        cursor.close();
        return data;
    }

    public int getFishSpecies(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        final FishData Fish_Data = new FishData();
        String Colour = String.valueOf(Fish_Data.Colour);
        String Cut = String.valueOf(Fish_Data.Cut);
        String Region = String.valueOf(Fish_Data.Region);
        String sql  =   "SELECT * FROM APPEARANCE WHERE REGION = " + Region + " AND CUT = " + Cut + " AND COLOUR = " + Colour;
        Cursor cursor = db.rawQuery(sql, new String[] {});
        cursor.moveToPosition(id);
        int data = cursor.getInt(5)-1;
        db.close();
        cursor.close();
        return data;
    }

    public int getFinalFishCounts()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        final FishData Fish_Data = new FishData();
        String Colour = String.valueOf(Fish_Data.Colour);
        String Cut = String.valueOf(Fish_Data.Cut);
        String Region = String.valueOf(Fish_Data.Region);
        String sql  =   "SELECT * FROM APPEARANCE WHERE REGION = " + Region + " AND CUT = " + Cut + " AND COLOUR = " + Colour;
        Cursor cursor = db.rawQuery(sql, new String[] {});
        int count = cursor.getCount();
        db.close();
        cursor.close();
        return count;
    }

    public byte[] getColourImage(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  =   "SELECT * FROM COLOUR_IMAGE";
        Cursor cursor = db.rawQuery(sql, new String[] {});
        cursor.moveToPosition(id);
        byte[] data = cursor.getBlob(1);
        db.close();
        cursor.close();
        return data;
    }

    public byte[] getCutImage(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  =   "SELECT * FROM CUT_IMAGE";
        Cursor cursor = db.rawQuery(sql, new String[] {});
        cursor.moveToPosition(id);
        byte[] data = cursor.getBlob(1);
        db.close();
        cursor.close();
        return data;
    }

    public byte[] getStatusImage()
    {
        final FishData Fish_Data = new FishData();
        int id = Fish_Data.Status;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  =   "SELECT * FROM STATUS";
        Cursor cursor = db.rawQuery(sql, new String[] {});
        cursor.moveToPosition(id);
        byte[] data = cursor.getBlob(1);
        db.close();
        cursor.close();
        return data;
    }

    public String getSpeciesStatus()
    {
        final FishData Fish_Data = new FishData();
        int id = Fish_Data.Status;
        int language = Fish_Data.Language;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  =   "SELECT * FROM STATUS";
        Cursor cursor = db.rawQuery(sql, new String[] {});
        cursor.moveToPosition(id);
        String data = cursor.getString(language + 1);
        db.close();
        cursor.close();
        return data;
    }

    public byte[] getSpeciesImage()
    {
        final FishData Fish_Data = new FishData();
        int id = Fish_Data.Species;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  =   "SELECT * FROM SPECIES";
        Cursor cursor = db.rawQuery(sql, new String[] {});
        cursor.moveToPosition(id);
        byte[] data = cursor.getBlob(2);
        db.close();
        cursor.close();
        return data;
    }

    public void updateSpeciesStatus()
    {
        final FishData Fish_Data = new FishData();
        int id = Fish_Data.Species;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  =   "SELECT * FROM SPECIES";
        Cursor cursor = db.rawQuery(sql, new String[] {});
        cursor.moveToPosition(id);
        Fish_Data.Status = cursor.getInt(3);
        db.close();
        cursor.close();
    }

    public String getSpeciesScientificName()
    {
        final FishData Fish_Data = new FishData();
        int id = Fish_Data.Species;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  =   "SELECT * FROM SPECIES";
        Cursor cursor = db.rawQuery(sql, new String[] {});
        cursor.moveToPosition(id);
        String data = cursor.getString(1);
        db.close();
        cursor.close();
        return data;
    }

    public String getSpeciesCommonName()
    {
        final FishData Fish_Data = new FishData();
        int id = Fish_Data.Species;
        int language = Fish_Data.Language;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  =   "SELECT * FROM SPECIES_LOCAL_NAME";
        Cursor cursor = db.rawQuery(sql, new String[] {});
        cursor.moveToPosition(id);
        String data = cursor.getString(language);
        db.close();
        cursor.close();
        return data;
    }

    public String getSpeciesDescription()
    {
        final FishData Fish_Data = new FishData();
        int id = Fish_Data.Species;
        int language = Fish_Data.Language;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  =   "SELECT * FROM SPECIES_INFO";
        Cursor cursor = db.rawQuery(sql, new String[] {});
        cursor.moveToPosition(id);
        String data = cursor.getString(language);
        db.close();
        cursor.close();
        return data;
    }

    // Table Item Count
    public int getItems(String Table)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  =   "SELECT * FROM " + Table;
        Cursor cursor = db.rawQuery(sql, new String[] {});
        int count = cursor.getCount();
        db.close();
        cursor.close();
        return count;
    }
}

