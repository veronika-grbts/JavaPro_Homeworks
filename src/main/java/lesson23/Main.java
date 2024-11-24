package lesson23;

import generate.PasswordGenerated;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        PasswordGenerated passwordGenerated = new PasswordGenerated();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println("Згенерований пароль: " + passwordGenerated.generatePassword(rand.nextInt(15)));
        }
    }
}
