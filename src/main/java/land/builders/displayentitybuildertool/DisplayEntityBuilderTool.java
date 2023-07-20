package land.builders.displayentitybuildertool;

import land.builders.displayentitybuildertool.commands.OpenStandSlabGuiCommand;
import land.builders.displayentitybuildertool.guis.slab.SlabGUI;
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
    }

    private void registerListener() {
        Bukkit.getServer().getPluginManager().registerEvents(new LeftClickListener(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new RightClickListener(), this);
    }

    private void buildGUIs(){
        SlabGUI.buildGUI();
    }
}
