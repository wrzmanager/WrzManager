package org.wrzmanager.backend.connector;

import java.io.IOException;
import java.net.URI;
import java.util.Date;

import javax.ws.rs.core.UriBuilder;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;

import org.wrzmanager.backend.directoryscanner.data.Movie;
import org.wrzmanager.backend.directoryscanner.data.Release;
import org.wrzmanager.backend.directoryscanner.data.UnProcessedRelease;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class XRelConnector {

	private final static URI BASE_URI = UriBuilder.fromUri(
			"http://api.xrel.to/api").build();
	private WebResource service;

	public XRelConnector() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		service = client.resource(BASE_URI);
	}

	public Release getReleaseInformation(UnProcessedRelease unProcessedRelease) {
		ClientResponse response = service.path("release").path("info.xml")
				.queryParam("dirname", unProcessedRelease.getDirName())
				.get(ClientResponse.class);
		if (response.getStatus() == 200) {
			String p = response.getEntity(String.class);
			Builder parser = new Builder();
			Document doc;
			try {
				doc = parser.build(p, null);
				String type = query(doc, "//ext_info/type");
				switch(type) {
				case "movie":
					return createRelease(doc, unProcessedRelease, type);
				default:
					return createRelease(doc, unProcessedRelease, type);
				}
				
				
			} catch (ParsingException | IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			System.out.println("404?");
			return null;
		}

	}
	
	private Release createRelease(Document doc, UnProcessedRelease unProcessedRelease, String type) {
		Release release = null;
		//FIXME this is ugly
		if(type.equals("movie")) {
			release = new Movie(unProcessedRelease.getDirName(), unProcessedRelease.getPath());
		} else {
			release = new Release(unProcessedRelease.getDirName(), unProcessedRelease.getPath());
		}		
		
		setReleaseData(release, doc);
		if(type.equals("movie")) {
			setMovieData((Movie) release, doc);
		}
		 
		return release;
	}
	
	private static void setReleaseData(Release release, Document doc) {
		String dirname = query(doc, "//dirname");
		String link_href = query(doc, "//link_href");
		String time = query(doc, "//time");
		String group_name = query(doc, "//group_name");
		String size_number = query(doc, "//size/number");
		String size_unit = query(doc, "//size/unit");
		String num_ratings = query(doc, "/release/num_ratings");
		String nuke_reason = query(doc, "//nuke_reason");
		String ext_info_id = query(doc, "//ext_info/id");
		String ext_info_title = query(doc, "//ext_info/title");
		String ext_info_link_href = query(doc, "//ext_info/link_href");
		String ext_info_rating = query(doc, "//ext_info/rating");
		String ext_num_ratings = query(doc, "//ext_info/num_ratings");
		
		String is_fix_rls = query(doc, "//is_fix_rls");
		String read_nfo = query(doc, "//read_nfo");
		
		release.setCrew(group_name);
		//release.setDirName(dirname);
		
		release.setPreTime(new Date(Integer.parseInt(time)));
		release.setSceneRelease(null); //TODO
		release.setTitle(ext_info_title);
		release.setYear(null); //TODO?
	}
	
	private static void setMovieData(Movie release, Document doc) {
		String imdbId = query(doc, "//uris/uri");
		if(imdbId != null && imdbId.startsWith("imdb")) {
			String[] splitString = imdbId.split(":");
			release.setImdbId(splitString[1]);
		}
		
	}

	private static String query(Document doc, String xPath) {
		try {
			return doc.query(xPath).get(0).getValue();
		} catch (Exception e) {
			return null;
		}
	}

}
