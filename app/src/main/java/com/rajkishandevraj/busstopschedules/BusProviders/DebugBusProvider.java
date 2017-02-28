package com.rajkishandevraj.busstopschedules.BusProviders;

import com.rajkishandevraj.busstopschedules.Bus.DebugBus;
import com.rajkishandevraj.busstopschedules.Bus.IBus;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class DebugBusProvider implements IBusProvider {
    private List<IBus> _listOfBuses;
    @Override
    public List<IBus> getBuses() {
        return _listOfBuses;
    }

    @Override
    public void OnPostExecuteComplete(Document document) {
        _listOfBuses = new ArrayList<>();
        _listOfBuses.add(new DebugBus("1", "Cherry Hinton", "12:15"));
        _listOfBuses.add(new DebugBus("17", "Stetchworth", "13:16"));
        _listOfBuses.add(new DebugBus("3", "Folbourn", "13:14"));
    }
}