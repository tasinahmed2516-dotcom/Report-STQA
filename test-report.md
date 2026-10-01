# Software Testing Report

## 0) Member

* **Student ID:** 0112310034
* **Name:** Alif Hasan Tasin

---

## A) Test Case List




# Test Case Table

| TC ID | Test Class | Test Case | Expected Result |
|---|---|---|---|
| TC-001 | BookingTest | `constructorSetsAllFields()` | All booking fields are initialized with the supplied values. |
| TC-002 | BookingTest | `newBookingStartsAsActive()` | A new booking has `ACTIVE` status. |
| TC-003 | BookingTest | `completeBookingSetsStatusToCompleted()` | Completing a booking changes status to `COMPLETED`. |
| TC-004 | BookingTest | `completeBookingDoesNotChangeOtherFields()` | Completing a booking does not change booking ID, vehicle, or amount. |
| TC-005 | BookingTest | `cancelBookingSetsStatusToCancelled()` | Cancelling a booking changes status to `CANCELLED`. |
| TC-006 | BookingTest | `cancelAfterCompleteOverridesStatus()` | Cancelling a completed booking changes status to `CANCELLED`. |
| TC-007 | BookingTest | `completeAfterCancelOverridesStatus()` | Completing a cancelled booking changes status to `COMPLETED`. |
| TC-008 | BookingTest | `toStringContainsBookingId()` | `toString()` contains the booking ID. |
| TC-009 | ParkingSlotTest | `testConstructor()` | Slot fields are initialized correctly; slot is active, balance is zero, wallet exists, and bookings are empty. |
| TC-010 | ParkingSlotTest | `testDeactivateSlot()` | Deactivating a slot makes it inactive. |
| TC-011 | ParkingSlotTest | `testActivateSlot()` | An inactive slot can be activated. |
| TC-012 | ParkingSlotTest | `testSlotIsAvailableWhenNoBookings()` | A slot with no bookings is available. |
| TC-013 | ParkingSlotTest | `testSlotIsAvailableForNonOverlappingBooking()` | A new booking that starts when an existing booking ends is allowed. |
| TC-014 | ParkingSlotTest | `testSlotIsUnavailableForOverlappingBooking()` | An overlapping booking is rejected as unavailable. |
| TC-015 | ParkingSlotTest | `testSlotIsUnavailableWhenNewBookingStartsDuringExistingBooking()` | A booking starting during an existing booking is unavailable. |
| TC-016 | ParkingSlotTest | `testSlotIsUnavailableWhenNewBookingEndsDuringExistingBooking()` | A booking ending during an existing booking is unavailable. |
| TC-017 | ParkingSlotTest | `testInactiveSlotIsNotCompatible()` | An inactive slot is not compatible with a vehicle. |
| TC-018 | ParkingSlotTest | `testMotorcycleCompatibleWithCompactSlot()` | Motorcycle is compatible with a compact slot. |
| TC-019 | ParkingSlotTest | `testMotorcycleCompatibleWithRegularSlot()` | Motorcycle is compatible with a regular slot. |
| TC-020 | ParkingSlotTest | `testMotorcycleNotCompatibleWithHandicappedSlot()` | Motorcycle is not compatible with a handicapped slot. |
| TC-021 | ParkingSlotTest | `testCarCompatibleWithRegularSlot()` | Car is compatible with a regular slot. |
| TC-022 | ParkingSlotTest | `testCarCompatibleWithLargeSlot()` | Car is compatible with a large slot. |
| TC-023 | ParkingSlotTest | `testCarNotCompatibleWithCompactSlot()` | Car is not compatible with a compact slot. |
| TC-024 | ParkingSlotTest | `testBusCompatibleWithLargeSlot()` | Bus is compatible with a large slot. |
| TC-025 | ParkingSlotTest | `testBusNotCompatibleWithRegularSlot()` | Bus is not compatible with a regular slot. |
| TC-026 | ParkingSlotTest | `testBicycleCompatibleWithHandicappedSlot()` | Bicycle is compatible with a handicapped slot. |
| TC-027 | ParkingSlotTest | `testMicrocarCompatibleWithCompactSlot()` | Microcar is compatible with a compact slot. |
| TC-028 | ParkingSlotTest | `testMicrocarNotCompatibleWithLargeSlot()` | Microcar is not compatible with a large slot. |
| TC-029 | ParkingSlotTest | `testInitialBalanceIsZero()` | A new parking slot has zero balance. |
| TC-030 | ParkingSlotTest | `testGetWallet()` | Slot wallet exists and has zero initial balance. |
| TC-031 | ParkingSlotTest | `testGetBookings()` | Slot bookings collection exists and is initially empty. |
| TC-032 | ParkingSystemTest | `testSingletonReturnsSameInstance()` | `getInstance()` returns the same `ParkingSystem` instance. |
| TC-033 | ParkingSystemTest | `testInitialVehiclesListIsEmpty()` | Vehicle list is initially empty. |
| TC-034 | ParkingSystemTest | `testInitialParkingSlotsListIsEmpty()` | Parking-slot list is initially empty. |
| TC-035 | ParkingSystemTest | `testInitialBookingsListIsEmpty()` | Booking list is initially empty. |
| TC-036 | ParkingSystemTest | `testInitialParkingRate()` | Initial parking rate is `10.0` per hour. |
| TC-037 | ParkingSystemTest | `testInitialSystemBalance()` | Initial system balance is `0.0`. |
| TC-038 | ParkingSystemTest | `testAddVehicle()` | A vehicle can be added and appears in the vehicle list. |
| TC-039 | ParkingSystemTest | `testAddMultipleVehicles()` | Multiple vehicles can be added successfully. |
| TC-040 | ParkingSystemTest | `testAddParkingSlot()` | A parking slot can be added and appears in the slot list. |
| TC-041 | ParkingSystemTest | `testAddMultipleParkingSlots()` | Multiple parking slots can be added successfully. |
| TC-042 | ParkingSystemTest | `testGetAvailableParkingSlotsForCar()` | For a car, compatible available regular and large slots are returned; compact is excluded. |
| TC-043 | ParkingSystemTest | `testGetAvailableParkingSlotsForBus()` | For a bus, only the compatible large slot is returned. |
| TC-044 | ParkingSystemTest | `testGetAvailableParkingSlotsForBicycle()` | For a bicycle, all four tested slot types are available. |
| TC-045 | ParkingSystemTest | `testGetAvailableParkingSlotsExcludesInactiveSlot()` | Inactive slots are excluded from available slots. |
| TC-046 | ParkingSystemTest | `testGetAvailableParkingSlotsWhenNoSlotsExist()` | An empty list is returned when no slots exist. |
| TC-047 | ParkingSystemTest | `testBookingWithEndTimeBeforeStartTime()` | Booking throws `IllegalBookingTimeException`. |
| TC-048 | ParkingSystemTest | `testBookingWithEqualStartAndEndTime()` | Booking throws `IllegalBookingTimeException`. |
| TC-049 | ParkingSystemTest | `testBookingIncompatibleSlot()` | Booking an incompatible slot throws `IllegalArgumentException`. |
| TC-050 | ParkingSystemTest | `testBookingInactiveSlot()` | Booking an inactive slot throws `IllegalArgumentException`. |
| TC-051 | ParkingSystemTest | `testSuccessfulBooking()` | A valid booking is created and added to system and slot booking lists. |
| TC-052 | ParkingSystemTest | `testBookingHasCorrectVehicle()` | Created booking contains the supplied vehicle. |
| TC-053 | ParkingSystemTest | `testBookingHasCorrectParkingSlot()` | Created booking contains the supplied parking slot. |
| TC-054 | ParkingSystemTest | `testCarRegularTwoHourBookingAmount()` | Two-hour car booking on a regular slot costs `20.0`. |
| TC-055 | ParkingSystemTest | `testMotorcycleRegularBookingAmount()` | Two-hour motorcycle booking on a regular slot costs `10.0`. |
| TC-056 | ParkingSystemTest | `testBicycleCompactBookingAmount()` | Two-hour bicycle booking on a compact slot costs `3.2`. |
| TC-057 | ParkingSystemTest | `testBusLargeBookingAmount()` | Two-hour bus booking on a large slot costs `60.0`. |
| TC-058 | ParkingSystemTest | `testSetVehicles()` | Vehicle list setter replaces the system vehicle list. |
| TC-059 | ParkingSystemTest | `testSetParkingSlots()` | Parking-slot list setter replaces the system slot list. |
| TC-060 | ParkingSystemTest | `testSetBookings()` | Booking list setter replaces the system booking list. |
| TC-061 | ParkingSystemTest | `testSetParkingRate()` | Parking rate can be changed to the supplied value. |
| TC-062 | ParkingSystemTest | `testSystemWalletExists()` | System wallet exists. |
| TC-063 | ParkingSystemTest | `testSetSystemWallet()` | System wallet can be replaced with the supplied wallet. |
| TC-064 | ParkingSystemTest | `testResetForTesting()` | Reset clears vehicles, slots, and bookings and restores rate and balance defaults. |
| TC-065 | VehicleTest | `constructor_withWallet_setsAllFieldsCorrectly()` | Vehicle ID, type, wallet, and balance are initialized correctly. |
| TC-066 | VehicleTest | `constructor_withInitialBalance_createsWallet()` | Vehicle creates a wallet with the supplied initial balance. |
| TC-067 | VehicleTest | `getVehicleId_returnsCorrectId()` | Vehicle ID getter returns the correct ID. |
| TC-068 | VehicleTest | `getVehicleType_returnsCorrectType()` | Vehicle type getter returns the correct type. |
| TC-069 | VehicleTest | `getWallet_returnsCorrectWallet()` | Wallet getter returns the same wallet instance. |
| TC-070 | VehicleTest | `getBalance_returnsWalletBalance()` | Vehicle balance matches its wallet balance. |
| TC-071 | VehicleTest | `toString_containsVehicleInformation()` | `toString()` contains vehicle ID, type, and balance. |
| TC-072 | WalletTest | `testDefaultConstructor()` | Default wallet balance is `0.0`. |
| TC-073 | WalletTest | `testInitialBalance()` | Wallet is initialized with the supplied balance. |
| TC-074 | WalletTest | `testAddFunds()` | Adding valid funds increases the wallet balance. |
| TC-075 | WalletTest | `testAddZeroFunds()` | Adding zero funds throws `InvalidAmountException` and balance remains unchanged. |
| TC-076 | WalletTest | `testAddNegativeFunds()` | Adding negative funds throws `InvalidAmountException` and balance remains unchanged. |
| TC-077 | WalletTest | `testDeductFunds()` | Deducting valid funds decreases the wallet balance. |
| TC-078 | WalletTest | `testDeductExactBalance()` | Deducting the exact balance results in zero balance. |
| TC-079 | WalletTest | `testDeductInsufficientFunds()` | Deducting more than the balance throws `InsufficientFundsException` and balance remains unchanged. |
| TC-080 | WalletTest | `testDeductZeroFunds()` | Deducting zero funds throws `InvalidAmountException`. |
| TC-081 | WalletTest | `testDeductNegativeFunds()` | Deducting negative funds throws `InvalidAmountException`. |
| TC-082 | WalletTest | `testTransferFunds()` | Valid transfer decreases source balance and increases destination balance. |
| TC-083 | WalletTest | `testTransferExactBalance()` | Transferring the exact source balance leaves source at zero and adds it to destination. |
| TC-084 | WalletTest | `testTransferInsufficientFunds()` | Insufficient transfer throws `InsufficientFundsException` and both balances remain unchanged. |
| TC-085 | WalletTest | `testTransferZeroFunds()` | Transferring zero funds throws `InvalidAmountException`. |
| TC-086 | WalletTest | `testTransferNegativeFunds()` | Transferring negative funds throws `InvalidAmountException`. |
























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






