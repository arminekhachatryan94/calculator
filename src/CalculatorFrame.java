import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CalculatorFrame extends JFrame {

    // frame dimensions
    private static final int FRAME_WIDTH = 248;
    private static final int FRAME_HEIGHT = 300;
    // panel dimensions
    private static final int PANEL_WIDTH = FRAME_WIDTH;
    private static final int PANEL_HEIGHT = 60;
    // menu dimensions
    private static final int MENU_WIDTH = FRAME_WIDTH;
    private static final int MENU_HEIGHT = FRAME_HEIGHT - PANEL_HEIGHT;

    // buttons
    private static CalculatorButton buttons[];
    private static CalculatorButton add, subtract, multiply, divide, mod, eq, ac, dot, neg;

    // calculator
    public static JLabel screenLabel;
    public static JPanel screenPanel;
    public static JPanel menuPanel;
    public static Solver solver;

    // constructor
    public CalculatorFrame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        createScreen();

        initializeButtons();
        createMenu();

        getContentPane().add(screenPanel, BorderLayout.NORTH);
        getContentPane().add(menuPanel, BorderLayout.PAGE_END);
        setResizable(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // helper methods
    public void initializeButtons() {
        buttons = new CalculatorButton[10];
        for( int i = 0; i < 10; i++ ){
            buttons[i] = new CalculatorButton(i + "", screenLabel, solver);
        }

        add = new CalculatorButton("+", screenLabel, solver);
        subtract = new CalculatorButton("-", screenLabel, solver);
        multiply = new CalculatorButton("x", screenLabel, solver);
        divide = new CalculatorButton("/", screenLabel, solver);
        mod = new CalculatorButton("%", screenLabel, solver);
        eq = new CalculatorButton("=", screenLabel, solver);
        ac = new CalculatorButton("AC", screenLabel, solver);
        dot = new CalculatorButton(".", screenLabel, solver);
        neg = new CalculatorButton("±", screenLabel, solver);
    }

    public void createScreen(){
        screenLabel = new JLabel();
        screenLabel.setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        screenLabel.setBorder(new EmptyBorder(0,100,0,0));
        screenLabel.setText("0");
        screenLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        screenLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        screenLabel.setForeground(Color.white);

        screenPanel = new JPanel();
        screenPanel.setLayout(new GridLayout(1, 1, 10, 10));
        screenPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        screenPanel.add(screenLabel);
        screenPanel.setBackground(new Color(52, 52, 52));

        solver = new Solver(screenLabel);
    }

    public void createMenu(){
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        menuPanel.setLayout(new GridLayout(5, 4));

        menuPanel.add(ac);
        menuPanel.add(neg);
        menuPanel.add(mod);
        menuPanel.add(divide);

        menuPanel.add(buttons[7]);
        menuPanel.add(buttons[8]);
        menuPanel.add(buttons[9]);
        menuPanel.add(multiply);

        menuPanel.add(buttons[4]);
        menuPanel.add(buttons[5]);
        menuPanel.add(buttons[6]);
        menuPanel.add(subtract);

        menuPanel.add(buttons[1]);
        menuPanel.add(buttons[2]);
        menuPanel.add(buttons[3]);
        menuPanel.add(add);

        menuPanel.add(buttons[0]);
        menuPanel.add(dot);
        menuPanel.add(eq);
    }

    /*
    private class ButtonKeyListener implements KeyListener {
        @Override
        public void keyTyped( KeyEvent evt ) {
            JOptionPane.showMessageDialog(menuPanel, evt.getKeyChar());
        }

        @Override
        public void keyPressed( KeyEvent evt ) {
        }

        @Override
        public void keyReleased( KeyEvent evt ) {
        }
    }
    */

}
