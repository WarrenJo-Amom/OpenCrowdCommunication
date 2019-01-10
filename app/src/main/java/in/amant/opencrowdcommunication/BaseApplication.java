package in.amant.opencrowdcommunication;

import android.app.Application;

import com.estimote.coresdk.common.config.EstimoteSDK;

import in.amant.opencrowdcommunication.beacon.BeaconID;
import in.amant.opencrowdcommunication.beacon.BeaconNotificationsManager;

public class BaseApplication extends Application{

    private boolean beaconNotificationsEnabled = false;

    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: put your App ID and App Token here
        // You can get them by adding your app on https://cloud.estimote.com/#/apps
        EstimoteSDK.initialize(getApplicationContext(), "beacontest-fqo", "9931f6415aaafe42b4ed378bb6130227");

        // uncomment to enable debug-level logging
        // it's usually only a good idea when troubleshooting issues with the Estimote SDK
        EstimoteSDK.enableDebugLogging(true);
    }

    public void enableBeaconNotifications() {
        if (beaconNotificationsEnabled) { return; }

        BeaconNotificationsManager beaconNotificationsManager = new BeaconNotificationsManager(this);
        beaconNotificationsManager.addNotification(
                // TODO: replace with UUID, major and minor of your own beacon
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 10195, 25512),
                "모두모임방1에 들어오셨습니다.",
                "모두모임방1에서 나가셨습니다.");
        beaconNotificationsManager.startMonitoring();

        BeaconNotificationsManager beaconNotificationsManager2 = new BeaconNotificationsManager(this);
        beaconNotificationsManager2.addNotification(
                // TODO: replace with UUID, major and minor of your own beacon
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 23208, 60507),
                "상상의숲에 들어오셨습니다.",
                "상상의숲에서 나가셨습니다.");
        beaconNotificationsManager2.startMonitoring();

        beaconNotificationsEnabled = true;
    }

    public boolean isBeaconNotificationsEnabled() {
        return beaconNotificationsEnabled;
    }

}
