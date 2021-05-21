package woga.boga.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import woga.boga.Game;
import woga.boga.MessageGenerator;

import java.util.Scanner;

// needs component annotation because the app config is in another module
@Component
public class ConsoleNumberGuess {

    // == Constants ==
    private static final Logger log = LoggerFactory.getLogger(ConsoleNumberGuess.class);

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
