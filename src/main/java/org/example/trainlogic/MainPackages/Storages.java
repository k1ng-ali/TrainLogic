package org.example.trainlogic.MainPackages;

import java.util.LinkedList;

public class Storages{
    private LinkedList<Storage> storages = new LinkedList<>();

    public Storages() {}
    public Storages(LinkedList<Storage> storages) {
        this.storages = storages;
    }

    public LinkedList<Storage> getStorages() {
        return storages;
    }

    public void add_storage(Storage storage) {
        storages.add(storage);
    }

    public void remove_storage(Storage storage) {
        storages.remove(storage);
    }
}
