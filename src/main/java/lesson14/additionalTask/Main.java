package lesson14.additionalTask;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        DataRepository repo = new DataRepository();
        DataHandler handler = new DataHandler();

        LinkedList<String> names = repo.getData();

        System.out.println("Початковий LinkedList");
        System.out.println(handler.formOutput(names));
        System.out.println();
        System.out.println("Унікальні імена в початковому порядку появи");
        System.out.println(handler.formInHashSet(names));
        System.out.println();
        System.out.println("Зворотний список унікальних повторюваних імен");
        System.out.println(handler.checkName(names));

    }
}
