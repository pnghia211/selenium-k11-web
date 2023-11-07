package Learning_HeadFirst.LostUpdated;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Data {
    private final  List<String> letters = new CopyOnWriteArrayList<>();

    public List<String> getLetters (){
        return letters;
    }

    public void  addLetter (char letter) {
        letters.add(String.valueOf(letter));
    }
}
