package com.example.fungwah.campusgo.common.picker;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by FungWah on 2017/11/18.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public MySetListener mySetListener = null;
    private Date date = null;

    public DatePickerFragment() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int year;
        int month;
        int day;
        if (date == null) {
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
            date = new Date(year - 1900, month, day);
        } else {
            year = date.getYear() + 1900;
            month = date.getMonth() + 1;
            day = date.getDate();
        }
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (mySetListener != null) {
            setDate(new Date(year - 1900, month - 1, dayOfMonth));
            mySetListener.onDateSet(view, year, month, dayOfMonth);
        }
    }

    public void setMySetListener(MySetListener mySetListener) {
        this.mySetListener = mySetListener;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public interface MySetListener {
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth);
    }

}
