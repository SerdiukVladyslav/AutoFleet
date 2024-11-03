package org.example;

import org.example.Services.*;
import org.example.Controllers.DriverController;
import org.example.Controllers.OrderController;
import org.example.Controllers.RepairRequestController;
import org.example.Controllers.VehicleController;
import org.example.Entities.Driver;
import org.example.Entities.Order;
import org.example.Entities.RepairRequest;
import org.example.Entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

import java.util.List;
import java.util.Scanner;

@Component
public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);

    @Autowired
    private OrderController orderController;
    @Autowired
    private DriverController driverController;
    @Autowired
    private VehicleController vehicleController;
    @Autowired
    private RepairRequestController repairRequestController;
    @Autowired
    private DriverService driverService;
    @Autowired
    private VehicleService vehicleService;

    public void displayMenu() {
        while (true) {
            System.out.println("Выберите таблицу для работы:");
            System.out.println("1. Orders");
            System.out.println("2. Drivers");
            System.out.println("3. Vehicles");
            System.out.println("4. Repair Requests");
            System.out.println("5. Выйти");

            int tableChoice = scanner.nextInt();
            scanner.nextLine();

            if (tableChoice == 5) {
                System.out.println("Завершение работы программы.");
                break;
            }

            switch (tableChoice) {
                case 1:
                    handleOrderMenu();
                    break;
                case 2:
                    handleDriverMenu();
                    break;
                case 3:
                    handleVehicleMenu();
                    break;
                case 4:
                    handleRepairRequestMenu();
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

    private void handleOrderMenu() {
        System.out.println("Меню Orders:");
        System.out.println("1. Показать все заказы");
        System.out.println("2. Создать новый заказ");
        System.out.println("3. Найти заказ по ID");
        System.out.println("4. Обновить заказ");
        System.out.println("5. Удалить заказ");
        System.out.println("6. Назад");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                List<Order> orders = orderController.getAllOrders();
                orders.forEach(System.out::println);
                break;
            case 2:
                Order newOrder = new Order();
                System.out.print("Введите пункт назначения: ");
                newOrder.setDestination(scanner.nextLine());
                System.out.print("Введите тип груза: ");
                newOrder.setCargoType(scanner.nextLine());
                System.out.print("Введите вес груза: ");
                newOrder.setCargoWeight(scanner.nextInt());
                scanner.nextLine(); // очищаем буфер
                System.out.print("Введите дату заказа (формат YYYY-MM-DD): ");
                newOrder.setOrderDate(LocalDate.parse(scanner.nextLine()));

                // Выберите водителя
                System.out.print("Введите ID водителя: ");
                Long driverId = scanner.nextLong();
                Driver driver = driverService.getDriverById(driverId);
                newOrder.setDriver(driver);

                // Выберите транспортное средство
                System.out.print("Введите ID транспортного средства: ");
                Long vehicleId = scanner.nextLong();
                Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
                newOrder.setVehicle(vehicle);

                System.out.println(orderController.createOrder(newOrder));
                break;
            case 3:
                System.out.print("Введите ID заказа: ");
                Long orderId = scanner.nextLong();
                System.out.println(orderController.getOrderById(orderId));
                break;
            case 4:
                System.out.print("Введите ID заказа для обновления: ");
                Long updateOrderId = scanner.nextLong();
                Order updatedOrder = orderController.getOrderById(updateOrderId);
                if (updatedOrder == null) {
                    System.out.println("Заказ с таким ID не найден.");
                    break;
                }

                scanner.nextLine(); // очищаем буфер
                System.out.print("Введите новый пункт назначения (или оставьте пустым для сохранения): ");
                String destination = scanner.nextLine();
                if (!destination.isEmpty()) {
                    updatedOrder.setDestination(destination);
                }

                System.out.print("Введите новый тип груза (или оставьте пустым для сохранения): ");
                String cargoType = scanner.nextLine();
                if (!cargoType.isEmpty()) {
                    updatedOrder.setCargoType(cargoType);
                }

                System.out.print("Введите новый вес груза (или оставьте пустым для сохранения): ");
                String cargoWeightInput = scanner.nextLine();
                if (!cargoWeightInput.isEmpty()) {
                    updatedOrder.setCargoWeight(Integer.parseInt(cargoWeightInput));
                }

                System.out.print("Введите новую дату заказа (или оставьте пустым для сохранения, формат YYYY-MM-DD): ");
                String orderDateInput = scanner.nextLine();
                if (!orderDateInput.isEmpty()) {
                    updatedOrder.setOrderDate(LocalDate.parse(orderDateInput));
                }

                System.out.println(orderController.updateOrder(updateOrderId, updatedOrder));
                break;
            case 5:
                System.out.print("Введите ID заказа для удаления: ");
                Long deleteOrderId = scanner.nextLong();
                orderController.deleteOrder(deleteOrderId);
                System.out.println("Заказ удален.");
                break;
            case 6:
                return;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void handleDriverMenu() {
        System.out.println("Меню Drivers:");
        System.out.println("1. Показать всех водителей");
        System.out.println("2. Создать нового водителя");
        System.out.println("3. Найти водителя по ID");
        System.out.println("4. Обновить водителя");
        System.out.println("5. Удалить водителя");
        System.out.println("6. Назад");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                List<Driver> drivers = driverController.getAllDrivers();
                drivers.forEach(System.out::println);
                break;
            case 2:
                Driver newDriver = new Driver();
                System.out.print("Введите имя водителя: ");
                newDriver.setName(scanner.nextLine());
                System.out.print("Введите количество лет опыта: ");
                newDriver.setExperienceYears(scanner.nextInt());
                System.out.print("Введите доход водителя: ");
                newDriver.setEarnings(scanner.nextDouble());
                scanner.nextLine(); // Очистка новой строки после nextDouble()
                System.out.println(driverController.createDriver(newDriver));
                break;
            case 3:
                System.out.print("Введите ID водителя: ");
                Long driverId = scanner.nextLong();
                System.out.println(driverController.getDriverById(driverId));
                break;
            case 4:
                System.out.print("Введите ID водителя для обновления: ");
                Long updateDriverId = scanner.nextLong();
                scanner.nextLine();

                Driver updatedDriver = new Driver();
                updatedDriver.setId(updateDriverId);

                System.out.print("Введите новое имя водителя: ");
                updatedDriver.setName(scanner.nextLine());

                System.out.print("Введите годы опыта водителя: ");
                updatedDriver.setExperienceYears(scanner.nextInt());
                scanner.nextLine();

                System.out.print("Введите доходы водителя: ");
                updatedDriver.setEarnings(scanner.nextDouble());
                scanner.nextLine();

                System.out.println(driverController.updateDriver(updateDriverId, updatedDriver));
                break;
            case 5:
                System.out.print("Введите ID водителя для удаления: ");
                Long deleteDriverId = scanner.nextLong();
                driverController.deleteDriver(deleteDriverId);
                System.out.println("Водитель удален.");
                break;
            case 6:
                return;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void handleVehicleMenu() {
        System.out.println("Меню Vehicles:");
        System.out.println("1. Показать все транспортные средства");
        System.out.println("2. Создать новое транспортное средство");
        System.out.println("3. Найти транспортное средство по ID");
        System.out.println("4. Обновить транспортное средство");
        System.out.println("5. Удалить транспортное средство");
        System.out.println("6. Назад");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                List<Vehicle> vehicles = vehicleController.getAllVehicles();
                vehicles.forEach(System.out::println);
                break;
            case 2:
                Vehicle newVehicle = new Vehicle();
                System.out.print("Введите модель транспортного средства: ");
                newVehicle.setModel(scanner.nextLine());
                System.out.print("Введите максимальную грузоподъемность: ");
                newVehicle.setMaxLoadCapacity(scanner.nextInt());
                System.out.print("Доступно (true/false): ");
                newVehicle.setAvailable(scanner.nextBoolean());
                System.out.println(vehicleController.createVehicle(newVehicle));
                break;
            case 3:
                System.out.print("Введите ID транспортного средства: ");
                Long vehicleId = scanner.nextLong();
                System.out.println(vehicleController.getVehicleById(vehicleId));
                break;
            case 4:
                System.out.print("Введите ID транспортного средства для обновления: ");
                Long updateVehicleId = scanner.nextLong();
                Vehicle updatedVehicle = new Vehicle();
                updatedVehicle.setId(updateVehicleId);
                System.out.print("Введите новую модель: ");
                updatedVehicle.setModel(scanner.next());
                System.out.print("Введите новую максимальную грузоподъемность: ");
                updatedVehicle.setMaxLoadCapacity(scanner.nextInt());
                System.out.print("Доступно (true/false): ");
                updatedVehicle.setAvailable(scanner.nextBoolean());
                System.out.println(vehicleController.updateVehicle(updateVehicleId, updatedVehicle));
                break;
            case 5:
                System.out.print("Введите ID транспортного средства для удаления: ");
                Long deleteVehicleId = scanner.nextLong();
                vehicleController.deleteVehicle(deleteVehicleId);
                System.out.println("Транспортное средство удалено.");
                break;
            case 6:
                return;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void handleRepairRequestMenu() {
        System.out.println("Меню Repair Requests:");
        System.out.println("1. Показать все заявки на ремонт");
        System.out.println("2. Создать новую заявку на ремонт");
        System.out.println("3. Найти заявку на ремонт по ID");
        System.out.println("4. Обновить заявку на ремонт");
        System.out.println("5. Удалить заявку на ремонт");
        System.out.println("6. Назад");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                List<RepairRequest> repairRequests = repairRequestController.getAllRepairRequests();
                repairRequests.forEach(System.out::println);
                break;
            case 2:
                RepairRequest newRequest = new RepairRequest();
                System.out.print("Введите ID автомобиля: ");
                Long vehicleId = scanner.nextLong();
                newRequest.setVehicle(repairRequestController.getVehicleById(vehicleId));

                System.out.print("Введите ID водителя: ");
                Long driverId = scanner.nextLong();
                newRequest.setDriver(repairRequestController.getDriverById(driverId));

                scanner.nextLine();
                System.out.print("Введите описание заявки: ");
                String description = scanner.nextLine();
                newRequest.setDescription(description);
                newRequest.setRequestDate(LocalDate.now());

                System.out.println(repairRequestController.createRepairRequest(newRequest));
                break;
            case 3:
                System.out.print("Введите ID заявки на ремонт: ");
                Long requestId = scanner.nextLong();
                System.out.println(repairRequestController.getRepairRequestById(requestId));
                break;
            case 4:
                System.out.print("Введите ID заявки на ремонт для обновления: ");
                Long updateRequestId = scanner.nextLong();
                RepairRequest updatedRequest = new RepairRequest();

                System.out.print("Введите ID автомобиля: ");
                Long vehicleIdForUpdate = scanner.nextLong();
                updatedRequest.setVehicle(repairRequestController.getVehicleById(vehicleIdForUpdate));

                System.out.print("Введите ID водителя: ");
                Long driverIdForUpdate = scanner.nextLong();
                updatedRequest.setDriver(repairRequestController.getDriverById(driverIdForUpdate));

                scanner.nextLine();
                System.out.print("Введите новое описание заявки: ");
                updatedRequest.setDescription(scanner.nextLine());
                updatedRequest.setRequestDate(LocalDate.now());

                System.out.println(repairRequestController.updateRepairRequest(updateRequestId, updatedRequest));
                break;
            case 5:
                System.out.print("Введите ID заявки на ремонт для удаления: ");
                Long deleteRequestId = scanner.nextLong();
                repairRequestController.deleteRepairRequest(deleteRequestId);
                System.out.println("Заявка на ремонт удалена.");
                break;
            case 6:
                return;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
}
