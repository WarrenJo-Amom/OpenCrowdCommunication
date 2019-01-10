package in.amant.opencrowdcommunication.network;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddEventRequest extends StringRequest {

    final static private String URL = "http://placebasedcomm.cafe24.com/add_event.php";
    private Map<String, String> parameters;

    public AddEventRequest(String dong, String spot, String name, String org, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("dong", dong);
        parameters.put("spot", spot);
        parameters.put("name", name);
        parameters.put("org", org);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }

}
