package com.xuxe.frostBot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.WriterStreamConsumer;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class App extends ListenerAdapter
{
	private Exception exception = null;
    private static Properties prop = new Properties();
    public static void main(String args[]) throws LoginException, InterruptedException
    {
        Ref.jda = new JDABuilder(AccountType.BOT).setToken(Ref.token).build().awaitReady();
        Ref.jda.getPresence().setGame(Game.watching("Basz's tralala"));
        Ref.jda.addEventListener(new App());
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message message = event.getMessage();
        String messageContent = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        User author = event.getAuthor();
        if(messageContent.startsWith(">>") && event.getMember().hasPermission(Permission.VIEW_AUDIT_LOGS)) {
			if (messageContent.startsWith(">>start")) {

				String[] command = messageContent.split(" ");
				String file = command[1].trim();
				try {
					FileReader f = new FileReader("config.properties");
					prop.load(f);
					Ref.filepath = prop.getProperty(file + "path");
					Ref.filename = prop.getProperty(file + "name");
					try {
						ProcessBuilder p = new ProcessBuilder();
						p.directory(new File(Ref.filepath));
						p.command(Ref.filename);
						//Process process = p.start();
						System.out.println("filename: " + Ref.filename + "\nfilepath: " + Ref.filepath);
						System.out.println("command: " + "cmd /c " + Ref.filename);
						Process process = Runtime.getRuntime().exec("cmd /c start \"\" " + Ref.filename, null, new File("" + Ref.filepath));
						channel.sendMessage(file + " should now be started.").queue();
						System.out.println("User: "+author.getName()+"#"+author.getDiscriminator()+" ran >>start "+file);
					} catch (IOException io) {
						io.printStackTrace();
					}

				} catch (FileNotFoundException fnf) {
					channel.sendMessage("File not found. Recheck your spelling and try again.").queue();
					exception = fnf;
				} catch (IOException e1) {
					channel.sendMessage("Something went wrong: " + e1.getLocalizedMessage()).queue();
					channel.sendMessage("Use >>stack for more info.").queue();
					exception = e1;
				}
			} else if (messageContent.equalsIgnoreCase(">>stack")) {
				if (exception != null) {
					channel.sendMessage("Stack trace ->").queue();
					for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
						channel.sendMessage(stackTraceElement.toString()).queue();
					}
				} else {
					channel.sendMessage("No data found").queue();
				}
			}
			else if (messageContent.equalsIgnoreCase(">>stop"))
			{
				channel.sendMessage("Server has been stopped.").queue();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally {
					channel.sendMessage("No, not really.").queue();
				}

			}
			else if(messageContent.equalsIgnoreCase(">>help"))
			{
				channel.sendMessage(">>start <name> to start a pre-defined file.").queue();
			}
        }

    }
}

