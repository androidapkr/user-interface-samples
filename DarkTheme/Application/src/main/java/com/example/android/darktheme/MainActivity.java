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

package com.example.android.darktheme;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import com.example.android.darktheme.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationListener = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                showFragment(WelcomeFragment.TAG);
                return true;
            case R.id.navigation_preferences:
                showFragment(PreferencesFragment.TAG);
                return true;
            case R.id.navigation_settings:
                showFragment(SettingsFragment.TAG);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationListener);

        if (savedInstanceState == null) {
            showFragment(WelcomeFragment.TAG);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.action_more);
        Drawable drawableWrap = DrawableCompat.wrap(item.getIcon()).mutate();
        DrawableCompat.setTint(drawableWrap, ColorUtils.getThemeColor(this, R.attr.colorOnPrimary));
        item.setIcon(drawableWrap);

        MenuItem itemSort = menu.findItem(R.id.action_sort);
        Drawable drawableSort = DrawableCompat.wrap(itemSort.getIcon()).mutate();
        DrawableCompat.setTint(drawableSort, ColorUtils.getThemeColor(this, R.attr.colorOnPrimary));
        itemSort.setIcon(drawableSort);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_more) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFragment(@NonNull String tag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            switch (tag) {
                case WelcomeFragment.TAG: {
                    fragment = new WelcomeFragment();
                    break;
                }
                case PreferencesFragment.TAG: {
                    fragment = new PreferencesFragment();
                    break;
                }
                case SettingsFragment.TAG: {
                    fragment = new SettingsFragment();
                    break;
                }
                default: {
                    break;
                }
            }
        }
        if (fragment == null) return;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, fragment, tag)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
