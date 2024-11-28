package org.example.trainlogic.MainPackages;

public abstract class Storage implements SerializableEntity{
    protected int num_storage;
    protected int capacity;
    protected int max_capacity;


    public Storage() {}

    public abstract String GetTypeCargo();

    public  void setCapacity(int capacity){
        this.capacity = capacity;
    };
    public  int getCapacity(){
        return capacity;
    };

    public  void setMax_capacity(int max_capacity){
        this.max_capacity = max_capacity;
    };
    public  int getMax_capacity(){
        return max_capacity;
    };

    public  void setNum_storage(int num_storage){
        this.num_storage = num_storage;
    };
    public  int getNum_storage(){
        return num_storage;
    };

    public void DisplayInfo(){};
}
