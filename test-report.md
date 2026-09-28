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
}




---

## B) Defects List

| Defect ID |          Class.Method Where Found                    |                   Description of Defect                       | 
|-----------|---------------------------- -------------------------|--------------------------------------------------------       |
| V21       |  void setUp(){vehicle = new Vehicle("ABC123");       |In Vehicle.java this.someField = Integer.parseInt(plateNumber);|                
| DEF-02    |                                                     |                
| DEF-03    |                                                    |                
