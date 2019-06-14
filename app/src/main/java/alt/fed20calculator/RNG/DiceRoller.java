package alt.fed20calculator.RNG;

import com.bernardomg.tabletop.dice.history.RollHistory;
import com.bernardomg.tabletop.dice.history.RollResult;
import com.bernardomg.tabletop.dice.interpreter.DiceInterpreter;
import com.bernardomg.tabletop.dice.parser.DefaultDiceParser;
import com.bernardomg.tabletop.dice.parser.DiceParser;

public class DiceRoller {
    public DiceRoller(){}

    public int getRoll(String roll) {
        final DiceParser parser;
        final RollHistory rolls;
        final DiceInterpreter<RollHistory> roller;

        parser = new DefaultDiceParser();
        roller = new com.bernardomg.tabletop.dice.interpreter.DiceRoller();

        rolls = parser.parse(roll, roller);

        // Prints the final result
        System.out.println(rolls.getTotalRoll());

        return rolls.getTotalRoll();
    }

    public Iterable<RollResult> getIndividualRolls(String roll){
        final DiceParser parser;
        final RollHistory rolls;
        final DiceInterpreter<RollHistory> roller;

        parser = new DefaultDiceParser();
        roller = new com.bernardomg.tabletop.dice.interpreter.DiceRoller();

        rolls = parser.parse(roll, roller);

        // Prints the final result
        System.out.println(rolls.getTotalRoll());

        return rolls.getRollResults();
    }
}
