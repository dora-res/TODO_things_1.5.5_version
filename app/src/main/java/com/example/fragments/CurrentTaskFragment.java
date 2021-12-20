package com.example.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentTaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CurrentTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentTaskFragment newInstance(String param1, String param2) {
        CurrentTaskFragment fragment = new CurrentTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_current_task, container, false);
        EditText newTask = v.findViewById(R.id.new_task);
        Button addTask = v.findViewById(R.id.add_task);
        Button deletTasks = v.findViewById(R.id.delete_current_task);
        TextView currentTask = v.findViewById(R.id.current_tasks);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = newTask.getText().toString() + '\n';
                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(requireContext()
                            .openFileOutput("listCurrentTasks.txt", MODE_PRIVATE)));
                    bw.write(a);
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(requireActivity()
                            .openFileInput("listCurrentTasks.txt")));
                    String str = "";
                    while ((str = br.readLine())!= null){
                        currentTask.append(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deletTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTask.setText("");
                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(getContext().openFileOutput("CurrentTasks.txt", MODE_PRIVATE)));
                    bw.write("");
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }



}


/*

     Resources res = getResources();

    @SuppressLint("RestrictedApi")
    private void readFile() {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput ("currentTasks.txt")));
            String str = "";
            //str = br.readLine();
            while ((str = br.readLine()) != null) {
                readInfo.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream openFileInput(String s) {
    }

    private void writeFile() {
        try {

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput ("currentTasks.txt",
                    MODE_PRIVATE)));
            bw.write(writeInfo.getText().toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

}

    private OutputStream openFileOutput(String s, int modePrivate) {
    }

 */