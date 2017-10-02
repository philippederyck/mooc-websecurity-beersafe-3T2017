package eu.beersafe.mooc.filters.base;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.servlet.http.HttpServletRequest;

import eu.beersafe.mooc.logger.Logger;

public abstract class ConfigurableManager {

	private String filename;
	
	public ConfigurableManager(String filename) {
		this.setFilename(filename);
	}
	
	protected abstract void processLine(String line);
	
	protected boolean readPolicyFile(HttpServletRequest request) {
		boolean result = true;
		try {
			String path = request.getServletContext().getRealPath("/_securitypolicies/" + this.getFilename());
			if(path != null) {
				BufferedReader br = new BufferedReader(new FileReader(path));
				String line = null;
				
				while((line = br.readLine()) != null) {
					if(!line.startsWith("#")) {
						processLine(line);
					}
				}
				
				br.close();
			}
		}
		catch(Exception e) {
			result = false;
			
			Logger.error("Failed to read configuration file", e);
		}
		return result;
	}

	private String getFilename() {
		return filename;
	}

	private void setFilename(String filename) {
		this.filename = filename;
	}
}
