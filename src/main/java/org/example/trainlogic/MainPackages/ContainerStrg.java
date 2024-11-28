package org.example.trainlogic.MainPackages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class ContainerStrg extends Storage implements SerializableEntity{
    LinkedList<Container> containers=new LinkedList<Container>();

    public ContainerStrg() {
        View.view("Введите максимальный вместимость склада: ");
        super.max_capacity = Controller.get_int();
        super.capacity = 0;
    }

    public ContainerStrg(int num_strg, int max_capacity, int capacity) {
        super.num_storage = num_strg;
        super.capacity = capacity;
        super.max_capacity = max_capacity;
    }

    @Override
    public String GetTypeCargo() {
        return "Контейнер";
    }


    public Boolean load(Container container) {
        if (this.containers.size() < super.max_capacity){
            containers.add(container);
            super.capacity += container.GetMass();
            return true;
        }
        else return false;
    }

    public Boolean unload(Container container) {
        if (this.containers.contains(container)){
            containers.remove(container);
            super.capacity -= container.GetMass();
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
        writer.println("ContainerStrg");
        writer.println(super.num_storage);
        writer.println(super.max_capacity);
        writer.println(super.capacity);
        for (Container c : containers){
            writer.println(c.GetMass());
            writer.println(c.GetSize());
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
            int mass = Integer.parseInt(line);
            int size = Integer.parseInt(reader.readLine());
            containers.add(new Container(mass, size));
        }
    }
}
