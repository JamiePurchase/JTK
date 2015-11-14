/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import engine.FormApplication;
import forms.FormTest;

/**
 *
 * @author Jamie
 */
public class Launch
{
    
    public static void main(String[] args)
    {
        new FormApplication("FORM TEST", 600, 300, "1.0", "").start(false);
        FormApplication.setTitlebar(null);
        FormApplication.setForm(new FormTest());
    }
    
}
