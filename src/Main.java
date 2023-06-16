import java.io.File;
import java.sql.Array;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {

//        FileServices.SalesMaps();
        File model3SalesCSV = new File("./src/CarSaleCSVs/model3.csv");
//        File modelSSalesCSV = new File("./src/CarSaleCSVs/modelS.csv");
//        File modelXSalesCSV = new File("./src/CarSaleCSVs/modelX.csv");
//
//
//        FileServices.getSalesTotalPerYear(model3SalesCSV, "Model 3");
//        FileServices.getSalesTotalPerYear(modelSSalesCSV, "Model S");
//        FileServices.getSalesTotalPerYear(modelXSalesCSV, "Model X");
//
//
//        FileServices.returnWorstMonthOptional(model3SalesCSV, "Model 3");
//        FileServices.returnWorstMonthOptional(modelSSalesCSV);
//        FileServices.returnWorstMonthOptional(modelXSalesCSV);

        FileServices.returnBestMonthOptional(model3SalesCSV, "Model 3");
        FileServices.returnWorstMonthOptional(model3SalesCSV, "Model 3");


    }

    public static void FileServices2(File file, String carModelName) throws Exception {
        ArrayList<CarSalesData> carSalesDataList = new ArrayList<>();
        carSalesDataList = FileServices.convertCSVToArrayList(file, carModelName);

        List<YearMonth> carSalesNums = new ArrayList<>();
        carSalesNums = carSalesDataList.stream()
                .max((car1, car2) -> car1.getSalesNumber().compareTo(car2.getSalesNumber()))
                .map((CarSalesData y) -> y.getYearMonth())
                .stream().toList();

        System.out.println(carSalesNums);
    }
}
