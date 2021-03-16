package com.parkit.parkingsystem.model;

import java.util.Date;

public class Ticket {
    private int id;
    private ParkingSpot parkingSpot;
    private String vehicleRegNumber;
    private double price;
    private Date inTime;
    private Date outTime;
    private boolean recurringUser;

    /**
     * @return ID number of ticket
     */
    public int getId() {
        return id;
    }

    /**
     * @param id number of this ticket
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return parking spot
     */
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    /**
     * @param parkingSpot of this ticket
     */
    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    /**
     * @return vehicle reg number
     */
    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    /**
     * @param vehicleRegNumber of this ticket
     */
    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    /**
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price of this ticket
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return entry time
     */
    public Date getInTime() {
        return inTime;
    }

    /**
     * @param inTime of this ticket
     */
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    /**
     * @return exit time
     */
    public Date getOutTime() {
        return outTime;
    }

    /**
     * @param outTime of this ticket
     */
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    /**
     * @return if is a recurring user
     */
    public boolean isRecurringUser() {
        return recurringUser;
    }

    /**
     * @param recurringUser of this ticket
     */
    public void setRecurringUser(boolean recurringUser) {
        this.recurringUser = recurringUser;
    }
}
