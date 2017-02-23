package com.rajkishandevraj.busstopschedules.BusProviders;

import android.content.Context;

import com.rajkishandevraj.busstopschedules.Bus.DebugBus;
import com.rajkishandevraj.busstopschedules.Bus.IBus;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class DebugBusProvider implements IBusProvider {
    private List<IBus> listOfBuses;
    @Override
    public List<IBus> getBuses() {
        return listOfBuses;
    }

    @Override
    public void OnPostExecuteComplete(Context context, Document document) {
        listOfBuses = new ArrayList<>();
        listOfBuses.add(new DebugBus("1", "Cherry Hinton", "12:15"));
        listOfBuses.add(new DebugBus("17", "Stetchworth", "13:16"));
        listOfBuses.add(new DebugBus("3", "Folbourn", "13:14"));
    }
}
