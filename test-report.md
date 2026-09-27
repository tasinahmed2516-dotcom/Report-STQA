# Software Testing Report

## 0) Member

* **Student ID:** 0112310034
* **Name:** Alif Hasan Tasin

---

## A) Test Case List

| Test Case |--- |BookingTest|


 @BeforeEach
    void setUp() {
        vehicle = new Vehicle("ABC123"); // adjust to your actual constructor
        parkingSlot = new ParkingSlot(1);        // adjust to your actual constructor
        startTime = LocalDateTime.of(2026, 9, 27, 9, 0);
        endTime = LocalDateTime.of(2026, 9, 27, 11, 0);
        booking = new Booking(101, vehicle, parkingSlot, startTime, endTime, 50.0);
    }

    @Test
    void constructor_setsAllFieldsCorrectly() {
        assertEquals(101, booking.getBookingId());
        assertEquals(vehicle, booking.getVehicle());
        assertEquals(parkingSlot, booking.getParkingSlot());
        assertEquals(startTime, booking.getStartTime());
        assertEquals(endTime, booking.getEndTime());
        assertEquals(50.0, booking.getAmount());
    }

    @Test
    void newBooking_hasActiveStatusByDefault() {
        assertEquals(BookingStatus.ACTIVE, booking.getBookingStatus());
    }

    @Test
    void completeBooking_setsStatusToCompleted() {
        booking.completeBooking();
        assertEquals(BookingStatus.COMPLETED, booking.getBookingStatus());
    }

    @Test
    void cancelBooking_setsStatusToCancelled() {
        booking.cancelBooking();
        assertEquals(BookingStatus.CANCELLED, booking.getBookingStatus());
    }

    @Test
    void completeBooking_afterCancel_overridesStatus() {
        booking.cancelBooking();
        booking.completeBooking();
        assertEquals(BookingStatus.COMPLETED, booking.getBookingStatus());
    }

    @Test
    void cancelBooking_afterComplete_overridesStatus() {
        booking.completeBooking();
        booking.cancelBooking();
        assertEquals(BookingStatus.CANCELLED, booking.getBookingStatus());
    }

    @Test
    void endTime_isAfterStartTime() {
        assertTrue(booking.getEndTime().isAfter(booking.getStartTime()));
    }

    @Test
    void amount_isNonNegative() {
        assertTrue(booking.getAmount() >= 0);
    }

    @Test
    void toString_containsKeyFields() {
        String result = booking.toString();
        assertTrue(result.contains("bookingId=101"));
        assertTrue(result.contains("amount=50.0"));
        assertTrue(result.contains("ACTIVE"));
    }

    @Test
    void differentBookingIds_areNotEqualByDefaultEquals() {
        Booking other = new Booking(102, vehicle, parkingSlot, startTime, endTime, 50.0);
        // No equals() override exists in Booking, so this checks reference inequality
        assertNotEquals(booking, other);
    }

