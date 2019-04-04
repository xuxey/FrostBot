package com.xuxe.frostBot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.logging.Logger;

public class StopCommand extends Command
{
    public StopCommand() {
        this.name = "stop";
        this.aliases = new String[]{"pause"};
        this.help = "Stops the process. No, not really";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        Logger.getGlobal().info("User "+commandEvent.getAuthor().getName()+"#"+commandEvent.getAuthor().getDiscriminator()+" used stop command");
        commandEvent.reply("Server has been stopped.");
        commandEvent.getChannel().sendTyping().queue();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            commandEvent.reply("No, not really.");
        }
    }
}
