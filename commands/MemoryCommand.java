package com.xuxe.frostBot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.logging.Logger;

public class MemoryCommand extends Command
{

    public MemoryCommand()
    {
        this.name = "memory";
        this.help = "returns memory used by the Bot";
        this.aliases = new String[]{"mem", "m", "data", "usage"};
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        Logger.getGlobal().info("User "+commandEvent.getAuthor().getName()+"#"+commandEvent.getAuthor().getDiscriminator()+" used memory command");
        int dataSize = 1024 * 1024;
        commandEvent.reply("Used Memory   : " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/dataSize + " MB");
        commandEvent.reply("Free Memory   : " + Runtime.getRuntime().freeMemory()/dataSize + " MB");
        commandEvent.reply("Total Memory  : " + Runtime.getRuntime().totalMemory()/dataSize + " MB");
        commandEvent.reply("Max Memory    : " + Runtime.getRuntime().maxMemory()/dataSize + " MB");
    }
}
