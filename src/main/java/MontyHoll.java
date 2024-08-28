import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Random;

@Getter
@Setter
public class MontyHoll {
    private static final int OPEN_DOORS = 3;
    private static final int DOOR_WINNER = 1;
    private final boolean win = true;

    private Random random;
    private HashMap<Integer, Boolean> resultHashMap;

    public MontyHoll() {
        random = new Random();
        resultHashMap = new HashMap<Integer, Boolean>();
    }

    public int openDoor(){
        return random.nextInt(OPEN_DOORS) + 1;
    }

    public int openComputer(int openDoorGamer) {
        int openComputer;
        do {
            openComputer = random.nextInt(OPEN_DOORS) + 1;
        } while (openComputer == openDoorGamer || openComputer == DOOR_WINNER);
        return openComputer;
    }

    public int switchDoor(int openDoorGamer, int openComputer) {
        int switchDoor;
        do {
            switchDoor = random.nextInt(OPEN_DOORS) + 1;
        } while (switchDoor == openDoorGamer || switchDoor == openComputer);
        return switchDoor;
    }

    public void statistics(int iteractions, HashMap<Integer, Boolean> resultHashMap ) {
        int countWinner = 0;
        for (Integer key : resultHashMap.keySet()) {
            if (resultHashMap.get(key) == win) {
                countWinner++;
            }
        }

        System.out.println("Вероятность выигрыша при смене двери составила: "
                + countWinner * 100.0 / iteractions + "%");
    }

    public void game(int iteractions) {


        for (int i = 0; i < iteractions; i++) {
            int openDoor = openDoor();
            int openComputerDoor = openComputer(openDoor);
            int switchDoor = switchDoor(openDoor, openComputerDoor);


            if (switchDoor == DOOR_WINNER) {
                resultHashMap.put(i, win);
            } else {
                resultHashMap.put(i, !win);
            }

        }

        statistics(iteractions, resultHashMap);
    }



}
