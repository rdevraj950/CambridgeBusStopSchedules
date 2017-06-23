package com.rajkishandevraj.busstopschedules.BusArrival;

import java.util.ArrayList;

public class BusArrival {
    private String _busNumber;
    private String _destination;
    private String _arrivalTime;

    public BusArrival(String busNumber, String destination, String arrivalTime){
        _busNumber = busNumber;
        _destination = destination;
        _arrivalTime = arrivalTime;
    }

    public String get_busNumber() {
        return _busNumber;
    }

    public String get_arrivalTime() {
        return _arrivalTime;
    }

    public String get_destination() {
        return _destination;
    }
}
