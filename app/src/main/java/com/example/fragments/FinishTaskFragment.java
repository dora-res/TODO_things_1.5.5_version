package com.example.fragments;

import static android.content.Context.MODE_PRIVATE;

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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FinishTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinishTaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FinishTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FinishTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FinishTaskFragment newInstance(String param1, String param2) {
        FinishTaskFragment fragment = new FinishTaskFragment();
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
        View v =inflater.inflate(R.layout.fragment_finish_task, container, false);

        EditText new_finish_task = v.findViewById(R.id.finish_tasks);
        Button add_new_finish_task = v.findViewById(R.id.add_finished_task);
        Button delete_finished_tasks = v.findViewById(R.id.delete_finished_tasks);
        TextView finished_tasks = v.findViewById(R.id.finished_tasks);

        add_new_finish_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = new_finish_task.getText().toString() + '\n';
                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(requireContext()
                            .openFileOutput("listFinishedTasks.txt", MODE_PRIVATE)));
                    bw.write(a);
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(requireActivity()
                            .openFileInput("listFinishedTasks.txt")));
                    String str = "";
                    while ((str = br.readLine())!= null){
                        finished_tasks.append(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        delete_finished_tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finished_tasks.setText("");
                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(requireContext().openFileOutput("listFinishedTasks.txt", MODE_PRIVATE)));
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