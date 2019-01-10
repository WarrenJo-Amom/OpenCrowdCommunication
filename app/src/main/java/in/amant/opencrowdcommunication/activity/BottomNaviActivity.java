package in.amant.opencrowdcommunication.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.adapter.ImageSliderAdapter;
import in.amant.opencrowdcommunication.fragment.Chat;
import in.amant.opencrowdcommunication.fragment.Dashboard;
import in.amant.opencrowdcommunication.fragment.Home;
import in.amant.opencrowdcommunication.fragment.People;
import in.amant.opencrowdcommunication.model.Event_varOfDistrict;
import in.amant.opencrowdcommunication.model.UserData;
import in.amant.opencrowdcommunication.viewPager.ImageViewPager;

public class BottomNaviActivity extends BaseActivity {

    static final int REQUEST_PROFILE = 200;

    String event;
    Event_varOfDistrict object;

    SharedPreferences pref;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    String key;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(Home.newInstance());
                    return true;
                case R.id.navigation_people:
                    Fragment peopleFragment = People.newInstance();
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("event", event);
                    peopleFragment.setArguments(bundle2);
                    replaceFragment(peopleFragment);
                    return true;
                case R.id.navigation_chat:
                    Fragment chatFragment = Chat.newInstance();
                    Bundle bundle = new Bundle();
                    bundle.putString("event", event);
                    bundle.putSerializable("object", object);
                    chatFragment.setArguments(bundle);
                    replaceFragment(chatFragment);
                    return true;
                case R.id.navigation_dashboard:
                    replaceFragment(Dashboard.newInstance());
                    return true;
            }
            return false;
        }
    };

    // Fragment 변환을 해주기 위한 부분, Fragment의 Instance를 받아서 변경
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navi);

        Intent intent = getIntent();
        event = intent.getExtras().getString("event");
        object = (Event_varOfDistrict) intent.getSerializableExtra("object");
        setTitle(event);

        // TODO: 2018-10-08 기존 등록된 프로필 있는지 없는지(프리퍼런스 체크)
        pref = getSharedPreferences(event, MODE_PRIVATE);
        String user = pref.getString("user", "");
        String org = pref.getString("org", "");
        String rank = pref.getString("rank", "");
        if(TextUtils.isEmpty(user)) {
            // 프로필 없으면,
            Intent startIntent = new Intent(this, MyProfileActivity.class);
            startIntent.putExtra("event", event);
            startActivityForResult(startIntent, REQUEST_PROFILE);
        } else {
            // 프로필 있으면,

            // TODO: 2018-12-06 서버에 참여자 프로필 등록 및 삭제


            Toast.makeText(getApplicationContext(), user + "(" + rank + ", " + org + ")님이 입장하셨습니다.", Toast.LENGTH_SHORT).show();

            UserData userData = new UserData(user, org, rank);  // 유저 이름과 메세지로 chatData 만들기
//            databaseReference.child("user").child(event).push().setValue(userData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
            DatabaseReference keyRef = databaseReference.child("user").child(event).push();
            key = keyRef.getKey();
            keyRef.setValue(userData);

            // TODO: 2018-10-08 프래그먼트 셋팅
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, Home.newInstance()).commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_PROFILE) {
            if(resultCode == RESULT_OK) {
                // TODO: 2018-10-09
                pref = getSharedPreferences(event, MODE_PRIVATE);
                String user = pref.getString("user", "");
                String org = pref.getString("org", "");
                String rank = pref.getString("rank", "");
                Toast.makeText(getApplicationContext(), user + "(" + rank + ", " + org + ")님이 입장하셨습니다.", Toast.LENGTH_SHORT).show();

                UserData userData = new UserData(user, org, rank);  // 유저 이름과 메세지로 chatData 만들기
//                databaseReference.child("user").child(event).push().setValue(userData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
//                databaseReference.child("user").child(event).push();
                DatabaseReference keyRef = databaseReference.child("user").child(event).push();
                key = keyRef.getKey();
                keyRef.setValue(userData);
            } else if(resultCode == RESULT_CANCELED) {
                finish();
            }

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        databaseReference.child("user").child(event).child(key).removeValue();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exit_event, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_exit:

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        BottomNaviActivity.this);

                // 제목셋팅
                alertDialogBuilder.setTitle(event);

                // AlertDialog 셋팅
                alertDialogBuilder
//                                .setMessage("해당 소통공간에 입장하시겠습니까?")
//                                .setCancelable(false)
                        .setPositiveButton("퇴장",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 해당 장소 액티비티로 이동
                                                /*Intent intent = new Intent(context, SpotActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.putExtra("spot", spot.getSpot());
                                                context.startActivity(intent);*/
                                        finish();
                                        pref = getSharedPreferences(event, MODE_PRIVATE);
                                        String user = pref.getString("user", "");
                                        String org = pref.getString("org", "");
                                        String rank = pref.getString("rank", "");
                                        databaseReference.child("user").child(event).child(key).removeValue();
                                        Toast.makeText(BottomNaviActivity.this, "퇴장 완료", Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소한다
                                        dialog.cancel();
                                    }
                                });
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View alertDialogView = layoutInflater.inflate(R.layout.exit_dialog, null);

                // 이미지 슬라이드 어댑터
                ImageSliderAdapter imageliderAdapter;
                ImageViewPager imageViewPager;

                imageViewPager = (ImageViewPager) alertDialogView.findViewById(R.id.viewpager);
                imageliderAdapter = new ImageSliderAdapter(BottomNaviActivity.this);
                imageViewPager.setAdapter(imageliderAdapter);

                // 페이저 인디케이터
                TabLayout tabLayout = (TabLayout)alertDialogView.findViewById(R.id.tab_layout);
                tabLayout.setupWithViewPager(imageViewPager, true);

                alertDialogBuilder.setView(alertDialogView);

                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                // 다이얼로그 보여주기
                alertDialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                BottomNaviActivity.this);

        // 제목셋팅
        alertDialogBuilder.setTitle(event);

        // AlertDialog 셋팅
        alertDialogBuilder
//                                .setMessage("해당 소통공간에 입장하시겠습니까?")
//                                .setCancelable(false)
                .setPositiveButton("퇴장",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 해당 장소 액티비티로 이동
                                                /*Intent intent = new Intent(context, SpotActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.putExtra("spot", spot.getSpot());
                                                context.startActivity(intent);*/
                                finish();
                                pref = getSharedPreferences(event, MODE_PRIVATE);
                                String user = pref.getString("user", "");
                                String org = pref.getString("org", "");
                                String rank = pref.getString("rank", "");
                                databaseReference.child("user").child(event).child(key).removeValue();
                                Toast.makeText(BottomNaviActivity.this, "퇴장 완료", Toast.LENGTH_SHORT).show();
                            }
                        })
                .setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 다이얼로그를 취소한다
                                dialog.cancel();
                            }
                        });
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertDialogView = layoutInflater.inflate(R.layout.exit_dialog, null);

        // 이미지 슬라이드 어댑터
        ImageSliderAdapter imageliderAdapter;
        ImageViewPager imageViewPager;

        imageViewPager = (ImageViewPager) alertDialogView.findViewById(R.id.viewpager);
        imageliderAdapter = new ImageSliderAdapter(BottomNaviActivity.this);
        imageViewPager.setAdapter(imageliderAdapter);

        // 페이저 인디케이터
        TabLayout tabLayout = (TabLayout)alertDialogView.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(imageViewPager, true);

        alertDialogBuilder.setView(alertDialogView);

        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();

        // 다이얼로그 보여주기
        alertDialog.show();

    }
}
