package com.rajkishandevraj.busstopschedules.ArrayAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rajkishandevraj.busstopschedules.BusArrival.BusArrival;
import com.rajkishandevraj.busstopschedules.R;

import java.util.List;

public class BusArrivalArrayAdapter extends ArrayAdapter<BusArrival> {
    private Context _context;
    private int _resource;
    private List<BusArrival> _busArrivals;

    public BusArrivalArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<BusArrival> objects) {
        super(context, resource, objects);
        _context = context;
        _resource = resource;
        _busArrivals = objects;
    }

    public View getView(int index, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = ((Activity)_context).getLayoutInflater().inflate(_resource, parent, false);
        }

        BusArrival busArrival = _busArrivals.get(index);
        TextView textView = (TextView) itemView.findViewById(R.id.bus_arrival_bus_number);
        textView.append(busArrival.get_busNumber());

        textView = (TextView) itemView.findViewById(R.id.bus_arrival_destination);
        textView.setText(busArrival.get_destination());

        textView = (TextView) itemView.findViewById(R.id.bus_arrival_arrival_time);
        textView.setText(busArrival.get_arrivalTime());

        return itemView;
    }
}
