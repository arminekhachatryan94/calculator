import java.math.BigDecimal;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Solver {
    private static String calculation;
    private static String op;
    private static String num2;
    private static boolean num2Set;
    private static boolean opSet;
    private static boolean solved;
    private static boolean div_0;

    private JLabel screen;

    public Solver(JLabel screen) {
        this.screen = new JLabel();
        this.screen = screen;

        calculation = "0";
        num2 = "0";
        op = "=";
        num2Set = false;
        opSet = false;
        solved = false;
        div_0 = false;
    }

    public void setNumber(String num) {
        if( this.solved && this.opSet == false ){
            this.calculation = num;
            this.solved = false;
            updateScreen(this.calculation);
        }
        else if( this.opSet == false ){
            if( this.calculation == "0" ){
                this.calculation = num;
            } else {
                this.calculation += num;
            }
            updateScreen(calculation);
        } else {
            if( this.num2 == "0" ){
                this.num2 = num;
                this.num2Set = true;
            } else {
                this.num2 += num;
            }
            updateScreen(this.num2);
        }
    }

    public void setOp(String op) {
        if( op == "AC" ) {
            this.calculation = "0";
            this.num2 = "0";
            this.op = "=";
            this.opSet = false;
            this.num2Set = false;
            updateScreen(this.calculation);
        } else if( op == "±" ) {
            if (this.num2Set) {
                BigDecimal n2 = new BigDecimal(this.num2);
                n2 = n2.multiply(new BigDecimal(-1));
                this.num2 = n2.toString();
                updateScreen(this.num2);
            } else {
                BigDecimal calc = new BigDecimal(this.calculation);
                calc = calc.multiply(new BigDecimal(-1));
                this.calculation = calc.toString();
                updateScreen(this.calculation);
            }
        } else if( op == "=" ) {
            evaluate();
            updateScreen(this.calculation);
            this.solved = true;
        } else if( op == "+" || op == "-" || op == "x"
                || op == "/" || op == "%" ) {
            if ( !this.opSet ) {
                this.op = op;
                this.opSet = true;
            } else {
                if( this.num2Set ) {
                    evaluate();
                    updateScreen(this.calculation);
                    this.op = op;
                    this.opSet = true;
                    this.solved = true;
                } else {
                    this.op = op;
                }
            }
        }
        else if( op == ".") {
            if( !this.solved ){
                // op is set & we are at num2
                if( this.opSet ){
                    if( this.num2Set && this.num2.indexOf('.') < 0 ){
                        this.num2 += ".";
                    } else if( this.num2.indexOf('.') < 0 ){
                        this.num2 = "0.";
                    }
                    updateScreen(this.num2);
                } else { // op is not set & we are at calc
                    if( this.calculation.indexOf('.') < 0 ){
                        this.calculation += ".";
                    }
                    updateScreen(this.calculation);
                }
            } else {
                if( this.opSet ){
                    if( this.num2Set && this.num2.indexOf('.') < 0 ){
                        this.num2 += ".";
                    } else if( this.num2.indexOf('.') < 0 ){
                        this.num2 = "0.";
                    }
                    updateScreen(this.num2);
                } else {
                    this.calculation = "0.";
                    updateScreen(this.calculation);
                }
                this.solved = false;
            }
        }
        else {
            // JOptionPane.showMessageDialog(menuPanel,calculation + " " + opSet + ": " + op + " " + num2Set + ": " + num2);
            JOptionPane.showMessageDialog(null,"Invalid operator");
        }
    }

    public void add(){
    ;
    }

    public void subtract() {
        ;
    }

    public void multiply() {
        ;
    }

    public void divide() {
        ;
    }

    public void mod() {
        ;
    }

    public void evaluate() {
        if( opSet ){
            BigDecimal calc = new BigDecimal(this.calculation);
            BigDecimal n2 = new BigDecimal(this.num2);
            // try {
            switch (this.op) {
                case "+":
                    calc = calc.add(n2);
                    this.calculation = calc.toString();
                    break;
                case "-":
                    calc = calc.subtract(n2);
                    this.calculation = calc.toString();
                    break;
                case "x":
                    calc = calc.multiply(n2);
                    this.calculation = calc.toString();
                    break;
                case "/":
                    if( n2.doubleValue() == 0 ){
                        this.calculation = "0";
                        this.div_0 = true;
                        // JOptionPane.showMessageDialog(menuPanel, "Division by zero.");
                    } else {
                        double quotient = calc.doubleValue()/n2.doubleValue();
                        calc = new BigDecimal(quotient);
                        this.calculation = calc.toString();
                        //calc = calc.divide(n2);
                        //calculation = calc.toString();
                    }
                    break;
                case "%":
                    if( n2.doubleValue() == 0 ){
                        this.calculation = "0";
                        this.div_0 = true;
                    } else {
                            /*
                            calc = calc.remainder(n2);
                            calculation = calc.toString();
                            */
                        double remainder = calc.doubleValue()%n2.doubleValue();
                        calc = new BigDecimal(remainder);
                        this.calculation = calc.toString();
                    }
                    break;
            }
            /* } catch(Exception e){
                if( op == "/" ) {
                    if( n2 == new BigDecimal("0") ){
                        JOptionPane.showMessageDialog(menuPanel, "division by zero");
                    } else {
                        BigDecimal calc2 = new BigDecimal(calculation);
                        calc2.setScale(5).divide(n2, BigDecimal.ROUND_HALF_UP);
                        calculation = calc.toString();
                    }
                }
            }*/

            calculation = new BigDecimal(calculation).stripTrailingZeros().toString();

            num2 = "0";
            op = "=";
            opSet = false;
            num2Set = false;
            solved = true;
        }
    }

    public void updateScreen(String text) {
        if( this.div_0 ){
            this.screen.setText("NaN");
            this.div_0 = false;
        } else {
            this.screen.setText(text);
        }
        /* if( n.remainder(new BigDecimal(1)) != new BigDecimal(0) ) {
            labelPanel.setText(val);
         } else {
            labelPanel.setText(val);
        } */
    }

}
