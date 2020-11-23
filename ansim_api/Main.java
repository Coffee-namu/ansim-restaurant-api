package ansim_api;

import java.util.Vector;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

class Main {
    public static void main(String[] args) {
        // @Author : CHJ
        /*SeoulAnsimUpsoInfo seoul = new SeoulAnsimUpsoInfo();
        JSONObject finalOutFile = new JSONObject();
        JSONArray outFile = new JSONArray();

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
        }*/
    

        // @Author : LJS
        Parser p1 = new Parser();
        Parser p2 = new Parser();
        String fname1 = "C:/Users/Jisu/Documents/Github/ansim-restaurant-api/ansim_api/resource/경기도안심식당정보.csv";
        String fname2 = "C:/Users/Jisu/Documents/Github/ansim-restaurant-api/ansim_api/resource/농림축산부안심식당정보.csv";
        Vector<JSONObject> jobjs1 = null;
        Vector<JSONObject> jobjs2 = null;
        
        try {
            p1.CSVParser(fname1);
            jobjs1 = p1.JsonParser();
            
            p2.CSVParser(fname2);
            jobjs2 = p2.JsonParser();
            
        }        
        catch (Exception e) { 
            e.printStackTrace(); 
        }
        
        try {
        	FileOutputStream test1=new FileOutputStream("./test1.json",false);
        	FileOutputStream test2=new FileOutputStream("./test2.json",false);
            OutputStreamWriter writer1=new OutputStreamWriter(test1,"UTF-8");
            OutputStreamWriter writer2=new OutputStreamWriter(test2,"UTF-8");
            BufferedWriter out1=new BufferedWriter(writer1);
            BufferedWriter out2=new BufferedWriter(writer2);
			JSONArray j1 = new JSONArray();
			JSONArray j2 = new JSONArray();
			
			for(int i=0;i<jobjs1.size();i++) {
                JSONObject temp1 = new JSONObject();
                temp1.put("name", jobjs1.get(i).get("사업장명"));
                temp1.put("category", jobjs1.get(i).get("업종상세명"));
                temp1.put("x", jobjs1.get(i).get("정제WGS84위도"));
                temp1.put("y", jobjs1.get(i).get("정제WGS84경도"));
                temp1.put("addr", jobjs1.get(i).get("정제지번주소"));
				j1.add(temp1);
			}
			out1.write(j1.toJSONString());
			
			for(int j=0;j<jobjs2.size();j++) {
				JSONObject temp2 = new JSONObject();
                temp2.put("name", jobjs2.get(j).get("사업장명"));
                temp2.put("category",jobjs2.get(j).get("업종상세"));
                temp2.put("addr", jobjs2.get(j).get("주소1"));
				j2.add(temp2);
			}
			out2.write(j2.toJSONString());
			
			test1.close();
			test2.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
