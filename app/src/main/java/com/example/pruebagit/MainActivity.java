package com.example.pruebagit;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.pruebagit.model.Card;
import com.example.pruebagit.model.Championship;
import com.example.pruebagit.model.Club;
import com.example.pruebagit.model.Color;
import com.example.pruebagit.model.Position;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static int DB_VERSION = 1;
    public static String DB_NAME = "cardchamp.db";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // COMENTARIO 555

        //crearBD();
        System.out.println("Empieza");
//        System.out.println(recuperarClub(1));
        testChampionship();
        System.out.println("Acaba");
    }

    public void testChampionship(){
        Card player = new Card(1, "Aaron", Color.BRONZE, 0, Position.GK, 60, 46, 46, 46, 46, 46, 46, 46, 7, 1);
        Championship championship = new Championship("Champions League", Color.BRONZE, null, 0);
        System.out.println(championship);
        championship.generateNewMatch(this, player);
        System.out.println(championship);
    }

    public void crearBD()
    {
        ConexionSQLite db2 = new ConexionSQLite(this, DB_NAME, null, DB_VERSION);
        try {
            db2.createDataBase();
            db2.openDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Club recuperarClub(int id) {
        SQLiteDatabase db = getReadableDatabase();
        System.out.println(db.getMaximumSize());
        System.out.println(db.getPageSize());
        System.out.println(db.getPath());
        String[] valores_recuperar = {"id", "name"};
        Cursor c = db.query("club", valores_recuperar, "id=" + id,
                null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }
        Club clubs = new Club(c.getInt(0), c.getString(1));
        db.close();
        c.close();
        return clubs;
    }

    private SQLiteDatabase getReadableDatabase()
    {
        ConexionSQLite db2 = new ConexionSQLite(this, DB_NAME, null, DB_VERSION);
        db2.openDataBase();
        return db2.getReadableDatabase();
    }
}