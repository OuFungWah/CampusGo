package com.example.fungwah.campusgo.command.picker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by FungWah on 2017/11/18.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public MySetListener mySetListener = null;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (mySetListener != null) {
            mySetListener.onDateSet(view, year, month, dayOfMonth);
        }
    }

    public void setMySetListener(MySetListener mySetListener) {
        this.mySetListener = mySetListener;
    }

    public interface MySetListener {
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth);
    }

}
