package pxlgen.gui;

import pxlgen.core.Pxlgen;
import pxlgen.core.exception.InvalidCommandException;
import pxlgen.core.exception.InvalidPluginException;
import pxlgen.core.function.Function;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * pxlgen.gui.Main.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class Main extends JFrame {

    private JTextField     inputField;
    private ImageComponent imageComponent;

    private final Pxlgen app;

    public static void main(String[] args) {
        new Main(new Pxlgen()).setVisible(true);
    }

    private Function getMatchingFunction() {
        String text = inputField.getText();
        Function[] matching = app.getMatchingFunction(text);
        if (matching.length > 0)
            return matching[0];
        return null;
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
        inputField.setFocusTraversalKeysEnabled(false);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == '\t') {
                    Function f = getMatchingFunction();
                    if (f != null) {
                        inputField.setText(f.getDomain() + "." + f.getName() + "()");
                        inputField.setCaretPosition(inputField.getText().length() - 1);
                    }
                }
            }
        });
        inputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChange();
            }

            private void onChange() {
                imageComponent.addMatchingFunctions(inputField.getText());
                imageComponent.repaint();
            }
        });
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

        imageComponent = new ImageComponent(app);
        imageComponent.refreshImage();
        add(imageComponent, BorderLayout.CENTER);

        new Thread(this::loadScript).start();
    }

    private void loadScript() {
        File file = new File("scripts/script.aled");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() == 0 || line.startsWith("#"))
                    continue;
                app.run(line);
                imageComponent.clearMessages();
                imageComponent.addMessage(line);
                imageComponent.refreshImage();
                Thread.sleep(200);
            }
            fileReader.close();
        } catch (InvalidCommandException e) {
            System.out.println("Invalid command: " + e.getMessage());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
