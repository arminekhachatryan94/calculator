import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CalculatorButton extends JButton implements ActionListener {
    public JLabel screen;
    public Solver solver;

    CalculatorButton(String text, JLabel screen, Solver solver) {
        // button
        setText(text);
        if( text == "+" || text == "-" || text == "x" || text == "/" || text == "=" ) {
            setBackground(new Color(245, 145, 56));
            setForeground(Color.white);
        } else {
            setBackground(new Color(224, 224, 224));
            setForeground(Color.black);
        }
        setBorderPainted(false);
        setOpaque(true);

        // screen
        this.screen = new JLabel();
        this.screen = screen;

        // solver
        this.solver = new Solver(screen);
        this.solver = solver;

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if( command.charAt(0) >= '0' && command.charAt(0) <= '9' ) {
            solver.setNumber(command);
        } else {
            solver.setOp(command);
        }
        repaint();
    }
}
