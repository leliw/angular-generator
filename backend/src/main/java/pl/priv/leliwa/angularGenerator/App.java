package pl.priv.leliwa.angularGenerator;

import pl.priv.leliwa.angularGenerator.tableWithDialog.Generator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Generator gen = new Generator();
        gen.generateAll("tableWithDialog", "c:/temp/input.json", "c:/temp");
    }
}
