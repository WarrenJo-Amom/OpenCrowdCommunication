package in.amant.opencrowdcommunication.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.activity.CommListActivity;
import in.amant.opencrowdcommunication.adapter.Adapter;
import in.amant.opencrowdcommunication.model.District;
import in.amant.opencrowdcommunication.network.LoadSpots;

public class FullActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;

    /*ArrayList<Distriction> districtions = new ArrayList<>();
    ArrayList<Spot> spots = new ArrayList<>();
    ArrayList<Spot> spots_united = new ArrayList<>();*/

    ArrayList<District> districts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);

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

    void setRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(FullActivity.this, districts);
        recyclerView.setAdapter(adapter);
    }

}
