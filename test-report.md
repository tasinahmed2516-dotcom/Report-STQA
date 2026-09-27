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



| Test Case |--- |ParkingSystemTest|

 @BeforeEach
    void setUp() {
        system = ParkingSystem.getInstance();
        system.resetForTesting();
        startTime = LocalDateTime.of(2025, 1, 1, 9, 0);
        endTime = LocalDateTime.of(2025, 1, 1, 11, 0); // 2-hour booking
    }



    @Test
    void completingSameBookingTwiceThrowsInsufficientFundsOnSecondCall() {
        ParkingSlot slot = new ParkingSlot("R1", ParkingSlotType.REGULAR);
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 100.0);
        Booking booking = system.book(vehicle, slot, startTime, endTime); // amount = 20.0

        system.completeBooking(booking); // succeeds: SYSTEM_WALLET pays slot 16.0, has 4.0 left


        assertThrows(InsufficientFundsException.class,
                () -> system.completeBooking(booking));


        assertEquals(16.0, slot.getBalance());
        assertEquals(BookingStatus.COMPLETED, booking.getBookingStatus());
    }

    @Test
    void cancellingSameBookingTwiceThrowsInsufficientFundsOnSecondCall() {
        ParkingSlot slot = new ParkingSlot("R1", ParkingSlotType.REGULAR);
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 100.0);
        Booking booking = system.book(vehicle, slot, startTime, endTime); // vehicle balance = 80.0

        system.cancelBooking(booking); // succeeds: vehicle refunded 18.0 -> 98.0, SYSTEM_WALLET left with 2.0


        assertThrows(InsufficientFundsException.class,
                () -> system.cancelBooking(booking));


        assertEquals(98.0, vehicle.getBalance());
        assertEquals(BookingStatus.CANCELLED, booking.getBookingStatus());
    }

    @Test
    void completingAlreadyCancelledBookingThrowsInsufficientFunds() {
        ParkingSlot slot = new ParkingSlot("R1", ParkingSlotType.REGULAR);
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 100.0);
        Booking booking = system.book(vehicle, slot, startTime, endTime);

        system.cancelBooking(booking);


        assertThrows(InsufficientFundsException.class,
                () -> system.completeBooking(booking));


        assertEquals(BookingStatus.CANCELLED, booking.getBookingStatus());
    }

    @Test
    void cancellingAlreadyCompletedBookingThrowsInsufficientFunds() {
        ParkingSlot slot = new ParkingSlot("R1", ParkingSlotType.REGULAR);
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, 100.0);
        Booking booking = system.book(vehicle, slot, startTime, endTime); // amount = 20.0

        system.completeBooking(booking);
        assertThrows(InsufficientFundsException.class,
                () -> system.cancelBooking(booking));


        assertEquals(BookingStatus.COMPLETED, booking.getBookingStatus());
    }


