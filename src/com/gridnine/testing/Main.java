package com.gridnine.testing;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> filterFlights;

        System.out.println("\nСписок без перелетов, имеющих вылеты до текущего момента времени:");
        filterFlights = FlightsFilterByRule.filterFlights(RulesList.departureBeforeCurrentTime.getInstance(),flights);
        printFlightsList(filterFlights);

        System.out.println("\nСписок без перелетов, имеющих сегменты с датой перелета раньше даты вылета:");
        filterFlights = FlightsFilterByRule.filterFlights(RulesList.arriveBeforeDepartureTime.getInstance(),flights);
        printFlightsList(filterFlights);

        System.out.println("\nСписок без перелетов, в которых время, проведенное на земле больше двух часов:");
        filterFlights = FlightsFilterByRule.filterFlights(RulesList.moreThanTwoHoursGround.getInstance(),flights);
        printFlightsList(filterFlights);

    }

    public static void printFlightsList(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }
}
