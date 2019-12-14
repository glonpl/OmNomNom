package p.l.omnomnom.converter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Measure {
    private String nazwa;
    private double przelicznik;

    private Measure(String nazwa, double przelicznik) {
        this.nazwa = nazwa;
        this.przelicznik = przelicznik;
    }

    public Measure() {
    }

    static List getWeightList() {
        List<Measure> weightList = new ArrayList<>();
        weightList.add(new Measure("g", 1));
        weightList.add(new Measure("dag", 10));
        weightList.add(new Measure("kg", 1000));
        weightList.add(new Measure("oz", 28.35));
        weightList.add(new Measure("lb", 453.6));
        return weightList;
    }

    static List getVelocityList() {
        List<Measure> velocityList = new ArrayList<>();
        velocityList.add(new Measure("ml", 1));
        velocityList.add(new Measure("dl", 100));
        velocityList.add(new Measure("l", 1000));
        velocityList.add(new Measure("cup", 250));
        velocityList.add(new Measure("tablespoon", 15));
        velocityList.add(new Measure("teaspoon", 5));
        velocityList.add(new Measure("pt", 473.2));
        velocityList.add(new Measure("fl oz", 29.57));
        return velocityList;
    }

    String getNazwa() {
        return nazwa;
    }

    double getPrzelicznik() {
        return przelicznik;
    }

    @NonNull
    @Override
    public String toString() {
        return getNazwa();
    }
}
