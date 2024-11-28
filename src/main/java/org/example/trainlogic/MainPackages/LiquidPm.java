package org.example.trainlogic.MainPackages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LiquidPm extends Platform implements SerializableEntity{
    Liquid liquid;

    public LiquidPm() {
        this.liquid = new Liquid();
        View.view("Введите максимальный грузоподьемность платформы: ");
        super.max_capacity = Controller.get_int();
        super.capacity = 0;
    }

    public LiquidPm (int num_platform, int capacity, int max_capacity, boolean IsLoaded, String name, int mass) {
        super.max_capacity = max_capacity;
        super.capacity = capacity;
        super.num_platform = num_platform;
        super.IsLoaded = IsLoaded;
        liquid = new Liquid(name, mass);
    }

    @Override
    public String GetTypeCargo() {
        return liquid.getName();
    }


    public Boolean load(Liquid liquid) {
        if (this.liquid.getName().equalsIgnoreCase(liquid.getName())&&
                super.max_capacity>=super.capacity + liquid.getMass()) {
            this.liquid.addMass(liquid.getMass());
            super.capacity += liquid.getMass();
            return true;
        }
        else return false;
    }

    public Boolean unload(Liquid liquid) {
        if (this.liquid.getName().equalsIgnoreCase(liquid.getName())&&
                super.capacity - liquid.getMass()>=0) {
            this.liquid.subMass(liquid.getMass());
            super.capacity -= liquid.getMass();
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
        writer.println("LiquidPm");
        writer.println(super.num_platform);
        writer.println(super.max_capacity);
        writer.println(super.capacity);
        writer.println(super.IsLoaded);
        writer.println(liquid.getName());
        writer.println(liquid.getMass());
    }

    @Override
    public void LoadFromFile(BufferedReader reader) throws IOException {
        super.num_platform = Integer.parseInt(reader.readLine());
        super.max_capacity = Integer.parseInt(reader.readLine());
        super.capacity = Integer.parseInt(reader.readLine());
        super.IsLoaded = Boolean.parseBoolean(reader.readLine());
       liquid = new Liquid(
                reader.readLine(),
                Integer.parseInt(reader.readLine())
        );

    }
}
