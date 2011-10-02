package org.wrzmanager.backend.releaseprocessor;

import java.util.LinkedList;
import java.util.List;

import org.wrzmanager.backend.connector.XRelConnector;
import org.wrzmanager.backend.directoryscanner.data.Release;
import org.wrzmanager.backend.directoryscanner.data.UnProcessedRelease;

public class ReleaseProcessor {
	
	private XRelConnector xRelConnector = new XRelConnector();
	
	public List<Release> processReleases(List<UnProcessedRelease> unProcessedReleases) {
		List<Release> releases = new LinkedList<>();
		for(UnProcessedRelease unProcessedRelease : unProcessedReleases) {
			//TODO handling when release is not available on xrel
			//TODO imdb stuff
			Release release = xRelConnector.getReleaseInformation(unProcessedRelease);
			if(release != null) {
				releases.add(release);
			}
		}
		return releases;
	}

}
