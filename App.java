package com.xuxe.frostBot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
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
import java.util.Properties;

@SuppressWarnings("unused")
public class App extends ListenerAdapter
{
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
        Exception exception = null;
        Message message = event.getMessage();
        String messageContent = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        Exception e = null;
        if(messageContent.startsWith(">>"))
        {
	        if(messageContent.startsWith(">>start")) {
	        		
	        	String[] command = messageContent.split(" ");
	        	String file = command[1].trim();
	        	try 
	            {
	                FileReader f = new FileReader("config.properties");
	    				prop.load(f);
	    				Ref.filepath = prop.getProperty(file+"path");
	    				Ref.filename= prop.getProperty(file+"name");
	    				
	    				try {
	    				    ProcessBuilder p = new ProcessBuilder();
	    				    p.directory(new File(Ref.filepath));
	    				    p.command("cmd", "/c", Ref.filename);
	    				    Process process = p.start();
	    				    
	    				    channel.sendMessage(file +" should now be started.").queue();
	    				}
	    				catch (IOException io)
	    				{
	    				    io.printStackTrace();
	    				} 
	    				
	    			} catch (FileNotFoundException fnf) {
	    				channel.sendMessage("File not found. Recheck your spelling and try again.").queue();;
	    				e = fnf;
	    			} catch (IOException e1) {
	    				// TODO Auto-generated catch block
	    				channel.sendMessage("Something went wrong: "+e1.getLocalizedMessage()).queue();
	    				channel.sendMessage("Use >>stack for moreee info.");
	    				e = e1;
	    			}
	        }
	        else if(messageContent.equalsIgnoreCase(">>stack"))
	        {
	            if(exception !=null)
	            {
	                channel.sendMessage("Stack trace ->").queue();
	                for(StackTraceElement stackTraceElement: exception.getStackTrace())
	                {
	                    channel.sendMessage(stackTraceElement.toString()).queue();
	                }
	            }
	            else
	            {
	                channel.sendMessage("No data found").queue();
	            }
	        }
        }


    }
}

