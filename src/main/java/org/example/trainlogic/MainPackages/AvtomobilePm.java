package org.example.trainlogic.MainPackages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class AvtomobilePm extends Platform implements SerializableEntity{
    LinkedList<Avtomobile> avtomobiles = new LinkedList<Avtomobile>();

    public AvtomobilePm() {
        View.view("Введите максимальный вместимость платформы: ");
        super.max_capacity = Controller.get_int();
        super.capacity = 0;
    }

    public AvtomobilePm (int num_platform, int capacity, int max_capacity, boolean IsLoaded) {
        super.max_capacity = max_capacity;
        super.capacity = capacity;
        super.num_platform = num_platform;
        super.IsLoaded = IsLoaded;
    }

    @Override
    public String GetTypeCargo() {
        return "Автомобиль";
    }


    public LinkedList<Avtomobile> getAvtomobiles() {
        return avtomobiles;
    }


    public Boolean load(Avtomobile avtomobile) {
        if (this.avtomobiles.size() < super.max_capacity) {
            this.avtomobiles.add(avtomobile);
            super.capacity += 1;
            return true;
        }
        return false;
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
                super.num_platform,
                super.max_capacity,
                super.capacity,
                GetTypeCargo());
    }

    @Override
    public void SaveToFile(PrintWriter writer) {
        writer.println("AvtomobilePm");
        writer.println(super.num_platform);
        writer.println(super.max_capacity);
        writer.println(super.capacity);
        writer.println(super.IsLoaded);
        for (Avtomobile avtomobile : avtomobiles) {
            writer.println(avtomobile.getName());
            writer.println(avtomobile.getMass());
        }
        writer.println("end");
    }

    @Override
    public void LoadFromFile(BufferedReader reader) throws IOException {
        super.num_platform = Integer.parseInt(reader.readLine());
        super.max_capacity = Integer.parseInt(reader.readLine());
        super.capacity = Integer.parseInt(reader.readLine());
        super.IsLoaded = Boolean.parseBoolean(reader.readLine());
        String line;
        while ((line = reader.readLine()).equals("end")) {
            String name = line;
            int mass = Integer.parseInt(reader.readLine());
            avtomobiles.add(new Avtomobile(name, mass));
        }
    }
}
