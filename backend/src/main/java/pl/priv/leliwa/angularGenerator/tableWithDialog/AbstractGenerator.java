package pl.priv.leliwa.angularGenerator.tableWithDialog;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

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
	
	public void generateAll(String template, String outputPath, String componentName) throws Exception {
		this.setTemplatePath("/templates/" + template);
		List<String> templates = getResourceFiles(this.getTemplatePath());
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
	
	protected List<String> getResourceFiles(String path) throws IOException {
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

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
}
