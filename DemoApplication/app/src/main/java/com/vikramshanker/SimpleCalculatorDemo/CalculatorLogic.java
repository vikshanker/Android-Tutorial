package com.vikramshanker.SimpleCalculatorDemo;

import static com.vikramshanker.SimpleCalculatorDemo.Operator.NONE;

/**
 * Created by Vikram on 11/7/16.
 */

public class CalculatorLogic {

    private Integer mLeft;
    private Integer mRight;
    private Operator mOp;
    private Integer mResult;

    public CalculatorLogic() {
        mLeft = null;
        mRight = null;
        mOp = NONE;
        mResult = null;
    }

    public void reset() {
        mLeft = null;
        mRight = null;
        mOp = NONE;
        mResult = null;
    }

    public void buttonPressed(Operator o) {
        if (o == Operator.EQU) {
            evaluate();
        } else if (mRight == null){
            mOp = o;
        } else {
            evaluate();
            mLeft = mResult;
            mRight = null;
            mOp = o;
            mResult = null;
        }
    }

    public void buttonPressed(int i) {
        if (mResult != null) {
            reset();
        }
        if (mOp == NONE) {
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
        if (mLeft == null || mRight == null || mOp == NONE) {
            reset();
            return null;
        }
        switch(mOp) {
            case ADD:
                mResult = mLeft + mRight;
                break;
            case MUL:
                mResult = mLeft * mRight;
                break;
            case SUB:
                mResult = mLeft - mRight;
                break;
            case DIV:
                // what is missing here?
                mResult = mLeft / mRight;
                break;
        }
        return null;
    }

    public String getDisplay() {
        if (mResult != null) {
            return Integer.toString(mResult);
        } else if (mLeft != null && mOp != NONE && mRight != null) {
            return Integer.toString(mLeft) + mOp.toString() + Integer.toString(mRight);
        } else if (mLeft != null && mOp != NONE) {
            return Integer.toString(mLeft) + mOp.toString();
        } else if (mLeft != null) {
            return Integer.toString(mLeft);
        }
        return "Ready!";
    }

    @Override
    public String toString() {
        return "CalculatorLogic{" +
                "mLeft=" + mLeft +
                ", mOp=" + mOp +
                ", mRight=" + mRight +
                ", mResult=" + mResult +
                '}';
    }
}