|Testcase | |VehicleTest|


 @Test
    void constructorWithWalletSetsAllFields() {
        Wallet wallet = new Wallet(50.0);
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, wallet);

        assertEquals(1, vehicle.getVehicleId());
        assertEquals(VehicleType.CAR, vehicle.getVehicleType());
        assertEquals(wallet, vehicle.getWallet());
        assertEquals(50.0, vehicle.getBalance());
    }



    @Test
    void constructorWithInitialBalanceSetsAllFields() {
        Vehicle vehicle = new Vehicle(2, VehicleType.MOTORCYCLE, 100.0);

        assertEquals(2, vehicle.getVehicleId());
        assertEquals(VehicleType.MOTORCYCLE, vehicle.getVehicleType());
        assertNotNull(vehicle.getWallet());
        assertEquals(100.0, vehicle.getBalance());
    }

    @Test
    void constructorWithZeroInitialBalance() {
        Vehicle vehicle = new Vehicle(3, VehicleType.BICYCLE, 0.0);
        assertEquals(0.0, vehicle.getBalance());
    }



    @Test
    void toStringContainsVehicleIdAndType() {
        Vehicle vehicle = new Vehicle(5, VehicleType.CAR, 20.0);
        String result = vehicle.toString();
        assertTrue(result.contains("vehicleId=5"));
        assertTrue(result.contains("vehicleType=CAR"));
        assertTrue(result.contains("walletBalance=20.0"));
    }




    @Test
    void stringConstructorThrowsOnAlphanumericInput() {
        assertThrows(NumberFormatException.class, () -> new Vehicle("ABC123"));
    }


    @Test
    void stringConstructorLeavesWalletNullCausingNpeOnGetBalance() {
        Vehicle vehicle = new Vehicle("123");
        assertThrows(NullPointerException.class, vehicle::getBalance);
    }


    @Test
    void stringConstructorLeavesVehicleTypeNull() {
        Vehicle vehicle = new Vehicle("123");
        assertNull(vehicle.getVehicleType());
    }


    @Test
    void stringConstructorCausesNpeOnToString() {
        Vehicle vehicle = new Vehicle("123");
        assertThrows(NullPointerException.class, vehicle::toString);
    }


    @Test
    void negativeVehicleIdIsAcceptedWithoutValidation() {
        Vehicle vehicle = new Vehicle(-1, VehicleType.CAR, 10.0);

        assertEquals(-1, vehicle.getVehicleId());
    }


    @Test
    void nullWalletIsAcceptedWithoutValidation() {
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, (Wallet) null);

        assertThrows(NullPointerException.class, vehicle::getBalance);
    }



    // --- assertSame vs assertEquals: wallet reference identity ---

    @Test
    void getWalletReturnsExactSameInstancePassedIn() {
        Wallet wallet = new Wallet(30.0);
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, wallet);

        // Not just equal in value — literally the same object
        assertSame(wallet, vehicle.getWallet());
    }

    @Test
    void constructorWithDoubleCreatesNewWalletInstanceEachTime() {
        Vehicle v1 = new Vehicle(1, VehicleType.CAR, 50.0);
        Vehicle v2 = new Vehicle(2, VehicleType.CAR, 50.0);

        // Same balance value, but must NOT be the same Wallet object
        assertNotSame(v1.getWallet(), v2.getWallet());
    }

    // --- Wallet mutation should reflect through Vehicle (shared reference) ---

    @Test
    void deductingFromWalletDirectlyReflectsInVehicleBalance() {
        Wallet wallet = new Wallet(100.0);
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, wallet);

        wallet.deductFunds(40.0);

        assertEquals(60.0, vehicle.getBalance());
    }

    @Test
    void addingFundsThroughVehicleWalletIsVisibleExternally() {
        Wallet wallet = new Wallet(10.0);
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, wallet);

        vehicle.getWallet().addFunds(25.0);

        assertEquals(35.0, wallet.getBalance());
    }

    // --- Two vehicles are independent, even with identical constructor args ---

    @Test
    void twoVehiclesWithSameArgsAreIndependentObjects() {
        Vehicle v1 = new Vehicle(1, VehicleType.CAR, 50.0);
        Vehicle v2 = new Vehicle(1, VehicleType.CAR, 50.0);

        assertNotSame(v1, v2);
        v1.getWallet().deductFunds(20.0);

        // v2's balance must be unaffected by v1's wallet mutation
        assertEquals(30.0, v1.getBalance());
        assertEquals(50.0, v2.getBalance());
    }

    // --- No equals()/hashCode() override: identity equality only ---

    @Test
    void vehiclesWithIdenticalFieldsAreNotEqualByDefault() {
        Vehicle v1 = new Vehicle(1, VehicleType.CAR, 50.0);
        Vehicle v2 = new Vehicle(1, VehicleType.CAR, 50.0);

        // BUG / design gap: no equals() override means this uses Object.equals()
        // (reference equality), even though the two vehicles are conceptually
        // "the same car" by vehicleId. Worth deciding if that's intended.
        assertNotEquals(v1, v2);
    }

    // --- Boundary balance values ---

    @Test
    void constructorAcceptsVeryLargeInitialBalance() {
        Vehicle vehicle = new Vehicle(1, VehicleType.BUS, 1_000_000.0);
        assertEquals(1_000_000.0, vehicle.getBalance());
    }

    @Test
    void constructorAcceptsNegativeInitialBalanceWithoutValidation() {
        // BUG: no check preventing a vehicle from starting with negative funds
        Vehicle vehicle = new Vehicle(1, VehicleType.CAR, -50.0);
        assertEquals(-50.0, vehicle.getBalance());
    }

    // --- VehicleType coverage across all enum values ---

    @Test
    void constructorAcceptsAllVehicleTypes() {
        for (VehicleType type : VehicleType.values()) {
            Vehicle vehicle = new Vehicle(1, type, 10.0);
            assertEquals(type, vehicle.getVehicleType());
        }
    }

    @Test
    void constructorAcceptsNullVehicleTypeWithoutValidation() {
        // BUG: null VehicleType is silently accepted
        Vehicle vehicle = new Vehicle(1, null, 10.0);
        assertNull(vehicle.getVehicleType());
    }

    // --- vehicleId boundary: zero ---

    @Test
    void zeroVehicleIdIsAcceptedWithoutValidation() {
        Vehicle vehicle = new Vehicle(0, VehicleType.CAR, 10.0);
        assertEquals(0, vehicle.getVehicleId());
    }



    @Test
    void toStringWorksForEveryVehicleType() {
        for (VehicleType type : VehicleType.values()) {
            Vehicle vehicle = new Vehicle(1, type, 10.0);
            assertDoesNotThrow(vehicle::toString);
        }
    }


|Testcase | |WalletTest|


