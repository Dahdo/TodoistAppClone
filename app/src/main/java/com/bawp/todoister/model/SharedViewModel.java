package com.bawp.todoister.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    MutableLiveData<Task> selectedItem = new MutableLiveData<Task>();
    boolean isEdit;

    public void setSelectedItem(Task task){
        selectedItem.setValue(task);
    }

    public MutableLiveData<Task> getSelectedItem() {
        return selectedItem;
    }
    public void setIsEdit(boolean value){
        isEdit = value;
    }
    public boolean getIsEdit(){
        return isEdit;
    }
}
