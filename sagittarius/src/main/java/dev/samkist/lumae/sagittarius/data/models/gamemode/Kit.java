package dev.samkist.lumae.sagittarius.data.models.gamemode;

import dev.samkist.lumae.sagittarius.data.models.MilkyModel;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class Kit extends MilkyModel<Kit> {
    public static final String scope = "kit";

    String name;
    String description;
    Map<Integer, ItemStack> items; //Inventory slot, item
    Integer cooldownSeconds;
    String permission;

    public Kit() { }

}
