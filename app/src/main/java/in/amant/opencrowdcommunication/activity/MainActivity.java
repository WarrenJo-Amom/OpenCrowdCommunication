package in.amant.opencrowdcommunication.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.adapter.Adapter;
import in.amant.opencrowdcommunication.adapter.Adapter2;
import in.amant.opencrowdcommunication.adapter.AdapterEvent;
import in.amant.opencrowdcommunication.adapter.AdapterEvent_Main;
import in.amant.opencrowdcommunication.model.District;
import in.amant.opencrowdcommunication.model.DistrictEvent;
import in.amant.opencrowdcommunication.network.LoadSpots;
import in.amant.opencrowdcommunication.test.FullActivity;
import in.amant.opencrowdcommunication.test.FullscreenActivity;
import in.amant.opencrowdcommunication.test.ScrollingActivity;
import in.amant.opencrowdcommunication.test.Test2Activity;
import in.amant.opencrowdcommunication.test.TestActivity;

public class MainActivity extends BaseActivity {

    ImageView imageView_map;
    TextView textView_viewAll;

    // 테스트 버튼
    Button button_depth1;
    Button button_scrolling;
    Button button_fullscreen;
    Button button_dialog;
    Button button_img;
    Button button_s20;
    Button button_blank;

    ImageView imageView_theme;
    LinearLayout layout_today_org;

    ImageView imageView15;
    ImageView imageView16;

    // 리사이클러뷰
    RecyclerView recyclerView;
    Adapter adapter;

    // 리사이클러뷰_이벤트 로드
    RecyclerView recyclerView2;
    AdapterEvent_Main adapter2;

    /*ArrayList<Distriction> districtions = new ArrayList<>();
    ArrayList<Spot> spots = new ArrayList<>();
    ArrayList<Spot> spots_united = new ArrayList<>();*/

    ArrayList<District> districts = new ArrayList<>();
    ArrayList<DistrictEvent> districts2 = new ArrayList<>();

    ListView listview;

    ImageView imageView_main3;

    ImageView imageView_main;

    ImageView image1;
    ImageView image2;

    CardView cardview1;
    CardView cardview2;

    TextView textView_eventAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("잘 생겼다! 혁신파크");

        textView_eventAll = (TextView) findViewById(R.id.textView_eventAll);
        textView_eventAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommListActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("spot", spot.getSpot());
                startActivity(intent);
            }
        });

        cardview1 = (CardView) findViewById(R.id.cardview1);
        cardview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "해당 기능은 준비중이며,\n현재 '오늘의 행사' 기능만 이용이 가능합니다.", Toast.LENGTH_SHORT).show();
            }
        });
        cardview2 = (CardView) findViewById(R.id.cardview2);
        cardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "해당 기능은 준비중이며,\n현재 '오늘의 행사' 기능만 이용이 가능합니다.", Toast.LENGTH_SHORT).show();
            }
        });

        image1 = (ImageView) findViewById(R.id.image1);
        Glide.with(this).load(R.drawable.food1).into(image1);
        image2 = (ImageView) findViewById(R.id.image2);
        Glide.with(this).load(R.drawable.food3).into(image2);

        imageView15 = (ImageView) findViewById(R.id.imageView15);
        Glide.with(this).load(R.drawable.today_org1).into(imageView15);
        imageView16 = (ImageView) findViewById(R.id.imageView16);
        Glide.with(this).load(R.drawable.today_org2).into(imageView16);

        layout_today_org = (LinearLayout) findViewById(R.id.layout_today_org);
        layout_today_org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "해당 기능은 준비중이며,\n현재 '오늘의 행사' 기능만 이용이 가능합니다.", Toast.LENGTH_SHORT).show();
            }
        });

        imageView_theme = (ImageView) findViewById(R.id.imageView_theme);
        Glide.with(this).load(R.drawable.theme2).into(imageView_theme);
        imageView_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "해당 기능은 준비중이며,\n현재 '오늘의 행사' 기능만 이용이 가능합니다.", Toast.LENGTH_SHORT).show();
            }
        });

        imageView_main = (ImageView) findViewById(R.id.imageView_main);
        Glide.with(this).load(R.drawable.today_menu).into(imageView_main);
        imageView_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "해당 기능은 준비중이며,\n현재 '오늘의 행사' 기능만 이용이 가능합니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 리사이클러뷰
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        loadSpots();
//        setData();
        setRecyclerView();

        // 리사이클러뷰_이벤트 로드
        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_events);
        loadEvents();
