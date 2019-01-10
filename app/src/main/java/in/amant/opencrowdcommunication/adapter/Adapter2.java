package in.amant.opencrowdcommunication.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.activity.CommListActivity2;
import in.amant.opencrowdcommunication.activity.SpotActivity;
import in.amant.opencrowdcommunication.model.District;
import in.amant.opencrowdcommunication.model.Spot;
import in.amant.opencrowdcommunication.model.Spot_varOfDistrict;

public class Adapter2 extends RecyclerView.Adapter {
    Context context;
    ArrayList<Spot> spots_united = new ArrayList<>();
    ArrayList<District> districts = new ArrayList<>();

    final int ITEM_HEADER = 0;
    final int ITEM_SPOT = 1;

    public Adapter2(Context context, ArrayList<District> districts) {
        this.context = context;
        this.spots_united = spots_united;
        this.districts = districts;

        // ArrayList<District> districts를 통해 뷰타입 할당
        for (District d: districts) {
            spots_united.add(new Spot("", d.getName()));
            for (Spot_varOfDistrict s: d.getSpots()) {
                spots_united.add(new Spot(s.getName(), ""));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous

        if(TextUtils.isEmpty(spots_united.get(position).getSpot())) {
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
        final Spot spot = spots_united.get(position);

        if (spot != null) {
            if(TextUtils.isEmpty(spot.getSpot())) {
                ((ViewHolder_ItemHeader) holder).textView_distriction.setText(spot.getDistrition());
            } else {
                ((ViewHolder_ItemSpot) holder).textView_spot.setText(spot.getSpot());
                ((ViewHolder_ItemSpot) holder).textView_spot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(context, CommListActivity2.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("spot", spot.getSpot());
                        context.startActivity(intent);

                        // 해당 장소 액티비티로 이동
                        /*Intent intent = new Intent(context, SpotActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("spot", spot.getSpot());
                        context.startActivity(intent);*/

                        /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                context);

                        // 제목셋팅
                        alertDialogBuilder.setTitle(spot.getSpot());

                        // AlertDialog 셋팅
                        alertDialogBuilder
//                                .setMessage("해당 소통공간에 입장하시겠습니까?")
//                                .setCancelable(false)
                                .setPositiveButton("입장",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(
                                                    DialogInterface dialog, int id) {
                                                // 해당 장소 액티비티로 이동
                                                Intent intent = new Intent(context, SpotActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.putExtra("spot", spot.getSpot());
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
                        alertDialogBuilder.setView(alertDialogView);

                        // 다이얼로그 생성
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // 다이얼로그 보여주기
                        alertDialog.show();*/

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

    @Override
    public int getItemCount() {
        return spots_united.size();
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