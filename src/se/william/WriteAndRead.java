package se.william;

import java.io.*;

public class WriteAndRead {

    private final String fileName = "highscore.txt";

    public void writeToFile(String inputText, boolean notOverwriting) {

        try {
            FileWriter fileWriter = new FileWriter(fileName, notOverwriting);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(inputText);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public Integer readFromFile() {

        String fileName = "highscore.txt";
        String line = null;
        String highscore = "";

        try {

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            highscore = bufferedReader.readLine();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Hittade inte: '" + fileName + "'");
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return Integer.valueOf(highscore);
    }
}
