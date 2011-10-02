package org.wrzmanager.backend.directoryscanner.data;

import java.util.Date;

public class Release {

	/**
	 * example: Kick.Ass.German.2010.AC3.DVDRiP.XViD.iNTERNAL-CiA
	 */
	private String dirName;
	
	private String title;
	
	private String year;

	private Boolean sceneRelease = null;

	/**
	 * can be <code>null</code>
	 */
	private String crew;

	/**
	 * can be <code>null</code>
	 */
	private Date preTime;

	/**
	 * Location on Harddrive (absolute Path)
	 */
	private String path;

	public Release(String dirName, String path) {
		if(dirName == null || path == null) {
			throw new NullPointerException();
		}
		
		this.dirName = dirName;
		this.path = path;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		if(dirName == null) {
			throw new NullPointerException();
		}
		this.dirName = dirName;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public Date getPreTime() {
		return preTime;
	}

	public void setPreTime(Date preTime) {
		this.preTime = preTime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		if(path == null) {
			throw new NullPointerException();
		}
		this.path = path;
	}

	public Boolean isSceneRelease() {
		return sceneRelease;
	}

	public void setSceneRelease(Boolean sceneRelease) {
		this.sceneRelease = sceneRelease;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Boolean getSceneRelease() {
		return sceneRelease;
	}

	
	// TODO size?

}
