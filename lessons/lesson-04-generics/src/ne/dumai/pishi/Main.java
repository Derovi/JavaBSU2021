package ne.dumai.pishi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

interface Taxed {
    int getTax();
    boolean isTaxPaid();
}

abstract class Building {
    float area;
}

interface COVIDStatisticsProvider {
    int getCOVIDCases();
}

class Office extends Building implements Taxed, COVIDStatisticsProvider {
    public Office(boolean taxPaid) {
        this.taxPaid = taxPaid;
    }

    int coffeeMachines;
    int employees;
    boolean taxPaid;

    @Override
    public int getTax() {
        return 30;
    }

    @Override
    public boolean isTaxPaid() {
        return taxPaid;
    }

    @Override
    public int getCOVIDCases() {
        return ThreadLocalRandom.current().nextInt(0,  coffeeMachines);
    }
}

class RubinPlaza extends Office {
    public RubinPlaza(boolean taxPaid) {
        super(taxPaid);
    }
}

class GoogleOffice extends Office {

    public GoogleOffice(boolean taxPaid) {
        super(taxPaid);
    }
}

class Shop extends Building implements Taxed {
    int dailyCustomers;
    boolean taxPaid;

    public Shop(boolean taxPaid) {
        this.taxPaid = taxPaid;
    }

    @Override
    public int getTax() {
        return 50;
    }

    @Override
    public boolean isTaxPaid() {
        return taxPaid;
    }
}

class House extends Building {
    int flatCount;
    private int floorCount;

    public int getFloorCount() {
        return floorCount;
    }
}

class Penalty {
    public Penalty(int value) {
        this.value = value;
    }

    int value;

    @Override
    public String toString() {
        return "Penalty{" +
                "value=" + value +
                '}';
    }
}

class MyPenalty extends Penalty {

    public MyPenalty(int value) {
        super(value);
    }
}

public class Main {
    static Optional<MyPenalty> calculatePenalty(Building building) {
        if (building instanceof Taxed taxed) {
            return taxed.isTaxPaid() ? Optional.empty() : Optional.of(new MyPenalty(taxed.getTax() * 2));
        }
        return Optional.empty();
    }

    static int getSummaryCOVIDCases(District<? extends COVIDStatisticsProvider, ?> district) {
        return district.buildings.stream().map(COVIDStatisticsProvider::getCOVIDCases).reduce(Integer::sum).orElse(0);
    }

    static void aba(Function<Number, Void> a) {

    }

    public static void main(String[] args) {
//        District<Building> vesnyanka = new District<Building>()
//                .address("st.lol")
//                .addBuilding(new Office(false))
//                .addBuilding(new Shop(false))
//                .addBuilding(new Office(false))
//                .addBuilding(new Office(true));
//
//        vesnyanka.collectInfo(Main::calculatePenalty).forEach(System.out::println);

        OfficeDistrict officeDistrict = new OfficeDistrict()
                .withAddress("Mountain View, CA")
                .withCompany("Baranov inc.")
                .addBuilding(new RubinPlaza(false));

        officeDistrict.collectInfo(Main::calculatePenalty).forEach(System.out::println);

        officeDistrict.collectInfo((Office office) -> calculatePenalty(office));

        Function<Number, Void> a = (number -> null);
        Function<Integer, Void> b = (number -> null);

//        getSummaryCOVIDCases(District<COVIDStatisticsProvider>);
//
//
//        getSummaryCOVIDCases(District<Office>);
    }
}
