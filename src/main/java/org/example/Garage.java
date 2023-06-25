package org.example;

import java.util.ArrayList;
import java.util.List;

public class Garage {
    private static Garage INSTANCE;
    private List<Car> carList = new ArrayList();

    private Garage() {
        carList.add(new Car(123,"bmw",3594.9, 2));
        carList.add(new Car(3634,"audi",38346.9, 1));
        carList.add(new Car(135,"ferrari",130000.4 , 10));
    }

    public static Garage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Garage();
        }
        return INSTANCE;
    }

    public String getAll() {
        String s = "<tr>";

        for (Car car:
                carList) {
            s += (
                    "<td>" + car.getSeries() + "</td>" +
                            "<td>" + car.getBrand() + "</td>" +
                            "<td>" + car.getCost() + "</td>" +
                            "<td>" + car.getQty() + "</td>" +
                            "</tr>\n"
            );
        }

        return s;
    }

    public String getExpensive() {
        Car mostExpensive = null;
        for (int i = 0; i < carList.size()-1; i++) {
            Car car1 = carList.get(i);
            Car car2 = carList.get(i+1);

            if (car1.getCost() > car2.getCost()) {
                mostExpensive = car1;
            } else {
                mostExpensive = car2;
            }
        }

        String s = "<tr>"+
                "<td>" + mostExpensive.getSeries() + "</td>" +
                "<td>" + mostExpensive.getBrand() + "</td>" +
                "<td>" + mostExpensive.getCost() + "</td>" +
                "<td>" + mostExpensive.getQty() + "</td>" +
                "</tr>\n";

        return s;
    }

    public String getSorted() {
        List<Car> listCopy = new ArrayList<>();
        listCopy.addAll(carList);

        listCopy.sort((o1, o2) -> {
            return o1.getBrand().compareTo(o2.getBrand());
        });

        String s = "<tr>";

        for (Car car:
                listCopy) {
            s += (
                    "<td>" + car.getSeries() + "</td>" +
                            "<td>" + car.getBrand() + "</td>" +
                            "<td>" + car.getCost() + "</td>" +
                            "<td>" + car.getQty() + "</td>" +
                            "</tr>\n"
            );
        }

        return s;
    }
}