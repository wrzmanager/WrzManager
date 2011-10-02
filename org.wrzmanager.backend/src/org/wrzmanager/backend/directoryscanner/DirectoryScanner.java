package org.wrzmanager.backend.directoryscanner;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.wrzmanager.backend.directoryscanner.data.UnProcessedRelease;

public class DirectoryScanner {

	public List<UnProcessedRelease> scanDirectory(File startDirectory) {
		File[] files = startDirectory.listFiles();
		List<UnProcessedRelease> unProcessedReleases = new LinkedList<>();

		for (File file : files) {
			if (file.isDirectory()) {
				//TODO what if an release-dir includes more releases? like Packs
				if (DirectoryScannerUtil.shouldBeAReleaseDirectory(file)) {
					unProcessedReleases.add(new UnProcessedRelease(file
							.getAbsolutePath()));
				} else {
					unProcessedReleases.addAll(scanDirectory(file));
				}

			}
		}

		return unProcessedReleases;
	}

}
