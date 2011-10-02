package org.wrzmanager.backend.directoryscanner;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.wrzmanager.backend.directoryscanner.data.UnProcessedRelease;

public class DirectoryScannerTest {
	
	DirectoryScanner dirScanner;
	
	@Before
	public void setUp() {
		dirScanner = new DirectoryScanner();
	}

	@Test
	public void testScanDirectory() {
		List<UnProcessedRelease> result =dirScanner.scanDirectory(new File("O:/Filme"));
		for(UnProcessedRelease rel : result) {
			System.out.println(rel);
		}
	}

}
