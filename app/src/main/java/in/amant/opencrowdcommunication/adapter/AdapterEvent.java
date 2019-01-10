package in.amant.opencrowdcommunication.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.activity.BottomNaviActivity;
import in.amant.opencrowdcommunication.activity.SpotActivity;
import in.amant.opencrowdcommunication.model.District;
import in.amant.opencrowdcommunication.model.DistrictEvent;
import in.amant.opencrowdcommunication.model.EventWithDistrict;
import in.amant.opencrowdcommunication.model.Event_varOfDistrict;
import in.amant.opencrowdcommunication.model.Spot;
import in.amant.opencrowdcommunication.model.Spot_varOfDistrict;
import in.amant.opencrowdcommunication.viewPager.ImageViewPager;

import static android.content.Context.MODE_PRIVATE;

// TODO: 2018-11-28 도대체 어떤 놈을 어떻게 인텐트로 다음 액티비티로 넘겨줘야 하는지... 

public class AdapterEvent extends RecyclerView.Adapter {
    Context context;
    ArrayList<Spot> spots_united = new ArrayList<>();
    ArrayList<EventWithDistrict> eventWithDistricts_united = new ArrayList<>();
    ArrayList<DistrictEvent> districts = new ArrayList<>();

    final int ITEM_HEADER = 0;
    final int ITEM_SPOT = 1;

    SharedPreferences pref;

    public AdapterEvent(Context context, ArrayList<DistrictEvent> districts) {
        this.context = context;
        this.spots_united = spots_united;
        this.districts = districts;

        // ArrayList<District> districts를 통해 뷰타입 할당
        for (DistrictEvent d: districts) {
            eventWithDistricts_united.add(new EventWithDistrict("", null, d.getName()));
            for (Event_varOfDistrict s: d.getEvents()) {
                eventWithDistricts_united.add(new EventWithDistrict(s.getName(), s,""));
//                spots_united.add(new Spot(s.getName(), d.getName()));
            }
        }

        // ArrayList<District> districts를 통해 뷰타입 할당
        /*for (DistrictEvent d: districts) {
            spots_united.add(new Spot("", d.getName()));
            for (Event_varOfDistrict s: d.getEvents()) {
                spots_united.add(new Spot(s.getName(), ""));
//                spots_united.add(new Spot(s.getName(), d.getName()));
            }
        }*/
    }

    @Override
    public int getItemCount() {
        return eventWithDistricts_united.size();
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous

        if(TextUtils.isEmpty(eventWithDistricts_united.get(position).getEvent())) {
            return ITEM_HEADER;
        } else {
            return ITEM_SPOT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_HEADER) {
            View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_header,parent,false);
            return new ViewHolder_ItemHeader(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_spot,parent,false);
            return new ViewHolder_ItemSpot(view);
        }
        /*View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);*/
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final EventWithDistrict eventWithDistrict = eventWithDistricts_united.get(position);

        if (eventWithDistrict != null) {
            if(TextUtils.isEmpty(eventWithDistrict.getEvent())) {
                ((ViewHolder_ItemHeader) holder).textView_distriction.setText(eventWithDistrict.getDistrit());
            } else {
                ((ViewHolder_ItemSpot) holder).textView_spot.setText(eventWithDistrict.getEvent());
                ((ViewHolder_ItemSpot) holder).textView_spot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 해당 장소 액티비티로 이동
                        /*Intent intent = new Intent(context, SpotActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("spot", spot.getSpot());
                        context.startActivity(intent);*/


                        // TODO: 2018-11-30 참여자 리스트에 유저 등록, 해제 위한 사전 작업 
                        /*pref = context.getSharedPreferences(eventWithDistrict.getEvent(), MODE_PRIVATE);
                        String user = pref.getString("user", "");
                        String org = pref.getString("org", "");
                        String rank = pref.getString("rank", "");
                        if(TextUtils.isEmpty(user)) {
                            
                        }*/
                        
                        

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                context);

                        // 제목셋팅
                        alertDialogBuilder.setTitle(eventWithDistrict.getEvent());

                        // AlertDialog 셋팅
                        alertDialogBuilder
//                                .setMessage("해당 소통공간에 입장하시겠습니까?")
//                                .setCancelable(false)
                                .setPositiveButton("입장",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(
                                                    DialogInterface dialog, int id) {
                                                // 해당 장소 액티비티로 이동
                                                /*Intent intent = new Intent(context, SpotActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.putExtra("spot", spot.getSpot());
                                                context.startActivity(intent);*/
                                                Intent intent = new Intent(context, BottomNaviActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.putExtra("event", eventWithDistrict.getEvent());
                                                intent.putExtra("object", eventWithDistrict.getEvent_varOfDistrict());
                                                context.startActivity(intent);
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
                        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View alertDialogView = layoutInflater.inflate(R.layout.enter_dialog, null);

                        // 이미지 슬라이드 어댑터
                        ImageSliderAdapter imageliderAdapter;
                        ImageViewPager imageViewPager;

                        imageViewPager = (ImageViewPager) alertDialogView.findViewById(R.id.viewpager);
                        imageliderAdapter = new ImageSliderAdapter(context);
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
                });
                /*((ViewHolder_ItemSpot) holder).textView_spot.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent != null ) {
                            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                ((ViewHolder_ItemSpot) holder).textView_spot.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                                return false;
                            } else if (motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                                notifyDataSetChanged();
                                ((ViewHolder_ItemSpot) holder).textView_spot.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                                return true;
                            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                ((ViewHolder_ItemSpot) holder).textView_spot.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                                view.performClick();
                            }
                        }

                        return true;
                    }

                });*/
            }
        }

    }

    public static class ViewHolder_ItemHeader extends RecyclerView.ViewHolder{
        TextView textView_distriction;
        public ViewHolder_ItemHeader(View itemView) {
            super(itemView);
            textView_distriction = itemView.findViewById(R.id.textView_distriction);
        }
    }

    public static class ViewHolder_ItemSpot extends RecyclerView.ViewHolder{
        TextView textView_spot;
        public ViewHolder_ItemSpot(View itemView) {
            super(itemView);
            textView_spot = itemView.findViewById(R.id.textView_spot);
        }
    }
}