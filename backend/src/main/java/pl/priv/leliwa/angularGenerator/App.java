package pl.priv.leliwa.angularGenerator;

import java.util.Properties;

import org.apache.velocity.app.Velocity;

import pl.priv.leliwa.angularGenerator.tableWithDialog.Generator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");        
//        p.setProperty("resource.loader.file.path", "templates");
        Velocity.init(p);
        
        Generator gen = new Generator();
        gen.generateAll("tableWithDialog", "c:/temp/input.json", "c:/temp", "Suffix");
    }
}
