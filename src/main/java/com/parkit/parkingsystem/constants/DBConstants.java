package com.parkit.parkingsystem.constants;

/**
 * SQL queries used in application
 */
public class DBConstants {

    /**
     * Get next parking spot available with min parking number for type of vehicle
     */
    public static final String GET_NEXT_PARKING_SPOT = "select min(PARKING_NUMBER) from parking where AVAILABLE = true and TYPE = ?";

    /**
     * Update availability of parking spot
     */
    public static final String UPDATE_PARKING_SPOT = "update parking set available = ? where PARKING_NUMBER = ?";

    /**
     * Set ticket information into the database
     */
    public static final String SAVE_TICKET = "insert into ticket(PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME, RECURRING_USERS) values(?,?,?,?,?,?)";

    /**
     * Update price and out time in the ticket into the database
     */
    public static final String UPDATE_TICKET = "update ticket set PRICE=?, OUT_TIME=? where ID=?";

    /**
     * Get ticket information via vehicle reg number
     */
    public static final String GET_TICKET = "select t.PARKING_NUMBER, t.ID, t.PRICE, t.IN_TIME, t.OUT_TIME, t.RECURRING_USERS, p.TYPE from ticket t,parking p where p.parking_number = t.parking_number and t.VEHICLE_REG_NUMBER=? order by t.IN_TIME desc limit 1";

    /**
     * Count the occurrences of a same vehicle reg number with out time is not null
     */
    public static final String GET_RECURRING_USERS = "select count(t.VEHICLE_REG_NUMBER) from ticket t where t.VEHICLE_REG_NUMBER=?  and t.OUT_TIME is not null";
}
