package pl.priv.leliwa.angularGenerator.tableWithDialog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.velocity.VelocityContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Generator extends AbstractGenerator {

	protected String jsonFileName;
	protected Map<String, Object> jsonMap;

	public void generateAllFromJson(String template, String inputJson, String outputPath) throws Exception {
		this.setJsonFileName(inputJson);
		this.generateAll(template, outputPath);
	}

	protected void putTemplateVariables(VelocityContext context) throws Exception {
		for (Entry<String, Object> e : jsonMap.entrySet()) {
			context.put(e.getKey(), e.getValue());
		}
	}
	
	public String getJsonFileName() {
		return jsonFileName;
	}

	@SuppressWarnings("unchecked")
	public void setJsonFileName(String jsonFileName) throws IOException {
		this.jsonFileName = jsonFileName;

		Path path = Paths.get(this.jsonFileName);
		String jsonString = Files.readString(path);
		ObjectMapper mapper = new ObjectMapper();
		jsonMap = mapper.readValue(jsonString, Map.class);
		this.addDefaultValues();
	}

	protected void addDefaultValues() {
		if (jsonMap.containsKey("Item") && !jsonMap.containsKey("item")) {
			String s = (String) jsonMap.get("Item");
			s = s.substring(0,1).toLowerCase() + s.substring(1);
			jsonMap.put("item", s);
		}
	}

}
