package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightsFilterByRule {
    static List<Flight> filterFlights(Rule typeRule, List<Flight> list) {
        List<Flight> flights = new ArrayList<>();
        if (typeRule instanceof DepartureBeforeCurrentTime) { // вылет до текущего времени
            boolean isBefore = false;
            for (int i = 0; i < list.size(); i++) {
                for (Segment segment : list.get(i).getSegments()) {
                    LocalDateTime dateTime = LocalDateTime.now();
                    if (segment.getDepartureDate().isBefore(dateTime)) {
                        isBefore = true;
                    }
                }
                if (isBefore) {
                    isBefore = false;
                    continue;
                } else {
                    flights.add(list.get(i));
                }
            }
        } else if (typeRule instanceof ArriveBeforeDepartureTime) { // есть сегменты с датой до прилета раньше вылета
            boolean arriveTimeEarlyDurant = false;
            for (int i = 0; i < list.size(); i++) {
                for (Segment segment : list.get(i).getSegments()) {
                    if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                        arriveTimeEarlyDurant = true;
                    }
                }
                if (arriveTimeEarlyDurant) {
                    arriveTimeEarlyDurant = false;
                    continue;
                } else {
                    flights.add(list.get(i));
                }
            }
        } else if (typeRule instanceof MoreThanTwoHoursGround) { // время на земле больше 2 часов
            boolean groundTimeMoreTwoHours = false;
            List<Segment> segments;
            int arrivalHour = 0, arrivalMinutes = 0;
            int departureHour = 0, departureMinutes = 0;
            int resultTime = 0;
            int groundTime = 0;
            for (int i = 0; i < list.size(); i++) {
                segments = list.get(i).getSegments();
                for (int j = 1; j < segments.size(); j++) {
                    departureHour = segments.get(j).getDepartureDate().getHour();
                    departureMinutes = segments.get(j).getDepartureDate().getMinute();

                    arrivalHour = segments.get(j-1).getArrivalDate().getHour();
                    arrivalMinutes = segments.get(j-1).getArrivalDate().getMinute();

                    groundTime = departureHour - arrivalHour;
                    groundTime = groundTime < 0 ? groundTime + 24 : groundTime;
                    groundTime = groundTime * 60 + (departureMinutes - arrivalMinutes);

                    resultTime += groundTime;

                    if (resultTime > 120) {
                        groundTimeMoreTwoHours = true;
                    }
                }
                if (groundTimeMoreTwoHours) {
                    groundTimeMoreTwoHours = false;
                    resultTime = 0;
                    continue;
                } else {
                    flights.add(list.get(i));
                }
            }
        }
        return flights;
    }
}
