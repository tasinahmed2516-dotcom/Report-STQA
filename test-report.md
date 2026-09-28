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



---

## B) Defects List

| Defect ID |          Class.Method Where Found                    |                   Description of Defect                       | 
|-----------|---------------------------- -------------------------|--------------------------------------------------------       |
| V21       |  void setUp(){vehicle = new Vehicle("ABC123");       |In Vehicle.java this.someField = Integer.parseInt(plateNumber);|                
| DEF-02    |                                                     |                
| DEF-03    |                                                    |                
