package dev.twme.displayentitybuildertool.guis.stairs;

import org.bukkit.Material;

import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.controlitem.PageItem;

public class StairsForwardItem extends PageItem {

    public StairsForwardItem() {
	super(true);
    }

    @Override
    public ItemProvider getItemProvider(PagedGui<?> gui) {
	ItemBuilder builder = new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE);
	builder.setDisplayName("§7Next Page").addLoreLines(
		gui.hasNextPage() ? "§7Jump to §e" + (gui.getCurrentPage() + 2) + "§7/§e" + gui.getPageAmount()
			: "§cYou are already on the last page!");

	return builder;
    }
}
