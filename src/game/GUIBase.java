package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class GUIBase extends JFrame{


    public static final String TWO_DIGIT_NUMBER_SAMPLE = "00";
    public static final String THREE_DIGIT_NUMBER_SAMPLE = "000";

    protected GUIBase(String title) {
        super(title);
    }

    /**
     * Show GUI frame of a mainPanel
     */
    public void showGUI() {
        setLocationRelativeTo(null);
//        System.out.println("show Bf : " + frame.getX() + " " + frame.getWidth() + " " + frame.getY()+ " " + frame.getHeight());
//        frame.setLocation(new Point(frame.getX() - frame.getWidth()/2, frame.getY() - frame.getHeight()/2));
//        System.out.println("Show Af " + frame.getX() + " " + frame.getWidth() + " " + frame.getY()+ " " + frame.getHeight());
        setVisible(true);
    }

    /**
     * hide GUI frame of a mainPanel
     */
    public void hideGUI() {
//        System.out.println("Hide Bf " + frame.getX() + " " + frame.getWidth() + " " + frame.getY()+ " " + frame.getHeight());
//        frame.setLocation(new Point(frame.getX() + frame.getWidth()/2, frame.getY() + frame.getHeight()/2));
//        System.out.println("Hide Af " + frame.getX() + " " + frame.getWidth() + " " + frame.getY()+ " " + frame.getHeight());
        setVisible(false);
    }

    /**
     * close GUI frame of a mainPanel
     */
    public void closeGUI() {
        dispose();
    }



    /**
     * limit the number of inputs in a SAMPELED text filed (as length of sample)
     */
    protected class EnterTextLimitListener implements DocumentListener, KeyListener{

        private int limit;
        private int typed;
        private JFormattedTextField textField;

        public EnterTextLimitListener(JFormattedTextField textField, int limit) {
            this.limit = limit;
            typed = 0;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            if(typed == limit){
                //go to next focusable component (internet)
                KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
                manager.focusNextComponent();
                return;
            }
            typed += e.getLength();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            typed -= e.getLength();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }


        @Override
        public void keyTyped(KeyEvent e) {
            if (typed == limit){
                e.consume();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    /**
     * Only number input key listener
     */
    protected class OnlyNumberKeyListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
            char c = keyEvent.getKeyChar();
            if (!(Character.isDigit(c))||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE) {
                keyEvent.consume();
            }
        }
    }



    //finding next focus component
//    public static Component findNextFocus() {
//        Component c = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
//        Container root = c.getFocusCycleRootAncestor();
//
//        FocusTraversalPolicy policy = root.getFocusTraversalPolicy();
//        Component nextFocus = policy.getComponentAfter(root, c);
//        if (nextFocus == null) {
//            nextFocus = policy.getDefaultComponent(root);
//        }
//        return nextFocus;
//    }


    //resizing a picture
//    public static ImageIcon resize(String inputImagePath, int scaledWidth, int scaledHeight){
//        // reads input image
//        File inputFile = new File(inputImagePath);
//        BufferedImage inputImage = null;
//        try {
//            inputImage = ImageIO.read(inputFile);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//        // creates output image
//        assert inputImage != null;
//        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());
//
//        // scales the input image to the output image
//        Graphics2D g2d = outputImage.createGraphics();
//        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
//        g2d.dispose();
//
//        return new ImageIcon(outputImage);
//    }
}