//        setData();
        setRecyclerView2();

        imageView_main3 = (ImageView) findViewById(R.id.imageView_main3);
        Glide.with(this).load(R.drawable.event_img).into(imageView_main3);
        imageView_main3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CommListActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        // 테스트 버튼
        button_depth1 = (Button) findViewById(R.id.button_depth1);
        button_depth1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Depth1TestActivity.class));
            }
        });

        // 스크롤링 액티비티
        button_scrolling = (Button) findViewById(R.id.button_scrolling);
        button_scrolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ScrollingActivity.class));
            }
        });

        // 풀스크린 액티비티
        button_fullscreen = (Button) findViewById(R.id.button_fullscreen);
        button_fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FullActivity.class));
            }
        });

        // 이미지 테스트 액티비티
        button_img = (Button) findViewById(R.id.button_img);
        button_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ImgTestActivity.class));
            }
        });

        // 서울20 액티비티
        button_s20 = (Button) findViewById(R.id.button_s20);
        button_s20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Seoul20Activity.class));
            }
        });

        // 빈 액티비티
        button_blank = (Button) findViewById(R.id.button_blank);
        button_blank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BlankActivity.class));
            }
        });

        // 풀스크린 액티비티
        button_dialog = (Button) findViewById(R.id.button_dialog);
        button_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MainActivity.this);

                // 제목셋팅
                alertDialogBuilder.setTitle("큰이야기방에 들어오셨습니다.");

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
                                    }
                                })
                        .setNegativeButton("입장",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소한다
                                        dialog.cancel();
                                    }
                                });
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View alertDialogView = layoutInflater.inflate(R.layout.enter_dialog_new, null);

                final ListView listview = (ListView) alertDialogView.findViewById(R.id.listView_dialog);

                //데이터를 저장하게 되는 리스트
                List<String> list = new ArrayList<>();

                //리스트뷰와 리스트를 연결하기 위해 사용되는 어댑터
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, list);

                //리스트뷰의 어댑터를 지정해준다.
                listview.setAdapter(adapter);


                //리스트뷰의 아이템을 클릭시 해당 아이템의 문자열을 가져오기 위한 처리
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView,
                                            View view, int position, long id) {

                        listview.getChildAt(0).setBackgroundColor(Color.WHITE);
                        listview.getChildAt(1).setBackgroundColor(Color.WHITE);
                        listview.getChildAt(2).setBackgroundColor(Color.WHITE);
                        view.setBackgroundColor(Color.LTGRAY);


                        //클릭한 아이템의 문자열을 가져옴
                        String selected_item = (String)adapterView.getItemAtPosition(position);

                    }
                });


                //리스트뷰에 보여질 아이템을 추가
                list.add("2018 마을공동체 활성화 방안 토론회");
                list.add("2018 사회적기업가 육성사업 설명회");
                list.add("2018 청년정책네트워크 2월 정기회의");

                alertDialogBuilder.setView(alertDialogView);

                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                // 다이얼로그 보여주기
                alertDialog.show();
            }
        });

        imageView_map = (ImageView) findViewById(R.id.imageView_map);
        Glide.with(this).load(R.drawable.map3).into(imageView_map);
        imageView_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CommListActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        textView_viewAll = (TextView) findViewById(R.id.textView_viewAll);
        textView_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CommListActivity.class);
                startActivity(intent);
            }
        });

        listview = (ListView)findViewById(R.id.listview);

        //데이터를 저장하게 되는 리스트
        List<String> list = new ArrayList<>();
        //리스트뷰에 보여질 아이템을 추가
//        list.add("서울혁신파크 12월 정기주차권 요금납부 안내");
//        list.add("코워킹스페이스 입주모집 심사결과 안내");
//        list.add("공용 공간 운영시간 변경 안내");
        list.add("서울혁신파크 12월 정기주차권 요금납부 안내");
        list.add("2018 서울혁신센터 코워킹스페이스 입주 모집 대면심사 결과 안내");
        list.add("2018년 식문화 문제해결 현장프로젝트 공모사업 최종심사 결과 공고");

        //리스트뷰와 리스트를 연결하기 위해 사용되는 어댑터
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        //리스트뷰의 어댑터를 지정해준다.
        listview.setAdapter(adapter);
//        setListViewHeightBasedOnChildren(listview);
        resizeParentLayoutHeight(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "해당 기능은 준비중이며,\n현재 '오늘의 행사' 기능만 이용이 가능합니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private static final int MAX_LISTVIEW_ROW_HEIGHT = 500;

    private void resizeParentLayoutHeight(android.widget.Adapter adapter) {

        if (adapter != null) {
            int listCount = adapter.getCount();

            if (listCount > 0) {

                int height = MAX_LISTVIEW_ROW_HEIGHT * listCount;

                resizeParentLayoutHeight(height);

                listview.post(new Runnable() {

                    @Override
                    public void run() {

                        int height = listview.getMeasuredHeight();

                        if (height > 0) {
                            resizeParentLayoutHeight(height);
                        }
                    }
                });
            }
        }
    }

    private void resizeParentLayoutHeight(int height) {

        final LinearLayout layout = (LinearLayout)findViewById(R.id.listViewLayout);

        if (layout != null) {

            final ViewGroup.LayoutParams params = layout.getLayoutParams();

            if (params != null) {

                if (listview != null) {

                    // 현재 뷰의 margin 을 계산한다.
                    ViewGroup.MarginLayoutParams margin = (ViewGroup.MarginLayoutParams)listview.getLayoutParams();

                    if(margin != null) {
                        height += (margin.topMargin + margin.bottomMargin);
                    }
                }

                params.height = height;

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        layout.setLayoutParams(params);
                    }
                });
            }
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    void loadSpots() {
        LoadSpots loadSpots = new LoadSpots("http://placebasedcomm.cafe24.com/load_spots.php");
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
            District[] districtArray = gson.fromJson(jsonArray.toString(), District[].class);

            for(District d: districtArray){
                districts.add(d);
            }

        } catch (Exception e) {

        }
    }

    void loadEvents() {
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
                districts2.add(d);
            }

        } catch (Exception e) {

        }
    }

    void setRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(MainActivity.this, districts);
        recyclerView.setAdapter(adapter);
    }

    void setRecyclerView2(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(layoutManager);
        adapter2 = new AdapterEvent_Main(MainActivity.this, districts2);
        recyclerView2.setAdapter(adapter2);
    }

}
