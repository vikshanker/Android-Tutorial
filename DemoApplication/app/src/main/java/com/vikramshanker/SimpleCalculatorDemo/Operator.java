package com.vikramshanker.SimpleCalculatorDemo;

/**
 * Created by Vikram on 11/7/16.
 */

public enum Operator {
    ADD, SUB, MUL, DIV, EQU, NONE;

    @Override
    public String toString() {
        switch (this) {
            case ADD:
                return "+";
            case SUB:
                return "-";
            case MUL:
                return "*";
            case DIV:
                return "/";
            case EQU:
                return "=";
            case NONE:
                return "NONE";
            default:
                throw new IllegalArgumentException("Called toString on invalid operator");
        }
    }
}
