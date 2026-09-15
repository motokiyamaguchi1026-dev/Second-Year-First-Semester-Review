package main;

import java.util.ArrayList;
import java.util.Scanner;

import model.MaintenanceRecord;
import model.Vehicle;
import service.MaintenanceService;
import util.InputUtil;

//画面操作用クラス
public class ConsoleUI {

    private Scanner scanner = new Scanner(System.in);

    // 車両と整備記録
    private MaintenanceService service = new MaintenanceService();

    // 入力チェック用
    private InputUtil inputUtil = new InputUtil(scanner);
   
    public void start() {
        while (true) {

            showMainMenu();
            int input = inputUtil.inputNumber("番号を入力してください：");
            switch (input) {

            case 1:
                registerVehicle();
                break;

            case 2:
                showVehicles();
                break;

            case 3:
                registerRecord();
                break;

            case 4:
                showRecords();
                break;

            case 5:
                search();
                break;

            case 6:
                updateRecord();
                break;

            case 7:
                deleteData();
                break;

            case 8:
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

    // メインメニュー
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

    // ケース１車両を登録する
    private void registerVehicle() {

        System.out.println();
        System.out.println("----- 車両登録 -----");

        String vehicleName = inputUtil.inputRequired("車両名を入力してください：");

        String vehicleNumber =inputUtil.inputRequired( "ナンバーを入力してください：");

        service.addVehicle(vehicleName, vehicleNumber);

        System.out.println("車両を登録しました。");
    }

    // ケース２車両一覧を表示する
    private void showVehicles() {

        System.out.println();
        System.out.println("----- 車両一覧 -----");

        // 車両が登録されていない場合の処理
        if (service.getVehicles().isEmpty()) {
            System.out.println("登録されている車両がありません。" );
            return;
        }

        for (Vehicle vehicle : service.getVehicles()) {
            System.out.println("ID：" + vehicle.getId());
            System.out.println("車両名：" + vehicle.getVehicleName());
            System.out.println("ナンバー：" + vehicle.getVehicleNumber());
            System.out.println("--------------------");
        }
    }

    // ケース３整備記録を登録する
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

        int vehicleId;

        while (true) {

            vehicleId =inputUtil.inputNumber("車両IDを入力してください：");

            if (service.existsVehicle(vehicleId)) {
                break;
            }
            System.out.println("指定された車両がありません。");
        }

        String maintenanceDate = inputUtil.inputRequired("整備日を入力してください：");
        int mileage = inputUtil.inputNumber("走行距離を入力してください：");
        String maintenanceContent = inputUtil.inputRequired("整備内容を入力してください：");
        int cost = inputUtil.inputNumber("費用を入力してください：");

        service.addRecord(
                vehicleId,
                maintenanceDate,
                mileage,
                maintenanceContent,
                cost
        );

        System.out.println("整備記録を登録しました。");
    }

    // ケース４整備記録一覧を表示する
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
        int vehicleId;

        while (true) {

            vehicleId =inputUtil.inputNumber("車両IDを入力してください：");

            if (service.existsVehicle(vehicleId)) {
                break;
            }

            System.out.println("指定された車両がありません。");
        }

        // 選択した車両の整備記録を取得
        ArrayList<MaintenanceRecord> vehicleRecords =
                service.getRecordsByVehicleId(vehicleId);

                if (vehicleRecords.isEmpty()) {
                	System.out.println("この車両の整備記録がありません。");
                	return;
        }

        System.out.println();
        System.out.println("----- 整備記録 -----");

        // 整備記録を表示
        for (MaintenanceRecord record : vehicleRecords) {
            System.out.println("整備記録ID：" + record.getId());
            System.out.println("整備日：" + record.getMaintenanceDate());
            System.out.println("走行距離：" + record.getMileage() + " km");
            System.out.println("整備内容：" + record.getMaintenanceContent());
            System.out.println("費用：" + record.getCost() + " 円");
            System.out.println("--------------------");
        }
    }

    // ケース５検索
    private void search() {
        System.out.println();
        System.out.println("----- 検索 -----");
        System.out.println("1. 車両検索");
        System.out.println("2. 整備記録検索");

        int input =inputUtil.inputNumber("番号を入力してください：");

        switch (input) {
        case 1:
            searchVehicle();
            break;

        case 2:
            searchRecord();
            break;

        default:
            System.out.println("正しい番号を入力してください。");
            break;
        }
    }
    //車両検索
    private void searchVehicle() {

        System.out.println();
        System.out.println("----- 車両検索 -----");
        System.out.println("1. 車両名");
        System.out.println("2. ナンバー");

        int input =inputUtil.inputNumber("番号を入力してください：");

        ArrayList<Vehicle> result;
        switch (input) {
        case 1:
            String vehicleName =inputUtil.inputRequired("車両名を入力してください：");
            result =service.searchVehiclesByName(vehicleName);
            break;

        case 2:
            String vehicleNumber =inputUtil.inputRequired("ナンバーを入力してください：");
            result =service.searchVehiclesByNumber(vehicleNumber);
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

        // 検索結果を表示
        for (Vehicle vehicle : result) {
            System.out.println("ID：" + vehicle.getId());
            System.out.println("車両名：" + vehicle.getVehicleName());
            System.out.println("ナンバー：" + vehicle.getVehicleNumber());
            System.out.println("--------------------");
        }
    }

    // 整備記録検索
    private void searchRecord() {

        System.out.println();
        System.out.println("----- 整備記録検索 -----");

        // 車両が登録されていない場合
        if (service.getVehicles().isEmpty()) {
            System.out.println("登録されている車両がありません。");
            return;
        }

        System.out.println(
                "車両を選択してください。"
        );

        for (Vehicle vehicle : service.getVehicles()) {
            System.out.println(vehicle.getId() + ". "
                    + vehicle.getVehicleName()
                    + "（" + vehicle.getVehicleNumber() + "）"
            );
        }

        int vehicleId;

        while (true) {

            vehicleId =
                    inputUtil.inputNumber(
                            "車両IDを入力してください："
                    );

            if (service.existsVehicle(vehicleId)) {
                break;
            }

            System.out.println(
                    "指定された車両がありません。"
            );
        }

        String keyword =
                inputUtil.inputRequired(
                        "整備内容を入力してください："
                );

        // 検索
        ArrayList<MaintenanceRecord> result =
                service.searchRecordsByContent(
                        vehicleId,
                        keyword
                );

        if (result.isEmpty()) {

            System.out.println(
                    "該当する整備記録がありません。"
            );

            return;
        }

        System.out.println();
        System.out.println("----- 検索結果 -----");

        for (MaintenanceRecord record : result) {
            System.out.println("整備記録ID：" + record.getId());
            System.out.println("整備日：" + record.getMaintenanceDate());
            System.out.println("走行距離：" + record.getMileage() + " km");
            System.out.println("整備内容："+ record.getMaintenanceContent());
            System.out.println("費用：" + record.getCost() + " 円");
            System.out.println("--------------------");
        }
    }

    // ケース6整備記録を更新する
    private void updateRecord() {

        System.out.println();
        System.out.println("----- 整備記録更新 -----");

        if (service.getRecords().isEmpty()) {
            System.out.println("登録されている整備記録がありません。");
            return;
        }
       
        System.out.println("登録されている整備記録：");

        for (MaintenanceRecord record : service.getRecords()) {
            System.out.println(
                    "ID：" + record.getId()
                    + " / 整備日："+ record.getMaintenanceDate()
                    + " / 整備内容："+ record.getMaintenanceContent()
            );
        }

        int recordId =
                inputUtil.inputNumber("更新する整備記録IDを入力してください：");
        MaintenanceRecord targetRecord = null;

        for (MaintenanceRecord record : service.getRecords()) {
            if (record.getId() == recordId) {
                targetRecord = record;
                break;
            }
        }

        // 記録が存在しない場合
        if (targetRecord == null) {
            System.out.println("指定された整備記録がありません。");
            return;
        }

        System.out.println();
        System.out.println("現在の整備記録");
        System.out.println("整備日："+ targetRecord.getMaintenanceDate());
        System.out.println("走行距離："+ targetRecord.getMileage()+ " km");
        System.out.println("整備内容：" + targetRecord.getMaintenanceContent());

        System.out.println("費用："+ targetRecord.getCost()+ " 円");

        System.out.println();
        System.out.println("新しい情報を入力してください。");

        String maintenanceDate =inputUtil.inputRequired("整備日：");

        int mileage =inputUtil.inputNumber("走行距離：");

        String maintenanceContent =inputUtil.inputRequired("整備内容：");

        int cost =inputUtil.inputNumber("費用：");

        service.updateRecord(
                recordId,
                maintenanceDate,
                mileage,
                maintenanceContent,
                cost
        );

        System.out.println("整備記録を更新しました。");
    }

    // ケース7削除メニュー
    private void deleteData() {

        System.out.println();
        System.out.println("----- 削除 -----");
        System.out.println("1. 車両を削除");
        System.out.println("2. 整備記録を削除");

        int input =inputUtil.inputNumber("番号を入力してください：");

        switch (input) {
        case 1:
            deleteVehicle();
            break;

        case 2:
            deleteRecord();
            break;

        default:
            System.out.println("正しい番号を入力してください。");
            break;
        }
    }

    private void deleteVehicle() {
        System.out.println();
        System.out.println("----- 車両削除 -----");

        if (service.getVehicles().isEmpty()) {
            System.out.println("登録されている車両がありません。");
            return;
        }

        for (Vehicle vehicle : service.getVehicles()) {
            System.out.println(
                    vehicle.getId() + ". "
                    + vehicle.getVehicleName()
                    + "（" + vehicle.getVehicleNumber() + "）"
            );
        }

        int vehicleId =
                inputUtil.inputNumber(
                        "削除する車両IDを入力してください："
                );

        Vehicle targetVehicle = null;

        for (Vehicle vehicle : service.getVehicles()) {

            if (vehicle.getId() == vehicleId) {
                targetVehicle = vehicle;
                break;
            }
        }

        if (targetVehicle == null) {
            System.out.println(
                    "指定された車両がありません。"
            );
            return;
        }

        System.out.println();
        System.out.println("車両「"+ targetVehicle.getVehicleName()+ "」を削除します。");

        System.out.println("この車両の整備記録もすべて削除されます。");

        String confirm =inputUtil.inputRequired("削除しますか？（y/n）：");

        // y以外ならキャンセル
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("削除をキャンセルしました。");
            return;
        }

        service.deleteVehicle(vehicleId);

        System.out.println("車両を削除しました。");
    }

    // 整備記録を削除
    private void deleteRecord() {

        System.out.println();
        System.out.println("----- 整備記録削除 -----");

        if (service.getRecords().isEmpty()) {
            System.out.println("登録されている整備記録がありません。");
            return;
        }
        for (MaintenanceRecord record : service.getRecords()) {
            System.out.println(
                    "ID：" + record.getId()
                    + " / 整備日："
                    + record.getMaintenanceDate()
                    + " / 整備内容："
                    + record.getMaintenanceContent()
            );
        }

        int recordId =
                inputUtil.inputNumber("削除する整備記録IDを入力してください：");

        boolean deleted =service.deleteRecord(recordId);

        if (deleted) {
            System.out.println("整備記録を削除しました。");
        } else {
            System.out.println("指定された整備記録がありません。");
        }
    }
}