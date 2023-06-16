import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;

public class FileServices {

    public static void SalesMaps() throws Exception {


    }

    static void getSalesTotalPerYear(File carSalesCSV, String carModelName) throws Exception {

        System.out.println("------------");
        System.out.println(carModelName + " Yearly Sales Report: ");
        System.out.println("------------");

        //CSV to ArrayList

//        Turn carSalesCSV into an ArrayList of CarSalesData POJOs
        ArrayList<CarSalesData> carSalesDataPOJOArrayList = convertCSVToArrayList(carSalesCSV, carModelName);


        Map<Integer, Integer> mapOfYearSales = getYearSalesFromArrayListOfCarSalesData(carSalesDataPOJOArrayList);

        printStreamOfSalesTotalPerYearFromMap(mapOfYearSales);
        System.out.println();

    }

    static void printStreamOfSalesTotalPerYearFromMap(Map<Integer, Integer> map) throws Exception {

        mapToEntrySet(map).stream()
                .forEach(x -> System.out.println(x.getKey() + ": " + x.getValue()));
    }

    public static ArrayList<CarSalesData> convertCSVToArrayList(File file, String carModelName) throws Exception {

        String line = "";
        String[] lineData = {};
        ArrayList<CarSalesData> carSalesDataArrayList = new ArrayList<>();

        try (BufferedReader carCSVReader = new BufferedReader(
                new FileReader(file))) {
            carCSVReader.readLine();
            while ((line = carCSVReader.readLine()) != null) {
                lineData = line.split(",");
                YearMonth yearMonth = convertToYearMonth(lineData[0]);
                //convertToYearMonth method below
                int carSales = Integer.parseInt(lineData[1]);
                carSalesDataArrayList.add(new CarSalesData(yearMonth, carSales, carModelName));
            }
        }
        return carSalesDataArrayList;
    }


    //    Takes String date in provided CSVs, e.g. "JAN-19", converts to YearMonth
    public static YearMonth convertToYearMonth(String inputYearMonthMMMYY) {

        String formattedMMM = inputYearMonthMMMYY.substring(0, 1).toUpperCase()
                + inputYearMonthMMMYY.substring(1).toLowerCase();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy", Locale.ENGLISH)
                .withResolverStyle(ResolverStyle.LENIENT);
        YearMonth formattedYearMonth = null;
        try {
            formattedYearMonth = YearMonth.parse(formattedMMM, formatter);
        } catch (DateTimeParseException | NumberFormatException e) {
            return null;
        }
        return formattedYearMonth;
    }


    public static Map<Integer, Integer> getYearSalesFromArrayListOfCarSalesData(ArrayList<CarSalesData> carModelSalesData) {

        Map<Integer, Integer> carSalesYearTotals = new LinkedHashMap<>();

        for (CarSalesData csd : carModelSalesData) {
            if (csd.getYearMonth() != null) {
                if (carSalesYearTotals.get(csd.getYearMonth().getYear()) != null) {
                    carSalesYearTotals.put(csd.getYearMonth().getYear(),
                            (carSalesYearTotals.get(csd.getYearMonth().getYear()) + csd.getSalesNumber()));

                } else {
                    carSalesYearTotals.put(csd.getYearMonth().getYear(),
                            (csd.getSalesNumber()));
                }
            }
        }
        return carSalesYearTotals;
    }



    public static Optional<Integer> returnBestMonthOptional(File file, String carModelName) throws Exception {
        ArrayList<CarSalesData> carSalesDataArrayList = convertCSVToArrayList(file, carModelName);

        Optional<YearMonth> bestMonthSales;
        bestMonthSales = carSalesDataArrayList.stream()
                .max((car1, car2) -> car1.getSalesNumber().compareTo(car2.getSalesNumber()))
                .map(CarSalesData::getYearMonth);

        System.out.println("The best month for " + carModelName + " was: " + bestMonthSales.get());

        return Optional.of(carSalesDataArrayList.stream().mapToInt(CarSalesData::getSalesNumber)
                .max()
                .orElse(0));
    }

    public static Optional<Integer> returnWorstMonthOptional(File file, String carModelName) throws Exception {
        ArrayList<CarSalesData> carSalesDataArrayList = convertCSVToArrayList(file, carModelName);

        Optional<YearMonth> worstMonthSales;
        worstMonthSales = carSalesDataArrayList.stream()
                .min((car1, car2) -> car1.getSalesNumber().compareTo(car2.getSalesNumber()))
                .map(CarSalesData::getYearMonth);

        System.out.println("The worst month for " + carModelName + " was: " + worstMonthSales.get());

        return Optional.of(carSalesDataArrayList.stream().mapToInt(CarSalesData::getSalesNumber)
                .min()
                .orElse(0));
    }


    public static Set<Map.Entry<Integer, Integer>> mapToEntrySet(Map<Integer, Integer> hashMap) {

        Set<Map.Entry<Integer, Integer>> mapConvertedToEntrySet = hashMap.entrySet();

        return mapConvertedToEntrySet;
    }
}


//
//    public static Optional<Integer> returnBestMonth(File file) throws Exception {
//        ArrayList<CarSalesData> carSalesDataArrayList = convertToArrayList(file);
//
//        return Optional.of(carSalesDataArrayList.stream().mapToInt(CarSalesData::getSalesNumber)
//                .max()
//                .orElse(0));
//    }
//
//    public static Optional<Integer> returnWorstMonth(File file) throws Exception {
//        ArrayList<CarSalesData> carSalesDataArrayList = convertToArrayList(file);
//
//        return Optional.of(carSalesDataArrayList.stream().mapToInt(CarSalesData::getSalesNumber)
//                .min()
//                .orElse(0));
//    }

