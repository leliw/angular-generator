package pl.priv.leliwa.angularGenerator;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractGeneratorTest {

	protected String getResourceFileName(String resoureName) {
		URL u = getClass().getResource(resoureName);
		return u.getFile().substring(1);
	}

	protected void assertFileContent(String fileName, StringWriter sw) throws Exception {
		String actual = sw.toString();
		assertFileContent(fileName, actual);
	}


	protected void assertFileContent(String fileName, String actualConent) throws Exception {
		URL u2 = getClass().getResource(fileName);
		Path path = Paths.get(u2.getFile().substring(1));
		String expected = Files.readString(path);
		assertEquals(expected, actualConent);		
	}
}
