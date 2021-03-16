package com.parkit.parkingsystem.model;

import com.parkit.parkingsystem.constants.ParkingType;

public class ParkingSpot {
    private int number;
    private ParkingType parkingType;
    private boolean isAvailable;

    /**
     * @param number number of parking spot
     * @param parkingType vehicle type
     * @param isAvailable availability of parking spot
     */
    public ParkingSpot(int number, ParkingType parkingType, boolean isAvailable) {
        this.number = number;
        this.parkingType = parkingType;
        this.isAvailable = isAvailable;
    }

    /**
     * @return number of parking spot
     */
    public int getId() {
        return number;
    }

    /**
     * @param number of parking spot
     */
    public void setId(int number) {
        this.number = number;
    }

    /**
     * @return vehicle type
     */
    public ParkingType getParkingType() {
        return parkingType;
    }

    /**
     * @param parkingType vehicle type
     */
    public void setParkingType(ParkingType parkingType) {
        this.parkingType = parkingType;
    }

    /**
     * @return if parking spot is available or not
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * @param available parking spot
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * @param o object o
     * @return number
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpot that = (ParkingSpot) o;
        return number == that.number;
    }

    /**
     * @return number of parking spot
     */
    @Override
    public int hashCode() {
        return number;
    }
}
