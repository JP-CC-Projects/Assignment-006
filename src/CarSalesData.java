import java.time.YearMonth;

public class CarSalesData {
    private YearMonth yearMonth;
    private Integer salesNumber;
    private String brandOfCar;

    CarSalesData(YearMonth yearMonth, Integer salesNumber){
        this.yearMonth = yearMonth;
        this.salesNumber = salesNumber;
    }
    CarSalesData(YearMonth yearMonth, Integer salesNumber, String brandOfCar){
        this.yearMonth = yearMonth;
        this.salesNumber = salesNumber;
        this.brandOfCar = brandOfCar;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Integer getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(Integer salesNumber) {
        this.salesNumber = salesNumber;
    }
    public String getBrandOfCar() {
        return brandOfCar;
    }

    public void setBrandOfCar(String brandOfCar) {
        this.brandOfCar = brandOfCar;
    }
}


