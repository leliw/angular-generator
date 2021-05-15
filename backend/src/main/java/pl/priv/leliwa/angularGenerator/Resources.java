package pl.priv.leliwa.angularGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class Resources {
    
	private static volatile Resources instance;
    
	public static Resources getInstance() {
        if (instance == null) {
            synchronized (Resources .class) {
                if (instance == null) {
                    instance = new Resources();
                }
            }
        }
        return instance;
    }
    
	public static List<String> GetResourceFiles(String path) throws IOException {
		return Resources.getInstance().getResourceFiles(path);
	}
	
	public List<String> getResourceFiles(String path) throws IOException {
		List<String> filenames = new ArrayList<>();

		try (InputStream in = getResourceAsStream(path);
				BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			String resource;

			while ((resource = br.readLine()) != null) {
				filenames.add(resource);
			}
		}

		return filenames;
	}

	public String GetResourceFileName(String resoureName) {
		return Resources.getInstance().getResourceFileName(resoureName);
	}
	
	public String getResourceFileName(String resoureName) {
		URL u = getClass().getResource(resoureName);
		return u.getFile().substring(1);
	}
	
	private InputStream getResourceAsStream(String resource) {
		final InputStream in = getContextClassLoader().getResourceAsStream(resource);

		return in == null ? getClass().getResourceAsStream(resource) : in;
	}

	private static ClassLoader getContextClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
}
