import java.io.File;
public class Main {

    public static void main(String[] args) throws Exception {

        File model3SalesCSV = new File("./src/CarSaleCSVs/model3.csv");
        File modelSSalesCSV = new File("./src/CarSaleCSVs/modelS.csv");
        File modelXSalesCSV = new File("./src/CarSaleCSVs/modelX.csv");

        FileServices.getSalesTotalPerYear(model3SalesCSV, "Model 3");
        FileServices.returnBestMonthOptional(model3SalesCSV, "Model 3");
        FileServices.returnWorstMonthOptional(model3SalesCSV, "Model 3");

        FileServices.getSalesTotalPerYear(modelSSalesCSV, "Model S");
        FileServices.returnBestMonthOptional(modelSSalesCSV, "Model S");
        FileServices.returnWorstMonthOptional(modelSSalesCSV, "Model S");

        FileServices.getSalesTotalPerYear(modelXSalesCSV, "Model X");
        FileServices.returnBestMonthOptional(modelXSalesCSV, "Model X");
        FileServices.returnWorstMonthOptional(modelXSalesCSV, "Model X");

    }
}
