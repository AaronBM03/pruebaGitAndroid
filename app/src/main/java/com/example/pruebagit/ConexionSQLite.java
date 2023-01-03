package com.example.pruebagit;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConexionSQLite extends SQLiteOpenHelper
{
    private static String DB_PATH = "/data/data/com.example.pruebagit/databases/";
    private static String DB_NAME = "cardchamp.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public ConexionSQLite(@Nullable Context context, @Nullable String name,
                          @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        // Nothing
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        // Nothing until structure changes
    }

    public void createDataBase() throws IOException
    {
        boolean dbExist = checkDataBase();

        if (dbExist)
        {
            // Si existe, no haemos nada!
        } else
        {
            // Llamando a este método se crea la base de datos vacía en la ruta
            // por defecto del sistema de nuestra aplicación por lo que
            // podremos sobreescribirla con nuestra base de datos.
            this.getReadableDatabase();
            try
            {
                copyDataBase();
            } catch (IOException e)
            {
                throw new Error("Error copiando database");
            }
        }
    }

    private boolean checkDataBase()
    {
        SQLiteDatabase checkDB = null;
        try
        {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e)
        {
            // Base de datos no creada todavia
        }

        if (checkDB != null)
        {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    public void openDataBase() throws SQLException
    {
        // Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close()
    {
        if (myDataBase != null)
        {
            myDataBase.close();
        }
        super.close();
    }

    private void copyDataBase() throws IOException
    {
        OutputStream databaseOutputStream = new FileOutputStream("" + DB_PATH + DB_NAME);
        InputStream databaseInputStream;

        byte[] buffer = new byte[1024];
        int length;

        databaseInputStream = myContext.getAssets().open("MiPrimeraBD");
        while ((length = databaseInputStream.read(buffer)) > 0)
        {
            databaseOutputStream.write(buffer);
        }

        databaseInputStream.close();
        databaseOutputStream.flush();
        databaseOutputStream.close();
    }
}
