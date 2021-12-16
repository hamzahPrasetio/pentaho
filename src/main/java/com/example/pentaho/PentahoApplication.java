package com.example.pentaho;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LoggingBuffer;
import org.pentaho.di.core.parameters.UnknownParamException;
import org.pentaho.di.trans.Trans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.pentaho.di.core.logging.KettleLogStore;
import org.w3c.dom.ls.LSOutput;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

@SpringBootApplication
public class PentahoApplication {

	public static RunningTransformations instance;

	public static void main(String[] args) {
		// set variable n parameter
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("Limit", "7");
		parameters.put("filename", "testing/tesfilename");
//		parameters.forEach((key, value) -> {
//			System.out.println(key + " - " + value);
//		});

		SpringApplication.run(PentahoApplication.class, args);

		if (Files.exists(Paths.get("testing"))) {
			System.out.println("testing folder exist");
		} else {
			System.out.println("parantly testing doesnt exit");
		}

		if (Files.exists(Paths.get(parameters.get("filename") + ".txt"))) {
			System.out.println("file output already exists");
			return;
		}

		KettleLogStore.init();
		try {
			KettleEnvironment.init();
		} catch (KettleException e) {
			e.printStackTrace();
		}

		// Create an instance of this demo class for convenience
		instance = new RunningTransformations();

		// run a transformation from the file system
		Trans trans = instance.runTransformationFromFileSystem( "ktr/test.ktr", parameters );

		// retrieve logging appender
		LoggingBuffer appender = KettleLogStore.getAppender();
		// retrieve logging lines for job
		String logText = appender.getBuffer( trans.getLogChannelId(), false ).toString();

		// report on logged lines
		System.out.println( "************************************************************************************************" );
		System.out.println( "LOG REPORT: Transformation generated the following log lines:\n" );
		System.out.println( logText );
		System.out.println( "END OF LOG REPORT" );
		System.out.println( "************************************************************************************************" );

		// run a transformation from the repository
		// NOTE: before running the repository example, you need to make sure that the
		// repository and transformation exist, and can be accessed by the user and password used
		// uncomment and run after you've got a test repository in place

		// instance.runTransformationFromRepository("test-repository", "/home/joe", "parameterized_transformation", "joe", "password");

//		new RunningTransformations().runTransformationFromFileSystem("E:\\APLIKASI\\data-integration\\test.ktr");
	}

}