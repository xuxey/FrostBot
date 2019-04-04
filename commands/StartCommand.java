package com.xuxe.frostBot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.xuxe.frostBot.Ref;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class StartCommand extends Command
{
    private static Properties prop = new Properties();
    public StartCommand()
    {
        this.help = "starts pre defined script";
        this.name = "start";
        this.aliases = new String[]{"s", "run", "commence"};
    }

    @Override
    protected void execute(CommandEvent commandEvent)
    {
        Logger.getGlobal().info("User "+commandEvent.getAuthor().getName()+"#"+commandEvent.getAuthor().getDiscriminator()+" used start command.\nThey used the trigger: "+commandEvent.getMessage().getContentRaw());
        Ref ref = new Ref();
        User author = commandEvent.getAuthor();
        String messageContent = commandEvent.getMessage().getContentRaw();
        String[] command = messageContent.split(" ");
        String file = command[1].trim();
        try {
            FileReader f = new FileReader("config.properties");
            prop.load(f);
            String filepath = prop.getProperty(file + "path");
            String filename = prop.getProperty(file + "name");
            f.close();
            prop.clear();
            try {
                Process p = Runtime.getRuntime().exec("cmd /c start \"\" " + filename, null, new File("" + filepath));

                if(p.isAlive()) {
                    Logger.getGlobal().info(file + " was started.");
                    commandEvent.reply(file + " should now be started.");
                }
                else
                {
                    commandEvent.reply("Could not start "+file);
                }




            } catch (IOException io) {
                io.printStackTrace();
            }

        } catch (FileNotFoundException fnf) {
            commandEvent.reply("File not found. Recheck your spelling and try again.");
            ref.setException(fnf);

        } catch (IOException e1) {
            commandEvent.reply("Something went wrong: " + e1.getLocalizedMessage());
            ref.setException(e1);
            commandEvent.reply("Use >>stack for more info.");
        }
    }
}
