package com.vikramshanker.demoapplication;

/**
 * Created by Vikram on 11/7/16.
 */


public interface CalculatorLogic {
    void reset();
    void buttonPressed(Operator o);
    void buttonPressed(int i);
    Integer evaluate();
    String getDisplay();
}
