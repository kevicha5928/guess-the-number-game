package woga.boga;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import woga.boga.annotations.BlahMinNumber;
import woga.boga.annotations.MaxNumber;

import java.util.Random;

@Getter
@Component
public class NumberGeneratorImpl implements NumberGenerator{
    // == fields ==
    @Getter(AccessLevel.NONE)
    private final Random random = new Random();
    private final int maxNumber;
    private final int minNumber;

    // == Constructors ==

    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber,@BlahMinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    // == public methods ==
    @Override
    public int next() {
        return random.nextInt(maxNumber-minNumber)+minNumber;
    }

}
