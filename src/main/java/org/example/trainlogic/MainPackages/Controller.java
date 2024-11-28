package org.example.trainlogic.MainPackages;
import org.example.trainlogic.MainPackages.View;

import java.util.Scanner;

public class Controller {

    public static String getString(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static int get_int(){
        int input_int;
        do {
            try {
                input_int = Integer.parseInt(getString());
                break;
            }
            catch (NumberFormatException e) {
                View.view("Неправильный ввод! Введите заново: ");
            }
        }while (true);
        return input_int;
    }
}
