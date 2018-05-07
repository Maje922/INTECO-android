package fr.eni.android.ch10_viewpagerexemple;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class CustomValueFormatter implements IAxisValueFormatter {


    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return String.valueOf(value) + "M";
    }
}
