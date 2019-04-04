package com.xuxe.frostBot.main;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.xuxe.frostBot.Ref;
import com.xuxe.frostBot.commands.MemoryCommand;
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
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class App extends ListenerAdapter
{
	private Exception exception = null;
    private static Properties prop = new Properties();
    public static void main(String[] args) throws LoginException, InterruptedException
    {
		Logger logger = Logger.getGlobal();
		try {
		Properties properties = new Properties();
		FileReader fileReader = null;
		fileReader = new FileReader("config.properties");
		properties.load(fileReader);
		String token = properties.getProperty("token");
		String ownerID = properties.getProperty("ownerID");
		String prefix = ">>";
		String display = "";
		display = properties.getProperty("display");
		prefix = properties.getProperty("prefix");
		fileReader.close();
		properties.clear();

		logger.info("FrostBot is starting. Using prefix \""+prefix+"\" and displaying" +display);
		EventWaiter eventWaiter = new EventWaiter();
		CommandClientBuilder commandClientBuilder = new CommandClientBuilder();
		commandClientBuilder.addCommand(new StartCommand());
		commandClientBuilder.addCommand(new StackCommand());
		commandClientBuilder.addCommand(new StopCommand());
		commandClientBuilder.addCommand(new MemoryCommand());

			commandClientBuilder.setOwnerId(ownerID);
			commandClientBuilder.useHelpBuilder(true);
			commandClientBuilder.setPrefix(prefix);
			JDA jda = new JDABuilder(AccountType.BOT).setToken(token).addEventListener(commandClientBuilder.build()).addEventListener(eventWaiter).build().awaitReady();

          jda.getPresence().setGame(Game.watching(display));

          logger.info("FrostBot has finished startup.");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

