import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import javax.swing.JLabel;

public class SolverTest {
    public JLabel screen = new JLabel();
    public Solver solver = new Solver(screen);

    @Test
    public void setNumbersAndSetOperatorToCalculator() {
        solver.setNumber("1");
        assertEquals(solver.calculation, "1");
        assertFalse(solver.opSet);
        assertFalse(solver.num2Set);

        solver.setNumber("2");
        assertEquals(solver.calculation, "12");
        assertFalse(solver.opSet);
        assertFalse(solver.num2Set);

        solver.setOp(".");
        assertEquals(solver.calculation, "12.");
        assertFalse(solver.opSet);
        assertFalse(solver.num2Set);

        solver.setOp("+");
        assertEquals(solver.op, "+");
        assertTrue(solver.opSet);
        assertFalse(solver.num2Set);

        
        solver.setOp(".");
        assertEquals(solver.num2, "0.");
        assertTrue(solver.opSet);
        assertFalse(solver.num2Set);

        solver.setNumber("5");
        assertEquals(solver.num2, "0.5");
        assertTrue(solver.opSet);
        assertFalse(solver.num2Set);

        solver.setOp("←");
        assertEquals(solver.num2, "0.");

        solver.setNumber("8");
        assertEquals(solver.num2, "0.8");
    }

    @Test
    public void addTwoStrings() {
        String num1 = "11.234";
        String num2 = ".006";
        String sum = solver.add(num1, num2);
        assertEquals(sum, "11.240");
    }

    @Test
    public void subtractTwoStrings() {
        String num1 = "5";
        String num2 = "6";
        String difference1 = solver.subtract(num1, num2);
        String difference2 = solver.subtract(num2, num1);
        assertTrue(difference1 != difference2);
        assertEquals(difference1, "-1");
        assertEquals(difference2, "1");
    }

    @Test
    public void multiplyTwoStrings() {
        String num1 = "4.2";
        String num2 = "8";
        String product = solver.multiply(num1, num2);
        assertEquals(product, "33.6");
    }

    @Test
    public void divideTwoStrings() {
        String num1 = "8";
        String num2 = "2";
        String quotient = solver.divide(num1, num2);
        assertEquals(quotient, "4");
    }

    @Test
    public void modTwoStrings() {
        String num1 = "9";
        String num2 = "2";
        String remainder = solver.mod(num1, num2);
        assertEquals(remainder, "1");
    }

    @Test
    public void evaluateExpression() {
        solver.setNumber("9");
        assertEquals(solver.calculation, "9");

        solver.setOp("x");
        assertEquals(solver.op, "x");
        
        solver.setNumber("2");
        assertEquals(solver.num2, "2");
        
        solver.evaluate();
        assertEquals(solver.calculation, "18");
    }

    @Test
    public void updateCalculatorScreen() {
        String screen_value = "500";
        solver.updateScreen(screen_value);
        
        assertEquals(screen.getText(), screen_value);
    }
}
