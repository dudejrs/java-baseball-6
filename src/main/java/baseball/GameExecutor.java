package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class GameExecutor {
    private BaseballGame game;
    private ConsoleInput consoleInput;
    private ConsoleLogger consoleLogger;

    public GameExecutor() {
        game = new BaseballGame(getRandomNumbers());
        consoleInput = new ConsoleInput();
        consoleLogger = new ConsoleLogger();
    }

    private int[] getRandomNumbers() {
        Set<Integer> result = new HashSet<>();

        while (result.size() < 3) {
            int number = Randoms.pickNumberInRange(1, 9);
            result.add(number);
        }

        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public void run() {
        game.sayHello(consoleLogger);
        while (true) {
            game.run(consoleInput, consoleLogger);
            consoleLogger.print("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.\n");
            String input = consoleInput.getInput();
            checkInput(input);
            if("2".equals(input)){
                break;
            }
        }
    }

    private void checkInput(String input){
        if(Pattern.matches("[12]",input))
            return;
        throw new IllegalArgumentException();
    }
}