| Test Case |--- |ParkingSlotTest|
@BeforeEach
    void setUp() {
        slot = new ParkingSlot("A1", ParkingSlotType.REGULAR);
        startTime = LocalDateTime.of(2025, 1, 1, 9, 0);
        endTime = LocalDateTime.of(2025, 1, 1, 11, 0);
    }



    @Test
    void constructorSetsFieldsAndDefaultsToActive() {
        assertEquals("A1", slot.getSlotId());
        assertEquals(ParkingSlotType.REGULAR, slot.getSlotType());
        assertTrue(slot.isActive());
    }

    @Test
    void newSlotHasEmptyBookingsAndZeroBalance() {
        assertTrue(slot.getBookings().isEmpty());
        assertEquals(0.0, slot.getBalance());
    }



    @Test
    void intConstructorLeavesSlotTypeNull() {
        ParkingSlot brokenSlot = new ParkingSlot(1);
        assertNull(brokenSlot.getSlotType());
    }

    @Test
    void intConstructorLeavesSlotInactiveByDefault() {
        ParkingSlot brokenSlot = new ParkingSlot(1);

        assertFalse(brokenSlot.isActive());
    }

    @Test
    void intConstructorCausesNpeOnGetBalance() {
        ParkingSlot brokenSlot = new ParkingSlot(1);

        assertThrows(NullPointerException.class, brokenSlot::getBalance);
    }

    @Test
    void intConstructorCausesNpeOnIsAvailable() {
        ParkingSlot brokenSlot = new ParkingSlot(1);

        assertThrows(NullPointerException.class,
                () -> brokenSlot.isAvailable(startTime, endTime));
    }



    @Test
    void deactivateSetsActiveFalse() {
        slot.deactivate();
        assertFalse(slot.isActive());
    }

    @Test
    void activateSetsActiveTrue() {
        slot.deactivate();
        slot.activate();
        assertTrue(slot.isActive());
    }



    @Test
    void inactiveSlotIsNeverCompatible() {
        slot.deactivate();
        assertFalse(slot.isCompatible(VehicleType.CAR, startTime, endTime));
    }



    @Test
    void regularSlotCompatibleWithCar() {
        assertTrue(slot.isCompatible(VehicleType.CAR, startTime, endTime));
    }

    @Test
    void regularSlotCompatibleWithMotorcycle() {
        assertTrue(slot.isCompatible(VehicleType.MOTORCYCLE, startTime, endTime));
    }

    @Test
    void regularSlotCompatibleWithBicycle() {
        assertTrue(slot.isCompatible(VehicleType.BICYCLE, startTime, endTime));
    }

    @Test
    void regularSlotCompatibleWithMicrocar() {
        assertTrue(slot.isCompatible(VehicleType.MICROCAR, startTime, endTime));
    }

    @Test
    void regularSlotNotCompatibleWithBus() {
        assertFalse(slot.isCompatible(VehicleType.BUS, startTime, endTime));
    }



    @Test
    void largeSlotCompatibleWithBus() {
        ParkingSlot largeSlot = new ParkingSlot("L1", ParkingSlotType.LARGE);
        assertTrue(largeSlot.isCompatible(VehicleType.BUS, startTime, endTime));
    }

    @Test
    void largeSlotNotCompatibleWithMicrocar() {
        ParkingSlot largeSlot = new ParkingSlot("L1", ParkingSlotType.LARGE);
        assertFalse(largeSlot.isCompatible(VehicleType.MICROCAR, startTime, endTime));
    }



    @Test
    void compactSlotNotCompatibleWithCar() {
        ParkingSlot compactSlot = new ParkingSlot("C1", ParkingSlotType.COMPACT);
        assertFalse(compactSlot.isCompatible(VehicleType.CAR, startTime, endTime));
    }

    @Test
    void compactSlotCompatibleWithMotorcycle() {
        ParkingSlot compactSlot = new ParkingSlot("C1", ParkingSlotType.COMPACT);
        assertTrue(compactSlot.isCompatible(VehicleType.MOTORCYCLE, startTime, endTime));
    }



    @Test
    void handicappedSlotCompatibleWithBicycleOnly() {
        ParkingSlot hSlot = new ParkingSlot("H1", ParkingSlotType.HANDICAPPED);
        assertTrue(hSlot.isCompatible(VehicleType.BICYCLE, startTime, endTime));
        assertFalse(hSlot.isCompatible(VehicleType.CAR, startTime, endTime));
        assertFalse(hSlot.isCompatible(VehicleType.MOTORCYCLE, startTime, endTime));
    }



    @Test
    void slotAvailableWhenNoBookings() {
        assertTrue(slot.isAvailable(startTime, endTime));
    }

    @Test
    void slotUnavailableWhenTimesOverlapActiveBooking() {
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 100.0);
        Booking booking = new Booking(1, vehicle, slot, startTime, endTime, 20.0);
        slot.getBookings().add(booking);

        LocalDateTime overlapStart = startTime.plusMinutes(30);
        LocalDateTime overlapEnd = endTime.plusMinutes(30);

        assertFalse(slot.isAvailable(overlapStart, overlapEnd));
    }

    @Test
    void slotAvailableWhenRequestedTimesDoNotOverlap() {
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 100.0);
        Booking booking = new Booking(1, vehicle, slot, startTime, endTime, 20.0);
        slot.getBookings().add(booking);

        LocalDateTime laterStart = endTime.plusHours(1);
        LocalDateTime laterEnd = endTime.plusHours(2);

        assertTrue(slot.isAvailable(laterStart, laterEnd));
    }



    @Test
    void cancelledBookingShouldNotBlockAvailability() {
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 100.0);
        Booking booking = new Booking(1, vehicle, slot, startTime, endTime, 20.0);
        booking.cancelBooking();
        slot.getBookings().add(booking);
        assertTrue(slot.isAvailable(startTime, endTime));
    }












---

## B) Defects List

| Defect ID | Class.Method Where Found | Description of Defect | Suggested Fix |
|-----------|----------------------------|-------------------------|----------------|
| DEF-01    |                            |                          |                |
| DEF-02    |                            |                          |                |
| DEF-03    |                            |                          |                |
