package pl.priv.leliwa.angularGenerator.generator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SpringGenerator extends Generator {

	protected String getOutputFileName(String outputPath, String templateName) {
		String Item = (String) this.jsonMap.get("Item");
		String fileName = templateName.replace("Item", Item);
		fileName = fileName.substring(0, fileName.length() - 3);
		return outputPath + "/" + fileName;
	}
	
	protected void addDefaultValues() {
		if (jsonMap.containsKey("Item") && !jsonMap.containsKey("item")) {
			String s = (String) jsonMap.get("Item");
			s = s.substring(0,1).toLowerCase() + s.substring(1);
			jsonMap.put("item", s);
		}
			
		if (jsonMap.containsKey("fields") && !jsonMap.containsKey("javaFields")) {
			@SuppressWarnings("unchecked")
			Map<String, String> fields = (Map<String, String>) jsonMap.get("fields");
			Map<String, String> javaFields = new LinkedHashMap<String, String>(); 
			for (Entry<String, String> e : fields.entrySet()) {
				String type = e.getValue();
				switch(type) {
				case "number": type = "Integer"; break;
				case "string": type = "String"; break;
				default: break;
				}
				javaFields.put(e.getKey(), type);
			}
			jsonMap.put("javaFields", javaFields);
		}
	}

}
