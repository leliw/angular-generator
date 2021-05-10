package pl.priv.leliwa.angularGenerator;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.app.Velocity;

import pl.priv.leliwa.angularGenerator.tableWithDialog.Generator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");        
//        p.setProperty("resource.loader.file.path", "templates");
        Velocity.init(p);
        
        Generator gen = new Generator();
//        StringWriter sw = gen.generate("-datasource.ts.vm");
//        StringWriter sw = gen.generate("-dialog.component.html.vm");
//        StringWriter sw = gen.generate("dialog.component.spec.ts.vm");
//        StringWriter sw = gen.generate("dialog.component.ts.vm");
//        StringWriter sw = gen.generate("component.html.vm");
//        StringWriter sw = gen.generate("component.spec.ts.vm");
        StringWriter sw = gen.generate("component.ts.vm");
        System.out.println(sw.toString());
    }
}
