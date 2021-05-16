package pl.priv.leliwa.angularGenerator;

import pl.priv.leliwa.angularGenerator.generator.AngularGenerator;
import pl.priv.leliwa.angularGenerator.generator.SpringGenerator;


public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	String frontendPath = "C:\\Home\\hanza2-UI\\src\\app";
    	String backendPath = "C:\\Users\\marcin\\eclipse-workspace\\hanza2\\src\\main\\java";
        SpringGenerator springGenerator = new SpringGenerator();
        AngularGenerator angularGenerator = new AngularGenerator();
//        gen.generateAllFromJson("tableWithDialog", "c:/temp/input.json", "c:/temp");
//        springGenerator.generateAllFromJson("spring/crudRepositoryRestController", "c:/temp/spring_product.json", backendPath);
        angularGenerator.generateAllFromJson("angular/tableWithDetail", "c:/temp/spring_product.json", frontendPath);
    }
}
