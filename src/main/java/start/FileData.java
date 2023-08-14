package start;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author Andra Buzila
 * @Since Mai 27, 2021
 */
public class FileData {
    File file = new File("text.txt");

    /**
     * @throws IOException
     */
    public void createFile() throws IOException{
        file.createNewFile();
    }

    /**
     * @param s
     * @throws IOException
     */
    public void writeInFile(String s) throws IOException{
        try{
            FileWriter write = new FileWriter("C:\\Users\\Cristian Buzila\\Desktop\\PT2021_30229_Buzila_Andra_Assignment_3\\text.txt", true);
            BufferedWriter buffer = new BufferedWriter(write);
            buffer.append(s);
            buffer.newLine();
            buffer.close();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
