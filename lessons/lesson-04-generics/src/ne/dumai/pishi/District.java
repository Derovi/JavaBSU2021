package ne.dumai.pishi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class OfficeDistrict extends District<Office, OfficeDistrict> {
    private String company;

    public OfficeDistrict() {
    }

    public OfficeDistrict(String address, List<Office> buildings, String company) {
        super(address, buildings);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public OfficeDistrict withCompany(String company) {
        setCompany(company);
        return this;
    }
}

class ShopDistrict extends District<Shop, ShopDistrict> {
    String shop;
}

class ChinaTown extends District<House, ChinaTown> {
    Optional<Integer> getMaxFloorCount() {
        return buildings.stream()
                        .map(House::getFloorCount)
                        .max(Integer::compareTo);
    }
}

public class District<T extends Building, E extends District<T, E>> {
    public District() {
    }

    public District(String address, List<T> buildings) {
        this.address = address;
        this.buildings = buildings;
    }

    public E withAddress(String address) {
        this.address = address;
        return (E) this;
    }

    public E addBuilding(T newBuilding) {
        this.buildings.add(newBuilding);
        return (E) this;
    }

    public void setBuildings(List<T> buildings) {
        this.buildings = buildings;
    }

    public List<T> getBuildings() {
        return buildings;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    void a(T kek) {

    }

    <U> List<U> collectInfo(Function<T, Optional<U>> calculator) {
        return buildings.stream()
                .map(calculator)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private String address;
    protected List<T> buildings = new ArrayList<>();
}
