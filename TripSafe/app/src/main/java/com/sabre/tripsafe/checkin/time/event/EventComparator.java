package com.sabre.tripsafe.checkin.time.event;

import java.util.Comparator;

/**
 * Created by Alan on 7/26/2015.
 */
public class EventComparator implements Comparator<AbstractCheckInEvent> {

    @Override
    public int compare(AbstractCheckInEvent lhs, AbstractCheckInEvent rhs) {
        return Long.compare(lhs.getId(),rhs.getId());
    }
}
