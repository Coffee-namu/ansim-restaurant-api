package ansim_api;

import java.util.Vector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

class Main {
    public static void main(String[] args) {
        // @Author : CHJ
        SeoulAnsimUpsoInfo seoul = new SeoulAnsimUpsoInfo();
        JSONObject finalOutFile = new JSONObject();
        JSONArray outFile = new JSONArray();

        /*
        for(int j = 0; j < 18; j++) {
            // Crawling all files. ( Index is below 8700 )
            seoul.setStartIndex(0 + j * 500);
            seoul.setEndIndex(500 + j * 500);
            try {
                JSONArray retValJS = seoul.requestDataArr();
                for(int i = 0; i < retValJS.size(); i++) {
                    JSONParser psr = new JSONParser();
                    JSONObject temp = (JSONObject) psr.parse(retValJS.get(i).toString());
                    JSONObject tempOutFile = new JSONObject();
                    if(temp.get("CRTFC_GBN_NM").equals("위생등급제"))    {
                        tempOutFile.put("name", temp.get("UPSO_NM")); // 가게 이름
                        tempOutFile.put("category",temp.get("BIZCND_CODE_NM")); // 가게 이름
                        tempOutFile.put("x", temp.get("X_CNTS")); // 경도, logitude
                        tempOutFile.put("y", temp.get("Y_DNTS")); // 위도, latitude
                        tempOutFile.put("addr", temp.get("RDN_CODE_NM") + " " +temp.get("RDN_DETAIL_ADDR")); // 한글 주소
                        outFile.add(tempOutFile);
                    }
                }
            }
            catch(Exception e){
                e.printStackTrace(); 
            }
        }	
        finalOutFile.put("restaurant", outFile);
        try {
            FileOutputStream fileStream = new FileOutputStream(new File("./test.json"));
            OutputStreamWriter writer = new OutputStreamWriter(fileStream, "UTF-8");

            writer.write(finalOutFile.toJSONString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        // @Author : LJS
        Parser p1 = new Parser();
        Parser p2 = new Parser();
        String fname1 = "C:/workspace/ansim-restaurant-api/ansim_api/resource/경기도안심식당정보.csv";
        String fname2 = "C:/workspace/ansim-restaurant-api/ansim_api/resource/농림축산부안심식당정보.csv";
        
        try {
            p1.CSVParser(fname1);
            Vector<JSONObject> jobjs1 = p1.JsonParser();
            for(int i = 0; i < jobjs1.size(); i++)
            	System.out.println(jobjs1.get(i));
            
            p2.CSVParser(fname2);
            Vector<JSONObject> jobjs2 = p2.JsonParser();
            for(int i = 0; i < jobjs2.size(); i++)
            	System.out.println(jobjs2.get(i));
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
}