package in.amant.opencrowdcommunication.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.bassaer.chatmessageview.model.Message;
import com.github.bassaer.chatmessageview.util.ChatBot;
import com.github.bassaer.chatmessageview.view.ChatView;

import java.util.ArrayList;
import java.util.Random;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.activity.BottomNaviActivity;
import in.amant.opencrowdcommunication.activity.ChatActivity;
import in.amant.opencrowdcommunication.activity.FireBaseActivity;
import in.amant.opencrowdcommunication.adapter.ListViewEventAdapter;
import in.amant.opencrowdcommunication.adapter.ListViewFuncContextAdapter;
import in.amant.opencrowdcommunication.model.Event_varOfDistrict;
import in.amant.opencrowdcommunication.model.FuncContext;
import in.amant.opencrowdcommunication.model.ListViewEventItem;
import in.amant.opencrowdcommunication.model.User;

public class Chat extends Fragment {

    ArrayList<FuncContext> funcContexts = new ArrayList<>();

    String event;
    Event_varOfDistrict object;

    public static Chat newInstance() {
        return new Chat();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false); // 여기서 UI를 생성해서 View를 return

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            event = bundle.getString("event", "");
            object = (Event_varOfDistrict) bundle.getSerializable("object");
            Toast.makeText(getActivity(), object.getNo(), Toast.LENGTH_SHORT).show();
        }

        ListView listview ;
        ListViewFuncContextAdapter adapter;

        funcContexts.add(new FuncContext("전체 대화를 봅니다.", "ALL", 53));
        /*funcContexts.add(new FuncContext("안건1. 마을기업 활성화 방안 토론", "SUBJECT", 32));
        funcContexts.add(new FuncContext("안건2. 마을기업 임대보증금 지원 방안 토론", "SUBJECT", 20));*/
        funcContexts.add(new FuncContext("점심 도시락 메뉴 신청(수도시락)", "MENU", 1));
        funcContexts.add(new FuncContext("발표자에게 질문하기", "QUESTION", 1));

        funcContexts.add(new FuncContext("스피치1. 지속가능한 공동체를 위한 기술을 찾다", "대안에너지 기술연구소 - 강신호 대표", 20));
        funcContexts.add(new FuncContext("스피치1. 서울의 내일을 만드는(Fab) 파크의 프로젝트", "서울이노베이션팹랩 - 구혜빈 대표", 20));
        funcContexts.add(new FuncContext("스피치1. 자연을 담은 카페를 직접 짓고 운영한다는 것에 관해서", "비전화 공방 - 김미경 대표", 20));
        funcContexts.add(new FuncContext("스피치1. Living in town, Working in Town", "(주)두꺼비하우징 - 김미정 대표", 20));
        funcContexts.add(new FuncContext("스피치1. 모두가 즐기는 영화", "베리어프리영화위원회 - 김수정 대표", 20));
        funcContexts.add(new FuncContext("스피치1. 제로 웨이스트", "삼사이워크 - 김연정 대표", 20));
        funcContexts.add(new FuncContext("스피치1. 맛동 : 서울식문화혁신플랫폼", "슬로우푸드문화원 - 김원일 대표", 20));
        funcContexts.add(new FuncContext("스피치1. beyond RED, 소화기, 시민중심의 안전으로!", "마커스랩(주) - 라지훈 이사", 20));
        funcContexts.add(new FuncContext("스피치1. 버려진 장난감과 서울혁신파크", "금자동이 - 박준성 대표", 20));
        funcContexts.add(new FuncContext("스피치1. 방치 자전거의 재탄생과 아트바이크", "약속의 자전거 - 오영일 대표", 20));
        funcContexts.add(new FuncContext("스피치1. 서울혁신파크 안에서 대안교육의 성과", "대안교육연대 - 이상화 사무국장", 20));
        funcContexts.add(new FuncContext("스피치1. 서울의 도시환경문제에 대한 건축적 아이디어", "에이랩 건축연구소 - 이태현 소장", 20));
        funcContexts.add(new FuncContext("스피치1. 서울혁신파크와 혁신", "협동조합 리쿱 - 임상렬 대표", 20));
        funcContexts.add(new FuncContext("스피치1. 예술가집단 사회적기업을 이해하고픈 분들을 위한 짧은 자료", "이야기꾼의 책공연 - 황덕신 공동대표", 20));

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
                intent.putExtra("eventNo", object.getNo());
                startActivity(intent);

                Toast.makeText(getActivity(), "현재 전체대화만 가능합니다.", Toast.LENGTH_SHORT).show();
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

        return view;
    }

}