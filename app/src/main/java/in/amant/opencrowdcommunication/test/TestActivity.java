package in.amant.opencrowdcommunication.test;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.activity.BaseActivity;
import in.amant.opencrowdcommunication.adapter.MyAdapter;

public class TestActivity extends BaseActivity {

    private ListView mListView1;
    private ListView mListView2;
    private ListView mListView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        /* 위젯과 멤버변수 참조 획득 */
        mListView1 = (ListView)findViewById(R.id.listView1);
        mListView2 = (ListView)findViewById(R.id.listView2);
        mListView3 = (ListView)findViewById(R.id.listView3);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();
    }

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();


        for (int i=0; i<5; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.park_intro), "name_" + i, "contents_" + i);
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView1.setAdapter(mMyAdapter);
        mListView2.setAdapter(mMyAdapter);
        mListView3.setAdapter(mMyAdapter);
    }
}
