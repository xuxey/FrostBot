package com.xuxe.frostBot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.xuxe.frostBot.Ref;
import net.dv8tion.jda.core.Permission;

import java.util.logging.Logger;

public class StackCommand extends Command
{
    public StackCommand()
    {
        this.help = "Gives a detailed stack trace of the most previous error.";
        this.name = "stack";
        this.aliases = new String[]{"error", "errorlog", "report"};
        this.userPermissions = new Permission[Permission.VIEW_AUDIT_LOGS.getOffset()];
    }
    @Override
    protected void execute(CommandEvent commandEvent) {
        Logger.getGlobal().info("User "+commandEvent.getAuthor().getName()+"#"+commandEvent.getAuthor().getDiscriminator()+" used stack command");
        Exception exception = new Ref().getException();
        if (exception != null) {
            commandEvent.reply("Stack trace ->");
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                commandEvent.reply(stackTraceElement.toString());
            }
        } else {
            commandEvent.reply("No data found");
        }
    }
}
