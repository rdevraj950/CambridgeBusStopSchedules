package com.rajkishandevraj.busstopschedules.ArrayAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rajkishandevraj.busstopschedules.Bus.Bus;
import com.rajkishandevraj.busstopschedules.Bus.IBus;
import com.rajkishandevraj.busstopschedules.R;

import java.util.List;

public class BusArrayAdapter extends ArrayAdapter<IBus> {
    private Context _context;
    private int _resource;
    private List<IBus> _buses;

    public BusArrayAdapter(Context context, int resource, List<IBus> objects) {
        super(context, resource, objects);
        _context = context;
        _resource = resource;
        _buses = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null){
            itemView = ((Activity)_context).getLayoutInflater().inflate(_resource, parent, false);
        }
        TextView busNumber = (TextView) itemView.findViewById(R.id.busNumber);
        busNumber.append(_buses.get(position).getBusNumber());
        TextView destination = (TextView) itemView.findViewById(R.id.destination);
        destination.append(_buses.get(position).getDestination());
        TextView arrivalTime = (TextView) itemView.findViewById(R.id.arrivalTime);
        arrivalTime.append(_buses.get(position).getArrivalTime());
        return itemView;
    }
}
