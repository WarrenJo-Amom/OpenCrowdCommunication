package in.amant.opencrowdcommunication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.amant.opencrowdcommunication.R;

public class Dashboard extends Fragment {

    public static Dashboard newInstance() {
        return new Dashboard();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false); // 여기서 UI를 생성해서 View를 return
    }

}