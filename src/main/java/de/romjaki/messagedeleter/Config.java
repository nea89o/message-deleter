package de.romjaki.messagedeleter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static de.romjaki.messagedeleter.Main.gson;

public class Config {
    private static Config instance;
    private static File configFile = new File("config.json");
    public String token;
    public boolean deletePinned;
    public String channel;


    public static Config getInstance() {
        if (instance == null) {
            try (Scanner s = new Scanner(configFile).useDelimiter("\\As")) {
                instance = gson.fromJson(s.next(), Config.class);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
