package com.rajkishandevraj.busstopschedules.BusProviders;

import com.rajkishandevraj.busstopschedules.Bus.IBus;

import java.util.List;

public interface IBusProvider {
    List<IBus> getBuses();
}
