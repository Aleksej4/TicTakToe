package Game.utils;

import java.util.function.Predicate;

public class CorrectSizeOfBoard implements Predicate<String> {

    @Override
    public boolean test(String number) {
        try {
            return Integer.parseInt(number) >= 3;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
