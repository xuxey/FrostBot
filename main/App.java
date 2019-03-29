package com.xuxe.frostBot.main;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.xuxe.frostBot.Ref;
import com.xuxe.frostBot.commands.StackCommand;
import com.xuxe.frostBot.commands.StartCommand;
import com.xuxe.frostBot.commands.StopCommand;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.Properties;

@SuppressWarnings("unused")
public class App extends ListenerAdapter
{
	private Exception exception = null;
    private static Properties prop = new Properties();
    public static void main(String args[]) throws LoginException, InterruptedException
    {
		try {
		Properties properties = new Properties();
		FileReader fileReader = null;
		fileReader = new FileReader("config.properties");
		properties.load(fileReader);
		String token = properties.getProperty("token");
		String ownerID = properties.getProperty("ownerID");
		fileReader.close();
		properties.clear();

		EventWaiter eventWaiter = new EventWaiter();
		CommandClientBuilder commandClientBuilder = new CommandClientBuilder();
		commandClientBuilder.addCommand(new StartCommand());
		commandClientBuilder.addCommand(new StackCommand());
		commandClientBuilder.addCommand(new StopCommand());

			commandClientBuilder.setOwnerId(ownerID);
			commandClientBuilder.useHelpBuilder(true);

			JDA jda = new JDABuilder(AccountType.BOT).setToken(token).addEventListener(commandClientBuilder.build()).addEventListener(eventWaiter).build().awaitReady();
        Ref.jda.getPresence().setGame(Game.watching("Basz's tralala"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   /* @Override
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

    }*/
}

