package vehicle;

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
                System.out.println("整備記録登録を選択しました。");
                break;

            case "4":
                System.out.println("整備記録一覧を選択しました。");
                break;

            case "5":
                System.out.println("検索を選択しました。");
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
}