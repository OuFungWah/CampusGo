package com.example.fungwah.campusgo.common.picker;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by FungWah on 2017/11/18.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public MySetListener mySetListener = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),this,hour,minute, true);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (mySetListener != null) {
            mySetListener.onTimeSet(view, hourOfDay,minute);
        }
    }

    public void setMySetListener(MySetListener mySetListener) {
        this.mySetListener = mySetListener;
    }

    public interface MySetListener {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute);
    }

}
