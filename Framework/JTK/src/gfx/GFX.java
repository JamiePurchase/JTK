/*
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package gfx;

import console.Output;
import engine.Application;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Jamie
 */
public class GFX
{
    
    public static void drawImage(Graphics g, BufferedImage image, int posX, int posY)
    {
        g.drawImage(image, posX, posY, null);
    }
    
    public static void drawImage(Graphics g, BufferedImage image, int posX, int posY, int sheetX, int sheetY, int sizeX, int sizeY)
    {
        g.drawImage(image.getSubimage(sheetX, sheetY, sizeX, sizeY), posX, posY, null);
    }
    
    public static void drawImage(Graphics g, String image, int posX, int posY)
    {
        g.drawImage(getImage(image), posX, posY, null);
    }
    
    public static void drawImage(Graphics g, String image, int posX, int posY, float alpha)
    {
        // Set Opacity
        Graphics2D g2D = (Graphics2D) g;
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2D.setComposite(composite);

        // Draw Image
        g2D.drawImage(getImage(image), posX, posY, null);

        // Clear Opacity
        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        g2D.setComposite(composite);
    }
    
    public static void drawImage(Graphics g, String image, int posX, int posY, int sheetX, int sheetY, int sizeX, int sizeY)
    {
        g.drawImage(getImage(image).getSubimage(sheetX, sheetY, sizeX, sizeY), posX, posY, null);
    }
    
    public static void drawImageFlip(Graphics g, String image, int posX, int posY)
    {
        g.drawImage(getImageFlip(getImage(image)), posX, posY, null);
    }
    
    public static void drawLine(Graphics g, int posX1, int posY1, int posX2, int posY2, String colour)
    {
        g.setColor(Application.getStyleColour(colour));
        g.drawLine(posX1, posY1, posX2, posY2);
    }
    
    public static void drawLine(Graphics g, int posX1, int posY1, int posX2, int posY2, StyleColour colour)
    {
        g.setColor(getColour(colour));
        g.drawLine(posX1, posY1, posX2, posY2);
    }
    
    public static void drawPolygon(Graphics g, Polygon polygon, String colour, boolean fill)
    {
        g.setColor(Application.getStyleColour(colour));
        if(fill) {g.fillPolygon(polygon);}
        else {g.drawPolygon(polygon);}
    }
    
    public static void drawRect(Graphics g, Rectangle rect, String colour, boolean fill)
    {
        g.setColor(Application.getStyleColour(colour));
        //g.setColor(Style.getStyleColour(colour));
        if(fill) {g.fillRect(rect.x, rect.y, rect.width, rect.height);}
        else {g.drawRect(rect.x, rect.y, rect.width, rect.height);}
    }
    
    public static void drawRect(Graphics g, Rectangle rect, StyleColour colour, boolean fill)
    {
        g.setColor(getColour(colour));
        if(fill) {g.fillRect(rect.x, rect.y, rect.width, rect.height);}
        else {g.drawRect(rect.x, rect.y, rect.width, rect.height);}
    }
    
    public static void drawRect(Graphics g, Rectangle rect, StyleColour colour, int border)
    {
        g.setColor(getColour(colour));
        for(int x = 0; x < border; x++)
        {
            g.drawRect(rect.x + x, rect.y + x, rect.width - (x * 2), rect.height - (x * 2));
        }
    }
    
    public static void drawRect(Graphics g, Rectangle rect, String colour, float opacity)
    {
        // Create Image
        BufferedImage image = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Application.getStyleColour(colour));
        g2d.fillRect(0, 0, rect.width, rect.height);
        g2d.dispose();
        
        // Set Opacity
        Graphics2D g2D = (Graphics2D) g;
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
        g2D.setComposite(composite);

        // Draw Image
        g2D.drawImage(image, rect.x, rect.y, null);

