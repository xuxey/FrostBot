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

public class StartCommand extends Command
{
    private static Properties prop = new Properties();
    public StartCommand()
    {
        this.help = "starts pre defined script";
        this.name = "start";
        this.aliases = new String[]{"s", "run", "commence"};
        this.userPermissions = new Permission[Permission.VIEW_AUDIT_LOGS.getOffset()];
    }

    @Override
    protected void execute(CommandEvent event)
    {
        Ref ref = new Ref();
        User author = event.getAuthor();
        String messageContent = event.getMessage().getContentRaw();
        String[] command = messageContent.split(" ");
        String file = command[1].trim();
        try {
            FileReader f = new FileReader("config.properties");
            prop.load(f);
            String filepath = prop.getProperty(file + "path");
            String filename = prop.getProperty(file + "name");
            try {
                ProcessBuilder p = new ProcessBuilder();
                p.directory(new File(filepath));
                p.command(filename);
                //Process process = p.start();
                System.out.println("filename: " + filename + "\nfilepath: " + filepath);
                System.out.println("command: " + "cmd /c " + filename);
                Process process = Runtime.getRuntime().exec("cmd /c start \"\" " + filename, null, new File("" + filepath));
                event.reply(file + " should now be started.");
                System.out.println("User: "+author.getName()+"#"+author.getDiscriminator()+" ran >>start "+file);
            } catch (IOException io) {
                io.printStackTrace();
            }

        } catch (FileNotFoundException fnf) {
            event.reply("File not found. Recheck your spelling and try again.");
            ref.setException(fnf);

        } catch (IOException e1) {
            event.reply("Something went wrong: " + e1.getLocalizedMessage());
            ref.setException(e1);
            event.reply("Use >>stack for more info.");
        }
    }
}
