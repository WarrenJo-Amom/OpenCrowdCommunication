package in.amant.opencrowdcommunication.activity;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.adapter.Adapter;
import in.amant.opencrowdcommunication.adapter.MyAdapter;
import in.amant.opencrowdcommunication.model.District;
import in.amant.opencrowdcommunication.model.Distriction;
import in.amant.opencrowdcommunication.model.Spot;
import in.amant.opencrowdcommunication.network.LoadSpots;

public class CommListActivity extends BaseActivity {

    RecyclerView recyclerView;
    Adapter adapter;

    /*ArrayList<Distriction> districtions = new ArrayList<>();
    ArrayList<Spot> spots = new ArrayList<>();
    ArrayList<Spot> spots_united = new ArrayList<>();*/

    ArrayList<District> districts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_list);
        setTitle("모든 장소");

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        loadSpots();
//        setData();
        setRecyclerView();
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

    /*void setData(){
        Distriction d1 = new Distriction("미래청");
        districtions.add(d1);
        Distriction d2 = new Distriction("상상청");
        districtions.add(d2);

        Spot s1 = new Spot("휴카페", "미래청");
        spots.add(s1);
        Spot s2 = new Spot("큰이야기방1", "미래청");
        spots.add(s2);
        Spot s3 = new Spot("다목적홀", "미래청");
        spots.add(s3);
        Spot s4 = new Spot("상상오름1", "상상청");
        spots.add(s4);
        Spot s5 = new Spot("오픈스페이스", "상상청");
        spots.add(s5);

        Spot ds1 = new Spot("", districtions.get(0).getName());
        spots_united.add(ds1);
        spots_united.add(s1);
        spots_united.add(s2);
        spots_united.add(s3);
        Spot ds2 = new Spot("", districtions.get(1).getName());
        spots_united.add(ds2);
        spots_united.add(s4);
        spots_united.add(s5);
    }*/

    void setRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(CommListActivity.this, districts);
        recyclerView.setAdapter(adapter);
    }

}
