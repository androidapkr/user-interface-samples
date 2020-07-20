package com.example.android.darktheme.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;

public class CustomMaterialToggleButton extends MaterialButton {
    public CustomMaterialToggleButton(@NonNull Context context) {
        super(context);
    }

    public CustomMaterialToggleButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMaterialToggleButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void toggle() {
        if (!isChecked())
            super.toggle();
    }
}
