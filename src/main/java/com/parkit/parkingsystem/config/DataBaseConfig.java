package com.parkit.parkingsystem.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Connection configuration to the database
 */
public class DataBaseConfig {

    private static final Logger LOGGER = LogManager.getLogger("DataBaseConfig");

    /**
     * Initialisation of database connexion
     * @return connection
     * @throws ClassNotFoundException class not found
     * @throws SQLException SQL Exception
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        LOGGER.info("Create DB connection");
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/prod?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "rootroot");
    }

    /**
     * @param con connection to close
     */
    public void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
                LOGGER.info("Closing DB connection");
            } catch (SQLException e) {
                LOGGER.error("Error while closing connection", e);
            }
        }
    }

    /**
     * @param ps prepared statement to close
     */
    public void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
                LOGGER.info("Closing Prepared Statement");
            } catch (SQLException e) {
                LOGGER.error("Error while closing prepared statement", e);
            }
        }
    }

    /**
     * @param rs result set to close
     */
    public void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                LOGGER.info("Closing Result Set");
            } catch (SQLException e) {
                LOGGER.error("Error while closing result set", e);
            }
        }
    }
}
