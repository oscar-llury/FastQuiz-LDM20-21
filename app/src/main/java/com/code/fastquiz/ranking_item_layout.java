package com.code.fastquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class ranking_item_layout extends BaseAdapter {

    private Context context;
    private ArrayList<Player> data;
    private static LayoutInflater inflater = null;

    public ranking_item_layout(Context context, ArrayList<Player> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.ranking_item_layout, null);

        TextView text1 = (TextView) vi.findViewById(R.id.text1);
        text1.setText(Integer.toString(position+1));
        TextView text2 = (TextView) vi.findViewById(R.id.text2);
        text2.setText(data.get(position).getName());
        TextView text3 = (TextView) vi.findViewById(R.id.text3);
        text3.setText(Integer.toString(data.get(position).getScore())+" pts");
        return vi;
    }
}
