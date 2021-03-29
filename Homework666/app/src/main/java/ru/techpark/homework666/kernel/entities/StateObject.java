package ru.techpark.homework666.kernel.entities;

public class StateObject {
    private Integer number;

    public StateObject(Integer number)  {
        this.number = number;
    }

    public int specifyColor() {
        return number % 2 == 0 ? android.R.color.holo_red_dark : android.R.color.holo_blue_dark;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
