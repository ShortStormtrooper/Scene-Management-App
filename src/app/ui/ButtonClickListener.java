package app.ui;

import app.parent.Scene;
import app.child.Sportsman;

import javax.swing.*;
import java.awt.event.*;

public class ButtonClickListener implements ActionListener {

    private JButton button;
    private JList sportnik;
    private JList prizorisce;
    private Sportsman selectedSportnik;
    private Scene selectedScene;
    private mainFrame mainFrame;

    public ButtonClickListener(JButton button, JList sportnik, JList prizorisce, mainFrame mainFrame) {
        this.button = button;
        this.sportnik = sportnik;
        this.prizorisce = prizorisce;
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {
            selectedSportnik = (Sportsman) sportnik.getSelectedValue();
            selectedScene = (Scene) prizorisce.getSelectedValue();

            System.out.println(sportnik);
            String text = selectedSportnik.checkValidity(selectedScene);
            mainFrame.setTFCheckSportsmanOutput(text);

        }
    }
}
