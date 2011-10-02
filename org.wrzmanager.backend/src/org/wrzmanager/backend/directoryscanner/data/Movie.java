package org.wrzmanager.backend.directoryscanner.data;

public class Movie extends Release {
	
	private String imdbId = null;
	
	//TODO quality and stuff

	public Movie(String dirName, String path) {
		super(dirName, path);
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

}
