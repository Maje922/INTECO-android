package fr.eni.android.ch10_viewpagerexemple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by nazim on 25/11/14.
 */
public class MyFragment extends Fragment {

    public static final String EXTRA_TEXT = "EXTRA_TEXT";
    private String msg;

    public static Fragment newInstance(final String msg) {
        MyFragment f = new MyFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_TEXT, msg);
        f.setArguments(bdl);
        return f;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            msg = getArguments().getString(EXTRA_TEXT, "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_view_pager, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view,
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView messageTextView = (TextView) view.findViewById(R.id.msg_txt);
        messageTextView.setText(msg);
    }
}
