
package parking;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSlotTest {

    // ---------- Constructor Tests ----------

    @Test
    void testConstructor() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        assertEquals("A1", slot.getSlotId());
        assertEquals(ParkingSlotType.COMPACT, slot.getSlotType());
        assertTrue(slot.isActive());
        assertEquals(0.0, slot.getBalance());
        assertNotNull(slot.getWallet());
        assertNotNull(slot.getBookings());
        assertTrue(slot.getBookings().isEmpty());
    }

    // ---------- Activation / Deactivation Tests ----------

    @Test
    void testDeactivateSlot() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        slot.deactivate();

        assertFalse(slot.isActive());
    }

    @Test
    void testActivateSlot() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        slot.deactivate();
        slot.activate();

        assertTrue(slot.isActive());
    }

    // ---------- Availability Tests ----------

    @Test
    void testSlotIsAvailableWhenNoBookings() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        assertTrue(slot.isAvailable(start, end));
    }

    @Test
    void testSlotIsAvailableForNonOverlappingBooking() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        LocalDateTime start =
                LocalDateTime.of(2026, 9, 28, 10, 0);

        LocalDateTime end =
                LocalDateTime.of(2026, 9, 28, 12, 0);

        Vehicle vehicle = new Vehicle(
                1,
                VehicleType.MOTORCYCLE,
                new Wallet(100.0)
        );

        Booking booking = new Booking(
                1,
                vehicle,
                slot,
                start,
                end,
                50.0
        );

        slot.getBookings().add(booking);

        // Existing booking: 10:00 - 12:00
        // New booking: 12:00 - 14:00
        // They don't overlap.
        LocalDateTime newStart =
                LocalDateTime.of(2026, 9, 28, 12, 0);

        LocalDateTime newEnd =
                LocalDateTime.of(2026, 9, 28, 14, 0);

        assertTrue(slot.isAvailable(newStart, newEnd));
    }

    @Test
    void testSlotIsUnavailableForOverlappingBooking() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        Wallet wallet = new Wallet(100.0);

        Vehicle vehicle = new Vehicle(
                1,
                VehicleType.MOTORCYCLE,
                wallet
        );

        Booking booking = new Booking(
                1,          // bookingId
                vehicle,    // vehicle
                slot,       // parkingSlot
                start,      // startTime
                end,        // endTime
                50.0        // amount
        );

        slot.getBookings().add(booking);

        LocalDateTime newStart =
                LocalDateTime.of(2026, 9, 28, 11, 0);

        LocalDateTime newEnd =
                LocalDateTime.of(2026, 9, 28, 13, 0);

        assertFalse(slot.isAvailable(newStart, newEnd));
    }

    @Test
    void testSlotIsUnavailableWhenNewBookingStartsDuringExistingBooking() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);
        Wallet wallet = new Wallet(100.0);
        Vehicle vehicle = new Vehicle(
                1,
                VehicleType.MOTORCYCLE,
                wallet
        );
        slot.getBookings().add(new Booking(
                1,
                vehicle,
                slot,
                start,
                end,
                50.0
        ));

        LocalDateTime newStart = LocalDateTime.of(2026, 9, 28, 11, 0);
        LocalDateTime newEnd = LocalDateTime.of(2026, 9, 28, 11, 30);

        assertFalse(slot.isAvailable(newStart, newEnd));
    }

    @Test
    void testSlotIsUnavailableWhenNewBookingEndsDuringExistingBooking() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        Wallet wallet = new Wallet(100.0);
        Vehicle vehicle = new Vehicle(
                1,
                VehicleType.MOTORCYCLE,
                wallet
        );
        slot.getBookings().add(new Booking(
                1,
                vehicle,
                slot,
                start,
                end,
                50.0
        ));

        LocalDateTime newStart = LocalDateTime.of(2026, 9, 28, 11, 0);
        LocalDateTime newEnd = LocalDateTime.of(2026, 9, 28, 13, 0);

        assertFalse(slot.isAvailable(newStart, newEnd));
    }

    // ---------- Inactive Slot Tests ----------

    @Test
    void testInactiveSlotIsNotCompatible() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        slot.deactivate();

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        assertFalse(
                slot.isCompatible(
                        VehicleType.MOTORCYCLE,
                        start,
                        end
                )
        );
    }

    // ---------- Motorcycle Compatibility ----------

    @Test
    void testMotorcycleCompatibleWithCompactSlot() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        assertTrue(slot.isCompatible(
                VehicleType.MOTORCYCLE,
                start,
                end
        ));
    }

    @Test
    void testMotorcycleCompatibleWithRegularSlot() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.REGULAR);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        assertTrue(slot.isCompatible(
                VehicleType.MOTORCYCLE,
                start,
                end
        ));
    }

    @Test
    void testMotorcycleNotCompatibleWithHandicappedSlot() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.HANDICAPPED);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        assertFalse(slot.isCompatible(
                VehicleType.MOTORCYCLE,
                start,
                end
        ));
    }

    // ---------- Car Compatibility ----------

    @Test
    void testCarCompatibleWithRegularSlot() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.REGULAR);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        assertTrue(slot.isCompatible(
                VehicleType.CAR,
                start,
                end
        ));
    }

    @Test
    void testCarCompatibleWithLargeSlot() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.LARGE);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        assertTrue(slot.isCompatible(
                VehicleType.CAR,
                start,
                end
        ));
    }

    @Test
    void testCarNotCompatibleWithCompactSlot() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        assertFalse(slot.isCompatible(
                VehicleType.CAR,
                start,
                end
        ));
    }

    // ---------- Bus Compatibility ----------

    @Test
    void testBusCompatibleWithLargeSlot() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.LARGE);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        assertTrue(slot.isCompatible(
                VehicleType.BUS,
                start,
                end
        ));
    }

    @Test
    void testBusNotCompatibleWithRegularSlot() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.REGULAR);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        assertFalse(slot.isCompatible(
                VehicleType.BUS,
                start,
                end
        ));
    }

    // ---------- Bicycle Compatibility ----------

    @Test
    void testBicycleCompatibleWithHandicappedSlot() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.HANDICAPPED);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        assertTrue(slot.isCompatible(
                VehicleType.BICYCLE,
                start,
                end
        ));
    }

    // ---------- Microcar Compatibility ----------

    @Test
    void testMicrocarCompatibleWithCompactSlot() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        assertTrue(slot.isCompatible(
                VehicleType.MICROCAR,
                start,
                end
        ));
    }

    @Test
    void testMicrocarNotCompatibleWithLargeSlot() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.LARGE);

        LocalDateTime start = LocalDateTime.of(2026, 9, 28, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 9, 28, 12, 0);

        assertFalse(slot.isCompatible(
                VehicleType.MICROCAR,
                start,
                end
        ));
    }

    // ---------- Wallet / Balance ----------

    @Test
    void testInitialBalanceIsZero() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        assertEquals(0.0, slot.getBalance());
    }

    @Test
    void testGetWallet() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        assertNotNull(slot.getWallet());
        assertEquals(0.0, slot.getWallet().getBalance());
    }

    // ---------- Get Bookings ----------

    @Test
    void testGetBookings() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        assertNotNull(slot.getBookings());
        assertEquals(0, slot.getBookings().size());
    }
}