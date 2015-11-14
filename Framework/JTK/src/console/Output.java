/*
 * JTK Framework
 * Version 1
 * Jamie Purchase
 */
package console;

/**
 *
 * @author Jamie
 */
public class Output
{
    
    public static void logo()
    {
        // Logo
        String bs = new String("\\");
        
        
        String[] tk1 = new String[9];
        tk1[0] = "       __ ";
        tk1[1] = "      |__|";
        tk1[2] = "       __ ";
        tk1[3] = "      |  |";
        tk1[4] = "   _  |  |";
        tk1[5] = "  / " + bs + "/  / ";
        tk1[6] = "  " + bs + "____/  ";
        tk1[7] = "";
        tk1[8] = "";
        
        String[] tk = new String[9];
        tk[0] = "    __     __   ___";
        tk[1] = "   |  |   |  | /  /";
        tk[2] = "  _|  |_  |  |/  / ";
        tk[3] = " |_    _| |     /  ";
        tk[4] = "   |  |   |     " + bs + "  ";
        tk[5] = "   |  |_  |  |" + bs + "  " + bs + " ";
        tk[6] = "   |____| |__| " + bs + "__" + bs;
        tk[7] = "";
        tk[8] = "     version 1.0";
        
        // Output
        print();
        for(int x = 0; x < tk.length; x++) {print(tk1[x] + tk[x]);}
        print();
    }
    
    private static void print()
    {
        print("");
    }
    
    public static void print(Object object)
    {
        System.out.println(object);
    }

}