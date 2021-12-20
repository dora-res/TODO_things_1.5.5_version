package com.example.fragments;

import static android.app.PendingIntent.getActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends FragmentActivity {
    Button to_current_task, to_finished_task, to_repiters_task;
    FragmentManager fm;
    FragmentTransaction ft;
    Fragment fragment1, fragment2, fragment3;
    TextView finished_tasks, currentTask, repeat_tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        fragment1 = fm.findFragmentById(R.id.fragment_container);
        if(fragment1 == null){
            fragment1 = new CurrentTaskFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment1)
                    .commit();
        }

        to_current_task = findViewById(R.id.to_current_task);
        to_finished_task = findViewById(R.id.to_finished_task);
        to_repiters_task = findViewById(R.id.to_repiters_task);
        finished_tasks = findViewById(R.id.finished_tasks);
        currentTask = findViewById(R.id.current_tasks);

        to_finished_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment2 == null){
                    fragment2 = new FinishTaskFragment();
                }
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment2);
                ft.commit();
     /*           try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput ("listFinishedTasks.txt",
                            MODE_PRIVATE)));
                    bw.write(finished_tasks.getText().toString());
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });

        to_current_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment1 == null){
                    fragment1 = new CurrentTaskFragment();
                }
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment1);
                ft.commit();
          /*      String b = currentTask.getText().toString();
                currentTask.setText(b);
                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("CurrentTasks.txt", MODE_PRIVATE)));
                    bw.write("\n"+b);
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });

        to_repiters_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment3 == null){
                    fragment3 = new RepitersTasksFragment();
                }
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment3);
                ft.commit();
          /*      String str = repeat_tasks.getText().toString();
                repeat_tasks.setText(str);
                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("RepitersTasks.txt", MODE_PRIVATE)));
                    bw.write("\n"+str);
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });

    }
}
//TODO написать возврат к первому фрагменту DONE
//TODO добавить 3тий фрагмент для повторяющихся задач DONE
//TODO придумать способ хранения введенных пользователем задач
//TODO 3.6.2 на сайте порешать