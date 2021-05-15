package pl.priv.leliwa.angularGenerator.generator;

import java.io.FileWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import pl.priv.leliwa.angularGenerator.Resources;

public abstract class AbstractGenerator {

	private String templatePath;
	private String templateName;
	
	protected abstract void putTemplateVariables(VelocityContext context) throws Exception;

	public AbstractGenerator() {
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");        
//        p.setProperty("resource.loader.file.path", "templates");
        Velocity.init(p);
	}
	
	public void generateAll(String template, String outputPath) throws Exception {
		this.setTemplatePath("/templates/" + template);
		List<String> templates = Resources.GetResourceFiles(this.getTemplatePath());
		for (String t : templates) {
			if (t.endsWith(".vm")) {
				String outputFileName = this.getOutputFileName(outputPath, t); 
				System.out.print("Generating " + outputFileName + " ... ");
				
				StringWriter sw = this.generate(t);
		        FileWriter fw = new FileWriter(outputFileName);
		        fw.write(sw.toString());
		        fw.close();
				System.out.println("ok.");
			}
		}
	}
	
	protected String getOutputFileName(String outputPath, String templateName) {
		return outputPath + "/" + templateName.substring(0, templateName.length() - 3);
	}

	public StringWriter generate(String templateName) throws Exception {
		this.templateName = templateName;
		return this.generate();
	}
	
	public StringWriter generate() throws Exception {
        VelocityContext context = new VelocityContext();
        this.putTemplateVariables(context);
        Template template = Velocity.getTemplate(this.getTemplateName());
        StringWriter sw = new StringWriter();
        template.merge( context, sw );
        return sw;
	}

	protected String getTemplateName() {
		if (getTemplatePath() != null)
			return String.format("%s/%s", getTemplatePath(), templateName);
		else
			return templateName;
	}
	
	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
}
