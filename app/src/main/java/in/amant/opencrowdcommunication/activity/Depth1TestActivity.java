package in.amant.opencrowdcommunication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.model.District;
import in.amant.opencrowdcommunication.model.Spot;
import in.amant.opencrowdcommunication.model.Spot_varOfDistrict;
import in.amant.opencrowdcommunication.network.LoadSpots;

public class Depth1TestActivity extends BaseActivity {

    ArrayList<District> districts = new ArrayList<>();

    ListView listView;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depth1_test);

        // Json 출력 테스트
        textView = (TextView) findViewById(R.id.textView_json);

        listView = (ListView) findViewById(R.id.listView);

        List<String> list = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);

        /*list.add("사과");
        list.add("배");
        list.add("귤");
        list.add("바나나");*/

        /*District district1 = new District("미래청");
        districts.add(district1);
        District district2 = new District("상상청");
        districts.add(district2);

        // 스팟 셋팅
        ArrayList<Spot_varOfDistrict> spots1 = new ArrayList<>();
        ArrayList<Spot_varOfDistrict> spots2 = new ArrayList<>();

        Spot_varOfDistrict spot1 = new Spot_varOfDistrict("휴카페");
        Spot_varOfDistrict spot2 = new Spot_varOfDistrict("큰이야기방1");
        Spot_varOfDistrict spot3 = new Spot_varOfDistrict("다목적홀");
        spots1.add(spot1);
        spots1.add(spot2);
        spots1.add(spot3);
        district1.setSpots(spots1);

        Spot_varOfDistrict spot4 = new Spot_varOfDistrict("상상오름1");
        Spot_varOfDistrict spot5 = new Spot_varOfDistrict("오픈스페이스");
        spots2.add(spot4);
        spots2.add(spot5);
        district2.setSpots(spots2);*/

        loadSpots();

        for(int i = 0; i < districts.size(); i++) {
            String district_name = districts.get(i).getName(); // TODO: 2018-10-15 먼저 District 셋팅
            list.add(district_name); // TODO: 2018-10-15 구역 이름 가져올 때와 스팟 이름 가져올 때 구분해야 함

            // Spot 셋팅
            ArrayList<Spot_varOfDistrict> spots_i = districts.get(i).getSpots();
            for(int j = 0; j < spots_i.size(); j++) {
                // Spot 이름 가져오기
                String spot_name = spots_i.get(j).getName();
                list.add(spot_name); // TODO: 2018-10-15 구역 이름 가져올 때와 스팟 이름 가져올 때 구분해야 함
            }
        }

    }

    void loadSpots() {
        LoadSpots loadSpots = new LoadSpots("http://placebasedcomm.cafe24.com/load_spots.php");
        loadSpots.start();
        try {
            loadSpots.join();
        } catch (InterruptedException e) {

        }

        try {
            textView.setText(loadSpots.getStr());

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

}
