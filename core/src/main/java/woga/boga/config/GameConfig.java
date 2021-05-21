package woga.boga.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import woga.boga.annotations.BlahMinNumber;
import woga.boga.annotations.GuessCount;
import woga.boga.annotations.MaxNumber;

@Configuration
@PropertySource("classpath:config/game.properties")
@ComponentScan(basePackages = "woga.boga")
public class GameConfig {
    // == Fields ==
    @Value("${game.maxNumber:20}")
    private int maxNumber;
    @Value("${game.guessCount:5}")
    private int guessCount;
    @Value("${game.minNumber:0}")
    private int minNumber;

    // == bean methods ==
    @Bean
    @MaxNumber
    public int maxNumber1(){
        return maxNumber;
    }

    @Bean
    @GuessCount
    public int guessCount1(){
        return guessCount;
    }

    @Bean
    @BlahMinNumber
    public int blahblah(){
        return minNumber;
    }

}
