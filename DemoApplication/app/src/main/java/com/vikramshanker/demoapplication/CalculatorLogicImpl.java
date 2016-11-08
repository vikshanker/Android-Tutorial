package com.vikramshanker.demoapplication;

import static com.vikramshanker.demoapplication.Operator.NONE;

/**
 * Created by Vikram on 11/7/16.
 */

public class CalculatorLogicImpl implements CalculatorLogic{

    private Integer mLeft;
    private Integer mRight;
    private Operator op;
    private Integer result;

    public CalculatorLogicImpl() {
        mLeft = null;
        mRight = null;
        op = NONE;
        result = null;
    }

    public void reset() {
        mLeft = null;
        mRight = null;
        op = NONE;
        result = null;
    }

    public void buttonPressed(Operator o) {
        if (o == Operator.EQU) {
            evaluate();
        } else if (mRight == null){
            op = o;
        } else {
            evaluate();
            mLeft = result;
            mRight = null;
            op = o;
            result = null;
        }
    }

    public void buttonPressed(int i) {
        if (result != null) {
            reset();
        }
        if (op == NONE) {
            if (mLeft == null) {
                mLeft = i;
            } else {
                mLeft = mLeft * 10 + i;
            }
        } else {
            if (mRight == null) {
                mRight = i;
            } else {
                mRight = mRight * 10 + i;
            }
        }
    }

    public Integer evaluate() {
        if (mLeft == null || mRight == null || op == NONE) {
            reset();
            return null;
        }
        switch(op) {
            case ADD:
                result = mLeft + mRight;
                break;
            case MUL:
                result = mLeft * mRight;
                break;
            case SUB:
                result = mLeft - mRight;
                break;
            case DIV:
                // what is missing here?
                result = mLeft / mRight;
                break;
        }
        return null;
    }

    public String getDisplay() {
        if (result != null) {
            return Integer.toString(result);
        } else if (mRight != null) {
            return Integer.toString(mRight);
        } else if (mLeft != null) {
            return Integer.toString(mLeft);
        }
        return "Ready!";
    }

    @Override
    public String toString() {
        return "CalculatorLogic{" +
                "mLeft=" + mLeft +
                ", op=" + op +
                ", mRight=" + mRight +
                ", result=" + result +
                '}';
    }
}
