package me.notbanana8.skinsystem.commands;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class SubCommand {
    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract String getPermission();

    public abstract void preform(Player player, String args[]);

    public abstract List<String> getSubcommandArgs(Player player, String args[]);
}
