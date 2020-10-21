package game.model.score;

import game.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class HighscoreHandler implements IHighscoreHandler {
    private final HashMap<String, Double> storedHighscores;

    public HighscoreHandler() {
        storedHighscores = new HashMap<>();
        createFile();
        readFromFile();
    }

    @Override
    public void storedHighscore(String level) {
        System.out.println(storedHighscores.get(level));
    }

    @Override
    public void writeToFile(String level, double time) {

    }



    @Override
    public void readFromFile() {
        String pathToHighscore = App.class.getResource("").getPath() + "highscore.txt";
        File highscoreFile = new File(pathToHighscore);
        Scanner highscoreScanner = null;
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
