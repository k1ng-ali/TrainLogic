package org.example.trainlogic.MainPackages;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LiquidStrg extends Storage implements SerializableEntity{
    Liquid liquid;

    public LiquidStrg() {
        this.liquid = new Liquid();
        View.view("Введите максимальный вместимость склада: ");
        super.max_capacity = Controller.get_int();
        super.capacity = 0;
    }

    public LiquidStrg(int num_strg, int max_capacity, int capacity, String name, int mass) {
        super.num_storage = num_strg;
        super.capacity = capacity;
        super.max_capacity = max_capacity;
        liquid = new Liquid(name, mass);
    }

    @Override
    public String GetTypeCargo() {
        return liquid.getName();
    }

    public String GetTypeLiquid()
    {
        return this.liquid.getName();
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
                super.num_storage,
                super.max_capacity,
                super.capacity,
                GetTypeCargo());
    }


    @Override
    public void SaveToFile(PrintWriter writer) {
        writer.println("LiquidStrg");
        writer.println(super.num_storage);
        writer.println(super.max_capacity);
        writer.println(super.capacity);
        writer.println(liquid.getName());
        writer.println(liquid.getMass());
    }

    @Override
    public void LoadFromFile(BufferedReader reader) throws IOException {
        super.num_storage = Integer.parseInt(reader.readLine());
        super.max_capacity = Integer.parseInt(reader.readLine());
        super.capacity = Integer.parseInt(reader.readLine());

        liquid = new Liquid(
                reader.readLine(),
                Integer.parseInt(reader.readLine())
        );
    }
}
