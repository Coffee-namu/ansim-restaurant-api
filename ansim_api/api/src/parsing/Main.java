package parsing;

import java.util.Vector;

import org.json.simple.JSONObject;

public class Main {	
    public static void main(String args[]){
        Parser p1 = new Parser();
        Parser p2 = new Parser();
        String fname1 = "경기도안심식당정보.csv";
        String fname2 = "농림축산부안심식당정보.csv";
        
        try {
            p1.CSVParser(fname1);
            Vector<JSONObject> jobjs1 = p1.JsonParser();
            for(int i=0;i<jobjs1.size();i++)
            	System.out.println(jobjs1.get(i));
            
            p2.CSVParser(fname2);
            Vector<JSONObject> jobjs2 = p2.JsonParser();
            for(int i=0;i<jobjs2.size();i++)
            	System.out.println(jobjs2.get(i));
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
}