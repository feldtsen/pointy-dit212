package game.model.score;

import game.App;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HighscoreHandler implements IHighscoreHandler {
    private final static String PATH_TO_HIGHSCORE = App.class.getResource("").getPath() + "highscore.txt";
    private final HashMap<String, Double> storedHighscores;

    public HighscoreHandler() {
        storedHighscores = new HashMap<>();
        createFile();
        readFromFile();
    }

    @Override
    public double getHighscore(String level) {
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
            PrintWriter printWriter = new PrintWriter(PATH_TO_HIGHSCORE);
            printWriter.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(PATH_TO_HIGHSCORE);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOut));
            for (Map.Entry<String, Double> entry : storedHighscores.entrySet()) {
                String name = entry.getKey();
                Double score = entry.getValue();
                System.out.println(name);
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

            while (highscoreScanner.hasNext()) {
                String currentLine = highscoreScanner.next();
                String[] words = currentLine.split(" ");

                String levelName = words[0];
                double levelScore = Double.parseDouble(words[1]);

                storedHighscores.put(levelName, levelScore);
            }

            highscoreScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("failed to create a scanner");
            e.printStackTrace();
        }

    }

    @Override
    public void createFile() {
        try {
            String pathToHighscore = App.class.getResource("").getPath() + "highscore.txt";
            File highscoreFile = new File(pathToHighscore);
            if (highscoreFile.createNewFile()) {
                System.out.println("File created: " + highscoreFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
