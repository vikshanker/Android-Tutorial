package com.vikramshanker.demoapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String STR_ADD = "+";
    private static final String STR_SUB = "-";
    private static final String STR_MUL = "*";
    private static final String STR_DIV = "/";
    private static final String STR_EQU = "=";

    private Button buttonZero;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private Button buttonFive;
    private Button buttonSix;
    private Button buttonSeven;
    private Button buttonEight;
    private Button buttonNine;
    private Button buttonAdd;
    private Button buttonSub;
    private Button buttonMul;
    private Button buttonDiv;
    private Button buttonEqu;
    private Button buttonClear;
    private TextView mResult;

    /* Expression information */
    private boolean newNumber = true;
    private long mPreviousValue;
    private Operator mLastOp = Operator.EQU;
    private long mCurrentValue;

    private enum Operator {
        ADD, SUB, MUL, DIV, EQU
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setListeners();
    }

    private void findViews() {
        buttonZero = (Button) findViewById(R.id.button_0);
        buttonOne = (Button) findViewById(R.id.button_1);
        buttonTwo = (Button) findViewById(R.id.button_2);
        buttonThree = (Button) findViewById(R.id.button_3);
        buttonFour = (Button) findViewById(R.id.button_4);
        buttonFive = (Button) findViewById(R.id.button_5);
        buttonSix = (Button) findViewById(R.id.button_6);
        buttonSeven = (Button) findViewById(R.id.button_7);
        buttonEight = (Button) findViewById(R.id.button_8);
        buttonNine = (Button) findViewById(R.id.button_9);
        buttonAdd = (Button) findViewById(R.id.button_add);
        buttonSub = (Button) findViewById(R.id.button_sub);
        buttonMul = (Button) findViewById(R.id.button_mul);
        buttonDiv = (Button) findViewById(R.id.button_div);
        buttonEqu = (Button) findViewById(R.id.button_equals);
        buttonClear = (Button) findViewById(R.id.button_clear);
        mResult = (TextView) findViewById(R.id.answer_text);
    }

    private void setListeners() {
        buttonZero.setOnClickListener(this);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonSix.setOnClickListener(this);
        buttonSeven.setOnClickListener(this);
        buttonEight.setOnClickListener(this);
        buttonNine.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonEqu.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
    }

    private void updateDisplay() {
        mResult.setText(Long.toString(mCurrentValue));
    }

    private void clearValue() {
        mPreviousValue = 0;
        mCurrentValue = 0;
        updateDisplay();
    }

    private void addDigitToResult(View v) {
        mCurrentValue = (newNumber) ? 0 : mCurrentValue;
        Button buttonView = (Button) v;
        String newDigitString = (String) buttonView.getText();
        int newDigit = Integer.parseInt(newDigitString);
        long newValue = 10 * mCurrentValue + newDigit;
        // update current values and view
        mCurrentValue = newValue;
        updateDisplay();
        newNumber = false;
    }

    private Operator getOperatorFromView(View v) {
        Button buttonView = (Button) v;
        String buttonText = (String) buttonView.getText();
        switch (buttonText) {
            case STR_ADD:
                return Operator.ADD;
            case STR_SUB:
                return Operator.SUB;
            case STR_MUL:
                return Operator.MUL;
            case STR_DIV:
                return Operator.DIV;
            default:
                return Operator.EQU;
        }
    }

    private void applyOperatorToExpression(View v) {
        String errMsg = "Can't divide by 0!";
        Operator op = getOperatorFromView(v);
        // only store previous value for same numbers
        if (!newNumber) {
            // case on previous operator to update current value
            switch (mLastOp) {
                case ADD:
                    mCurrentValue += mPreviousValue;
                    break;
                case SUB:
                    mCurrentValue = mPreviousValue - mCurrentValue;
                    break;
                case MUL:
                    mCurrentValue *= mPreviousValue;
                    break;
                case DIV:
                    // division by 0 error
                    if (mCurrentValue == 0) {
                        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
                    } else {
                        mCurrentValue = mPreviousValue / mCurrentValue;
                    }
                    break;
                default:
                    break;
            }
            mPreviousValue = mCurrentValue;
            newNumber = true;
        }
        mLastOp = op;
        updateDisplay();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
                addDigitToResult(v);
                break;
            case R.id.button_clear:
                clearValue();
                break;
            case R.id.button_add:
            case R.id.button_sub:
            case R.id.button_mul:
            case R.id.button_div:
            case R.id.button_equals:
                applyOperatorToExpression(v);
                break;
            default:
                break;
        }
    }
}
