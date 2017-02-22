# CambridgeBusStopSchedules
Android app that shows scheduled arrival of buses in Cambridge
# Usage
To add more bus stops:

1. Search for your bus stop in http://www.cambridgeshirebus.info/Text/Default.aspx
2. Format the bus stop reference to mimic the following:

  `{bus_stop_reference}&stopName={stop_name_of_your_reference}+{use_plus_to_indicate_spaces}`

3. Add to Urls  array within:

  `CambridgeBusStopSchedules/app/src/main/java/com/rajkishandevraj/busstopschedules/ListOfBusStopsActivity.java`
