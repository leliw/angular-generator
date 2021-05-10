package pl.priv.leliwa.angularGenerator.tableWithDialog;

import java.io.StringWriter;

public class Generator extends AbstractGenerator {

	private String templatePath = "/templates/tableWithDialog";
	private String templateName;

	public StringWriter generate(String templateName) {
		this.templateName = templateName;
		return super.generate();
	}
	@Override
	protected String getTemplateName() {
		return String.format("%s/%s", templatePath, templateName);
	}

}
