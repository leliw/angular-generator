package pl.priv.leliwa.angularGenerator.tableWithDialog;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.velocity.VelocityContext;
import org.json.JSONObject;

public class Generator extends AbstractGenerator {


	private String jsonFileName;

	public void generateAll(String template, String inputJson, String outputPath, String componentName) throws Exception {
		this.jsonFileName = inputJson;
		this.generateAll(template, outputPath, componentName);
	}
	
	protected void putTemplateVariables(VelocityContext context) throws Exception {	
		String jsonString = Files.readString(Paths.get(this.jsonFileName));
	    JSONObject jsonObj = new JSONObject(jsonString);
	    for(String key : jsonObj.keySet())
	    {
	      context.put(key, jsonObj.get(key));
	    }
	}
	
}
