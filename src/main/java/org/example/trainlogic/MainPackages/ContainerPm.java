package org.example.trainlogic.MainPackages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ContainerPm extends Platform implements  SerializableEntity{
    Container container;

    public ContainerPm() {
        this.container = new Container();
        super.max_capacity = 1;
        super.capacity = 0;
        super.IsLoaded = false;
    }

    public ContainerPm (int num_platform, int capacity, int max_capacity, boolean IsLoaded) {
        super.max_capacity = max_capacity;
        super.capacity = capacity;
        super.num_platform = num_platform;
        super.IsLoaded = IsLoaded;
    }

    @Override
    public String GetTypeCargo() {
        return "Контейнер";
    }


    public Boolean load(Container container) {
        if (this.container.equals(container) && !super.IsLoaded){
            super.IsLoaded = true;
            super.capacity += container.GetMass();
            return true;
        }
        else return false;
    }

    public Boolean unload(Container container) {
        if (this.container.equals(container) && super.IsLoaded){
            super.IsLoaded = false;
            super.capacity -= container.GetMass();
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
        writer.println("ContainerPm");
        writer.println(super.num_platform);
        writer.println(super.max_capacity);
        writer.println(super.capacity);
        writer.println(super.IsLoaded);
        writer.println(container.GetMass());
        writer.println(container.GetSize());
    }

    @Override
    public void LoadFromFile(BufferedReader reader) throws IOException {
        super.num_platform = Integer.parseInt(reader.readLine());
        super.max_capacity = Integer.parseInt(reader.readLine());
        super.capacity = Integer.parseInt(reader.readLine());
        super.IsLoaded = Boolean.parseBoolean(reader.readLine());
        this.container = new Container(
                Integer.parseInt(reader.readLine()),
                Integer.parseInt(reader.readLine())
        );
    }
}
