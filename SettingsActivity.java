package com.example.quran;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // تأكد من أن Toolbar موجود في الملف XML وتم تهيئته بشكل صحيح
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // الحصول على ActionBar والتحقق من عدم كونه null
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            // تفعيل/إيقاف الوضع الليلي
            SwitchPreferenceCompat darkModePref = findPreference("dark_mode");
            if (darkModePref != null) {
                darkModePref.setOnPreferenceChangeListener((preference, newValue) -> {
                    boolean isDarkMode = (Boolean) newValue;
                    if (isDarkMode) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                    return true;
                });
            }

            // إعدادات الحساب
            Preference accountSettingsPref = findPreference("account_settings");
            if (accountSettingsPref != null) {
                accountSettingsPref.setOnPreferenceClickListener(preference -> {
                    // اضف هنا الاجراء المطلوب لإدارة الحساب
                    Toast.makeText(getContext(), "إدارة الحساب", Toast.LENGTH_SHORT).show();
                    return true;
                });
            }

            // إعدادات التنبيهات
            Preference notificationsPref = findPreference("notifications");
            if (notificationsPref != null) {
                notificationsPref.setOnPreferenceClickListener(preference -> {
                    // اضف هنا الاجراء المطلوب لإعدادات التنبيهات
                    Toast.makeText(getContext(), "إعدادات التنبيهات", Toast.LENGTH_SHORT).show();
                    return true;
                });
            }

            // معلومات التطبيق
            Preference appInfoPref = findPreference("app_info");
            if (appInfoPref != null) {
                appInfoPref.setOnPreferenceClickListener(preference -> {
                    // اضف هنا الاجراء المطلوب لعرض معلومات التطبيق
                    Toast.makeText(getContext(), "معلومات التطبيق", Toast.LENGTH_SHORT).show();
                    return true;
                });
            }
        }
    }
}