package pl.priv.leliwa.angularGenerator;

import pl.priv.leliwa.angularGenerator.tableWithDialog.Generator;
import pl.priv.leliwa.angularGenerator.tableWithDialog.SpringGenerator;


public class App 
{
    public static void main( String[] args ) throws Exception
    {
        SpringGenerator gen = new SpringGenerator();
//        gen.generateAllFromJson("tableWithDialog", "c:/temp/input.json", "c:/temp");
        gen.generateAllFromJson("spring/crudRepositoryRestController", "c:/temp/spring_product.json", "c:/temp");
    }
}
