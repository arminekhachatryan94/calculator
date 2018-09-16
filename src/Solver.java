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

    public String add(String n1, String n2){
        BigDecimal dec1 = new BigDecimal(n1);
        BigDecimal dec2 = new BigDecimal(n2);
        dec1 = dec2.add(dec1);
        return dec1.toString();
    }

    public String subtract(String n1, String n2) {
        BigDecimal dec1 = new BigDecimal(n1);
        BigDecimal dec2 = new BigDecimal(n2);
        dec1 = dec2.subtract(dec1);
        return dec1.toString();
    }

    public String multiply(String n1, String n2) {
        BigDecimal dec1 = new BigDecimal(n1);
        BigDecimal dec2 = new BigDecimal(n2);
        dec1 = dec2.multiply(dec1);
        return dec1.toString();
    }

    public String divide(String n1, String n2) {
        BigDecimal dec1 = new BigDecimal(n1);
        BigDecimal dec2 = new BigDecimal(n2);
        if( dec2.doubleValue() == 0 ){
            this.div_0 = true;
            JOptionPane.showMessageDialog(null, "Division by zero.");
            return "0";
        } else {
            double quotient = dec1.doubleValue()/dec2.doubleValue();
            return new BigDecimal(quotient).toString();
        }
    }

    public String mod(String n1, String n2) {
        BigDecimal dec1 = new BigDecimal(n1);
        BigDecimal dec2 = new BigDecimal(n2);
        if( dec2.doubleValue() == 0 ){
            this.div_0 = true;
            return "0";
        } else {
            double remainder = dec1.doubleValue()%dec2.doubleValue();
            return new BigDecimal(remainder).toString();
        }
    }

    public void evaluate() {
        if( opSet ){
            switch (this.op) {
                case "+":
                    this.calculation = add(calculation, num2);
                    break;
                case "-":
                    this.calculation = subtract(calculation, num2);
                    break;
                case "x":
                    this.calculation = multiply(calculation, num2);
                    break;
                case "/":
                    calculation = divide(calculation, num2);
                    break;
                case "%":
                    calculation = mod(calculation, num2);
                    break;
            }

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
    }

}
