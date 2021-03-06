/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.darktheme.settings;

import android.os.Bundle;
import android.util.Log;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.android.darktheme.DarkThemeApplication;
import com.example.android.darktheme.R;
import com.example.android.darktheme.ThemeHelper;

public class SettingsFragment extends PreferenceFragmentCompat {

    public static final String TAG = "SettingsFragmentTag";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        ListPreference themePreference = findPreference("themePref");
        if (themePreference != null) {
            themePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (preference.getKey().equals("themePref")) {
                        String themeOption = (String) newValue;
                        Log.d("_TAG_", "SettingsFragment: 44 : Theme " + themeOption);
                        DarkThemeApplication.saveTheme(getContext(), themeOption);
                        ThemeHelper.applyTheme(themeOption);
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        Log.d("_TAG_", "SettingsFragment: 41 : " + preference.getKey() + " : " + preference.getTitle());
        return true;
    }

}
