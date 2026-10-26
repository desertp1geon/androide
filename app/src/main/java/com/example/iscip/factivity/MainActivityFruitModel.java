
package com.example.iscip.factivity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.iscip.BR;

public class MainActivityFruitModel extends BaseObservable {

    private String name = "";
    private String type = "";
    private String description = "";
    private String calories = "";

    private boolean navigateToNext = false;
    private boolean showError = false;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
        notifyPropertyChanged(BR.calories);
    }

    public boolean isNavigateToNext() {
        return navigateToNext;
    }

    public void setNavigateToNext(boolean navigateToNext) {
        this.navigateToNext = navigateToNext;
    }

    public boolean isShowError() {
        return showError;
    }

    public void setShowError(boolean showError) {
        this.showError = showError;
    }
}
