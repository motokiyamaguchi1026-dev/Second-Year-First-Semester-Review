package vehicle;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {

    private Scanner scanner = new Scanner(System.in);
    private MaintenanceService service = new MaintenanceService();

    public void start() {

        while (true) {

            showMainMenu();

            System.out.print("番号を入力してください：");
            String input = scanner.nextLine();

            switch (input) {

            case "1":
                registerVehicle();
                break;

            case "2":
                showVehicles();
                break;

            case "3":
                registerRecord();
                break;

            case "4":
                showRecords();
                break;

            case "5":
                search();
                break;

            case "6":
                System.out.println("整備記録更新を選択しました。");
                break;

            case "7":
                System.out.println("削除を選択しました。");
                break;

            case "8":
                System.out.println("アプリを終了します。");
                scanner.close();
                return;

            default:
                System.out.println("正しい番号を入力してください。");
                break;
            }

            System.out.println();
        }
    }

    private void showMainMenu() {

        System.out.println("================================");
        System.out.println("     車両整備記録システム");
        System.out.println("================================");
        System.out.println();
        System.out.println("1. 車両登録");
        System.out.println("2. 車両一覧");
        System.out.println("3. 整備記録登録");
        System.out.println("4. 整備記録一覧");
        System.out.println("5. 検索");
        System.out.println("6. 整備記録更新");
        System.out.println("7. 削除");
        System.out.println("8. 終了");
        System.out.println();
    }

//    ケース１
    private void registerVehicle() {

        System.out.println();
        System.out.println("----- 車両登録 -----");

        System.out.print("車両名を入力してください：");
        String vehicleName = scanner.nextLine();

        System.out.print("ナンバーを入力してください：");
        String vehicleNumber = scanner.nextLine();

        service.addVehicle(vehicleName, vehicleNumber);

        System.out.println("車両を登録しました。");
    }
    
//  ケース2
    private void showVehicles() {

        System.out.println();
        System.out.println("----- 車両一覧 -----");

        if (service.getVehicles().isEmpty()) {
            System.out.println("登録されている車両がありません。");
            return;
        }

        for (Vehicle vehicle : service.getVehicles()) {

            System.out.println("ID：" + vehicle.getId());
            System.out.println("車両名：" + vehicle.getVehicleName());
            System.out.println("ナンバー：" + vehicle.getVehicleNumber());
            System.out.println("--------------------");
        }
    }
//    ケース３
    private void registerRecord() {

        System.out.println();
        System.out.println("----- 整備記録登録 -----");

        if (service.getVehicles().isEmpty()) {
            System.out.println("登録されている車両がありません。");
            System.out.println("先に車両を登録してください。");
            return;
        }

        System.out.println("登録する車両を選択してください。");

        for (Vehicle vehicle : service.getVehicles()) {
            System.out.println(
                    vehicle.getId() + ". "
                    + vehicle.getVehicleName()
                    + "（" + vehicle.getVehicleNumber() + "）"
            );
        }

        System.out.print("車両IDを入力してください：");
        int vehicleId = Integer.parseInt(scanner.nextLine());

        System.out.print("整備日を入力してください：");
        String maintenanceDate = scanner.nextLine();

        System.out.print("走行距離を入力してください：");
        int mileage = Integer.parseInt(scanner.nextLine());

        System.out.print("整備内容を入力してください：");
        String maintenanceContent = scanner.nextLine();

        System.out.print("費用を入力してください：");
        int cost = Integer.parseInt(scanner.nextLine());

        service.addRecord(
                vehicleId,
                maintenanceDate,
                mileage,
                maintenanceContent,
                cost
        );

        System.out.println("整備記録を登録しました。");
    }
    
    
//    ケース4
    private void showRecords() {

        System.out.println();
        System.out.println("----- 整備記録一覧 -----");

        if (service.getVehicles().isEmpty()) {
            System.out.println("登録されている車両がありません。");
            return;
        }

        System.out.println("車両を選択してください。");

        for (Vehicle vehicle : service.getVehicles()) {

            System.out.println(
                    vehicle.getId() + ". "
                    + vehicle.getVehicleName()
                    + "（" + vehicle.getVehicleNumber() + "）"
            );
        }

        System.out.print("車両IDを入力してください：");
        int vehicleId = Integer.parseInt(scanner.nextLine());

        ArrayList<MaintenanceRecord> vehicleRecords =
                service.getRecordsByVehicleId(vehicleId);

        if (vehicleRecords.isEmpty()) {
            System.out.println("この車両の整備記録がありません。");
            return;
        }

        System.out.println();
        System.out.println("----- 整備記録 -----");

        for (MaintenanceRecord record : vehicleRecords) {

            System.out.println("整備記録ID：" + record.getId());
            System.out.println("整備日：" + record.getMaintenanceDate());
            System.out.println("走行距離：" + record.getMileage() + " km");
            System.out.println("整備内容：" + record.getMaintenanceContent());
            System.out.println("費用：" + record.getCost() + " 円");
            System.out.println("--------------------");
        }
    }
    
    private void search() {

        System.out.println();
        System.out.println("----- 検索 -----");
        System.out.println("1. 車両検索");
        System.out.println("2. 整備記録検索");

        System.out.print("番号を入力してください：");
        String input = scanner.nextLine();

        switch (input) {

        case "1":
            searchVehicle();
            break;

        case "2":
            searchRecord();
            break;

        default:
            System.out.println("正しい番号を入力してください。");
            break;
        }
    }
    private void searchVehicle() {

        System.out.println();
        System.out.println("----- 車両検索 -----");
        System.out.println("1. 車両名");
        System.out.println("2. ナンバー");

        System.out.print("番号を入力してください：");
        String input = scanner.nextLine();

        ArrayList<Vehicle> result;

        switch (input) {

        case "1":
            System.out.print("車両名を入力してください：");
            String vehicleName = scanner.nextLine();

            result = service.searchVehiclesByName(vehicleName);
            break;

        case "2":
            System.out.print("ナンバーを入力してください：");
            String vehicleNumber = scanner.nextLine();

            result = service.searchVehiclesByNumber(vehicleNumber);
            break;

        default:
            System.out.println("正しい番号を入力してください。");
            return;
        }

        if (result.isEmpty()) {
            System.out.println("該当する車両がありません。");
            return;
        }

        System.out.println();
        System.out.println("----- 検索結果 -----");

        for (Vehicle vehicle : result) {

            System.out.println("ID：" + vehicle.getId());
            System.out.println("車両名：" + vehicle.getVehicleName());
            System.out.println("ナンバー：" + vehicle.getVehicleNumber());
            System.out.println("--------------------");
        }
    }
    private void searchRecord() {

        System.out.println();
        System.out.println("----- 整備記録検索 -----");

        if (service.getVehicles().isEmpty()) {
            System.out.println("登録されている車両がありません。");
            return;
        }

        System.out.println("車両を選択してください。");

        for (Vehicle vehicle : service.getVehicles()) {

            System.out.println(
                    vehicle.getId() + ". "
                    + vehicle.getVehicleName()
                    + "（" + vehicle.getVehicleNumber() + "）"
            );
        }

        System.out.print("車両IDを入力してください：");
        int vehicleId = Integer.parseInt(scanner.nextLine());

        System.out.print("整備内容を入力してください：");
        String keyword = scanner.nextLine();

        ArrayList<MaintenanceRecord> result =
                service.searchRecordsByContent(vehicleId, keyword);

        if (result.isEmpty()) {
            System.out.println("該当する整備記録がありません。");
            return;
        }

        System.out.println();
        System.out.println("----- 検索結果 -----");

        for (MaintenanceRecord record : result) {

            System.out.println("整備記録ID：" + record.getId());
            System.out.println("整備日：" + record.getMaintenanceDate());
            System.out.println("走行距離：" + record.getMileage() + " km");
            System.out.println("整備内容：" + record.getMaintenanceContent());
            System.out.println("費用：" + record.getCost() + " 円");
            System.out.println("--------------------");
        }
    }
    
}