/*
 * Authors: Joachim Ørfeldt Pedersen, Mattias Oom
 *
 * The highscore handler reads and stores highscores to a file.
 * This enables the player to store their max score for each level
 * played. 
 */

package game.model.score;

import game.App;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HighscoreHandler implements IHighscoreHandler {
    // Path to highscore storing file
    private final static String PATH_TO_HIGHSCORE = App.class.getResource("").getPath() + "highscore.txt";
    // Data set that stores highscores for each level
    private final HashMap<String, Double> storedHighscores;

    public HighscoreHandler() {
        storedHighscores = new HashMap<>();
        // Creates a new highscore text file if one does not exist
        createFile();
        // Loads storedHighscores with saved highscores from highscore text file
        readFromFile();
    }

    @Override
    public double getHighscore(String level) {
        // If this is the first time we check for a new highscore, compare it to the mac DOUBLE value
        if (!storedHighscores.containsKey(level)) {
            setHighscore(level, Double.MAX_VALUE);
        }

        return storedHighscores.get(level);
    }



    @Override
    public void setHighscore(String level, double score) {
        if (storedHighscores.containsKey(level)) storedHighscores.replace(level, score);
        else storedHighscores.put(level, score);

        writeToFile();
    }

    @Override
    public void writeToFile() {
        try {
            // Clear the content of highscore.txt to make in ready to be populated with new data
            PrintWriter printWriter = new PrintWriter(PATH_TO_HIGHSCORE);
            printWriter.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(PATH_TO_HIGHSCORE);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOut));
            // For every entry in storedHighscore, we create a new string separated with an new line in highscore.txt
            for (Map.Entry<String, Double> entry : storedHighscores.entrySet()) {
                String name = entry.getKey();
                Double score = entry.getValue();
                bufferedWriter.write(name + " " + score);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void readFromFile() {
        File highscoreFile = new File(PATH_TO_HIGHSCORE);
        Scanner highscoreScanner;
        try {
            highscoreScanner = new Scanner(highscoreFile);
            highscoreScanner.useDelimiter(System.lineSeparator());
            // For every line we retrieve, we split it, and then add respective part to storedHighscores
            while (highscoreScanner.hasNext()) {
                String currentLine = highscoreScanner.next();
                String[] words = currentLine.split(" ");

                String levelName = words[0];
                double levelScore = Double.parseDouble(words[1]);

                storedHighscores.put(levelName, levelScore);
            }

            highscoreScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void createFile() {
        try {
            String pathToHighscore = App.class.getResource("").getPath() + "highscore.txt";
            File highscoreFile = new File(pathToHighscore);
            highscoreFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, Double> getStoredHighscores() {
        return storedHighscores;
    }
}
