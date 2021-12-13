package com.example.pentaho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.pentaho.di.core.logging.KettleLogStore;

@SpringBootApplication
public class PentahoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PentahoApplication.class, args);
		KettleLogStore.init();
		new RunningTransformations().runTransformationFromFileSystem("D:\\Job\\Export\\tesrest.ktr");
	}

}
