package org.example.trainlogic.MainPackages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class GritsPm extends Platform implements SerializableEntity{
    Grits grits;


    public GritsPm() {
        this.grits = new Grits();
        View.view("Введите максимальный грузоподьемность платформы: ");
        super.max_capacity = Controller.get_int();
        super.capacity = 0;
    }

    public GritsPm (int num_platform, int capacity, int max_capacity, boolean IsLoaded, String name, int mass) {
        super.max_capacity = max_capacity;
        super.capacity = capacity;
        super.num_platform = num_platform;
        super.IsLoaded = IsLoaded;
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
                super.num_platform,
                super.max_capacity,
                super.capacity,
                GetTypeCargo());
    }


    @Override
    public void SaveToFile(PrintWriter writer) {
        writer.println("GritsPm");
        writer.println(super.num_platform);
        writer.println(super.max_capacity);
        writer.println(super.capacity);
        writer.println(super.IsLoaded);
        writer.println(grits.getName());
        writer.println(grits.getMass());
    }

    @Override
    public void LoadFromFile(BufferedReader reader) throws IOException {
        super.num_platform = Integer.parseInt(reader.readLine());
        super.max_capacity = Integer.parseInt(reader.readLine());
        super.capacity = Integer.parseInt(reader.readLine());
        super.IsLoaded = Boolean.parseBoolean(reader.readLine());
        grits = new Grits(
                reader.readLine(),
                Integer.parseInt(reader.readLine())
        );
    }
}
