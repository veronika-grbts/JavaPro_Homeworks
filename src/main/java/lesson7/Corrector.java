package lesson7;

public class Corrector {

    public String handleData(String[] strs){
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        char b = 98;
        for (String str : strs) {
            if (str.contains("b")) {
                str = str.replace("b", "o");
            }
            count ++;
            stringBuilder.append(count).append(") ").append(str).append("\n");
        }
        return String.valueOf(stringBuilder);
    }
}
