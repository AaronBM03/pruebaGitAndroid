package com.example.pruebagit.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pruebagit.ConexionSQLite;
import com.example.pruebagit.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Championship {
    private static final int TOTAL_MATCHES = 10;
    private String name;
    private Color color;
    private List<Match> matches;
    private int currentMatch;

    /**
     * @param name         Name of the championship
     * @param color        Color/rarity of the championship
     * @param matches      List of matches for the championship. If passed a null, it initializes an empty list
     * @param currentMatch Current match of the championship
     */
    public Championship(String name, Color color, List<Match> matches, int currentMatch) {
        super();
        this.name = name;
        this.color = color;
        this.matches = (matches != null) ? matches : new ArrayList<>();
        this.currentMatch = currentMatch;
    }


    public Championship() {

    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Color getColor() {
        return color;
    }


    public void setColor(Color color) {
        this.color = color;
    }


    public List<Match> getMatches() {
        return matches;
    }


    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }


    public int getCurrentMatch() {
        return currentMatch;
    }


    public void setCurrentMatch(int currentMatch) {
        this.currentMatch = currentMatch;
    }

    public void generateNewMatch(Context ctx, Card player) {
        Match match = new Match(player);
        match.setOpponent(searchOpponentCard(ctx, player.getCardAvg()));
        matches.add(currentMatch, match);
    }

    public Card searchOpponentCard(Context ctx, int playerAvg) {
        SQLiteDatabase db = getReadableDatabase(ctx);

        String[] valores_recuperar = {"id", "name", "color", "rarity", "position", "cardAvg", "rating", "pace", "shooting", "passing", "dribbling", "defending", "physicality", "nation_id", "club_id"};
        Cursor c = db.query("card", valores_recuperar, "cardAvg=" + playerAvg,
                null, null, null, null, null);
        if (c != null) {
            int count = c.getCount();
			c.moveToPosition(new Random().nextInt(count));
        }

		Card opponent = null;
		try {
			opponent = new Card(c.getInt(0),
					c.getString(c.getColumnIndexOrThrow("name")),
					Color.values()[c.getInt(c.getColumnIndexOrThrow("color"))],
					c.getInt(c.getColumnIndexOrThrow("rarity")),
					Position.values()[c.getInt(c.getColumnIndexOrThrow("position"))],
					c.getInt(c.getColumnIndexOrThrow("cardAvg")),
					c.getInt(c.getColumnIndexOrThrow("rating")),
					c.getInt(c.getColumnIndexOrThrow("pace")),
					c.getInt(c.getColumnIndexOrThrow("shooting")),
					c.getInt(c.getColumnIndexOrThrow("passing")),
					c.getInt(c.getColumnIndexOrThrow("dribbling")),
					c.getInt(c.getColumnIndexOrThrow("defending")),
					c.getInt(c.getColumnIndexOrThrow("physicality")),
					c.getInt(c.getColumnIndexOrThrow("nation_id")),
					c.getInt(c.getColumnIndexOrThrow("club_id"))
					);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		db.close();
        c.close();
        return opponent;
    }

    private SQLiteDatabase getReadableDatabase(Context ctx) {
        // TODO Cambiar el contexto
        ConexionSQLite db2 = new ConexionSQLite(ctx, MainActivity.DB_NAME, null, MainActivity.DB_VERSION);
        db2.openDataBase();
        return db2.getReadableDatabase();
    }

    @Override
    public String toString() {
        return "Championship{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", matches=" + matches +
                ", currentMatch=" + currentMatch +
                '}';
    }
}
