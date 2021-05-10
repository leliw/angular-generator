package pl.priv.leliwa.angularGenerator.tableWithDialog;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public abstract class AbstractGenerator {

	protected abstract String getTemplateName();

	public StringWriter generate() {
        VelocityContext context = new VelocityContext();
        this.putTemplateVariables(context);
        Template template = Velocity.getTemplate(this.getTemplateName());
        StringWriter sw = new StringWriter();
        template.merge( context, sw );
        return sw;
	}

	private void putTemplateVariables(VelocityContext context) {
		String s = "xxx";
		context.put( "item", "Dictionary");
		context.put( "Item", "Dictionary");
		context.put( "component", "dictionaries");
		context.put( "Component", "Dictionaries");
		context.put( "apiPath", "/dictionaries");
		Map<String, String> fields = new LinkedHashMap<String, String>();
		fields.put("id", "number");
		fields.put("type", "string");
		fields.put("symbol", "string");
		fields.put("name", "string");
		fields.put("description", "string");
		context.put("fields", fields);
		context.put("id", "id");
	}

}
