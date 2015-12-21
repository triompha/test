package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileMerge {

	public static void main(String[] args) throws IOException {

		String outputDir = "/Users/triompha/";
		String outputFileName = "total1.file";

//		String inputDir = "/Users/triompha/Downloads/queqiao.git/";
		String inputDir = "/Users/triompha/Downloads/catch-everest/";
		
		

		List<String> arrayList = new ArrayList<>();
		collect(inputDir, arrayList);

		FileUtils.writeLines(new File(outputDir + outputFileName), "utf-8",
				arrayList);

		System.out.println("completed");

	}

	public static void collect(String filedir, List<String> arrayList)
			throws IOException {

		File file = new File(filedir);
		if (file.isFile()
				&& (file.getName().endsWith("php")
						|| file.getName().endsWith(".java") 
						|| file.getName().endsWith(".xml") 
//						|| file.getName().endsWith(".config") 
						|| file.getName().endsWith(".sh"))) {
			arrayList.add("file:--------------"+file.getAbsolutePath()+"----------------");
			arrayList.add(FileUtils.readFileToString(file, "utf-8") + "\r\n");
			arrayList.add(FileUtils.readFileToString(file, "utf-8"));
		}

		if (file.isDirectory()) {
			for (String files : file.list()) {
				collect(file.getAbsolutePath() + "/" + files, arrayList);
			}
		}

	}

}
