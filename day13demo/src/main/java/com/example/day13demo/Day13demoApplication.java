package com.example.day13demo;

import java.io.File;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//ApplicationRunner- when the app starts, it runs this method
public class Day13demoApplication implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(Day13demoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (args.containsOption("dataDir")){
			final String dataDir=args.getOptionValues("dataDir").get(0);

			File fileDir = new File(dataDir);

			//make directory if it doesnt exist
			if (!fileDir.exists()){ 
				fileDir.mkdir();
				System.out.println("***" + fileDir.getAbsolutePath());
				System.out.println("***" + fileDir.getPath());
				System.out.println("***" + fileDir.getParent());
			} else {
				//if not get the path of the directory
				System.out.println(fileDir.getAbsolutePath());
			}
		}
	}

}
