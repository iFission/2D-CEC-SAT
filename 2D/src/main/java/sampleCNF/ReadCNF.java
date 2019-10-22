import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCNF {
    public static void main(String[] args) {
        String path = "/Users/ALEX/Documents/2D-CEC-SAT/2D/src/main/java/sampleCNF/s8Sat.cnf";

        String line = null;

        try {
            FileReader fileReader = new FileReader(path);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + path + "'");
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
}