package com.example.zaliczenie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class CalculatorActivity extends Fragment {
    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, button10, buttonC, buttonEqual;
    EditText crunchifyEditText;

    String currentOperations = "";

    List<Result> mResults = new ArrayList<>();

    float mValueOne, mValueTwo;

    boolean crunchifyAddition, mSubtract, crunchifyMultiplication, crunchifyDivision;


    CalculatorActivity(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calc_activity,container,false);

        button0 = (Button) view.findViewById(R.id.button0);
        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button4 = (Button) view.findViewById(R.id.button4);
        button5 = (Button) view.findViewById(R.id.button5);
        button6 = (Button) view.findViewById(R.id.button6);
        button7 = (Button) view.findViewById(R.id.button7);
        button8 = (Button) view.findViewById(R.id.button8);
        button9 = (Button) view.findViewById(R.id.button9);
        button10 = (Button) view.findViewById(R.id.button10);
        buttonAdd = (Button) view.findViewById(R.id.buttonadd);
        buttonSub = (Button) view.findViewById(R.id.buttonsub);
        buttonMul = (Button) view.findViewById(R.id.buttonmul);
        buttonDivision = (Button) view.findViewById(R.id.buttondiv);
        buttonC = (Button) view.findViewById(R.id.buttonC);
        buttonEqual = (Button) view.findViewById(R.id.buttoneql);
        crunchifyEditText = (EditText) view.findViewById(R.id.edt1);


        AppDatabase appDatabase = Room.databaseBuilder(view.getContext(),AppDatabase.class,"database").
                allowMainThreadQueries().build();

        ResultDao resultDao = appDatabase.resultDao();
        mResults = resultDao.getResults();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + "0");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (crunchifyEditText == null) {
                    crunchifyEditText.setText("");
                } else {
                    mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                    crunchifyAddition = true;
                    crunchifyEditText.setText(null);
                    currentOperations = currentOperations + mValueOne + " + ";
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                mSubtract = true;
                crunchifyEditText.setText(null);
                currentOperations = currentOperations + mValueOne + " - ";
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                crunchifyMultiplication = true;
                crunchifyEditText.setText(null);
                currentOperations = currentOperations + mValueOne + " x ";
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(crunchifyEditText.getText() + "");
                crunchifyDivision = true;
                crunchifyEditText.setText(null);
                currentOperations = currentOperations + mValueOne + " / ";
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueTwo = Float.parseFloat(crunchifyEditText.getText() + "");

                if (crunchifyAddition == true) {
                    String res = mValueOne + mValueTwo + "";
                    crunchifyEditText.setText(res);
                    crunchifyAddition = false;
                    Result result = new Result(currentOperations + mValueTwo,res);
                    resultDao.insert(result);
                    mResults.add(result);
                    currentOperations = "";
                }

                if (mSubtract == true) {
                    String res = mValueOne - mValueTwo + "";
                    crunchifyEditText.setText(res);
                    mSubtract = false;
                    Result result = new Result(currentOperations + mValueTwo,res);
                    resultDao.insert(result);
                    mResults.add(result);
                    currentOperations = "";
                }

                if (crunchifyMultiplication == true) {
                    String res = mValueOne * mValueTwo + "";
                    crunchifyEditText.setText(res);
                    crunchifyMultiplication = false;
                    Result result = new Result(currentOperations + mValueTwo,res);
                    resultDao.insert(result);
                    mResults.add(result);
                    currentOperations = "";
                }

                if (crunchifyDivision == true) {
                    String res = mValueOne / mValueTwo + "";
                    crunchifyEditText.setText(res);
                    crunchifyDivision = false;
                    Result result = new Result(currentOperations + mValueTwo,res);
                    resultDao.insert(result);
                    mResults.add(result);
                    currentOperations = "";
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText("");
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.setText(crunchifyEditText.getText() + ".");
            }
        });

        return view;
    }
}
