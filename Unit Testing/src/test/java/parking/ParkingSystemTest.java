package parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSystemTest {

    private ParkingSystem system;

    private LocalDateTime start;
    private LocalDateTime end;

    @BeforeEach
    void setUp() {
        system = ParkingSystem.getInstance();
        system.resetForTesting();

        start = LocalDateTime.of(2026, 9, 28, 10, 0);
        end = LocalDateTime.of(2026, 9, 28, 12, 0);
    }



    @Test
    void testSingletonReturnsSameInstance() {
        ParkingSystem system1 = ParkingSystem.getInstance();
        ParkingSystem system2 = ParkingSystem.getInstance();

        assertSame(system1, system2);
    }



    @Test
    void testInitialVehiclesListIsEmpty() {
        assertTrue(system.getVehicles().isEmpty());
    }

    @Test
    void testInitialParkingSlotsListIsEmpty() {
        assertTrue(system.getParkingSlots().isEmpty());
    }

    @Test
    void testInitialBookingsListIsEmpty() {
        assertTrue(system.getBookings().isEmpty());
    }

    @Test
    void testInitialParkingRate() {
        assertEquals(10.0, system.getPARKING_RATE_PER_HOUR());
    }

    @Test
    void testInitialSystemBalance() {
        assertEquals(0.0, system.getBalance());
    }



    @Test
    void testAddVehicle() {
        Vehicle vehicle = new Vehicle(123, VehicleType.CAR,20.00);

        system.addVehicle(vehicle);

        assertEquals(1, system.getVehicles().size());
        assertTrue(system.getVehicles().contains(vehicle));
    }

    @Test
    void testAddMultipleVehicles() {
        Vehicle vehicle1 = new Vehicle(123, VehicleType.CAR,20.0);
        Vehicle vehicle2 = new Vehicle(124, VehicleType.BUS,30.0);

        system.addVehicle(vehicle1);
        system.addVehicle(vehicle2);

        assertEquals(2, system.getVehicles().size());
    }



    @Test
    void testAddParkingSlot() {
        ParkingSlot slot =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        system.addParkingSlot(slot);

        assertEquals(1, system.getParkingSlots().size());
        assertTrue(system.getParkingSlots().contains(slot));
    }

    @Test
    void testAddMultipleParkingSlots() {
        ParkingSlot slot1 =
                new ParkingSlot("C01", ParkingSlotType.COMPACT);

        ParkingSlot slot2 =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        system.addParkingSlot(slot1);
        system.addParkingSlot(slot2);

        assertEquals(2, system.getParkingSlots().size());
    }



    @Test
    void testGetAvailableParkingSlotsForCar() {
        Vehicle car = new Vehicle(123, VehicleType.CAR,20.0);

        ParkingSlot compact =
                new ParkingSlot("C01", ParkingSlotType.COMPACT);

        ParkingSlot regular =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        ParkingSlot large =
                new ParkingSlot("L01", ParkingSlotType.LARGE);

        system.addParkingSlot(compact);
        system.addParkingSlot(regular);
        system.addParkingSlot(large);

        List<ParkingSlot> result =
                system.getAvailableParkingSlots(car, start, end);

        assertEquals(2, result.size());
        assertTrue(result.contains(regular));
        assertTrue(result.contains(large));
        assertFalse(result.contains(compact));
    }

    @Test
    void testGetAvailableParkingSlotsForBus() {
        Vehicle bus = new Vehicle(123, VehicleType.BUS,20.0);

        ParkingSlot compact =
                new ParkingSlot("C01", ParkingSlotType.COMPACT);

        ParkingSlot regular =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        ParkingSlot large =
                new ParkingSlot("L01", ParkingSlotType.LARGE);

        system.addParkingSlot(compact);
        system.addParkingSlot(regular);
        system.addParkingSlot(large);

        List<ParkingSlot> result =
                system.getAvailableParkingSlots(bus, start, end);

        assertEquals(1, result.size());
        assertTrue(result.contains(large));
    }

    @Test
    void testGetAvailableParkingSlotsForBicycle() {
        Vehicle bicycle = new Vehicle(123, VehicleType.BICYCLE,20.0);

        ParkingSlot compact =
                new ParkingSlot("C01", ParkingSlotType.COMPACT);

        ParkingSlot regular =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        ParkingSlot large =
                new ParkingSlot("L01", ParkingSlotType.LARGE);

        ParkingSlot handicapped =
                new ParkingSlot("H01", ParkingSlotType.HANDICAPPED);

        system.addParkingSlot(compact);
        system.addParkingSlot(regular);
        system.addParkingSlot(large);
        system.addParkingSlot(handicapped);

        List<ParkingSlot> result =
                system.getAvailableParkingSlots(bicycle, start, end);

        assertEquals(4, result.size());
    }

    @Test
    void testGetAvailableParkingSlotsExcludesInactiveSlot() {
        Vehicle car = new Vehicle(123, VehicleType.CAR,20.0);

        ParkingSlot slot =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        slot.deactivate();

        system.addParkingSlot(slot);

        List<ParkingSlot> result =
                system.getAvailableParkingSlots(car, start, end);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAvailableParkingSlotsWhenNoSlotsExist() {
        Vehicle car = new Vehicle(123, VehicleType.CAR,20.0);

        List<ParkingSlot> result =
                system.getAvailableParkingSlots(car, start, end);

        assertTrue(result.isEmpty());
    }



    @Test
    void testBookingWithEndTimeBeforeStartTime() {
        Vehicle car = new Vehicle(123, VehicleType.CAR,20.0);

        ParkingSlot slot =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        LocalDateTime invalidEnd =
                LocalDateTime.of(2026, 9, 28, 9, 0);

        assertThrows(
                IllegalBookingTimeException.class,
                () -> system.book(
                        car,
                        slot,
                        start,
                        invalidEnd
                )
        );
    }

    @Test
    void testBookingWithEqualStartAndEndTime() {
        Vehicle car = new Vehicle(123, VehicleType.CAR,20.0);

        ParkingSlot slot =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        assertThrows(
                IllegalBookingTimeException.class,
                () -> system.book(
                        car,
                        slot,
                        start,
                        start
                )
        );
    }



    @Test
    void testBookingIncompatibleSlot() {
        Vehicle car = new Vehicle(123, VehicleType.CAR,20.0);

        ParkingSlot compact =
                new ParkingSlot("C01", ParkingSlotType.COMPACT);

        assertThrows(
                IllegalArgumentException.class,
                () -> system.book(
                        car,
                        compact,
                        start,
                        end
                )
        );
    }

    @Test
    void testBookingInactiveSlot() {
        Vehicle car = new Vehicle(123, VehicleType.CAR,20.0);

        ParkingSlot slot =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        slot.deactivate();

        assertThrows(
                IllegalArgumentException.class,
                () -> system.book(
                        car,
                        slot,
                        start,
                        end
                )
        );
    }



    @Test
    void testSuccessfulBooking() {
        Vehicle car = new Vehicle(122, VehicleType.CAR,20.0);

        ParkingSlot slot =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        Booking booking =
                system.book(car, slot, start, end);

        assertNotNull(booking);
        assertEquals(1, system.getBookings().size());
        assertTrue(system.getBookings().contains(booking));
        assertTrue(slot.getBookings().contains(booking));
    }

    @Test
    void testBookingHasCorrectVehicle() {
        Vehicle car = new Vehicle(126, VehicleType.CAR,20.0);

        ParkingSlot slot =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        Booking booking =
                system.book(car, slot, start, end);

        assertEquals(car, booking.getVehicle());
    }

    @Test
    void testBookingHasCorrectParkingSlot() {
        Vehicle car = new Vehicle(121, VehicleType.CAR,20.0);

        ParkingSlot slot =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        Booking booking =
                system.book(car, slot, start, end);

        assertEquals(slot, booking.getParkingSlot());
    }



    @Test
    void testCarRegularTwoHourBookingAmount() {
        Vehicle car = new Vehicle(111, VehicleType.CAR,20.0);

        ParkingSlot slot =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        Booking booking =
                system.book(car, slot, start, end);


        assertEquals(20.0, booking.getAmount());
    }

    @Test
    void testMotorcycleRegularBookingAmount() {
        Vehicle motorcycle =
                new Vehicle(110, VehicleType.MOTORCYCLE,20.0);

        ParkingSlot slot =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        Booking booking =
                system.book(
                        motorcycle,
                        slot,
                        start,
                        end
                );


        assertEquals(10.0, booking.getAmount());
    }

    @Test
    void testBicycleCompactBookingAmount() {
        Vehicle bicycle =
                new Vehicle(100, VehicleType.BICYCLE,20.0);

        ParkingSlot slot =
                new ParkingSlot("C01", ParkingSlotType.COMPACT);

        Booking booking =
                system.book(
                        bicycle,
                        slot,
                        start,
                        end
                );


        assertEquals(3.2, booking.getAmount(), 0.001);
    }

    @Test
    void testBusLargeBookingAmount() {
        Vehicle bus =
                new Vehicle(133, VehicleType.BUS, 100.0);

        ParkingSlot slot =
                new ParkingSlot("L01", ParkingSlotType.LARGE);

        Booking booking =
                system.book(
                        bus,
                        slot,
                        start,
                        end
                );


        assertEquals(60.0, booking.getAmount(), 0.001);
    }





    @Test
    void testSetVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        Vehicle car = new Vehicle(111, VehicleType.CAR,20.0);
        vehicles.add(car);

        system.setVehicles(vehicles);

        assertSame(vehicles, system.getVehicles());
        assertEquals(1, system.getVehicles().size());
    }

    @Test
    void testSetParkingSlots() {
        List<ParkingSlot> slots = new ArrayList<>();

        ParkingSlot slot =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        slots.add(slot);

        system.setParkingSlots(slots);

        assertSame(slots, system.getParkingSlots());
    }

    @Test
    void testSetBookings() {
        List<Booking> bookings = new ArrayList<>();

        system.setBookings(bookings);

        assertSame(bookings, system.getBookings());
    }

    @Test
    void testSetParkingRate() {
        system.setPARKING_RATE_PER_HOUR(20.0);

        assertEquals(20.0, system.getPARKING_RATE_PER_HOUR());
    }



    @Test
    void testSystemWalletExists() {
        assertNotNull(system.getSYSTEM_WALLET());
    }

    @Test
    void testSetSystemWallet() {
        Wallet wallet = new Wallet();

        system.setSYSTEM_WALLET(wallet);

        assertSame(wallet, system.getSYSTEM_WALLET());
    }



    @Test
    void testResetForTesting() {
        Vehicle car = new Vehicle(111, VehicleType.CAR,20.0);

        ParkingSlot slot =
                new ParkingSlot("R01", ParkingSlotType.REGULAR);

        system.addVehicle(car);
        system.addParkingSlot(slot);

        system.resetForTesting();

        assertTrue(system.getVehicles().isEmpty());
        assertTrue(system.getParkingSlots().isEmpty());
        assertTrue(system.getBookings().isEmpty());
        assertEquals(10.0, system.getPARKING_RATE_PER_HOUR());
        assertEquals(0.0, system.getBalance());
    }

}