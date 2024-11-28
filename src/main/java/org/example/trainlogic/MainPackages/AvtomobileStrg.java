package org.example.trainlogic.MainPackages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class AvtomobileStrg extends Storage implements SerializableEntity{
    LinkedList<Avtomobile> avtomobiles = new LinkedList<Avtomobile>();

    public AvtomobileStrg() {
        View.view("Введите максимальный вместимость склада: ");
        super.max_capacity = Controller.get_int();
        super.capacity = 0;
    }

    public AvtomobileStrg(int num_strg, int max_capacity, int capacity) {
        super.num_storage = num_strg;
        super.capacity = capacity;
        super.max_capacity = max_capacity;
    }

    @Override
    public String GetTypeCargo() {
        return "Автомобиль";
    }

    public LinkedList<Avtomobile> getAvtomobiles() {
        return avtomobiles;
    }

    boolean load(Avtomobile avtomobile) {
        if (this.avtomobiles.size() < super.max_capacity) {
            this.avtomobiles.add(avtomobile);
            super.capacity += 1;
            return true;
        }
        else return false;
    }

    public Boolean unload(Avtomobile avtomobile) {
        if (this.avtomobiles.contains(avtomobile)) {
            this.avtomobiles.remove(avtomobile);
            super.capacity -= 1;
            return true;
        }
        else return false;
    }


    public void ShowAvtomobiles() {
        View.view("%-15s| %-37s |%40s%n",
                "№",
                "Модель автомобилья",
                "Масса автомобился");
        int i = 1;
        for (Avtomobile avtomobile : avtomobiles) {
            View.view("%-15s| %-37s |%40s%n",
                    i, avtomobile.getName(),
                    avtomobile.getMass());
            i++;
        }
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
        writer.println("AvtomobileStrg");
        writer.println(super.num_storage);
        writer.println(super.max_capacity);
        writer.println(super.capacity);
        for (Avtomobile avtomobile : avtomobiles) {
            writer.println(avtomobile.getName());
            writer.println(avtomobile.getMass());
        }
        writer.println("end");
    }

    @Override
    public void LoadFromFile(BufferedReader reader) throws IOException {
        super.num_storage = Integer.parseInt(reader.readLine());
        super.max_capacity = Integer.parseInt(reader.readLine());
        super.capacity = Integer.parseInt(reader.readLine());
        String line;
        while ((line = reader.readLine()).equals("end")) {
            String name = line;
            int mass = Integer.parseInt(line);
            avtomobiles.add(new Avtomobile(name, mass));
        }
    }
}
