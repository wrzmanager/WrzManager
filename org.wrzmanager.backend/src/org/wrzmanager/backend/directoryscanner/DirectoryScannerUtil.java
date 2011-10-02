package org.wrzmanager.backend.directoryscanner;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DirectoryScannerUtil {

	public static boolean shouldBeAReleaseDirectory(File file) {
		String dirName = file.getName();
		//TODO some more checks, maybe contained files
		return shouldBeAReleaseDirectoryName(dirName);
	}

	public static boolean shouldBeAReleaseDirectoryName(String dirName) {
		Pattern p = Pattern.compile(".+-[a-zA-Z0-9]+"); //TODO improve it
		Matcher m = p.matcher(dirName);
		return m.matches();
	}


}
