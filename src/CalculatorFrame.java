import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private static JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private static JButton add, sub, mult, div, mod, eq, ac, dot, neg;

    // calculator
    private static JPanel screenPanel;
    private static JPanel menuPanel;

    // calculation variable
    private static double calculation;

    // constructor
    public CalculatorFrame() {
        calculation = 0;

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        initializeButtons();
        addButtonListeners();

        createScreen();
        createMenu();

        getContentPane().add(screenPanel, BorderLayout.NORTH);
        getContentPane().add(menuPanel, BorderLayout.PAGE_END);
        setResizable(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // helper methods
    public void initializeButtons() {
        b0 = new JButton("0");
        b0.setBackground(new Color(224, 224, 224));
        b0.setForeground(Color.black);
        b0.setBorderPainted(false);
        b0.setOpaque(true);
        b0.setFont(new Font("Arial", Font.PLAIN, 15));

        b1 = new JButton("1");
        b1.setBackground(new Color(224, 224, 224));
        b1.setForeground(Color.black);
        b1.setBorderPainted(false);
        b1.setOpaque(true);

        b2 = new JButton("2");
        b2.setBackground(new Color(224, 224, 224));
        b2.setForeground(Color.black);
        b2.setBorderPainted(false);
        b2.setOpaque(true);

        b3 = new JButton("3");
        b3.setBackground(new Color(224, 224, 224));
        b3.setForeground(Color.black);
        b3.setBorderPainted(false);
        b3.setOpaque(true);

        b4 = new JButton("4");
        b4.setBackground(new Color(224, 224, 224));
        b4.setForeground(Color.black);
        b4.setBorderPainted(false);
        b4.setOpaque(true);

        b5 = new JButton("5");
        b5.setBackground(new Color(224, 224, 224));
        b5.setForeground(Color.black);
        b5.setBorderPainted(false);
        b5.setOpaque(true);

        b6 = new JButton("6");
        b6.setBackground(new Color(224, 224, 224));
        b6.setForeground(Color.black);
        b6.setBorderPainted(false);
        b6.setOpaque(true);

        b7 = new JButton("7");
        b7.setBackground(new Color(224, 224, 224));
        b7.setForeground(Color.black);
        b7.setBorderPainted(false);
        b7.setOpaque(true);

        b8 = new JButton("8");
        b8.setBackground(new Color(224, 224, 224));
        b8.setForeground(Color.black);
        b8.setBorderPainted(false);
        b8.setOpaque(true);

        b9 = new JButton("9");
        b9.setBackground(new Color(224, 224, 224));
        b9.setForeground(Color.black);
        b9.setBorderPainted(false);
        b9.setOpaque(true);

        add = new JButton("+");
        add.setBackground(new Color(245, 145, 56));
        add.setForeground(Color.white);
        add.setBorderPainted(false);
        add.setOpaque(true);

        sub = new JButton("-");
        sub.setBackground(new Color(245, 145, 56));
        sub.setForeground(Color.white);
        sub.setBorderPainted(false);
        sub.setOpaque(true);

        mult = new JButton("x");
        mult.setBackground(new Color(245, 145, 56));
        mult.setForeground(Color.white);
        mult.setBorderPainted(false);
        mult.setOpaque(true);

        div = new JButton("/");
        div.setBackground(new Color(245, 145, 56));
        div.setForeground(Color.white);
        div.setBorderPainted(false);
        div.setOpaque(true);

        mod = new JButton("%");
        mod.setBackground(new Color(224, 224, 224));
        mod.setForeground(Color.black);
        mod.setBorderPainted(false);
        mod.setOpaque(true);

        eq = new JButton("=");
        eq.setBackground(new Color(245, 145, 56));
        eq.setForeground(Color.white);
        eq.setBorderPainted(false);
        eq.setOpaque(true);

        ac = new JButton("AC");
        ac.setBackground(new Color(224, 224, 224));
        ac.setForeground(Color.black);
        ac.setBorderPainted(false);
        ac.setOpaque(true);

        neg = new JButton("±");
        neg.setBackground(new Color(224, 224, 224));
        neg.setForeground(Color.black);
        neg.setBorderPainted(false);
        neg.setOpaque(true);

        dot = new JButton(".");
        dot.setBackground(new Color(224, 224, 224));
        dot.setForeground(Color.black);
        dot.setBorderPainted(false);
        dot.setOpaque(true);
    }

    public void addButtonListeners(){
        ActionListener numListener = new NumberButtonListener();

        b0.addActionListener(numListener);
        b1.addActionListener(numListener);
        b2.addActionListener(numListener);
        b3.addActionListener(numListener);
        b4.addActionListener(numListener);
        b5.addActionListener(numListener);
        b6.addActionListener(numListener);
        b7.addActionListener(numListener);
        b8.addActionListener(numListener);
        b9.addActionListener(numListener);

        ActionListener opListener = new OperatorButtonListener();

        add.addActionListener(opListener);
        sub.addActionListener(opListener);
        mult.addActionListener(opListener);
        div.addActionListener(opListener);
        mod.addActionListener(opListener);
        eq.addActionListener(opListener);
        ac.addActionListener(opListener);
        dot.addActionListener(opListener);
        neg.addActionListener(opListener);
    }

    public void createScreen(){
        JLabel label;
        label = new JLabel();
        label.setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        label.setBorder(new EmptyBorder(0,100,0,0));
        label.setText("" + calculation);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setForeground(Color.white);

        screenPanel = new JPanel();
        screenPanel.setLayout(new GridLayout(1, 1, 10, 10));
        screenPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        screenPanel.add(label);
        screenPanel.setBackground(new Color(52, 52, 52));
    }

    public void createMenu(){

        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        menuPanel.setLayout(new GridLayout(5, 4));

        menuPanel.add(ac);
        menuPanel.add(neg);
        menuPanel.add(mod);
        menuPanel.add(div);

        menuPanel.add(b7);
        menuPanel.add(b8);
        menuPanel.add(b9);
        menuPanel.add(mult);

        menuPanel.add(b4);
        menuPanel.add(b5);
        menuPanel.add(b6);
        menuPanel.add(sub);

        menuPanel.add(b1);
        menuPanel.add(b2);
        menuPanel.add(b3);
        menuPanel.add(add);

        menuPanel.add(b0);
        menuPanel.add(dot);
        menuPanel.add(eq);
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            calculation = Double.parseDouble(command);
            updateCalculationOnScreenPanel();
        }
    }

    private class OperatorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            JOptionPane.showMessageDialog(menuPanel, command);
        }
    }

    public void updateCalculationOnScreenPanel() {
        JLabel labelPanel = (JLabel) screenPanel.getComponents()[0];
        labelPanel.setText(Double.toString(calculation));
        repaint();
    }

}
