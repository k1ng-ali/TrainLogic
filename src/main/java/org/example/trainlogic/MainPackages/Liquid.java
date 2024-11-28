package org.example.trainlogic.MainPackages;

public class Liquid {
    private String name;
    private int mass;

    public Liquid(String name, int mass) {
        this.name = name;
        this.mass = mass;
    }

    public Liquid(){
        int choise;
        do {
            View.view("Нефть. . . . . . . . . . . . . . . . . . . . 1\n");
            View.view("Дизельное топливо. . . . . . . . . . . . . . 2\n");
            View.view("Мазут. . . . . . . . . . . . . . . . . . . . 3\n");
            View.view("\nВыберите тип груза: ");
            choise = Controller.get_int();

            switch (choise){
                case 1:
                    this.name = "Нефть";
                    break;
                case 2:
                    this.name = "Дизельное топливо";
                    break;
                case 3:
                    this.name = "Мазут";
                    break;
                default:
                    View.view("Ощибка! Введите от 1 до 3 включительно!");
            }
        }while (choise != 1 && choise != 3 && choise != 2);
    }
    public String getName() {
        return name;
    }
    public int getMass() {
        return mass;
    }
    public void setMass(int mass) {
        this.mass = mass;
    }
    public void addMass(int mass){
        this.mass += mass;
    }
    public void subMass(int mass){
        this.mass -= mass;
    }
}
