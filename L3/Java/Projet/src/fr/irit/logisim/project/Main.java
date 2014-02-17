/***
 * @author: Tom Jorquera, Duy Dinh 
 * @contact: Tom.Jorquera@irit.fr, Duy.Dinh@irit.fr
 * @version : 1.0
 */
package fr.irit.logisim.project;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import com.cburch.logisim.file.LoadFailedException;
import com.cburch.logisim.file.Loader;
import com.cburch.logisim.file.LogisimFile;
import com.cburch.logisim.gui.main.Frame;
import com.cburch.logisim.proj.Project;
import com.cburch.logisim.tools.Library;

import fr.irit.logisim.component.library.myComponentsLibrary;
import fr.irit.logisim.jarLib.JarBuilder;

public class Main {

	/***
	 * Jar file for our custom components
	 */
	static String myComponentLib = "customLib.jar";
	/***
	 * Name of the main class of the library loader
	 */
	static String className = "fr.irit.logisim.component.library.myComponentsLibrary";

	/**
	 * Used to display a custom logisim interface, loading only the base and our
	 * custom library
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws IOException
	 * 
	 */
	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, IOException {

		// load our custom library (myComponents)
		new myComponentsLibrary();
		System.out.format("Building %s library.\n", myComponentLib);
		// automatically build the jar from bin/*.class
		JarBuilder.build("./bin", myComponentLib);

		// create the main frame (Logisim GUI)
		JFrame mainFrame = new JFrame();

		// create the project loader
		Loader loader = new Loader(mainFrame);

		// copy the config file from our custom template
		// if new.circ exists it will be overwritten
		FileManager.copyFile("./example.circ", "./circuit.circ");
		File newFile = new File("./circuit.circ");

		// use the new file to load our custom config
		LogisimFile file = null;
		try {
			System.out.format("Loading circuit from file %s.\n", newFile);
			file = loader.openLogisimFile(newFile);
		} catch (LoadFailedException ex) {
			// displayException(monitor, ex);
			System.err.println(ex);
		} finally {

		}

		// load our custom library from the generated .jar
		System.out.format("Loading %s library.\n", myComponentLib);
		Library l = loader.loadJarLibrary(new File(myComponentLib), className);

		// add the custom library to our config
		file.addLibrary(l);

		// create a new project using our config
		Project newProj = new Project(file);

		// put it in a new(??) frame
		Frame newFrame = new Frame(newProj);
		newProj.setFrame(newFrame);

		newFrame.pack();
		newFrame.setVisible(true);
	}	
}
