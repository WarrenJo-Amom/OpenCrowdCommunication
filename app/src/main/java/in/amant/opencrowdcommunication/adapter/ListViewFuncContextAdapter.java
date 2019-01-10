package in.amant.opencrowdcommunication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.model.FuncContext;
import in.amant.opencrowdcommunication.model.ListViewEventItem;

public class ListViewFuncContextAdapter extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewEventItem> listViewItemList = new ArrayList<ListViewEventItem>() ;
    private ArrayList<FuncContext> funcContexts = new ArrayList<FuncContext>() ;

    // ListViewAdapter의 생성자
    public ListViewFuncContextAdapter(ArrayList<FuncContext> funcContexts) {
        this.funcContexts = funcContexts;
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return funcContexts.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_func_context, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.textView2) ;
        TextView descTextView2 = (TextView) convertView.findViewById(R.id.textView3) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        FuncContext listViewItem = funcContexts.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        String type = null;
        String addedComment = "개의 대화가 있습니다.";
        switch (listViewItem.getType()) {
            case "ALL":
                type = "전체보기";
                addedComment = "개의 대화가 있습니다.";
                break;
            case "SUBJECT":
                type = "주제 대화";
                addedComment = "개의 대화가 있습니다.";
                break;
            case "VOTE":
                type = "투표";
                addedComment = "개의 투표가 있습니다.";
                break;
            case "MENU":
                type = "음료, 도시락 주문";
                addedComment = "개의 주문이 있습니다.";
                break;
            case "QUESTION":
                type = "질문 등록";
                addedComment = "개의 질문이 있습니다.";
                break;
            default:
                type = listViewItem.getType();
        }
        titleTextView.setText(listViewItem.getFuncContext());
        descTextView.setText(type);
        descTextView2.setText(listViewItem.getAmout() + addedComment);

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    /*public void addItem(Drawable icon, String title, String desc, String desc2) {
        ListViewEventItem item = new ListViewEventItem();

        item.setTitle(title);
        item.setDesc(desc);
        item.setDesc2(desc2);

        listViewItemList.add(item);
    }*/

}
