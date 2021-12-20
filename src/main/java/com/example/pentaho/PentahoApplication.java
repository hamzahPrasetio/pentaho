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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;

@SpringBootApplication
public class PentahoApplication {

	public static RunningTransformations instance;

	public static void main(String[] args) {

		Utils utils = new Utils();
		HashMap<String, String> parameters = new HashMap<String, String>();

		// testing" fungsi
		// list folder for month 12 year 2021
		utils.listFilesInFolder(new File(System.getProperty("user.dir") + "/testing"), Month.of(12), 2021);

		// set variable n parameter
		String filename = "tesfilename";
		parameters.put("Limit", "7");
		parameters.put("filename", utils.constructCompletePath(filename));


		SpringApplication.run(PentahoApplication.class, args);

		if (Files.exists(Paths.get(parameters.get("filename")))) {
			System.out.println("file output already exists");
			return;
		}

		KettleLogStore.init();
		try {
			KettleEnvironment.init();
		} catch (KettleException e) {
			e.printStackTrace();
		}

		instance = new RunningTransformations();
		Trans trans = instance.runTransformationFromFileSystem( "ktr/test.ktr", parameters );

		LoggingBuffer appender = KettleLogStore.getAppender();
		String logText = appender.getBuffer( trans.getLogChannelId(), false ).toString();
		System.out.println( "************************************************************************************************" );
		System.out.println( "LOG REPORT: Transformation generated the following log lines:\n" );
		System.out.println( logText );
		System.out.println( "END OF LOG REPORT" );
		System.out.println( "************************************************************************************************" );
	}
}