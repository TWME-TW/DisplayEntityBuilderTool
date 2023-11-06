package dev.twme.displayentitybuildertool;

import dev.twme.displayentitybuildertool.commands.OpenStandSlabGuiCommand;
import dev.twme.displayentitybuildertool.commands.OpenStandStairsGuiCommand;
import dev.twme.displayentitybuildertool.commands.ToggleSmallBlockModeCommand;
import dev.twme.displayentitybuildertool.guis.slab.SlabGUI;
import dev.twme.displayentitybuildertool.guis.stairs.StairsGUI;
import dev.twme.displayentitybuildertool.listeners.LeftClickListener;
import dev.twme.displayentitybuildertool.listeners.RightClickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisplayEntityBuilderTool extends JavaPlugin {

    @Override
    public void onEnable() {

        registerCommands();
        registerListener();
        buildGUIs();

    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        this.getCommand("standslab").setExecutor(new OpenStandSlabGuiCommand());
        this.getCommand("standstairs").setExecutor(new OpenStandStairsGuiCommand());
        this.getCommand("smallblock").setExecutor(new ToggleSmallBlockModeCommand());
    }


    private void registerListener() {
        Bukkit.getServer().getPluginManager().registerEvents(new LeftClickListener(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new RightClickListener(), this);
    }

    private void buildGUIs(){
        StairsGUI.buildGUI();
        SlabGUI.buildGUI();
    }
}
