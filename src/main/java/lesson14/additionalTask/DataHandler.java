package lesson14.additionalTask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class DataHandler {

    public String formInHashSet(LinkedList<String> names){
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.addAll(names);
        return formOutput(set);
    }

    public String checkName(LinkedList<String> names){
        LinkedList<String> repeatNames =  findDuplicates(names);
        LinkedHashSet<String> uniqueDuplicates = new LinkedHashSet<>(repeatNames);
        return formOutput(uniqueDuplicates);
    }

    public String formOutput(LinkedList<String> names){
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < names.size(); i++){
            String temp = names.get(i);
            output.append(temp);
            if (i < names.size() - 1) {
                output.append(", ");
            }
        }
        return output.toString();
    }

    public String formOutput(HashSet<String> names){
        StringBuilder output = new StringBuilder();
        names.forEach(item -> output.append(item).append(" "));
        return output.toString();
    }

    public static LinkedList<String> findDuplicates(LinkedList<String> list) {
        LinkedList<String> duplicates = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).equals(name)) {
                    count++;
                }
            }
            if (count > 1) {
                duplicates.addFirst(name);
            }
        }
        return duplicates;
    }

}
