package org.wrzmanager.backend.connector;

import org.junit.Before;
import org.junit.Test;
import org.wrzmanager.backend.directoryscanner.data.UnProcessedRelease;

public class XRelConnectorTest {

	XRelConnector connector;

	@Before
	public void setUp() {
		connector = new XRelConnector();
	}

	@Test
	public void testGetReleaseInformation() {
		UnProcessedRelease unProcessedRelease = new UnProcessedRelease("O:\\Filme\\A.Beautiful.Mind.Genie.und.Wahnsinn.2001.German.DVDRiP.AC3.XViD.iNTERNAL-SAMFD");
		connector.getReleaseInformation(unProcessedRelease);
	}

}
