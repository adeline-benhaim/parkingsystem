package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket) {
        if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
            assert ticket.getOutTime() != null;
            throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
        }

        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();

        // TODO: Some tests are failing here. Need to check if this logic is correct
        double durationMs = outHour - inHour;
        double duration = durationMs / (60 * 60 * 1000);
        double discount = 1;

        if (ticket.isRecurringUser()) {
            discount = Fare.DISCOUNT_FOR_RECURRING_USERS;
        }

        ParkingType parkingType = ticket.getParkingSpot().getParkingType();

        if (duration >= 0.5) {
            double rate;
            switch (parkingType) {
                case CAR: {
                    rate = Fare.CAR_RATE_PER_HOUR;
                    break;
                }
                case BIKE: {
                    rate = Fare.BIKE_RATE_PER_HOUR;
                    break;
                }
                default:
                throw new IllegalArgumentException("Unknown Parking Type");
            }
            double price = duration * rate * discount;
            price = toRoundPrice(price);
            ticket.setPrice(price);
        } else {
            switch (parkingType) {
                case CAR:
                case BIKE: {
                    ticket.setPrice(0);
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unknown Parking Type");
            }
        }
    }

    public double toRoundPrice(double price) {
        return Math.round(price * 100.0) / 100.0;
    }
}