        // Clear Opacity
        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        g2D.setComposite(composite);
    }
    
    public static void drawRect(Graphics g, Rectangle rect, StyleColour colour, float opacity)
    {
        // Create Image
        BufferedImage image = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(getColour(colour));
        g2d.fillRect(0, 0, rect.width, rect.height);
        g2d.dispose();
        
        // Set Opacity
        Graphics2D g2D = (Graphics2D) g;
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
        g2D.setComposite(composite);

        // Draw Image
        g2D.drawImage(image, rect.x, rect.y, null);

        // Clear Opacity
        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        g2D.setComposite(composite);
    }
    
    public static void drawRectBorder(Graphics g, Rectangle rect, String colour, boolean drawTop, boolean drawRight, boolean drawBottom, boolean drawLeft)
    {
        g.setColor(Application.getStyleColour(colour));
        if(drawBottom) {g.drawLine(rect.x, rect.y + rect.height, rect.x + rect.width, rect.y + rect.height);}
        if(drawLeft) {g.drawLine(rect.x, rect.y, rect.x, rect.y + rect.height);}
        if(drawRight) {g.drawLine(rect.x + rect.width, rect.y, rect.x + rect.width, rect.y + rect.height);}
        if(drawTop) {g.drawLine(rect.x, rect.y, rect.x + rect.width, rect.y);}
    }

    private static Color getColour(StyleColour colour)
    {
        if(colour == StyleColour.INPUT_BKG) {return getColourRGB(255, 255, 255);}
        if(colour == StyleColour.INPUT_BORDER) {return getColourRGB(0, 0, 0);}
        if(colour == StyleColour.INPUT_TEXT) {return getColourRGB(0, 0, 0);}
        if(colour == StyleColour.TEXT_STANDARD) {return getColourRGB(0, 0, 0);}
        if(colour == StyleColour.TEXT_STANDARD_ERROR) {return getColourRGB(150, 0, 0);}
        if(colour == StyleColour.TITLE_BKG) {return getColourRGB(255, 128, 0);}
        if(colour == StyleColour.TITLE_BKG2) {return getColourRGB(0, 128, 255);}
        if(colour == StyleColour.WINDOW_BKG) {return getColourRGB(255, 255, 255);}
        if(colour == StyleColour.WINDOW_BORDER) {return getColourRGB(0, 0, 0);}
        return Color.BLACK;
    }

    public static Color getColourRGB(int r, int g, int b)
    {
        float hsb[] = Color.RGBtoHSB(r,g,b,null);
        return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    }
    
    private static Font getFont(StyleText font)
    {
        if(font == StyleText.HEADER) {return new Font("Courier New", Font.PLAIN, 64);}
        if(font == StyleText.INPUT_STANDARD) {return new Font("Courier New", Font.PLAIN, 14);}
        if(font == StyleText.STANDARD) {return new Font("Courier New", Font.PLAIN, 14);}
        if(font == StyleText.STANDARD_BOLD) {return new Font("Courier New", Font.BOLD, 14);}
        if(font == StyleText.STANDARD_ERROR) {return new Font("Courier New", Font.PLAIN, 14);}
        return new Font("Courier New", Font.PLAIN, 14);
    }
    
    public static BufferedImage getImage(String file)
    {
        // Debug
        //Output.print("GFX -> getImage(" + file + ")");
        String path = Application.getSystemResource(file);
        if(!path.substring(0, 1).equals("C")) {path = "C" + path;}
        //Output.print("  path = " + path);

        BufferedImage image = null;
        //try {image = ImageIO.read(new File(Application.getSystemResource(file)));}
        try {image = ImageIO.read(new File(path));}
        catch (IOException ex)
        {
            //Output.print("IMAGE: " + new File(Application.getSystemResource(file)).getAbsolutePath());
            //Output.print(ex);
        }
        return image;
    }
    
    public static BufferedImage getImageFlip(BufferedImage image)
    {
        AffineTransform transform1 = AffineTransform.getScaleInstance(-1, 1);
        transform1.translate(-image.getWidth(null), 0);
        AffineTransformOp transform2 = new AffineTransformOp(transform1, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return transform2.filter(image, null);
    }
    
    public static BufferedImage getImagePath(String file)
    {
        // Debug
        Output.print("GFX -> getImagePath(" + file + ")");
        String path = file;
        //if(!path.substring(0, 1).equals("C")) {path = "C" + path;}
        Output.print("  path = " + path);
        
        BufferedImage image = null;
        //try {image = ImageIO.read(new File(file));}
        try {image = ImageIO.read(new File("file:///" + path));}
        catch (IOException ex)
        {
            Output.print("IMAGE FROM PATH: " + path);
            Output.print(ex);
        }
        
        return image;
    }
    
    public static int getTextHeight(Graphics g, String text)
    {
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics().getFontMetrics(g.getFont()).getHeight();
    }
    
    public static int getTextHeight(Graphics g, String text, StyleText font)
    {
        g.setFont(getFont(font));
        return getTextWidth(g, text);
    }
    
    private static int getTextWidth(Graphics g, String text)
    {
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics().getFontMetrics(g.getFont()).stringWidth(text);
    }
    
    public static int getTextWidth(Graphics g, String text, StyleText font)
    {
        g.setFont(getFont(font));
        return getTextWidth(g, text);
    }
    
    public static int getTextWidth(Graphics g, String text, String font)
    {
        g.setFont(Application.getStyleFont(font));
        return getTextWidth(g, text);
    }

    private static void write(Graphics g, String write, int posX, int posY, String align)
    {
        if(align == "CENTER") {posX = posX - (getTextWidth(g, write) / 2);}
        if(align == "RIGHT") {posX = posX - getTextWidth(g, write);}
        g.drawString(write, posX, posY);
    }

    public static void write(Graphics g, String write, int posX, int posY, String align, String font, String colour)
    {
        g.setFont(Application.getStyleFont(font));
        g.setColor(Application.getStyleColour(colour));
        write(g, write, posX, posY, align);
    }

    public static void write(Graphics g, String write, int posX, int posY, String align, StyleText font, StyleColour colour)
    {
        g.setFont(getFont(font));
        g.setColor(getColour(colour));
        write(g, write, posX, posY, align);
    }

    public static void writeShadow(Graphics g, String write, int posX, int posY, int offset, String align, String font, String colour, String shadow)
    {
        g.setFont(Application.getStyleFont(font));
        g.setColor(Application.getStyleColour(shadow));
        write(g, write, posX + offset, posY + offset, align);
        g.setColor(Application.getStyleColour(colour));
        write(g, write, posX, posY, align);
    }

    public static void writeShadow(Graphics g, String write, int posX, int posY, int offset, String align, StyleText font, StyleColour colour, StyleColour shadow)
    {
        g.setFont(getFont(font));
        g.setColor(getColour(shadow));
        write(g, write, posX + offset, posY + offset, align);
        g.setColor(getColour(colour));
        write(g, write, posX, posY, align);
    }

}