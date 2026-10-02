package parking;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

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
}
