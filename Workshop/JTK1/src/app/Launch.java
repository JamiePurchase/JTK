/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import engine.Application;
import states.StateTest;

/**
 *
 * @author Jamie
 */
public class Launch
{
    
    public static void main(String[] args)
    {
        new Application("HELLO", 1300, 640, "1.0", "").start(false);
        Application.stateNew(new StateTest());
    }
    
}