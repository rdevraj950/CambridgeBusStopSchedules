package com.rajkishandevraj.busstopschedules;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class ListOfBusStopsActivity extends AppCompatActivity {
    //Lists the ending URL for the specific bus stops
    //Alter "stopName=" to set the name of the bus stop shown to the user
    ArrayList<String> Urls = new ArrayList<String>() {{
        add("0500CCITY525&stopName=Cambridge+Railway+Station+(Stop+2)");
        add("0500STEVE012&stopName=Gazelle+Way+(Inbound)");
        add("0500STEVE018&stopName=Marshal's+Close+(Inbound)");
        add("0500CCITY489&stopName=Cambridge+Emmanuel+Street+(Stop+E3)");
        add("0500CCITY488&stopName=Cambridge+Emmanuel+Street+(Stop+E2)");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout busStopMenuTblLayout = (TableLayout) findViewById(R.id.busStopMenuTblLayout);

        for(final String url : Urls){
            //Create TableRow for each URL
            TableRow tblRow = new TableRow(this);
            tblRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            //Create a TextView for each URL inside the tblRow
            TextView txtView = new TextView(this);
            txtView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            txtView.setTextSize(15);

            /*May have been better to create Factory to sort the URL and the Names of the bus stops or even to deal with creation of the Rows*/
            //Pattern is compiled from a string (RegExp)
            //Matcher class seems to store the pattern and the variable to which the pattern will be 'matched'
            Matcher match = Pattern.compile("stopName=").matcher(url);
            //match.find() finds the next instance where the pattern and the matcher match
            if(match.find()) {
                //Takes the index of the offset of the matched result and the url length to return the required substring
                //and replaces the '+' with spaces adding \n at the start and end
                txtView.setText("\n" + url.substring(match.end(), url.length()).replace('+', ' ') + "\n");
            }

            //Make the tblRow clickable
            tblRow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Intent myIntent = new Intent(ListOfBusStopsActivity.this, BusStopActivity.class);
                    myIntent.putExtra("busStopUrl", url);
                    startActivity(myIntent);
                }
            });

            tblRow.addView(txtView);

            busStopMenuTblLayout.addView(tblRow);
        }
    }
}
