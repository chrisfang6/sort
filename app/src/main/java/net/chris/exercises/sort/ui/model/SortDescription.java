package net.chris.exercises.sort.ui.model;

import android.databinding.ObservableField;

public class SortDescription {

    public final ObservableField<String> sortTypeName = new ObservableField<>();

    public SortDescription(String typeName) {
        sortTypeName.set(typeName);
    }
}
