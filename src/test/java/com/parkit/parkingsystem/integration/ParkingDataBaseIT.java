package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ParkingDataBaseIT {

    private static final DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static ParkingSpotDAO parkingSpotDAO;
    private static TicketDAO ticketDAO;
    private static DataBasePrepareService dataBasePrepareService;
    private static Ticket ticket;

    @Mock
    private static InputReaderUtil inputReaderUtil;

    @BeforeAll
    private static void setUp() {
        parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
        ticketDAO = new TicketDAO();
        ticketDAO.dataBaseConfig = dataBaseTestConfig;
        dataBasePrepareService = new DataBasePrepareService();
    }

    @BeforeEach
    private void setUpPerTest() {
        dataBasePrepareService.clearDataBaseEntries();
    }

    @AfterAll
    private static void tearDown() {

    }

    //TODO: check that a ticket is actually saved in DB and Parking table is updated with availability
    @Test
    @DisplayName("Entrance of a car, ticket is save in DB and availability is update")
    public void testParkingACar() throws Exception {
        //GIVEN
        when(inputReaderUtil.readSelection()).thenReturn(1);
        ParkingType parkingType = ParkingType.CAR;
        parkingSpotDAO.getNextAvailableSlot(parkingType);
        when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);

        //WHEN
        parkingService.processIncomingVehicle();

        //THEN
        assertThat(parkingSpotDAO.getNextAvailableSlot(parkingType)).isNotEqualTo(1);
        assertThat(ticketDAO.getTicket("ABCDEF").getParkingSpot().getId()).isEqualTo(1);

    }

    @Test
    @DisplayName("Entrance of a bike, ticket is save in DB and availability is update")
    public void testParkingABike() throws Exception {
        //GIVEN
        when(inputReaderUtil.readSelection()).thenReturn(2);
        ParkingType parkingType = ParkingType.BIKE;
        parkingSpotDAO.getNextAvailableSlot(parkingType);
        when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);

        //WHEN
        parkingService.processIncomingVehicle();

        //THEN
        assertThat(parkingSpotDAO.getNextAvailableSlot(parkingType)).isNotEqualTo(1);
        assertThat(ticketDAO.getTicket("ABCDEF").getParkingSpot().getId()).isEqualTo(4);

    }

    //TODO: check that the fare generated and out time are populated correctly in the database
    @Test
    @DisplayName("Exit of a car, fare and out time are correctly generated in DB")
    public void testParkingLotExit() throws Exception {
        //GIVEN
        testParkingACar();
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        Thread.sleep(1000);
        parkingService.processExitingVehicle();

        //WHEN
        ticket = ticketDAO.getTicket("ABCDEF");

        //THEN
        assertThat(ticket.getPrice()).isEqualTo(0);
        assertThat(ticket.getParkingSpot().getId()).isEqualTo(1);
        assertNotNull(ticket.getOutTime());
    }

    @Test
    @DisplayName("Entrance then exit of a car two times, user become a recurring user")
    public void testParkingInAndOut2TimesToBecomeRecurringUser() throws Exception {
        //GIVEN
        testParkingLotExit();
        Thread.sleep(1000);

        //WHEN
        testParkingLotExit();
        Thread.sleep(1000);

        //THEN
        assertTrue(ticketDAO.getTicket("ABCDEF").isRecurringUser());
    }

}
