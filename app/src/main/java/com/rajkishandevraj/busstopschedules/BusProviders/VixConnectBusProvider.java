package com.rajkishandevraj.busstopschedules.BusProviders;
import android.app.Activity;
import android.content.Context;

import com.rajkishandevraj.busstopschedules.Bus.Bus;
import com.rajkishandevraj.busstopschedules.Bus.IBus;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

public class VixConnectBusProvider implements IBusProvider{
    private static List<IBus> listOfBuses;
    @Override
    public List<IBus> getBuses() {
        return listOfBuses;
    }

    @Override
    public void OnPostExecuteComplete(Context context, Document document) {
        Elements details = document.getElementsByClass("emphasise");
        Elements departures = document.getElementsByClass("body-cell");

        try {
            ((Activity)context).setTitle(details.get(1).text());
        } catch(Exception e){((Activity)context).setTitle("fail");}

        int counter = 1;
        Bus tmpBus = new Bus();
        for (int i = 0; i < departures.size(); i++) {
            if (counter == 1){
                tmpBus.setBusNumber(departures.get(i).ownText());
                counter++;
                continue;
            }
            if (counter == 2){
                counter++;
                continue;
            }
            if (counter == 3){
                tmpBus.setDestination(departures.get(i).ownText());
                counter++;
                continue;
            }
            if (counter == 4){
                counter++;
                continue;
            }
            if (counter == 5){
                tmpBus.setArrivalTime(departures.get(i).ownText());
                counter++;
                continue;
            }
            if (counter == 6){
                counter = 0;
                counter++;
                continue;
            }
        }
    }

}
