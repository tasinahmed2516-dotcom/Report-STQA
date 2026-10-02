package parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    private Vehicle vehicle;
    private ParkingSlot parkingSlot;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Booking booking;

    @BeforeEach
    void setUp() {

        vehicle = new Vehicle(123, VehicleType.CAR, 100.0);
        parkingSlot = new ParkingSlot("S1", ParkingSlotType.REGULAR);
        startTime = LocalDateTime.of(2025, 1, 1, 9, 0);
        endTime = LocalDateTime.of(2025, 1, 1, 11, 0);
        booking = new Booking(1, vehicle, parkingSlot, startTime, endTime, 50.0);
    }



    @Test
    void constructorSetsAllFields() {
        assertEquals(1, booking.getBookingId());
        assertEquals(vehicle, booking.getVehicle());
        assertEquals(parkingSlot, booking.getParkingSlot());
        assertEquals(startTime, booking.getStartTime());
        assertEquals(endTime, booking.getEndTime());
        assertEquals(50.0, booking.getAmount());
    }

    @Test
    void newBookingStartsAsActive() {
        assertEquals(BookingStatus.ACTIVE, booking.getBookingStatus());
    }



    @Test
    void completeBookingSetsStatusToCompleted() {
        booking.completeBooking();
        assertEquals(BookingStatus.COMPLETED, booking.getBookingStatus());
    }

    @Test
    void completeBookingDoesNotChangeOtherFields() {
        booking.completeBooking();
        assertEquals(1, booking.getBookingId());
        assertEquals(vehicle, booking.getVehicle());
        assertEquals(50.0, booking.getAmount());
    }



    @Test
    void cancelBookingSetsStatusToCancelled() {
        booking.cancelBooking();
        assertEquals(BookingStatus.CANCELLED, booking.getBookingStatus());
    }



    @Test
    void cancelAfterCompleteOverridesStatus() {
        booking.completeBooking();
        booking.cancelBooking();
        assertEquals(BookingStatus.CANCELLED, booking.getBookingStatus());
    }

    @Test
    void completeAfterCancelOverridesStatus() {
        booking.cancelBooking();
        booking.completeBooking();
        assertEquals(BookingStatus.COMPLETED, booking.getBookingStatus());
    }



    @Test
    void toStringContainsBookingId() {
        assertTrue(booking.toString().contains("bookingId=1"));
    }
}