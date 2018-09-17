import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CalculatorMenu extends JMenuBar {
    public JMenu menu = new JMenu("Menu");
    public JMenuItem exit = new JMenuItem("exit");

    CalculatorMenu(){
        menu.add(exit);
        add(menu);
        addMenuListeners();
    }

    private void addMenuListeners() {
        exit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitActionPerformed();
            }
        });
    }

    private void exitActionPerformed() {
        System.exit(0);
    }
}
