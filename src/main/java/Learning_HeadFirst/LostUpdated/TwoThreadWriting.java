package Learning_HeadFirst.LostUpdated;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TwoThreadWriting {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        Data data = new Data();
        threadPool.execute(() -> addLetterToData('a', data));
        threadPool.execute(() -> addLetterToData('A', data));
        threadPool.shutdown();
    }

    private static void addLetterToData(char letter, Data data) {
        for (int i = 0; i < 26; i++) {
            data.addLetter(letter++);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }
        }
        System.out.println(Thread.currentThread().getName() + data.getLetters());
        System.out.println(Thread.currentThread().getName() + " size = " + data.getLetters().size());
    }
}
