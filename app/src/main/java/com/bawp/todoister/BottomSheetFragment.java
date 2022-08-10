package com.bawp.todoister;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawp.todoister.model.Priority;
import com.bawp.todoister.model.Task;
import com.bawp.todoister.model.TaskViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;

import java.util.Calendar;
import java.util.Date;

import com.bawp.todoister.model.Task;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    private EditText enterTodo;
    private ImageButton calendarButton;
    private ImageButton priorityButton;
    private RadioGroup priorityRadioGroup;
    private RadioButton selectedRadioButton;
    private int selectedButtonId;
    private ImageButton saveButton;

    private Group calendarGroup;
    private CalendarView calendarView;
    Calendar calendar = Calendar.getInstance();
    Date dueDate;


    public BottomSheetFragment(){

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        enterTodo = view.findViewById(R.id.enter_todo_text);
        calendarButton = view.findViewById(R.id.today_calendar_button);
        priorityButton = view.findViewById(R.id.priority_todo_button);
        priorityRadioGroup = view.findViewById(R.id.radioGroup_priority);
        saveButton = view.findViewById(R.id.save_todo_button);
        calendarGroup = view.findViewById(R.id.calendar_group);
        calendarView = view.findViewById(R.id.calendar_view);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendarButton.setOnClickListener(view1 -> {
            calendarGroup.setVisibility(
                    calendarGroup.getVisibility() == View.GONE ? View.VISIBLE : View.GONE
            );
        });

        calendarView.setOnDateChangeListener((calendarView, year, month, date) -> {
            calendar.clear();
            calendar.set(year, month, date);
            dueDate = calendar.getTime();

        });

        saveButton.setOnClickListener(view1 -> {
            String taskText = enterTodo.getText().toString().trim();
            if(!TextUtils.isEmpty(taskText) && dueDate != null){
                Task task = new Task(taskText, Priority.HIGH, dueDate,
                        Calendar.getInstance().getTime(), false);
                TaskViewModel.insert(task);
            }

        });

    }
}