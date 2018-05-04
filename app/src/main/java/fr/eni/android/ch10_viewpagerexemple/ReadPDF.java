package fr.eni.android.ch10_viewpagerexemple;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class ReadPDF extends Fragment {

    ListView listView;
    String[] elementos = {"elemento1", "elemento2", "elemento3"};

    public ReadPDF() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_pd, container, false);
        listView = (ListView) view.findViewById(R.id.lista);

        ArrayList<String> lista = new ArrayList<>();
            lista.add("PDF1");
            lista.add("PDF2");
            lista.add("PDF3");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, elementos);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setDataAndType(Uri.parse("http://nimoz.pl/files/publications/2/loremipsum.pdf"), "application/pdf");
                startActivity(intent);

            }
        });

        return view;
    }


}






