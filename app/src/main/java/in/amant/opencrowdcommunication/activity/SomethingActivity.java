package in.amant.opencrowdcommunication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import in.amant.opencrowdcommunication.R;

public class SomethingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_something);
        setTitle("2018 마을공동체 활성화 방안 토론회");
    }
}
