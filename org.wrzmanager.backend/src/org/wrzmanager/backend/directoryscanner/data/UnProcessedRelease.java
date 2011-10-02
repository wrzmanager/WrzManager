package org.wrzmanager.backend.directoryscanner.data;

import java.io.File;

public class UnProcessedRelease {
	private String path;
	//TODO store any other information that can be gathered only based on the files
	
	public UnProcessedRelease(String path) {
		if(path == null) {
			throw new NullPointerException();
		}
		
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getDirName() {
		File f = new File(path);
		return f.getName();
	}
	
	@Override
	public String toString() {
		return path;
	}
}
