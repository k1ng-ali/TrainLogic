package org.example.trainlogic.MainPackages;

public class Avtomobile {
    private String name;
    private int mass;

    public Avtomobile() {
        View.view("Введите модел автомобиля: ");
        name = Controller.getString();
        View.view("Введите масса автомобиля: ");
        mass = Controller.get_int();
    }

    public Avtomobile(String name, int mass) {
        this.name = name;
        this.mass = mass;
    }

    public String getName() {return name;};
    public int getMass() {return mass;}
}
