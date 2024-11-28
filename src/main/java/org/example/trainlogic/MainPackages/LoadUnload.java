package org.example.trainlogic.MainPackages;


public class LoadUnload {
    public static boolean load_plt(Train train, Storages storages, boolean debugMode)
    {
        boolean f = false;
        int choise;
        do {
            ViewInfo.view_type_platforms();
            View.view("Выберите тип груза: ");
            choise = Controller.get_int();
            int numPm, numStrg;

            switch (choise){
                case 1:
                    Liquid liquid = new Liquid();
                    View.view("\nДоступные склады: \n");
                    for (Storage storage: storages.getStorages()){
                        if (liquid.getName().equalsIgnoreCase(storage.GetTypeCargo())){
                            storage.DisplayInfo();
                        }
                    }

                    View.view("\nДоступные платформы: \n");
                    for (Platform platform: train.getPlatforms()){
                        if (liquid.getName().equalsIgnoreCase(platform.GetTypeCargo())){
                            platform.DisplayInfo();
                        }
                    }

                    do {
                        View.view("Введите номер платформы: ");
                        numPm = Controller.get_int();
                        View.view("Введите номер склада: ");
                        numStrg = Controller.get_int();
                        View.view("Введите масса груза: ");
                        liquid.setMass(Controller.get_int());
                        try {
                            for (Platform platform : train.getPlatforms()) {
                                if (platform.getNum_platform() == numPm) {
                                    if (platform instanceof LiquidPm) {
                                        ((LiquidPm) platform).load(liquid);
                                    }
                                }
                            }
                            for (Storage storage: storages.getStorages()){
                                if (storage.getNum_storage() == numStrg) {
                                    if (storage instanceof LiquidStrg){
                                        ((LiquidStrg) storage).unload(liquid);
                                    }
                                }
                            }
                            if (debugMode) LogMode.LogWrite("Груз " + liquid.getName() + " массой "+ liquid.getMass() + " загружено на платформу " + numPm + " из склада " + numStrg);
                            break;
                        }
                        catch (Exception e){
                            View.view("Ощибка! Повторите попытку!");
                        }
                    }while (true);
                    break;
                case 2:
                    Grits grits = new Grits();
                    View.view("\nДоступные склады: \n");
                    for (Storage storage: storages.getStorages()){
                        if (grits.getName().equalsIgnoreCase(storage.GetTypeCargo())){
                            storage.DisplayInfo();
                        }
                    }

                    View.view("\nДоступные платформы: \n");
                    for (Platform platform: train.getPlatforms()){
                        if (grits.getName().equalsIgnoreCase(platform.GetTypeCargo())){
                            platform.DisplayInfo();
                        }
                    }

                    do {
                        View.view("Введите номер платформы: ");
                        numPm = Controller.get_int();
                        View.view("Введите номер склада: ");
                        numStrg = Controller.get_int();
                        View.view("Введите масса груза: ");
                        grits.setMass(Controller.get_int());
                        try
                        {
                            for (Platform platform : train.getPlatforms()) {
                                if (platform.getNum_platform() == numPm) {
                                    if (platform instanceof GritsPm) {
                                        ((GritsPm) platform).load(grits);
                                    }
                                }
                            }
                            for (Storage storage: storages.getStorages()){
                                if (storage.getNum_storage() == numStrg) {
                                    if (storage instanceof GritsStrg) {
                                        ((GritsStrg) storage).unload(grits);
                                    }
                                }
                            }
                            if (debugMode) LogMode.LogWrite("Груз " + grits.getName() + " массой "+ grits.getMass() + " загружено на платформу " + numPm + " из склада " + numStrg);
                            break;
                        }
                        catch (Exception e) {
                            View.view("Ощибка! повторите попытку!");
                        }
                    }while (true);
                    break;
                case 3:
                    Container container = new Container();
                    String typeCargo = "Контейнер";
                    View.view("\nДоступные склады: \n");
                    for (Storage storage: storages.getStorages()){
                        if (typeCargo.equalsIgnoreCase(storage.GetTypeCargo())){
                            storage.DisplayInfo();
                        }
                    }

                    View.view("\nДоступные платформы: \n");
                    for (Platform platform: train.getPlatforms()){
                        if (typeCargo.equalsIgnoreCase(platform.GetTypeCargo())){
                            platform.DisplayInfo();
                        }
                    }

                    do {
                        View.view("Введите номер платформы: ");
                        numPm = Controller.get_int();
                        View.view("Введите номер склада: ");
                        numStrg = Controller.get_int();
                        try
                        {
                            for (Platform platform : train.getPlatforms()) {
                                if (platform.getNum_platform() == numPm) {
                                    if (platform instanceof ContainerPm) {
                                        ((ContainerPm) platform).load(container);
                                    }
                                }
                            }
                            for (Storage storage: storages.getStorages()){
                                if (storage.getNum_storage() == numStrg) {
                                    if (storage instanceof ContainerStrg) {
                                        ((ContainerStrg) storage).unload(container);
                                    }
                                }
                            }
                            if (debugMode) LogMode.LogWrite("Груз " + "Контейнер" + " массой "+container.GetMass() + " загружено на платформу " + numPm + " из склада " + numStrg);
                            break;
                        }
                        catch (Exception e) {
                            View.view("Ощибка! повторите попытку!");
                        }
                    }while (true);
                    break;
                case 4:
                    boolean flag = false;
                    View.view("\nДоступные склады: \n");
                    for (Storage storage: storages.getStorages()){
                        if (storage instanceof AvtomobileStrg){
                            storage.DisplayInfo();
                        }
                    }

                    View.view("\nДоступные платформы: \n");
                    for (Platform platform: train.getPlatforms()){
                        if (platform instanceof AvtomobilePm){
                            platform.DisplayInfo();
                        }
                    }
                    Avtomobile avtomobile;
                    String typeCargo1 = "Автомобиль";


                    do {
                        View.view("Введите номер платформы: ");
                        numPm = Controller.get_int();
                        View.view("Введите номер склада: ");
                        numStrg = Controller.get_int();
                        for (Storage storage: storages.getStorages()){
                            if (storage.getNum_storage() == numStrg) {
                                if (storage instanceof AvtomobileStrg) {
                                    AvtomobileStrg avtomobileStrg = (AvtomobileStrg) storage;
                                    View.view("\nДоступные автомобили: \n");
                                    avtomobileStrg.ShowAvtomobiles();
                                    View.view("\"Введите номер автомобиля\"");
                                    int num_avto = Controller.get_int();
                                    Avtomobile avtomobile1 = avtomobileStrg.getAvtomobiles().get(num_avto - 1);
                                    try {
                                        avtomobileStrg.unload(avtomobile1);
                                        for (Platform platform: train.getPlatforms()){
                                            if (platform.getNum_platform() == numPm) {
                                                if (platform instanceof AvtomobilePm) {
                                                    AvtomobilePm avtomobilePm = (AvtomobilePm) platform;
                                                    try {
                                                        avtomobilePm.load(avtomobile1);
                                                        if (debugMode) LogMode.LogWrite("Автомобиль " + avtomobile1.getName() + "загружено в платформу " + avtomobilePm.getNum_platform() + " Из склада " + avtomobileStrg.getNum_storage());
                                                        flag = true;
                                                    }
                                                    catch (Exception e) {
                                                        flag = false;
                                                        View.view("Ощибка! автомабил не загружено на платформу!");
                                                    }
                                                }
                                            }
                                        }
                                        flag = true;
                                    }
                                    catch (Exception e) {
                                        flag = false;
                                        View.view("Ощибка автомобил не нащлось!");
                                    }
                                }
                            }
                        }
                        if(flag){break;}
                    }while (true);
                    break;
                case 5:
                    View.view("Выход в меню...");
                    break;
                default:
                    View.view("Введите пожалуйста целое число от 1 до 5 включительно");
            }
        }while (choise != 5);
        return f;
    }


