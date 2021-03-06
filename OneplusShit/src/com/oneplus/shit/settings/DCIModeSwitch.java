/*
* Copyright (C) 2017 The OmniROM Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/
package com.oneplus.shit.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemProperties;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceManager;
import com.oneplus.shit.util.Utils;

public class DCIModeSwitch implements OnPreferenceChangeListener {

    private static final String FILE = "/sys/class/graphics/fb0/dci_p3";

    public static String getFile() {
        if (Utils.fileWritable(FILE)) {
            return FILE;
        }
        return null;
    }

    public static boolean isSupportedPanel() {
        //String panel = SystemProperties.get("ro.product.panel");
        //return panel != null && panel.contains("s6e3fa5");
        return true;
    }

    public static boolean isSupported() {
        return Utils.fileWritable(getFile());
    }

    public static boolean isCurrentlyEnabled(Context context) {
        return Utils.getFileValueAsBoolean(getFile(), false);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Boolean enabled = (Boolean) newValue;
        Utils.writeValue(getFile(), enabled ? "1" : "0");
        return true;
    }
}
