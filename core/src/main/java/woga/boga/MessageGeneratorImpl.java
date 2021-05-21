package woga.boga;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageGeneratorImpl implements MessageGenerator{
    // == Constants ==
    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    // == Fields ==
    private final Game game;

    // == Constructors ==
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    // == init methods ==
    @PostConstruct
    public void init(){
        log.info("The game is {}",game);
    }

    // == Public Methods ==
    @Override
    public String getMainMessage() {

        return "Number is between " +
                game.getSmallest()+
                " and "+
                game.getBiggest()+
                ". Can you guess it?";

    }

    @Override
    public String getResultMessage() {
        String guessText = game.getRemainingGuesses()==1?"guess":"guesses";

        if (game.isGameWon()){
            return "You got it! The number was " +game.getNumber();
        } else if (game.isGameLost()){
            return "You lost. The number was " + game.getNumber();
        } else if (!game.isValidNumberRange()){
            return "Invalid number range! You have "+game.getRemainingGuesses()+" "+guessText+" left.";
        } else if (game.getRemainingGuesses()==game.getGuessCount()){
            return "What is your first guess? You have "+game.getRemainingGuesses()+" "+guessText+" left.";
        } else {
            String direction = "Lower";

            if (game.getGuess()< game.getNumber()){
                direction = "Higher";
            }
            return direction + "! You have "+ game.getRemainingGuesses()+" "+guessText+" left.";
        }
    }
}
