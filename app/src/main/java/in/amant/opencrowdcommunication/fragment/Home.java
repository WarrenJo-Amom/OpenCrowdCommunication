package in.amant.opencrowdcommunication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.activity.ChatActivity;
import in.amant.opencrowdcommunication.adapter.ListViewFuncContextAdapter;
import in.amant.opencrowdcommunication.model.FuncContext;

public class Home extends Fragment {

    ArrayList<FuncContext> funcContexts = new ArrayList<>();

    String event;

    ImageView imageView4;

    public static Home newInstance() {
        return new Home();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_home, container, false); // 여기서 UI를 생성해서 View를 return

        View view = inflater.inflate(R.layout.fragment_home, container, false); // 여기서 UI를 생성해서 View를 return

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            event = bundle.getString("event", "");
        }

        ListView listview ;
        ListViewFuncContextAdapter adapter;

        funcContexts.add(new FuncContext("전체 대화를 봅니다.", "ALL", 53));
        funcContexts.add(new FuncContext("안건1. 마을기업 활성화 방안 토론", "SUBJECT", 32));
        funcContexts.add(new FuncContext("안건2. 마을기업 임대보증금 지원 방안 토론", "SUBJECT", 20));
        funcContexts.add(new FuncContext("점심 도시락 메뉴 신청(수도시락)", "MENU", 1));
        funcContexts.add(new FuncContext("발표자에게 질문하기", "QUESTION", 1));

        // Adapter 생성
        adapter = new ListViewFuncContextAdapter(funcContexts) ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listView);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                /*ListViewEventItem item = (ListViewEventItem) parent.getItemAtPosition(position) ;
                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                String descStr2 = item.getDesc2() ;*/

                // TODO : use item data.
                Intent intent = new Intent(getActivity(), ChatActivity.class);
//                Intent intent = new Intent(getActivity(), FireBaseActivity.class);
                intent.putExtra("event", event);
                intent.putExtra("funcTitle", funcContexts.get(position).getFuncContext());
                startActivity(intent);
            }
        }) ;

        // 첫 번째 아이템 추가.
        /*adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "전체 대화", "서울시마을공동체종합지원센터", "15명 · 오늘(진행중)") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "2018 사회적기업가 육성사업 설명회", "신나는조합", "20명 · 4월 22일(종료)") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background),
                "2018 청년정책네트워크 2월 정기회의", "청년정책네트워크", "27명 · 2월 5일(종료)") ;*/



        imageView4 = (ImageView) view.findViewById(R.id.imageView4);
        Glide.with(this).load(R.drawable.home_inno).into(imageView4);




        return view;
    }

}