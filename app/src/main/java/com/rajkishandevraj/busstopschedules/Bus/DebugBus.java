package com.rajkishandevraj.busstopschedules.Bus;

public class DebugBus implements IBus {
    private String _busNumber;
    private String _destination;
    private String _arrivalTime;

    public DebugBus(String busNumber, String destination, String arrivalTime) {
        this._busNumber = busNumber;
        this._destination = destination;
        this._arrivalTime = arrivalTime;
    }

    public String getDestination() { return _destination; }

    public String getBusNumber() { return _busNumber; }

    public String getArrivalTime() { return _arrivalTime; }
}
