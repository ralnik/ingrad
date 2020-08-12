package ru.ralnik.ingrad.exception;

import android.app.Activity;

import ru.ralnik.ingrad.activity.exception.ExceptionActivity;

public class IngradException extends Exception {
    private Activity activity;
    private String message;

    public IngradException(Activity activity, String message) {
        this.activity = activity;
        this.message = message;
        showDialogException();
    }

    private void showDialogException() {
        new ExceptionActivity(activity, message);
    }
}
