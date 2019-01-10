package in.amant.opencrowdcommunication.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.activity.BaseActivity;
import in.amant.opencrowdcommunication.activity.SpotActivity;


public class AlertDialogActivity extends AppCompatActivity {

    private String notiMessage;



    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Bundle bun = getIntent().getExtras();

        notiMessage = bun.getString("notiMessage");





//        setContentView(R.layout.alertdialog);



/*        TextView adMessage = (TextView)findViewById(R.id.message);

        adMessage.setText(notiMessage);



        Button adButton = (Button)findViewById(R.id.submit);



        adButton.setOnClickListener(new SubmitOnClickListener());*/
















        startActivity(new Intent(this, MyAlert.class));
        finish();




    }

    private class SubmitOnClickListener implements View.OnClickListener {



        public void onClick(View v) {

            finish();



        }

    }
}
