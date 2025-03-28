import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import parsers.MiCalc;

public class CalculatorTests {

    @Test
    public void test_add() {
        MiCalc miCalc = new MiCalc("12+(144/12)+213-671*(2-12)");
        assertEquals(6947, miCalc.eval_brackets());
    }

    @Test
    public void test_exponents() {
        System.out.println(Math.pow(4,3));
        MiCalc miCalc = new MiCalc("4^3");
        assertEquals(64, miCalc.eval_brackets());
    }
}
