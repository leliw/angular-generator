/**
 * 
 */
package pl.priv.leliwa.angularGenerator;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.priv.leliwa.angularGenerator.tableWithDialog.Generator;

/**
 * @author Marcin Leliwa
 *
 */
public class TableWithDetailTest {
	private static Generator generator;

	@BeforeClass
	public static void BeforeClass() {
		generator = new Generator();
		generator.setTemplatePath("/templates/tableWithDetail");
	}

	@Test
	public void datasource() throws Exception {
//		List<String> templates = getResourceFiles("/tableWithDetail/");
//		for (String t : templates) {
////			System.out.println(t);
//		}
		
		generator.setJsonFileName(getResourceFileName("/tableWithDetail/product.json"));
		StringWriter sw = generator.generate("-datasource.ts.vm");
		this.assertFileContent("/tableWithDetail/product-datasource.ts", sw);
	}
	
	
	
	
	private String getResourceFileName(String resoureName) {
		URL u = getClass().getResource(resoureName);
		return u.getFile().substring(1);
	}

	private void assertFileContent(String fileName, StringWriter sw) throws Exception {
		String actual = sw.toString();
		assertFileContent(fileName, actual);
	}


	protected void assertFileContent(String fileName, String actualConent) throws Exception {
		URL u2 = getClass().getResource(fileName);
		Path path = Paths.get(u2.getFile().substring(1));
		String expected = Files.readString(path);
		assertEquals(expected, actualConent);		
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
}
