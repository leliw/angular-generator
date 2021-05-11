package pl.priv.leliwa.angularGenerator.tableWithDialog;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.json.JSONObject;

public class Generator extends AbstractGenerator {

	private String templatePath = "/templates/tableWithDialog";
	private String templateName;
	private String jsonFileName;

	public void generateAll(String template, String outputPath, String componentName) throws Exception {
		this.templatePath = "/templates/" + template;
		List<String> templates = getResourceFiles(this.templatePath);
		for (String t : templates) {
			if (t.endsWith(".vm")) {
				String outputFileName = outputPath + "/" + componentName + (t.startsWith("-") ? "" :  ".") + t.substring(0, t.length() - 3); 
				System.out.print("Generating " + outputFileName + " ... ");
				
				StringWriter sw = this.generate(t);
		        FileWriter fw = new FileWriter(outputFileName);
		        fw.write(sw.toString());
		        fw.close();
				System.out.println("ok.");
			}
		}
	}
	
	public void generateAll(String template, String inputJson, String outputPath, String componentName) throws Exception {
		this.jsonFileName = inputJson;
		this.generateAll(template, outputPath, componentName);
	}
	
	public StringWriter generate(String templateName) throws Exception {
		this.templateName = templateName;
		return super.generate();
	}
	@Override
	protected String getTemplateName() {
		return String.format("%s/%s", templatePath, templateName);
	}

	private List<String> getResourceFiles(String path) throws IOException {
	    List<String> filenames = new ArrayList<>();

	    try (
	            InputStream in = getResourceAsStream(path);
	            BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
	        String resource;

	        while ((resource = br.readLine()) != null) {
	            filenames.add(resource);
	        }
	    }

	    return filenames;
	}
	
	private InputStream getResourceAsStream(String resource) {
	    final InputStream in
	            = getContextClassLoader().getResourceAsStream(resource);

	    return in == null ? getClass().getResourceAsStream(resource) : in;
	}
	
	private ClassLoader getContextClassLoader() {
	    return Thread.currentThread().getContextClassLoader();
	}

	
	protected void putTemplateVariables(VelocityContext context) throws Exception {
		
		String jsonString = Files.readString(Paths.get(this.jsonFileName));
	    JSONObject jsonObj = new JSONObject(jsonString);
	    for(String key : jsonObj.keySet())
	    {
	      context.put(key, jsonObj.get(key));
	    }
		
//		context.put( "item", "Dictionary");
//		context.put( "Item", "Dictionary");
//		context.put( "component", "dictionaries");
//		context.put( "Component", "Dictionaries");
//		context.put( "apiPath", "/dictionaries");
//		Map<String, String> fields = new LinkedHashMap<String, String>();
//		fields.put("id", "number");
//		fields.put("type", "string");
//		fields.put("symbol", "string");
//		fields.put("name", "string");
//		fields.put("description", "string");
//		context.put("fields", fields);
//		context.put("id", "id");
	}
}
