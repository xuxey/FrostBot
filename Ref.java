package com.xuxe.frostBot;

import net.dv8tion.jda.core.JDA;

public class Ref
{
    public static JDA jda;
    private Exception exception = null;

    //getters and setters
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    private String prefix = ">>";

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }


}
