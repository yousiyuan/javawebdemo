package org.lnson.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

public class FileExtUtils {

	public static String getExtension(String filepath) {
		try {
			String fileType = getContentType(filepath);
			if (fileType == null || "".equals(fileType.trim()))
				return "";
			MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
			MimeType type = allTypes.forName(fileType);
			String ext = type.getExtension();
			return ext;
		} catch (MimeTypeException | IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getMimeType(String fileUrl) throws java.io.IOException, MalformedURLException {
		URL u = new URL(fileUrl);
		URLConnection uc = u.openConnection();
		String type = uc.getContentType();
		return type;
	}

	public static String getContentType(String filepath) throws IOException {
		Path path = Paths.get(filepath);
		String type = Files.probeContentType(path);
		return type;
	}

	public static String getFileName(String filepath) {
		Path path = Paths.get(filepath);
		String filename = path.getFileName().toString();
		if (filename.lastIndexOf(".") > -1)
			return filename.substring(0, filename.lastIndexOf("."));
		else
			return filename;
	}

}
