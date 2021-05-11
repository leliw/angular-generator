package pl.priv.leliwa.angularGenerator.tableWithDialog;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public abstract class AbstractGenerator {

	protected abstract String getTemplateName();
	protected abstract void putTemplateVariables(VelocityContext context) throws Exception;

	public StringWriter generate() throws Exception {
        VelocityContext context = new VelocityContext();
        this.putTemplateVariables(context);
        Template template = Velocity.getTemplate(this.getTemplateName());
        StringWriter sw = new StringWriter();
        template.merge( context, sw );
        return sw;
	}

}
