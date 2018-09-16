import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
    private static CalculatorButton add, subtract, multiply, divide, mod, eq, ac, dot, neg, del;

    // calculator
    public static JLabel screenLabel;
    public static JPanel screenPanel;
    public static JPanel numPanel;
    public static Solver solver;

    // state of keys
    public boolean shift;

    // constructor
    public CalculatorFrame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        createScreen();

        initializeButtons();
        createMenu();

        getContentPane().add(screenPanel, BorderLayout.NORTH);
        getContentPane().add(numPanel, BorderLayout.PAGE_END);
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
        del = new CalculatorButton("←", screenLabel, solver);
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
        numPanel = new JPanel();
        numPanel.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        numPanel.setLayout(new GridLayout(5, 4));

        CalculatorKeyListener keyListener = new CalculatorKeyListener();
        numPanel.addKeyListener(keyListener);
        numPanel.setFocusable(true);
        numPanel.requestFocusInWindow();

        numPanel.add(ac);
        numPanel.add(neg);
        numPanel.add(mod);
        numPanel.add(divide);

        numPanel.add(buttons[7]);
        numPanel.add(buttons[8]);
        numPanel.add(buttons[9]);
        numPanel.add(multiply);

        numPanel.add(buttons[4]);
        numPanel.add(buttons[5]);
        numPanel.add(buttons[6]);
        numPanel.add(subtract);

        numPanel.add(buttons[1]);
        numPanel.add(buttons[2]);
        numPanel.add(buttons[3]);
        numPanel.add(add);

        numPanel.add(del);
        numPanel.add(buttons[0]);
        numPanel.add(dot);
        numPanel.add(eq);

    }

    private class CalculatorKeyListener implements KeyListener {
        /*
        Key Codes
        0-9: (48 - 57)
        = or +: 61
        -: 45
        *: 56
        /: 47
        %: 53
        .: 46
        shift: 16
        enter: 10
        */

        @Override
        public void keyTyped( KeyEvent evt ) {
            ;
        }

        @Override
        public void keyPressed( KeyEvent evt ) {
            int code = evt.getKeyCode();
            if( code == 16 ){
                shift = true;
            }
            else if( code >= 48 && code <= 57){
                if(shift){
                    if( code == 53 ){
                        solver.setOp("%");
                    } else if( code == 56 ){
                        solver.setOp("*");
                    }
                } else {
                    solver.setNumber(evt.getKeyChar() + "");
                }
            } else if( code == 45 && !shift ){
                solver.setOp("-");
            } else if( code == 61 ){
                if(shift){
                    solver.setOp("+");
                } else {
                    solver.setOp("=");
                }
            } else if( code == 47 && !shift ){
                solver.setOp("/");
            } else if( code == 46 ){
                solver.setOp(".");
            } else if( code == 10 ){
                solver.setOp("=");
            } else if(code == 8 ){
                solver.delete();
            }
            repaint();
        }

        @Override
        public void keyReleased( KeyEvent evt ) {
            int code = evt.getKeyCode();
            if( code == 16 ){
                shift = false;
            }
        }
    }
}