|Test case | --------|WalletTest|


    @Test
    void testDefaultConstructor() {
        Wallet wallet = new Wallet();

        assertEquals(0.0, wallet.getBalance());
    }


    @Test
    void testInitialBalance() {
        Wallet wallet = new Wallet(100.0);

        assertEquals(100.0, wallet.getBalance());
    }



    @Test
    void testAddFunds() {
        Wallet wallet = new Wallet(100.0);

        wallet.addFunds(50.0);

        assertEquals(150.0, wallet.getBalance());
    }


    @Test
    void testAddZeroFunds() {
        Wallet wallet = new Wallet(100.0);

        assertThrows(
                InvalidAmountException.class,
                () -> wallet.addFunds(0.0)
        );

        assertEquals(100.0, wallet.getBalance());
    }

    
    @Test
    void testAddNegativeFunds() {
        Wallet wallet = new Wallet(100.0);

        assertThrows(
                InvalidAmountException.class,
                () -> wallet.addFunds(-50.0)
        );

        assertEquals(100.0, wallet.getBalance());
    }

  
    @Test
    void testDeductFunds() {
        Wallet wallet = new Wallet(100.0);

        wallet.deductFunds(40.0);

        assertEquals(60.0, wallet.getBalance());
    }

    
    @Test
    void testDeductExactBalance() {
        Wallet wallet = new Wallet(100.0);

        wallet.deductFunds(100.0);

        assertEquals(0.0, wallet.getBalance());
    }

    
    @Test
    void testDeductInsufficientFunds() {
        Wallet wallet = new Wallet(100.0);

        assertThrows(
                InsufficientFundsException.class,
                () -> wallet.deductFunds(150.0)
        );

        assertEquals(100.0, wallet.getBalance());
    }

    
    @Test
    void testDeductZeroFunds() {
        Wallet wallet = new Wallet(100.0);

        assertThrows(
                InvalidAmountException.class,
                () -> wallet.deductFunds(0.0)
        );
    }

   
    @Test
    void testDeductNegativeFunds() {
        Wallet wallet = new Wallet(100.0);

        assertThrows(
                InvalidAmountException.class,
                () -> wallet.deductFunds(-20.0)
        );
    }

   
    @Test
    void testTransferFunds() {
        Wallet from = new Wallet(100.0);
        Wallet to = new Wallet(50.0);

        from.transferFunds(to, 30.0);

        assertEquals(70.0, from.getBalance());
        assertEquals(80.0, to.getBalance());
    }

    
    @Test
    void testTransferExactBalance() {
        Wallet from = new Wallet(100.0);
        Wallet to = new Wallet(50.0);

        from.transferFunds(to, 100.0);

        assertEquals(0.0, from.getBalance());
        assertEquals(150.0, to.getBalance());
    }

    
    @Test
    void testTransferInsufficientFunds() {
        Wallet from = new Wallet(100.0);
        Wallet to = new Wallet(50.0);

        assertThrows(
                InsufficientFundsException.class,
                () -> from.transferFunds(to, 150.0)
        );

        assertEquals(100.0, from.getBalance());
        assertEquals(50.0, to.getBalance());
    }

   
    @Test
    void testTransferZeroFunds() {
        Wallet from = new Wallet(100.0);
        Wallet to = new Wallet(50.0);

        assertThrows(
                InvalidAmountException.class,
                () -> from.transferFunds(to, 0.0)
        );
    }

    
    @Test
    void testTransferNegativeFunds() {
        Wallet from = new Wallet(100.0);
        Wallet to = new Wallet(50.0);

        assertThrows(
                InvalidAmountException.class,
                () -> from.transferFunds(to, -20.0)
        );
    }
















