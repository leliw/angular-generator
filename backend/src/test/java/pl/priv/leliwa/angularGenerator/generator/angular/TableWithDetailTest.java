/**
 * 
 */
package pl.priv.leliwa.angularGenerator.generator.angular;

import java.io.StringWriter;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.priv.leliwa.angularGenerator.AbstractGeneratorTest;
import pl.priv.leliwa.angularGenerator.generator.AngularGenerator;
import pl.priv.leliwa.angularGenerator.generator.Generator;

/**
 * @author Marcin Leliwa
 *
 */
public class TableWithDetailTest extends AbstractGeneratorTest {
	private static Generator generator;

	@BeforeClass
	public static void BeforeClass() {
		generator = new AngularGenerator();
		generator.setTemplatePath("/templates/angular/tableWithDetail");
	}

	@Test
	public void datasource() throws Exception {
		generator.setJsonFileName(getResourceFileName("/angular/tableWithDetail/product.json"));
		StringWriter sw = generator.generate("-datasource.ts.vm");
		this.assertFileContent("/angular/tableWithDetail/product-datasource.ts", sw);
	}
	

	
}
