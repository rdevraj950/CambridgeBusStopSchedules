package com.rajkishandevraj.busstopschedules.Bus;

public class Bus implements IBus{
    private String _busNumber;
    private String _destination;
    private String _arrivalTime;

    public String getDestination() { return _destination; }

    public String getBusNumber() { return _busNumber; }

    public String getArrivalTime() { return _arrivalTime; }

    public void setArrivalTime(String arrivalTime) { _arrivalTime = arrivalTime; }

    public void setBusNumber(String busNumber) { _busNumber = busNumber; }

    public void setDestination(String destination) { _destination = destination; }
}
