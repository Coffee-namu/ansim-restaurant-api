package ansim_api;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.net.HttpURLConnection; 
import java.net.URL; 

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class SeoulAnsimUpsoInfo {
    private String requestUrl = new String("http://openapi.seoul.go.kr:8088/");
    private String apiKey = new String("556742697a63686a3130314d434b4661");
    // XML 로 주고싶으면 json 대신 xml로 바꾸면 된다.
    private String requestType = new String("/json/CrtfcUpsoInfo/");
    private int startIndex, endIndex;
    private int maxRequestNum = 8388;

    public SeoulAnsimUpsoInfo() {
        // 기본으로 0  ~ 5 를 읽어온다.
        this.setStartIndex(0);
        this.setEndIndex(5);
    }

    public SeoulAnsimUpsoInfo(String apiKey) {
        // 기본으로 0  ~ 5 를 읽어온다.
        this.setStartIndex(0);
        this.setEndIndex(5);
        this.apiKey = apiKey;
    }

    private String requestData() throws Exception {
        URL requestJoinUrl = new URL(this.requestUrl + this.apiKey + this.requestType + Integer.toString(this.startIndex) + "/" + Integer.toString(this.endIndex) + "/");
        HttpURLConnection con = (HttpURLConnection) requestJoinUrl.openConnection();

        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public JSONObject requestDataJSON() throws Exception {
        String response = this.requestData();
        JSONParser jsparser = new JSONParser();
        JSONObject retValue = (JSONObject) jsparser.parse(response);
        return retValue;
    }

    public JSONArray requestDataArr() throws Exception {
        JSONObject response = this.requestDataJSON();
        JSONParser psr = new JSONParser();
        JSONObject retValJS2 = (JSONObject) psr.parse(response.get("CrtfcUpsoInfo").toString());
        return (JSONArray) retValJS2.get("row");
    }

    public String requestDataString() throws Exception {
        return this.requestData();
    }

    public void setStartIndex(int index) {
        this.startIndex = index;
    }
    public void setEndIndex(int index) {
        if(index < this.maxRequestNum) {
            this.endIndex = index;
        }
        else {
            this.endIndex = this.maxRequestNum;
        }
    }
    public int getStartIndex(int index) {
        return this.startIndex;
    }
    public int getEndIndex(int index) {
        return this.endIndex;
    }
}
