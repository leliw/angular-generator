package pl.priv.leliwa.angularGenerator.generator;

public class AngularGenerator extends Generator {

	protected String getOutputFileName(String outputPath, String templateName) {
		String componentName = (String) this.jsonMap.get("Component");
		return outputPath + "/" + componentName + (templateName.startsWith("-") ? "" :  ".") + templateName.substring(0, templateName.length() - 3);
	}
	
	protected void addDefaultValues() {
		if (jsonMap.containsKey("Item") && !jsonMap.containsKey("item")) {
			String s = (String) jsonMap.get("Item");
			s = s.substring(0,1).toLowerCase() + s.substring(1);
			jsonMap.put("item", s);
		}
	}

}
