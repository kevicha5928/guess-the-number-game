package woga.boga.console;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import woga.boga.Game;
import woga.boga.MessageGenerator;

import java.util.Scanner;

// needs component annotation because the app config is in another module
@Slf4j
@Component
public class ConsoleNumberGuess {

    // == Fields ==
    private final Game game;
    private final MessageGenerator messageGenerator;

    // == Constructors
    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("start() -> Container ready for use.");

        Scanner scanner = new Scanner(System.in);
        while(true){
            //current game state
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            // get next user input guess
            int guess = scanner.nextInt();
            scanner.nextLine();

            // update game state
            game.setGuess(guess);
            game.check();

            // check to see if game is done
            if (game.isGameLost()|| game.isGameWon()){
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again y/n?");

                String playAgainString = scanner.nextLine().trim();
                if(!playAgainString.equalsIgnoreCase("y")){
                    break;
                }
                game.reset();

            }
        }
    }
}
