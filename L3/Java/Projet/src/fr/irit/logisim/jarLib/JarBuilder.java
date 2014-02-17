/***
 * @author: Tom Jorquera, Duy Dinh 
 * @contact: Tom.Jorquera@irit.fr, Duy.Dinh@irit.fr
 * @version : 1.0
 */
package fr.irit.logisim.jarLib;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * Convenience class used to create a jar file
 * 
 * Taken from
 * http://stackoverflow.com/questions/1281229/how-to-use-jaroutputstream
 * -to-create-a-jar-file
 * (with some modifications)
 * 
 */
public class JarBuilder {

	/**
	 * Build the target jar from source file
	 * @param input directory containing *.class
	 * @param outputJar target jar file
	 * @throws IOException
	 */
	public static void build(String input, String outputJar) throws IOException {
		Manifest manifest = new Manifest();
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION,
				"1.0");
		JarOutputStream target = new JarOutputStream(new FileOutputStream(
				outputJar), manifest);

		File f=new File(input);
		
		for (File nestedFile : f.listFiles()) {
			add(nestedFile, target);
		}

		target.close();
	}

	private static void add(File source, JarOutputStream target)
			throws IOException {
		BufferedInputStream in = null;
		try {
			if (source.isDirectory()) {
				String name = source.getPath().replace("\\", "/").replaceFirst("./bin/", "");
				
				if (!name.isEmpty()) {
					if (!name.endsWith("/"))
						name += "/";
					JarEntry entry = new JarEntry(name.replace("\\", "/").replaceFirst("./bin/", ""));
					entry.setTime(source.lastModified());
					target.putNextEntry(entry);
					target.closeEntry();
				}
				for (File nestedFile : source.listFiles()) {
					add(nestedFile, target);
				}

				return;
			}

			JarEntry entry = new JarEntry(source.getPath().replace("\\", "/")
					.replaceFirst("./bin/", ""));
			//System.out.println(entry.getName());

			entry.setTime(source.lastModified());
			target.putNextEntry(entry);
			in = new BufferedInputStream(new FileInputStream(source));

			byte[] buffer = new byte[1024];
			while (true) {
				int count = in.read(buffer);
				if (count == -1)
					break;
				target.write(buffer, 0, count);
			}
			target.closeEntry();
		} finally {
			if (in != null)
				in.close();
		}
	}
}