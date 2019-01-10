package in.amant.opencrowdcommunication.activity;

import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.adapter.ImageSliderAdapter;
import in.amant.opencrowdcommunication.adapter.ListViewEventAdapter;
import in.amant.opencrowdcommunication.adapter.SliderAdapter;
import in.amant.opencrowdcommunication.model.ListViewEventItem;
import in.amant.opencrowdcommunication.viewPager.ImageViewPager;

public class SpotActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);

        Intent intent = getIntent();
        String spot = intent.getExtras().getString("spot");

        setTitle(spot);

        ListView listview ;
        ListViewEventAdapter adapter;

        // Adapter 생성
        adapter = new ListViewEventAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private static final long MIN_CLICK_INTERVAL=600;

            private long mLastClickTime;

            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                long currentClickTime= SystemClock.uptimeMillis();
                long elapsedTime=currentClickTime-mLastClickTime;
                mLastClickTime=currentClickTime;

                // 중복 클릭인 경우
                if(elapsedTime<=MIN_CLICK_INTERVAL){
                    return;
                }

                // 중복 클릭아 아니라면

                // get item
                ListViewEventItem item = (ListViewEventItem) parent.getItemAtPosition(position) ;
                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                String descStr2 = item.getDesc2() ;

                // TODO : use item data.
                Intent intent = new Intent(getApplicationContext(), BottomNaviActivity.class);
                intent.putExtra("event", titleStr);
                startActivity(intent);
            }
        }) ;

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background),
                "2018 마을공동체 활성화 방안 토론회", "서울시마을공동체종합지원센터", "15명 · 오늘(진행중)") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background),
                "2018 사회적기업가 육성사업 설명회", "신나는조합", "20명 · 4월 22일(종료)") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background),
                "2018 청년정책네트워크 2월 정기회의", "청년정책네트워크", "27명 · 2월 5일(종료)") ;


        /*SliderAdapter sliderAdapter;
        ViewPager viewPager;

        viewPager = (ViewPager) findViewById(R.id.view);
        sliderAdapter = new SliderAdapter(getApplicationContext());
        viewPager.setAdapter(sliderAdapter);*/

        // 이미지 슬라이드 어댑터
        ImageSliderAdapter imageliderAdapter;
        ImageViewPager imageViewPager;

        imageViewPager = (ImageViewPager) findViewById(R.id.viewpager);
        imageliderAdapter = new ImageSliderAdapter(getApplicationContext());
        imageViewPager.setAdapter(imageliderAdapter);

        // 페이저 인디케이터
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(imageViewPager, true);

    }
}
