# Software Testing Report

## 0) Member

* **Student ID:** 0112310034
* **Name:** Alif Hasan Tasin

---

## A) Test Case List

| Test Case |--- |BookingTest|

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

| Test Case |--- |ParkingSlotTest|





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
                1,         
                vehicle,    
                slot,       
                start,      
                end,        
                50.0        
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


    @Test
    void testGetBookings() {
        ParkingSlot slot = new ParkingSlot("A1", ParkingSlotType.COMPACT);

        assertNotNull(slot.getBookings());
        assertEquals(0, slot.getBookings().size());
    }

|Test Case | -----|ParkingSystemTest|




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




|Test Case |--------|VehicleTest|







    @Test
    void constructor_withWallet_setsAllFieldsCorrectly() {
        Wallet wallet = new Wallet(500.0);

        Vehicle vehicle = new Vehicle(
                101,
                VehicleType.CAR,
                wallet
        );

        assertEquals(101, vehicle.getVehicleId());
        assertEquals(VehicleType.CAR, vehicle.getVehicleType());
        assertSame(wallet, vehicle.getWallet());
        assertEquals(500.0, vehicle.getBalance());
    }

    @Test
    void constructor_withInitialBalance_createsWallet() {
        Vehicle vehicle = new Vehicle(
                102,
                VehicleType.BUS,
                1000.0
        );

        assertEquals(102, vehicle.getVehicleId());
        assertEquals(VehicleType.BUS, vehicle.getVehicleType());
        assertNotNull(vehicle.getWallet());
        assertEquals(1000.0, vehicle.getBalance());
    }

    @Test
    void getVehicleId_returnsCorrectId() {
        Vehicle vehicle = new Vehicle(123, VehicleType.TRUCK, 200.0);

        assertEquals(123, vehicle.getVehicleId());
    }

    @Test
    void getVehicleType_returnsCorrectType() {
        Vehicle vehicle = new Vehicle(123, VehicleType.MOTORCYCLE, 200.0);

        assertEquals(VehicleType.MOTORCYCLE, vehicle.getVehicleType());
    }

    @Test
    void getWallet_returnsCorrectWallet() {
        Wallet wallet = new Wallet(300.0);
        Vehicle vehicle = new Vehicle(123, VehicleType.BICYCLE, wallet);

        assertSame(wallet, vehicle.getWallet());
    }

    @Test
    void getBalance_returnsWalletBalance() {
        Vehicle vehicle = new Vehicle(123, VehicleType.MICROCAR, 750.0);

        assertEquals(750.0, vehicle.getBalance());
    }

    @Test
    void toString_containsVehicleInformation() {
        Vehicle vehicle = new Vehicle(123, VehicleType.CAR, 500.0);

        String result = vehicle.toString();

        assertTrue(result.contains("123"));
        assertTrue(result.contains("CAR"));
        assertTrue(result.contains("500.0"));
    }




















---

## B) Defects List

| Defect ID |          Class.Method Where Found                    |                   Description of Defect                       | 
|-----------|---------------------------- -------------------------|--------------------------------------------------------       |
| V21       |  void setUp(){vehicle = new Vehicle("ABC123");       |In Vehicle.java this.someField = Integer.parseInt(plateNumber);|                
| DEF-02    |                                                     |                
| DEF-03    |                                                    |                
