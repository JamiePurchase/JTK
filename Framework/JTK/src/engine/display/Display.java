/*
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package engine.display;

import input.InputKeyboard;
import input.InputMouse;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jamie
 */
public class Display
{
    private static JFrame frame;
    private JPanel panel;
    private static Canvas canvas;
    private String title;
    private int width, height;
    private String icon;
    private InputKeyboard keyboard;
    private InputMouse mouse;

    public Display(String title, int width, int height, InputKeyboard keyboard, InputMouse mouse, String icon)
    {
        this.title = title;
        this.width = width;
        this.height = height;
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.icon = icon;
        createDisplay();
        this.panel.requestFocus();
    }

    private void createDisplay()
    {
        // Create the frame
        frame = new JFrame(this.title);
        frame.setSize(this.width, this.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        
        // Frame Icon
        if(this.icon.length() > 0)
        {
            java.net.URL iconPath = ClassLoader.getSystemResource(this.icon);
            Image iconImage = Toolkit.getDefaultToolkit().createImage(iconPath);
            frame.setIconImage(iconImage);
        }
            
        // Frame Visible
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dim.width / 2) - (this.width / 2), (dim.height / 2) - (this.height / 2));

        // Create a JPanel
        panel = new JPanel();
        panel.addKeyListener(this.keyboard);
        
        // TEST
        panel.setOpaque(true);
        
        frame.add(panel);
        panel.requestFocusInWindow();

        // Create the canvas
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(this.width, this.height));
        canvas.setMaximumSize(new Dimension(this.width, this.height));
        canvas.setMinimumSize(new Dimension(this.width, this.height));
        canvas.addMouseListener(this.mouse);
        canvas.addMouseMotionListener(this.mouse);

        // Add the canvas to the frame
        frame.add(canvas);
        frame.pack();
    }
    
    public static void frameMove(int moveX, int moveY)
    {
        frame.setLocation(frame.getLocationOnScreen().x + moveX, frame.getLocationOnScreen().y + moveY);
    }

    public static Canvas getCanvas()
    {
        return canvas;
    }
    
    public JPanel getPanel()
    {
        return panel;
    }

}