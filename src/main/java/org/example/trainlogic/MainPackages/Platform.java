package org.example.trainlogic.MainPackages;

import java.util.LinkedList;

public abstract class Platform implements SerializableEntity{
    protected int num_platform;
    protected int capacity;
    protected int max_capacity;
    protected boolean IsLoaded;

    public Platform(int num_platform, int capacity, int max_capacity) {
        this.num_platform = num_platform;
        this.capacity = capacity;
        this.max_capacity = max_capacity;
    }
    public Platform() {}

    public abstract String GetTypeCargo();

    public int getCapacity(){
        return this.capacity;
    };

    public  int getMax_capacity(){
        return this.max_capacity;
    };

    public void setNum_platform(int num_platform){
        this.num_platform = num_platform;
    };
    public int getNum_platform(){
        return this.num_platform;
    };

    public void DisplayInfo(){};
}