    public static boolean unload_plt(Train train, Storages storages, boolean debugMode){
        boolean f = false;
        int choise;
        do {
            ViewInfo.view_type_platforms();
            View.view("Выберите тип груза: ");
            choise = Controller.get_int();
            int numPm, numStrg;

            switch (choise){
                case 1:
                    Liquid liquid = new Liquid();
                    View.view("\nДоступные склады: \n");
                    for (Storage storage: storages.getStorages()){
                        if (liquid.getName().equalsIgnoreCase(storage.GetTypeCargo())){
                            storage.DisplayInfo();
                        }
                    }

                    View.view("\nДоступные платформы: \n");
                    for (Platform platform: train.getPlatforms()){
                        if (liquid.getName().equalsIgnoreCase(platform.GetTypeCargo())){
                            platform.DisplayInfo();
                        }
                    }

                    do {
                        View.view("Введите номер платформы: ");
                        numPm = Controller.get_int();
                        View.view("Введите номер склада: ");
                        numStrg = Controller.get_int();
                        View.view("Введите масса груза: ");
                        liquid.setMass(Controller.get_int());
                        try {
                            for (Platform platform : train.getPlatforms()) {
                                if (platform.getNum_platform() == numPm) {
                                    if (platform instanceof LiquidPm) {
                                        ((LiquidPm) platform).unload(liquid);
                                    }
                                }
                            }
                            for (Storage storage: storages.getStorages()){
                                if (storage.getNum_storage() == numStrg) {
                                    if (storage instanceof LiquidStrg){
                                        ((LiquidStrg) storage).load(liquid);
                                    }
                                }
                            }
                            if (debugMode) LogMode.LogWrite("Груз " + liquid.getName() + " массой "+ liquid.getMass() + " загружено из платформы " + numPm + " на склад " + numStrg);
                            break;
                        }
                        catch (Exception e){
                            View.view("Ощибка! Повторите попытку!");
                        }
                    }while (true);
                    break;
                case 2:
                    Grits grits = new Grits();
                    View.view("\nДоступные склады: \n");
                    for (Storage storage: storages.getStorages()){
                        if (grits.getName().equalsIgnoreCase(storage.GetTypeCargo())){
                            storage.DisplayInfo();
                        }
                    }

                    View.view("\nДоступные платформы: \n");
                    for (Platform platform: train.getPlatforms()){
                        if (grits.getName().equalsIgnoreCase(platform.GetTypeCargo())){
                            platform.DisplayInfo();
                        }
                    }

                    do {
                        View.view("Введите номер платформы: ");
                        numPm = Controller.get_int();
                        View.view("Введите номер склада: ");
                        numStrg = Controller.get_int();
                        View.view("Введите масса груза: ");
                        grits.setMass(Controller.get_int());
                        try
                        {
                            for (Platform platform : train.getPlatforms()) {
                                if (platform.getNum_platform() == numPm) {
                                    if (platform instanceof GritsPm) {
                                        ((GritsPm) platform).unload(grits);
                                    }
                                }
                            }
                            for (Storage storage: storages.getStorages()){
                                if (storage.getNum_storage() == numStrg) {
                                    if (storage instanceof GritsStrg) {
                                        ((GritsStrg) storage).load(grits);
                                    }
                                }
                            }
                            if (debugMode) LogMode.LogWrite("Груз " + grits.getName() + " массой "+ grits.getMass() + " загружено из платформы " + numPm + " на склад " + numStrg);
                            break;
                        }
                        catch (Exception e) {
                            View.view("Ощибка! повторите попытку!");
                        }
                    }while (true);
                    break;
                case 3:
                    Container container = new Container();
                    String typeCargo = "Контейнер";
                    View.view("\nДоступные склады: \n");
                    for (Storage storage: storages.getStorages()){
                        if (typeCargo.equalsIgnoreCase(storage.GetTypeCargo())){
                            storage.DisplayInfo();
                        }
                    }

                    View.view("\nДоступные платформы: \n");
                    for (Platform platform: train.getPlatforms()){
                        if (typeCargo.equalsIgnoreCase(platform.GetTypeCargo())){
                            platform.DisplayInfo();
                        }
                    }

                    do {
                        View.view("Введите номер платформы: ");
                        numPm = Controller.get_int();
                        View.view("Введите номер склада: ");
                        numStrg = Controller.get_int();
                        try
                        {
                            for (Platform platform : train.getPlatforms()) {
                                if (platform.getNum_platform() == numPm) {
                                    if (platform instanceof ContainerPm) {
                                        ((ContainerPm) platform).unload(container);
                                    }
                                }
                            }
                            for (Storage storage: storages.getStorages()){
                                if (storage.getNum_storage() == numStrg) {
                                    if (storage instanceof ContainerStrg) {
                                        ((ContainerStrg) storage).load(container);
                                    }
                                }
                            }
                            if (debugMode) LogMode.LogWrite("Груз " + "Контейнер" + " массой "+container.GetMass() + " загружено из платформы " + numPm + " ны склад " + numStrg);
                            break;
                        }
                        catch (Exception e) {
                            View.view("Ощибка! повторите попытку!");
                        }
                    }while (true);
                    break;
                case 4:
                    View.view("\nДоступные склады: \n");
                    for (Storage storage: storages.getStorages()){
                        if (storage instanceof AvtomobileStrg){
                            storage.DisplayInfo();
                        }
                    }

                    View.view("\nДоступные платформы: \n");
                    for (Platform platform: train.getPlatforms()){
                        if (platform instanceof AvtomobilePm){
                            platform.DisplayInfo();
                        }
                    }
                    Avtomobile avtomobile;
                    String typeCargo1 = "Автомобиль";

                    do {
                        boolean flag = false;
                        View.view("Введите номер платформы: ");
                        numPm = Controller.get_int();
                        View.view("Введите номер склада: ");
                        numStrg = Controller.get_int();
                        for (Platform platform: train.getPlatforms()){
                            if (platform.getNum_platform() == numPm) {
                                if (platform instanceof AvtomobilePm) {
                                    AvtomobilePm avtomobilePm = (AvtomobilePm) platform;
                                    View.view("\nДоступные автомобили: \n");
                                    avtomobilePm.ShowAvtomobiles();
                                    View.view("\"Введите номер автомобиля\"");
                                    int num_avto = Controller.get_int();
                                    Avtomobile avtomobile1 = avtomobilePm.getAvtomobiles().get(num_avto-1);
                                    try {
                                        avtomobilePm.unload(avtomobile1);
                                        for (Storage storage: storages.getStorages()){
                                            if (storage.getNum_storage() == numStrg) {
                                                if (storage instanceof AvtomobileStrg) {
                                                    AvtomobileStrg avtomobileStrg = (AvtomobileStrg) storage;
                                                    try{
                                                        avtomobileStrg.load(avtomobile1);
                                                        if (debugMode) LogMode.LogWrite("Автомобиль " + avtomobile1.getName() + "загружено из платформы " + avtomobilePm.getNum_platform() + " на склад " + avtomobileStrg.getNum_storage());
                                                        flag = true;
                                                    }
                                                    catch (Exception e) {
                                                        flag = false;
                                                        View.view("Ощибка! автомабил не загружено на склад!");
                                                    }
                                                }
                                            }
                                        }
                                        flag = true;
                                    }
                                    catch (Exception e) {
                                        flag = false;
                                        View.view("Ощибка автомобил не нащлось!");
                                    }

                                }
                            }
                        }
                        if (flag){break;};
                    }while (true);
                    break;
                case 5:
                    View.view("Выход в меню...");
                    break;
                default:
                    View.view("Введите пожалуйста целое число от 1 до 5 включительно");
            }
        }while (choise != 5);
        return f;
    }
}
