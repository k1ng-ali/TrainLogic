package org.example.trainlogic.MainPackages;
import java.util.UUID;

public class ID {
    private String ID;

    public ID(String prefix)
    {
        ID = prefix + "+" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }

    public String GetID(){
        return ID;
    }
}
