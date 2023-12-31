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
        builder.setDisplayName("§7上一頁")
                .addLoreLines(gui.hasPreviousPage()
                        ? "§7跳到頁面 §e" + gui.getCurrentPage() + "§7/§e" + gui.getPageAmount()
                        : "§c已經到第一頁了");

        return builder;
    }

}
