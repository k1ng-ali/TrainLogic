package org.example.trainlogic.MainPackages;

public class Container {
    private int size;
    private int mass;

    public Container(int mass, int size) {
        this.size = size;
        this.mass = mass;
    }

    public Container()
    {
        int choise;
        do {
            View.view("Размер: 20 фут, Вес: 30 тонн . . . . . . . . . . 1\n");
            View.view("Размер: 40 фут, Вес: 40 тонн . . . . . . . . . . 2\n");
            View.view("\nВыберите размер контейнера: ");
            choise = Controller.get_int();

            switch (choise) {
                case 1:
                    this.size = 20;
                    this.mass = 30000;
                    break;
                case 2:
                    this.size = 40;
                    this.mass = 40000;
                    break;
                default:
                    View.view("Введите целое число от 1 до 2 включительно!");
            }
        }while (choise != 1 && choise != 2);
    }
    public int GetMass(){
        return this.mass;
    }

    public int GetSize(){
        return this.size;
    }
}
