package com.rajkishandevraj.busstopschedules.Factories;

import com.rajkishandevraj.busstopschedules.Bus.DebugBus;
import com.rajkishandevraj.busstopschedules.BusProviders.DebugBusProvider;
import com.rajkishandevraj.busstopschedules.BusProviders.IBusProvider;
import com.rajkishandevraj.busstopschedules.BusProviders.VixConnectBusProvider;

public class BusProviderFactory {
    private static IBusProvider iBusProvider;
    public static IBusProvider instance(){
        if (iBusProvider == null)
            iBusProvider = new DebugBusProvider();
        return iBusProvider;
    }
}
