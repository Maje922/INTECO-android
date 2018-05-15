package fr.eni.android.ch10_viewpagerexemple;

import com.github.mikephil.charting.components.AxisBase;

import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class CustomValueFormatter implements IAxisValueFormatter {


    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return String.valueOf(value) + "€/M";
    }
}
