package lesson14.additionalTask;

import java.util.LinkedList;
import java.util.Random;

public class DataRepository {
    public String [] names = {"Anna", "Bob", "Tom", "Sara", "John", "Eva", "Peter", "Emma", "Nick", "Nika"};

    public LinkedList<String> getData() {
        LinkedList<String> data = new LinkedList<>();
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            int randomIndex = rand.nextInt(names.length);
            data.offer(names[randomIndex]);
        }
        return data;
    }

}
