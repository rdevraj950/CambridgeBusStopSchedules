package com.rajkishandevraj.busstopschedules.BusProviders;

import android.content.Context;

import com.rajkishandevraj.busstopschedules.Bus.IBus;

import org.jsoup.nodes.Document;

import java.util.List;

public interface IBusProvider {
    List<IBus> getBuses();
    void OnPostExecuteComplete(Document document);
}
