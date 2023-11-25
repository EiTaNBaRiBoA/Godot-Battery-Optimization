package com.Utils.BatteryOptimization;

import android.app.Activity;
import android.content.Context;
import android.util.ArraySet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.waseemsabir.betterypermissionhelper.BatteryPermissionHelper;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;
import org.godotengine.godot.plugin.SignalInfo;
import org.godotengine.godot.plugin.UsedByGodot;

import java.util.Set;

public class MainClass extends GodotPlugin {
    private static String NamePlugin = "BatteryOptimization";
    private Activity pActivity;
    private Context context;
    private BatteryPermissionHelper batteryPermissionHelper = null;

    public MainClass(Godot godot) {
        super(godot);
    }

    @NonNull
    @Override
    public String getPluginName() {
        return NamePlugin;
    }


    @Nullable
    @Override
    public View onMainCreate(Activity activity) {
        pActivity = activity;
        context = activity.getApplicationContext();
        batteryPermissionHelper = BatteryPermissionHelper.Companion.getInstance();
        return super.onMainCreate(activity);
    }

    public boolean isBatteryOptimizationGranted() {
        if (batteryPermissionHelper.isBatterySaverPermissionAvailable(context, true)) {
            //TODO Check if granted
        }
        return false;
    }

    public void requestBatteryOptimization() {
        batteryPermissionHelper.getPermission(pActivity, true, true);
    }

    @NonNull
    @Override
    public Set<SignalInfo> getPluginSignals() {
        Set<SignalInfo> signals = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            signals = new ArraySet<>();
            signals.add(new SignalInfo("permission_battery_request_completed", String.class));
            return signals;
        } else
            return super.getPluginSignals();

    }

    @UsedByGodot
    public boolean isPermissionGranted() {
        return isBatteryOptimizationGranted();
    }

    @UsedByGodot
    public void requestBatteryPermission() {
        if (isPermissionGranted()) {
            requestBatteryOptimization();
            return;
        }

    }
}
