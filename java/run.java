import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class run {

    public static void main(String[] args) {

        try {
            runProcess();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void runProcess() throws IOException {
        final String[][] COMPARE_STR_ARR = {
                 {"/trunk/src/", "WebContent/WEB-INF/classes/"}
                ,{"/trunk/", ""}
		,{".java", ".class"}
        };

        Scanner scanner = null;
        BufferedWriter bufferedWriter = null;
        Set<String> resultPath = new HashSet<>();

        try {
            // Open the file path from svn
            File svnPathFile = new File("filePath.txt");
            scanner = new Scanner(svnPathFile);

            System.out.println("=============start : path transformation=============");
            while(scanner.hasNextLine()) {
                String textLine = scanner.nextLine();
                String convertedPath = "";

                System.out.println("before path : " + textLine);
                convertedPath = textLine.replace(COMPARE_STR_ARR[0][0], COMPARE_STR_ARR[0][1])
					.replace(COMPARE_STR_ARR[1][0], COMPARE_STR_ARR[1][1])
					.replace(COMPARE_STR_ARR[2][0], COMPARE_STR_ARR[2][1])
                                        .concat(" ");

                resultPath.add(convertedPath);
                System.out.println("after path : " + convertedPath);
            }
            System.out.println("=============end : path transformation=============");

            // command input and create a batch file to create a jar file
            File resultPathFile = new File("compression.bat");
            bufferedWriter = new BufferedWriter(new FileWriter(resultPathFile));

            if(resultPathFile.isFile() && resultPathFile.canWrite()) {
		bufferedWriter.write("@echo off");
		bufferedWriter.write("\r\n");
                bufferedWriter.write("jar cvf deployjar/deployJar.jar ");

                System.out.println("=============start : create batch command=============");
                System.out.println("jar cvf deployjar/deployJar.jar");

                for(String text : resultPath) {
                    if(text != null) {
                        bufferedWriter.write(text);
                        System.out.println(text);
                    }
                }
		    
		bufferedWriter.write("\r\n");
		bufferedWriter.write("cd deployjar ");
		bufferedWriter.write("\r\n");
		bufferedWriter.write("jar xvf deployJar.jar ");
		bufferedWriter.write("\r\n");
		bufferedWriter.write("pause");
                System.out.println("=============end : create batch command=============");
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(scanner != null) {
                scanner.close();
            }
            if(bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }

}
