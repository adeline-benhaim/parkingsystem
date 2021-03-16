package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ParkingSpotDAO {
    private static final Logger LOGGER = LogManager.getLogger("ParkingSpotDAO");

    public DataBaseConfig dataBaseConfig = new DataBaseConfig();

    /**
     * Get next available slot for vehicle
     *
     * @param parkingType vehicle type
     * @return parking number if available
     */
    public int getNextAvailableSlot(ParkingType parkingType) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = -1;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.GET_NEXT_PARKING_SPOT);
            ps.setString(1, parkingType.toString());
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception ex) {
            LOGGER.error("Error fetching next available slot", ex);
        } finally {
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closeConnection(con);
        }
        return result;
    }

    /**
     * Update the availability for that parking slot
     *
     * @param parkingSpot parking spot which will be updated
     * @return true if the parking update was successful
     */
    public boolean updateParking(ParkingSpot parkingSpot) {
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.UPDATE_PARKING_SPOT);
            ps.setBoolean(1, parkingSpot.isAvailable());
            ps.setInt(2, parkingSpot.getId());
            int updateRowCount = ps.executeUpdate();
            dataBaseConfig.closePreparedStatement(ps);
            return (updateRowCount == 1);
        } catch (Exception ex) {
            LOGGER.error("Error updating parking info", ex);
            return false;
        } finally {
            dataBaseConfig.closeConnection(con);
        }
    }

}
