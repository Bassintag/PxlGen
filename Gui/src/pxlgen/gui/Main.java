package pxlgen.gui;

import pxlgen.core.Pxlgen;
import pxlgen.core.exception.InvalidCommandException;
import pxlgen.core.exception.InvalidPluginException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * pxlgen.gui.Main.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class Main extends JFrame {

    private JTextField     inputField;
    private JTextArea      historyTextArea;
    private ImageComponent imageComponent;

    private final Pxlgen app;

    public static void main(String[] args) {
        new Main(new Pxlgen()).setVisible(true);
    }

    private Main(Pxlgen app) {
        this.app = app;

        try {
            app.loadPluginFolder("plugins");
        } catch (InvalidPluginException e) {
            e.printStackTrace();
        }

        setTitle("Pxlgen GUI");
        setSize(960, 540);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        inputField = new JTextField();
        inputField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    app.run(inputField.getText());
                    imageComponent.refreshImage();
                } catch (InvalidCommandException e1) {
                    System.out.println("Invalid command: " + e1.getMessage());
                }
            }
        });
        add(inputField, BorderLayout.SOUTH);

        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        historyTextArea.setText("History >> ");
//        add(historyTextArea, BorderLayout.WEST);

        imageComponent = new ImageComponent(app);
        imageComponent.refreshImage();
        add(imageComponent, BorderLayout.CENTER);

        loadScript();
    }

    private void loadScript() {
        File file = new File("script.aled");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                app.run(line);
            }
            imageComponent.refreshImage();
            fileReader.close();
        } catch (InvalidCommandException e) {
            System.out.println("Invalid command: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