---

# B) Defects List

### Defect ID: Tc-001 

          
new Booking(1, vehicle, slot, start, end, -50.0);
new Booking(1, vehicle, slot, start, end, Double.NaN);

###A negative amount means the customer is paid to park. NaN and infinity break totals, comparisons and reports, because NaN is not equal to anything, including itself. 
            

### Defect ID: Tc-002

endTime = LocalDateTime.of(2025, 1, 1, 8, 0);   
new Booking(1, vehicle, parkingSlot, startTime, endTime, 50.0);  

###A parking session cannot end before it starts, and a zero-length session makes no sense. This produces a negative or zero duration, which leads to wrong billing and can cause overlapping or impossible slot schedules.


### Defect ID: Tc-004 


booking.completeBooking();



### Defect ID: Tc-005 

booking.cancelBooking();  

### Both methods set the status without checking the current one. A completed booking can be cancelled, and a cancelled booking can be completed. A booking can also be completed or cancelled twice.



### Defect ID: TC-076


   
  
    @Test
  
    void testInitialBalance() {
    
    Wallet wallet = new Wallet(-100.0);

    assertEquals(-100.0, wallet.getBalance());
    }

this is a real bug depends on your specification. If your requirements say initial balance must be non-negative, then this is definitely a bug.






### Defect ID: TC-078


    @Test
    void deductFunds_exactAmount_shouldSucceed_bug() {
    Wallet wallet = new Wallet();
    wallet.addFunds(0.1);
    wallet.addFunds(0.2);

    
    assertDoesNotThrow(() -> wallet.deductFunds(0.3));
    assertEquals(0.0, wallet.getBalance());
   }

