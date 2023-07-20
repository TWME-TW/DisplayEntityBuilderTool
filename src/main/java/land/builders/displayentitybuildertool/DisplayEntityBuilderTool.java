package land.builders.displayentitybuildertool;

import land.builders.displayentitybuildertool.commands.OpenStandSlabGuiCommand;
import land.builders.displayentitybuildertool.commands.OpenStandStairsGuiCommand;
import land.builders.displayentitybuildertool.guis.slab.SlabGUI;
import land.builders.displayentitybuildertool.guis.stairs.StairsGUI;
import land.builders.displayentitybuildertool.guis.stairs.StairsItem;
import land.builders.displayentitybuildertool.listeners.LeftClickListener;
import land.builders.displayentitybuildertool.listeners.RightClickListener;
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
