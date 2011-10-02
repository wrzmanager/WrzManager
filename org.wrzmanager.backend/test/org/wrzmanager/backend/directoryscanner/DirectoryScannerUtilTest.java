package org.wrzmanager.backend.directoryscanner;

import static org.junit.Assert.*;

import org.junit.Test;

public class DirectoryScannerUtilTest {

	@Test
	public void testShouldBeAReleaseDirectoryName() {
		boolean result = DirectoryScannerUtil
				.shouldBeAReleaseDirectoryName("Kick.Ass.German.2010.AC3.DVDRiP.XViD.iNTERNAL-CiA");
		assertTrue(result);

		result = DirectoryScannerUtil
				.shouldBeAReleaseDirectoryName("sample");
		assertFalse(result);
	}

}
