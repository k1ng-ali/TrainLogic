package org.example.trainlogic.MainPackages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface SerializableEntity {
    void SaveToFile(PrintWriter writer);
    void LoadFromFile(BufferedReader reader) throws IOException;
}
