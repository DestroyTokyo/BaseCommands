package delta.cion.tokyo.baseCommands.commands.server;

import delta.cion.tokyo.api.command.DeltaCommand;
import delta.cion.tokyo.api.locales.Localize;
import delta.cion.tokyo.api.permission.PermissionManager;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.entity.Player;

public class ServerInfo extends DeltaCommand {

    public ServerInfo() {
        super(new Command("server-info"));

        Command removeCommand = new Command("remove");
        removeCommand.addSyntax(this::removeItem);
        getCommand().addSubcommand(removeCommand);
    }

    private void getItem(CommandSender sender, CommandContext context) {
        if (sender instanceof Player player && !PermissionManager.hasPermission(player, "get.item")) {
            sender.sendMessage(Localize.getTranslate("no-permission", getCommand().getName()));
            return;
        }

        if (isConsole(sender)) return;
        int itemCount = context.get("item_count");
        String itemName = context.get("item");

        Material material = Material.fromKey(itemName);

        if (itemCount == 0) itemCount = 1;
        if (material == null) {
            sender.sendMessage("Item with id [" + itemName + "] not found.");
            sender.sendMessage("Please check item name on Minecraft Wiki or Minestom docs.");
            return;
        }
        if (material.maxStackSize() == 1) itemCount = 1;

        ItemStack itemStack = ItemStack.of(material, itemCount);
        ((Player) sender).getInventory().addItemStack(itemStack);
    }
}