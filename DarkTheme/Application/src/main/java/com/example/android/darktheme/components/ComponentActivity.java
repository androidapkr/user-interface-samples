package com.example.android.darktheme.components;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.android.darktheme.DarkThemeApplication;
import com.example.android.darktheme.R;
import com.example.android.darktheme.ThemeHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.ProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.android.darktheme.ThemeHelper.DARK_MODE;
import static com.example.android.darktheme.ThemeHelper.LIGHT_MODE;

public class ComponentActivity extends AppCompatActivity {
    TextInputEditText textInputLayoutEdit;
    TextInputLayout textInputLayout;
    ProgressIndicator myProgressBarCircleDeterminate;
    int progress = 90;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setSelectedItemId(DarkThemeApplication.getThemePref(getApplicationContext()).equals(LIGHT_MODE) ? R.id.navigation_light : R.id.navigation_dark);

        bottomNavigationView.setOnNavigationItemReselectedListener(this::onBottomNavThemeChanged);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            onBottomNavThemeChanged(item);
            return true;
        });

        textInputLayout = findViewById(R.id.text_input_layout_outline_box);

        textInputLayoutEdit = findViewById(R.id.text_input_edit_layout_outline_box);
        textInputLayoutEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInputLayout.setErrorEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validateEditText(editable);
            }
        });
        textInputLayoutEdit.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validateEditText(((EditText) v).getText());
            }
        });


        myProgressBarCircleDeterminate = findViewById(R.id.myProgressBarCircleDeterminate);
        myProgressBarCircleDeterminate.setProgress(progress);

        SeekBar seekBarForProgressBar = findViewById(R.id.seekbar_for_progress);
        seekBarForProgressBar.setProgress(progress);
        seekBarForProgressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                myProgressBarCircleDeterminate.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void validateEditText(Editable s) {
        if (!TextUtils.isEmpty(s)) {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        } else if (s.toString().trim().length() >= 3 && s.toString().trim().length() <= 6) {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
        } else {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(getString(R.string.edit_text_invalid_message));
        }
    }

    private void onBottomNavThemeChanged(MenuItem item) {
        if (item.getItemId() == R.id.navigation_light) {
            DarkThemeApplication.saveTheme(getApplicationContext(), LIGHT_MODE);
            ThemeHelper.applyTheme(LIGHT_MODE);
        } else if (item.getItemId() == R.id.navigation_dark) {
            ThemeHelper.applyTheme(DARK_MODE);
            DarkThemeApplication.saveTheme(getApplicationContext(), DARK_MODE);
        }
    }
}