@BeforeEach
    void setUp() {
        wallet = new Wallet();
    }



    @Test
    void defaultConstructor_startsAtZeroBalance() {
        assertEquals(0.0, wallet.getBalance(), 0.0001);
    }

    @Test
    void parameterizedConstructor_setsInitialBalance() {
        Wallet w = new Wallet(50.0);
        assertEquals(50.0, w.getBalance(), 0.0001);
    }

    // ---------- addFunds ----------

    @Test
    void addFunds_increasesBalance() {
        wallet.addFunds(25.0);
        assertEquals(25.0, wallet.getBalance(), 0.0001);
    }

    @Test
    void addFunds_multipleTimes_accumulates() {
        wallet.addFunds(10.0);
        wallet.addFunds(15.5);
        assertEquals(25.5, wallet.getBalance(), 0.0001);
    }

    @Test
    void addFunds_zeroAmount_throwsInvalidAmountException() {
        assertThrows(InvalidAmountException.class, () -> wallet.addFunds(0.0));
    }

    @Test
    void addFunds_negativeAmount_throwsInvalidAmountException() {
        assertThrows(InvalidAmountException.class, () -> wallet.addFunds(-5.0));
    }

    // ---------- deductFunds ----------

    @Test
    void deductFunds_decreasesBalance() {
        wallet.addFunds(100.0);
        wallet.deductFunds(40.0);
        assertEquals(60.0, wallet.getBalance(), 0.0001);
    }

    @Test
    void deductFunds_exactBalance_resultsInZero() {
        wallet.addFunds(30.0);
        wallet.deductFunds(30.0);
        assertEquals(0.0, wallet.getBalance(), 0.0001);
    }

    @Test
    void deductFunds_insufficientBalance_throwsInsufficientFundsException() {
        wallet.addFunds(10.0);
        assertThrows(InsufficientFundsException.class, () -> wallet.deductFunds(20.0));
    }

    @Test
    void deductFunds_zeroAmount_throwsInvalidAmountException() {
        wallet.addFunds(10.0);
        assertThrows(InvalidAmountException.class, () -> wallet.deductFunds(0.0));
    }

    @Test
    void deductFunds_negativeAmount_throwsInvalidAmountException() {
        wallet.addFunds(10.0);
        assertThrows(InvalidAmountException.class, () -> wallet.deductFunds(-5.0));
    }

    @Test
    void deductFunds_fromEmptyWallet_throwsInsufficientFundsException() {
        assertThrows(InsufficientFundsException.class, () -> wallet.deductFunds(1.0));
    }

    // ---------- transferFunds ----------

    @Test
    void transferFunds_movesMoneyBetweenWallets() {
        wallet.addFunds(100.0);
        Wallet other = new Wallet();

        wallet.transferFunds(other, 40.0);

        assertEquals(60.0, wallet.getBalance(), 0.0001);
        assertEquals(40.0, other.getBalance(), 0.0001);
    }

    @Test
    void transferFunds_exactBalance_leavesSenderAtZero() {
        wallet.addFunds(50.0);
        Wallet other = new Wallet();

        wallet.transferFunds(other, 50.0);

        assertEquals(0.0, wallet.getBalance(), 0.0001);
        assertEquals(50.0, other.getBalance(), 0.0001);
    }

    @Test
    void transferFunds_insufficientBalance_throwsInsufficientFundsException_andDoesNotModifyEitherWallet() {
        wallet.addFunds(20.0);
        Wallet other = new Wallet();

        assertThrows(InsufficientFundsException.class, () -> wallet.transferFunds(other, 50.0));

        assertEquals(20.0, wallet.getBalance(), 0.0001);
        assertEquals(0.0, other.getBalance(), 0.0001);
    }

    @Test
    void deductFunds_exactAmount_shouldSucceed_bug() {
        Wallet wallet = new Wallet();
        wallet.addFunds(0.1);
        wallet.addFunds(0.2);

        // Trying to deduct exactly what's "supposed" to be there
        assertDoesNotThrow(() -> wallet.deductFunds(0.3));
        assertEquals(0.0, wallet.getBalance());
    }





    @Test
    void transferFunds_zeroAmount_throwsInvalidAmountException() {
        wallet.addFunds(20.0);
        Wallet other = new Wallet();

        assertThrows(InvalidAmountException.class, () -> wallet.transferFunds(other, 0.0));
    }

    @Test
    void transferFunds_negativeAmount_throwsInvalidAmountException() {
        wallet.addFunds(20.0);
        Wallet other = new Wallet();

        assertThrows(InvalidAmountException.class, () -> wallet.transferFunds(other, -10.0));
    }

    @Test
    void transferFunds_toSelf_balanceUnchanged() {
        wallet.addFunds(30.0);
        wallet.transferFunds(wallet, 10.0);
        // deduct 10 then add 10 back to the same wallet
        assertEquals(30.0, wallet.getBalance(), 0.0001);
    }


    @Test
    void deductFunds_floatingPointPrecision_bug() {
        Wallet wallet = new Wallet();
        wallet.addFunds(0.1);
        wallet.addFunds(0.2);

        // Mathematically, balance should be exactly 0.3
        System.out.println("Balance: " + wallet.getBalance());

        // This assertion is likely to FAIL due to floating-point rounding
        assertEquals(0.3, wallet.getBalance());
    }




---

## B) Defects List

| Defect ID | Class.Method Where Found | Description of Defect | Suggested Fix |
|-----------|----------------------------|-------------------------|----------------|
| DEF-01    |                            |                          |                |
| DEF-02    |                            |                          |                |
| DEF-03    |                            |                          |                |
