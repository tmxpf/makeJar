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
                {"beforeCustomTest1", "afterCustomTest1"}
                , {"beforeCustomTest2", "afterCustomTest2"}
                ,{"beforeCustomTest3", "afterCustomTest3"}
                , {"beforeCustomTest4", "afterCustomTest4"}
        };

        Scanner scanner = null;
        BufferedWriter bufferedWriter = null;
        Set<String> resultPath = new HashSet<>();

        try {
            // svn에서 가져온 파일경로 모음 오픈
            File svnPathFile = new File("filePath.txt");
            scanner = new Scanner(svnPathFile);

            System.out.println("=============start : path transformation=============");
            while(scanner.hasNextLine()) {
                String textLine = scanner.nextLine();
                String convertedPath = "";

                System.out.println("before path : " + textLine);
                convertedPath = textLine.replace(COMPARE_STR_ARR[3][0], COMPARE_STR_ARR[3][1])
                                                .concat(" ");

                resultPath.add(convertedPath);
                System.out.println("after path : " + convertedPath);
            }
            System.out.println("=============end : path transformation=============");

            //jar파일 만들기 위한 명령어 입력 및 batch파일 생성
            File resultPathFile = new File("compression.bat");
            bufferedWriter = new BufferedWriter(new FileWriter(resultPathFile));

            if(resultPathFile.isFile() && resultPathFile.canWrite()) {
                bufferedWriter.write("jar cvf deployjar/deployJar.jar ");

                System.out.println("=============start : create batch command=============");
                System.out.println("jar cvf deployjar/deployJar.jar");

                for(String text : resultPath) {
                    if(text != null) {
                        bufferedWriter.write(text);
                        System.out.println(text);
                    }
                }
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
