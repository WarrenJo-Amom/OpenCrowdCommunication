package in.amant.opencrowdcommunication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import in.amant.opencrowdcommunication.R;

public class MyProfileActivity extends BaseActivity {

    Button button;
    RadioGroup radioGroup;
    ImageView imageView;
    EditText editText_name;
    EditText editText_org;
    EditText editText_rank;
    EditText editText_age;
    RadioButton radioButton_male;
    RadioButton radioButton_female;

    TextView textView_eventName;

    String event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        setTitle("프로필 등록");
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_clear_white_18dp);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        event = intent.getExtras().getString("event");

        textView_eventName = (TextView) findViewById(R.id.textView_eventName);
        textView_eventName.setText(event);

        imageView = (ImageView) findViewById(R.id.imageView2);

        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_org = (EditText) findViewById(R.id.editText_org);
        editText_rank = (EditText) findViewById(R.id.editText_rank);
        editText_age = (EditText) findViewById(R.id.editText_age);

        radioButton_male = (RadioButton) findViewById(R.id.radioButton);
        radioButton_female = (RadioButton) findViewById(R.id.radioButton2);

        radioGroup = (RadioGroup) findViewById(R.id.radio_sex);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioButton) {
                    imageView.setImageResource(R.drawable.man);
                } else {
                    imageView.setImageResource(R.drawable.woman);
                }
            }
        });

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // User Name
                SharedPreferences pref = getSharedPreferences(event, MODE_PRIVATE);
                //String userNameTemp = pref.getString("userName", "null");
                //if(userNameTemp.equals("null")) {
                //userName = "user" + new Random().nextInt(10000);
                String userName = editText_name.getText().toString();
                String userOrg = editText_org.getText().toString();
                String userRank = editText_rank.getText().toString();
                String userSex;
                if(radioButton_male.isChecked()) {
                    userSex = "male";
                } else {
                    userSex = "female";
                }
                int userAge = 0;
                try {
                    userAge = Integer.valueOf(editText_age.getText().toString());
                } catch (Exception e) {

                }
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("user", userName);
                editor.putString("org", userOrg);
                editor.putString("rank", userRank);
                editor.putString("userSex", userSex);
                editor.putInt("userAge", userAge);
                editor.commit();
                /*} else {
                    userName = userNameTemp;
                }*/

                if(TextUtils.isEmpty(userName)) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    setResult(RESULT_OK);
                    finish();
                }
//                startActivity(new Intent(getApplicationContext(), BottomNaviActivity.class));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}