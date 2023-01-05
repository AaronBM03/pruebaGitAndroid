package com.example.pruebagit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ClubAdapter extends BaseAdapter {
    private List<Club> clubs;
    private Context context;

    public ClubAdapter(List<Club> clubs, Context context) {
        this.clubs = clubs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return clubs.size();
    }

    @Override
    public Club getItem(int position) {
        return clubs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View clubListItem = LayoutInflater.from(context).inflate(R.layout.club_layout, null);
        TextView clubNameText = clubListItem.findViewById(R.id.clubNameTextView);
        TextView clubIdText = clubListItem.findViewById(R.id.clubIdTextView);

        clubNameText.setText(clubs.get(position).getName());
        clubIdText.setText("#" + clubs.get(position).getId());

        return clubListItem;
    }
}
