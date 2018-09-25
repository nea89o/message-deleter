package de.romjaki.messagedeleter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;
import java.util.Timer;

public class Main {
    public static Gson gson = new GsonBuilder()
            .create();
    public static JDA jda;
    public static Timer timer = new Timer();


    public static void main(String[] args)  {


        try {
            jda = new JDABuilder(AccountType.BOT)
                    .setToken(Config.getInstance().token)
                    .addEventListener(new CreationListener())
                    .build();
        } catch (LoginException e) {
            System.err.println("Invalid bot token. (Or discord is down again smh)");
        }


    }
}
