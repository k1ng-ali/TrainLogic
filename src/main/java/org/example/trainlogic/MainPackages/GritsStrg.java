package org.example.trainlogic.MainPackages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class GritsStrg extends Storage implements SerializableEntity{
    Grits grits;


    public GritsStrg() {
        this.grits = new Grits();
        View.view("Введите максимальный вместимость склада: ");
        super.max_capacity = Controller.get_int();
        super.capacity = 0;
    }

    public GritsStrg(int num_strg, int max_capacity, int capacity, String name, int mass) {
        super.num_storage = num_strg;
        super.capacity = capacity;
        super.max_capacity = max_capacity;
        grits = new Grits(name, mass);
    }

    @Override
    public String GetTypeCargo() {
        return grits.getName();
    }


    public Boolean load(Grits grits) {
        if (this.grits.getName().equalsIgnoreCase(grits.getName())&&
                super.max_capacity>=super.capacity + grits.getMass()) {
            this.grits.addMass(grits.getMass());
            super.capacity += grits.getMass();
            return true;
        }
        else return false;
    }

    public Boolean unload(Grits grits) {
        if (this.grits.getName().equalsIgnoreCase(grits.getName())&&
                0>=super.capacity - grits.getMass()) {
            this.grits.subMass(grits.getMass());
            super.capacity -= grits.getMass();
            return true;
        }
        else return false;
    }

    @Override
    public void DisplayInfo() {
        View.view("%-37s| %-33s| %-26s |%40s%n",
                super.num_storage,
                super.max_capacity,
                super.capacity,
                GetTypeCargo());
    }

    @Override
    public void SaveToFile(PrintWriter writer) {
        writer.println("GritsStrg");
        writer.println(super.num_storage);
        writer.println(super.max_capacity);
        writer.println(super.capacity);
        writer.println(grits.getName());
        writer.println(grits.getMass());
    }

    @Override
    public void LoadFromFile(BufferedReader reader) throws IOException {
        super.num_storage = Integer.parseInt(reader.readLine());
        super.max_capacity = Integer.parseInt(reader.readLine());
        super.capacity = Integer.parseInt(reader.readLine());

        grits = new Grits(
                reader.readLine(),
                Integer.parseInt(reader.readLine())
        );
    }
}
