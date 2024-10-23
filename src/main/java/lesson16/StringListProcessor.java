package lesson16;

public class StringListProcessor {

     public static int countUppercase(String s){
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}