Description: 
Here the deduction actually works fine (since 0.30000000000000004 >= 0.3 is true), but getBalance() afterward won't be exactly 0.0 — it'll be a tiny residual like 4.44E-17. That's the kind of bug that silently corrupts balances over many transactions without ever throwing an exception.




### Defect ID: TC-079

      @Test
      void deductFunds_floatingPointPrecision_bug() {
      Wallet wallet = new Wallet();
      wallet.addFunds(0.1);
      wallet.addFunds(0.2);

      System.out.println("Balance: " + wallet.getBalance());

       assertEquals(0.3, wallet.getBalance());
     } 

Description:
0.1 + 0.2 in double arithmetic equals 0.30000000000000004, not 0.3. Run this test with a plain assertEquals(0.3, wallet.getBalance()) (no delta tolerance) and it will fail — proving the bug.




                                               |                




# C) Mutant Analysis

## Overall Summary

| Metric | Result |
|---|---:|
| Number of Classes | 5 |
| Line Coverage | 95% |
| Line Coverage Details | 161/170 |
| Mutation Coverage | 81% |
| Mutation Coverage Details | 81/100 |
| Test Strength | 89% |
| Test Strength Details | 81/91 |

## Breakdown by Class

| Class Name | Line Coverage | Line Coverage Details | Mutation Coverage | Mutation Coverage Details | Test Strength | Test Strength Details |
|---|---:|---:|---:|---:|---:|---:|
| `Booking.java` | 100% | 21/21 | 100% | 8/8 | 100% | 8/8 |
| `ParkingSlot.java` | 100% | 37/37 | 84% | 31/37 | 84% | 31/37 |
| `ParkingSystem.java` | 88% | 63/72 | 66% | 23/35 | 88% | 23/26 |
| `Vehicle.java` | 100% | 16/16 | 100% | 5/5 | 100% | 5/5 |
| `Wallet.java` | 100% | 24/24 | 93% | 14/15 | 93% | 14/15 |

## Pit Test Coverage Report

![Pit Test Coverage Report](Capture.JPG)





# D) Contribution

My contribution was focused on unit testing. My faculty designed and i am implement test cases for the major classes of the parking management system, covering functional, exception, boundary, and negative scenarios. I also performed PIT mutation testing to evaluate test effectiveness and documented the test cases and coverage results.









