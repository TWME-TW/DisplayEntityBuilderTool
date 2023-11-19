package dev.twme.displayentitybuildertool.guis.slab;

import org.bukkit.Material;

import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.controlitem.PageItem;

public class SlabBackItem extends PageItem {

    public SlabBackItem() {
	super(false);
    }

    @Override
    public ItemProvider getItemProvider(PagedGui<?> gui) {
	ItemBuilder builder = new ItemBuilder(Material.RED_STAINED_GLASS_PANE);
	builder.setDisplayName("§7Previous Page")
		.addLoreLines(gui.hasPreviousPage() ? "§7Jump to  §e" + gui.getCurrentPage() + "§7/§e" + gui.getPageAmount()
			: "§cYou are already on the first page!");

	return builder;
    }

}
