/**
 * 
 */
package pl.priv.leliwa.angularGenerator.generator.spring;

import java.io.StringWriter;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.priv.leliwa.angularGenerator.AbstractGeneratorTest;
import pl.priv.leliwa.angularGenerator.generator.Generator;
import pl.priv.leliwa.angularGenerator.generator.SpringGenerator;

/**
 * @author Marcin Leliwa
 *
 */
public class SpringGeneratorCrudRepositoryRestControllerTest extends AbstractGeneratorTest {
	private static Generator generator;

	@BeforeClass
	public static void BeforeClass() {
		generator = new SpringGenerator();
		generator.setTemplatePath("/templates/spring/crudRepositoryRestController");
	}

	@Test
	public void testItem() throws Exception {
		generator.setJsonFileName(getResourceFileName("/spring/crudRepositoryRestController/spring_product.json"));
		                                               
		StringWriter sw = generator.generate("item.java.vm");
		this.assertFileContent("/spring/crudRepositoryRestController/Product.java_result", sw);
	}
	
	@Test
	public void testItemController() throws Exception {
		generator.setJsonFileName(getResourceFileName("/spring/crudRepositoryRestController/spring_product.json"));
		                                               
		StringWriter sw = generator.generate("itemController.java.vm");
		this.assertFileContent("/spring/crudRepositoryRestController/ProductController.java_result", sw);
	}
	
	@Test
	public void testItemRepository() throws Exception {
		generator.setJsonFileName(getResourceFileName("/spring/crudRepositoryRestController/spring_product.json"));
		                                               
		StringWriter sw = generator.generate("itemRepository.java.vm");
		this.assertFileContent("/spring/crudRepositoryRestController/ProductRepository.java_result", sw);
	}
	
	@Test
	public void testItemNotFoundException() throws Exception {
		generator.setJsonFileName(getResourceFileName("/spring/crudRepositoryRestController/spring_product.json"));
		                                               
		StringWriter sw = generator.generate("itemNotFoundException.java.vm");
		this.assertFileContent("/spring/crudRepositoryRestController/ProductNotFoundException.java_result", sw);
	}
	
}
