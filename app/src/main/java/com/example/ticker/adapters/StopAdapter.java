package com.example.ticker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ticker.R;
import com.example.ticker.models.Route;
import com.example.ticker.models.Stop;

import java.util.ArrayList;
import java.util.List;

public class StopAdapter extends ArrayAdapter {

    private List<Stop> dataList;

    private Context mContext;

    private int itemLayout;

    private ListFilter listFilter = new StopAdapter.ListFilter();

    private List<Stop> dataListAllItems;


    public StopAdapter(@NonNull Context context, int resource, List<Stop> storeDataLst) {
        super(context, resource, storeDataLst);
        this.dataList = storeDataLst;
        this.mContext = context;
        this.itemLayout = resource;

    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return dataList.get(position).getStation_name();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            view.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
            TextView itemTextView = view.findViewById(R.id.id_text);
            itemTextView.setText(getItem(position));

        }
        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    public class ListFilter extends Filter {
        private Object lock = new Object();

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (dataListAllItems == null) {
                synchronized (lock) {
                    dataListAllItems = new ArrayList<>(dataList);
                }
            }
            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    results.values = dataListAllItems;
                    results.count = dataListAllItems.size();
                }
            } else {
                final String searchStrLowerCase = prefix.toString().toLowerCase();
                ArrayList<Stop> matchValues = new ArrayList<Stop>();
                for (Stop Item : dataListAllItems) {
                    String dataItem = Item.getStation_name();
                    if (dataItem.toLowerCase().startsWith(searchStrLowerCase)) {
                        matchValues.add(Item);
                    }
                }
                results.values = matchValues;
                results.count = matchValues.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                dataList = (ArrayList<Stop>) results.values;
            } else {
                dataList = null;
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
