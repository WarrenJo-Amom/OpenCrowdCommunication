package in.amant.opencrowdcommunication.test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.activity.BottomNaviActivity;
import in.amant.opencrowdcommunication.activity.MainActivity;
import in.amant.opencrowdcommunication.activity.SpotActivity;
import in.amant.opencrowdcommunication.model.DistrictEvent;
import in.amant.opencrowdcommunication.model.EventWithDistrict;
import in.amant.opencrowdcommunication.model.Event_varOfDistrict;
import in.amant.opencrowdcommunication.network.LoadSpots;

public class MyAlert extends AppCompatActivity {

    ArrayList<DistrictEvent> districts = new ArrayList<>();
    ArrayList<EventWithDistrict> eventWithDistricts_united = new ArrayList<>();
    ArrayList<EventWithDistrict> eventWithDistricts_united_mirae = new ArrayList<>();
    int listNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadSpots();

        // ArrayList<District> districts를 통해 뷰타입 할당
        for (DistrictEvent d: districts) {
            eventWithDistricts_united.add(new EventWithDistrict("", null, d.getName()));
            for (Event_varOfDistrict s: d.getEvents()) {
                eventWithDistricts_united.add(new EventWithDistrict(s.getName(), s,""));
//                spots_united.add(new Spot(s.getName(), d.getName()));
            }
        }

        eventWithDistricts_united_mirae.clear();
        for(int i=0; i < eventWithDistricts_united.size(); i++) {
            if(!TextUtils.isEmpty(eventWithDistricts_united.get(i).getEvent())) {
                eventWithDistricts_united_mirae.add(eventWithDistricts_united.get(i));
            }
        }





        /*eventWithDistricts_united_mirae.clear();
        for(int i=0; i < eventWithDistricts_united.size(); i++) {
            if(eventWithDistricts_united.get(i).getDistrit().equals("미래청")) {
                eventWithDistricts_united_mirae.add(eventWithDistricts_united.get(i));
            }
        }*/
        /*eventWithDistricts_united_mirae.clear();
        for(int i=0; i < 3; i++) {
            eventWithDistricts_united_mirae.add(eventWithDistricts_united.get(i));
        }*/


        // 새로운 다이얼로그

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MyAlert.this);

        // 제목셋팅
        alertDialogBuilder.setTitle("모두모임방1에 들어오셨습니다.");

        // AlertDialog 셋팅
        alertDialogBuilder
//                                .setMessage("해당 소통공간에 입장하시겠습니까?")
//                                .setCancelable(false)
                .setPositiveButton("아니요, 그냥 둘러볼래요.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 해당 장소 액티비티로 이동
                                /*Intent intent = new Intent(MainActivity.this, SpotActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("spot", spot.getSpot());
                                MainActivity.this.startActivity(intent);*/
                                dialog.cancel();
                                finish();

                            }
                        })
                .setNegativeButton("입장",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 다이얼로그를 취소한다
                                finish();
                                Intent intent = new Intent(getApplicationContext(), BottomNaviActivity.class);
                                intent.putExtra("event", eventWithDistricts_united_mirae.get(listNo).getEvent());
                                intent.putExtra("object", eventWithDistricts_united_mirae.get(listNo).getEvent_varOfDistrict());
                                startActivity(intent);

                            }
                        });
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertDialogView = layoutInflater.inflate(R.layout.enter_dialog_new, null);

        final ListView listview = (ListView) alertDialogView.findViewById(R.id.listView_dialog);

        //데이터를 저장하게 되는 리스트
        List<String> list = new ArrayList<>();

        //리스트뷰와 리스트를 연결하기 위해 사용되는 어댑터
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MyAlert.this,
                android.R.layout.simple_list_item_1, list);

        //리스트뷰의 어댑터를 지정해준다.
        listview.setAdapter(adapter);


        //리스트뷰의 아이템을 클릭시 해당 아이템의 문자열을 가져오기 위한 처리
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                listNo = position;

                listview.getChildAt(0).setBackgroundColor(Color.WHITE);
                listview.getChildAt(1).setBackgroundColor(Color.WHITE);
                listview.getChildAt(2).setBackgroundColor(Color.WHITE);
                view.setBackgroundColor(Color.LTGRAY);


                //클릭한 아이템의 문자열을 가져옴
                String selected_item = (String)adapterView.getItemAtPosition(position);

            }
        });


        //리스트뷰에 보여질 아이템을 추가
        list.clear();
        /*list.add("혁신가의 스피치(혁신TED1)");
        list.add("2018 마을공동체 활성화 방안 토론회");
        list.add("2018 사회적기업가 육성사업 설명회");
        list.add("2018 청년정책네트워크 2월 정기회의");*/
        for(int i=0; i < 3; i++) {
            list.add(eventWithDistricts_united_mirae.get(i).getEvent());
        }

        alertDialogBuilder.setView(alertDialogView);

        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();

        // 다이얼로그 보여주기
        alertDialog.show();













        // 기존에 있던 팝업 다이얼로그 띄우기

        /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MyAlert.this);

        // 제목셋팅
        alertDialogBuilder.setTitle("미래청");

        // AlertDialog 셋팅
        alertDialogBuilder
//                                .setMessage("해당 소통공간에 입장하시겠습니까?")
//                                .setCancelable(false)
                .setPositiveButton("입장",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 해당 장소 액티비티로 이동
                                Intent intent = new Intent(MyAlert.this, SpotActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("spot", "미래청");
                                startActivity(intent);
                                finish();
                            }
                        })
                .setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 다이얼로그를 취소한다
                                dialog.cancel();
                                finish();
                            }
                        })
                *//*.setOnKeyListener(new AlertDialog.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        if (keyEvent == KeyEvent.ba) {
                            finish();
                            dialogInterface.dismiss();
                        }
                        return true;
                    }

                    *//**//**//**//*@Override
                    public boolean onKey(DialogInterface arg0, int keyCode,
                                         KeyEvent event) {
                        // TODO Auto-generated method stub
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            finish();
                            dialog.dismiss();
                        }
                        return true;
                    }*//**//**//**//*
                })*//*
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.cancel();
                        finish();
                    }
                });
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertDialogView = layoutInflater.inflate(R.layout.enter_dialog, null);
        alertDialogBuilder.setView(alertDialogView);

        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();

        // 다이얼로그 보여주기
        alertDialog.show();*/
    }

    void loadSpots() {
        districts.clear();

        LoadSpots loadSpots = new LoadSpots("http://placebasedcomm.cafe24.com/load_events.php");
        loadSpots.start();
        try {
            loadSpots.join();
        } catch (InterruptedException e) {

        }

        try {

            JSONObject jsonObject = new JSONObject(loadSpots.getStr());
            JSONArray jsonArray = (JSONArray) jsonObject.get("districts");
            Gson gson = new Gson();
            /*Type listType = new TypeToken<ArrayList<District>>() {}.getType();
            districts_loadTest = gson.fromJson(jsonArray.toString(), listType);*/
            DistrictEvent[] districtArray = gson.fromJson(jsonArray.toString(), DistrictEvent[].class);

            for(DistrictEvent d: districtArray){
                districts.add(d);
            }

        } catch (Exception e) {

        }
    }

}
