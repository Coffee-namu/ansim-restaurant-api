package ansim_api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.JSONObject;

public class Parser {
    private Vector<String> jsonData = new Vector<String>();

	public void CSVParser(String filename) {
        try{
            FileReader input = new FileReader(filename);
            BufferedReader br = new BufferedReader(input); 

            String line = "";

            while ((line = br.readLine()) != null)
                this.jsonData.add(line);
          
            br.close();
        }
        catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        }
        catch (IOException e) { 
            e.printStackTrace(); 
        }
	}

    public Vector<JSONObject> JsonParser() {
    	Vector<JSONObject> jobjs = new Vector<JSONObject>();
        String[] category = this.jsonData.get(0).split(",");
        int catenum = category.length;
        int objnum = this.jsonData.size();

        for(int j = 1; j < objnum; j++){
            String[] tmp = new String[catenum];
            tmp = this.jsonData.get(j).split(",",-1);
            JSONObject jobj = new JSONObject();

            for(int k = 0; k < catenum; k++) {
            	if(tmp[k] == "" || tmp[k] == " ")
            		jobj.put(category[k],null);
            	else
            		jobj.put(category[k],tmp[k]);
            }
            	
            jobjs.add(jobj);
        }
        
        return jobjs;
    }
}
