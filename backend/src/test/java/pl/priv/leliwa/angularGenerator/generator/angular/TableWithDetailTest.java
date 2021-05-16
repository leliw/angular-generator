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
	public void datasourceTs() throws Exception {
		generator.setJsonFileName(getResourceFileName("/angular/tableWithDetail/product.json"));
		StringWriter sw = generator.generate("-datasource.ts.vm");
		this.assertFileContent("/angular/tableWithDetail/products-datasource.ts_result", sw);
	}
	
	@Test
	public void componentTs() throws Exception {
		generator.setJsonFileName(getResourceFileName("/angular/tableWithDetail/product.json"));
		StringWriter sw = generator.generate("component.ts.vm");
		this.assertFileContent("/angular/tableWithDetail/products.component.ts_result", sw);
	}

	@Test
	public void componentHtml() throws Exception {
		generator.setJsonFileName(getResourceFileName("/angular/tableWithDetail/product.json"));
		StringWriter sw = generator.generate("component.html.vm");
		this.assertFileContent("/angular/tableWithDetail/products.component.html_result", sw);
	}

	@Test
	public void componentCss() throws Exception {
		generator.setJsonFileName(getResourceFileName("/angular/tableWithDetail/product.json"));
		StringWriter sw = generator.generate("component.css.vm");
		this.assertFileContent("/angular/tableWithDetail/products.component.css_result", sw);
	}
	
	@Test
	public void componentDetailTs() throws Exception {
		generator.setJsonFileName(getResourceFileName("/angular/tableWithDetail/product.json"));
		StringWriter sw = generator.generate("-detail.component.ts.vm");
		this.assertFileContent("/angular/tableWithDetail/products-detail.component.ts_result", sw);
	}

	@Test
	public void componentDetailHtml() throws Exception {
		generator.setJsonFileName(getResourceFileName("/angular/tableWithDetail/product.json"));
		StringWriter sw = generator.generate("-detail.component.html.vm");
		this.assertFileContent("/angular/tableWithDetail/products-detail.component.html_result", sw);
	}

	@Test
	public void componentDetailCss() throws Exception {
		generator.setJsonFileName(getResourceFileName("/angular/tableWithDetail/product.json"));
		StringWriter sw = generator.generate("-delete-dialog.component.css.vm");
		this.assertFileContent("/angular/tableWithDetail/products-delete-dialog.component.css_result", sw);
	}
	
	@Test
	public void componentDeleteDialogTs() throws Exception {
		generator.setJsonFileName(getResourceFileName("/angular/tableWithDetail/product.json"));
		StringWriter sw = generator.generate("-delete-dialog.component.ts.vm");
		this.assertFileContent("/angular/tableWithDetail/products-delete-dialog.component.ts_result", sw);
	}

	@Test
	public void componentDeleteDialogHtml() throws Exception {
		generator.setJsonFileName(getResourceFileName("/angular/tableWithDetail/product.json"));
		StringWriter sw = generator.generate("-delete-dialog.component.html.vm");
		this.assertFileContent("/angular/tableWithDetail/products-delete-dialog.component.html_result", sw);
	}

	@Test
	public void componentDeleteDialogCss() throws Exception {
		generator.setJsonFileName(getResourceFileName("/angular/tableWithDetail/product.json"));
		StringWriter sw = generator.generate("-delete-dialog.component.css.vm");
		this.assertFileContent("/angular/tableWithDetail/products-delete-dialog.component.css_result", sw);
	}
}
