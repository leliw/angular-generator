package pl.priv.leliwa.angularGenerator.tableWithDialog;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.velocity.VelocityContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Generator extends AbstractGenerator {

	private String jsonFileName;

	public void generateAll(String template, String inputJson, String outputPath, String componentName)
			throws Exception {
		this.jsonFileName = inputJson;
		this.generateAll(template, outputPath, componentName);
	}

	protected void putTemplateVariables(VelocityContext context) throws Exception {
		Path path = Paths.get(this.jsonFileName);
		String jsonString = Files.readString(path);

		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, ?> json = mapper.readValue(jsonString, Map.class);
		for (Entry<String, ?> e : json.entrySet()) {
			context.put(e.getKey(), e.getValue());
		}
	}

	public String getJsonFileName() {
		return jsonFileName;
	}

	public void setJsonFileName(String jsonFileName) {
		this.jsonFileName = jsonFileName;
	}

}
