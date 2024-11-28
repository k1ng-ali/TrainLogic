package org.example.trainlogic.MainPackages;

import java.io.*;
import java.util.LinkedList;

public class FileManeger {
    public static <T extends SerializableEntity> void SaveAll(LinkedList<T> elements, String fileName,
                                                              boolean append) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, append))) {
            for (T element : elements) {
                element.SaveToFile(writer);
                writer.println("---");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static LinkedList<SerializableEntity> LoadAll(String fileName) throws IOException {
        LinkedList<SerializableEntity> elements = new LinkedList<>();
        try (BufferedReader reader  = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                SerializableEntity element = null;
                switch (line) {
                    case "AvtomobilePm":
                        element = new AvtomobilePm(0,0,0, false);
                        break;
                    case "AvtomobileStrg":
                        element = new AvtomobileStrg(0,0,0);
                        break;
                    case "ContainerPm":
                        element = new ContainerPm(0,0,0,false);
                        break;
                    case "ContainerStrg":
                        element = new ContainerStrg(0,0,0);
                        break;
                    case "GritsPm":
                        element = new GritsPm(0,0,0,false, null, 0);
                        break;
                    case "GritsStrg":
                        element = new GritsStrg(0,0,0,null,0);
                        break;
                    case "LiquidPm":
                        element = new LiquidPm(0,0,0,false, null, 0);
                        break;
                    case "LiquidStrg":
                        element = new LiquidStrg(0,0,0, null, 0);
                        break;
                }
                if (element != null) {
                    element.LoadFromFile(reader);
                    elements.add(element);
                }
                reader.readLine();
            }
        }
        return elements;
    }
}
