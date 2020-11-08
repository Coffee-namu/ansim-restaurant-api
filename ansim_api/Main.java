package ansim_api;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

class Main {
    public static void main(String[] args) {
        SeoulAnsimUpsoInfo seoul = new SeoulAnsimUpsoInfo();
        try {
            JSONArray retValJS = seoul.requestDataArr();
            for(int i = 0; i < retValJS.size(); i++) {
                System.out.println(retValJS.get(i));
            }
        }
        catch(Exception e){
            System.out.println("ERR!");
        }
    }
}