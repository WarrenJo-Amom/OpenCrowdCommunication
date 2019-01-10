package in.amant.opencrowdcommunication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.amant.opencrowdcommunication.R;
import in.amant.opencrowdcommunication.model.District;
import in.amant.opencrowdcommunication.model.Spot_varOfDistrict;
import in.amant.opencrowdcommunication.network.AddEventRequest;
import in.amant.opencrowdcommunication.network.LoadSpots;

public class AddEventActivity extends AppCompatActivity {

    ArrayAdapter spinnerAdapter;
    Spinner spinner;
    List list = new ArrayList();

    Button button3;

    ArrayAdapter spinnerAdapter_spots;
    Spinner spinner_spots;
    List list_spots = new ArrayList();

    String dong;
    String spot;

    ArrayList<District> districts = new ArrayList<>();

    EditText editText_eventName;
    EditText editText_eventOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        setTitle("행사 개설");

        editText_eventName = (EditText) findViewById(R.id.editText_eventName);
        editText_eventOrg = (EditText) findViewById(R.id.editText_eventOrg);


        list.add("동명을 선택하세요.");
        loadDongs();

        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "동명을 선택하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    // 선택된 "동" 이름 String
                    dong = list.get(spinner.getSelectedItemPosition()).toString();
                    list_spots.clear();
                    list_spots.add("장소를 선택하세요.");
                    loadSpots(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        list_spots.add("장소를 선택하세요.");
//        loadSpots();

        spinnerAdapter_spots = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list_spots);

        spinner_spots = (Spinner) findViewById(R.id.spinner2);
        spinner_spots.setAdapter(spinnerAdapter_spots);
        spinner_spots.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spot = list_spots.get(spinner_spots.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 개설 완료 버튼 누른 후 동작 처리
                if(spinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "동명을 선택하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    // 선택된 "동" 이름 String
//        list.get(spinner.getSelectedItemPosition())
                    Toast.makeText(getApplicationContext(), "개설 성공", Toast.LENGTH_SHORT).show();




                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                final JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if(success) {
                                    Toast.makeText(getApplicationContext(), "행사 개설 성공", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "행사 개설 실패", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                            }
                        }
                    };

                    AddEventRequest addEventRequest = new AddEventRequest(dong, spot, editText_eventName.getText().toString(), editText_eventOrg.getText().toString(), responseListener);
                    RequestQueue queue = Volley.newRequestQueue(AddEventActivity.this);
                    queue.add(addEventRequest);





                }
            }
        });





    }

    void loadDongs() {
        LoadSpots loadSpots = new LoadSpots("http://placebasedcomm.cafe24.com/load_dongs.php");
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
                list.add(d.getName());
            }

        } catch (Exception e) {

        }
    }

    void loadSpots(int position) {
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

            for(Spot_varOfDistrict s: districts.get(position-1).getSpots()){
                list_spots.add(s.getName());
            }

        } catch (Exception e) {

        }
    }

}
