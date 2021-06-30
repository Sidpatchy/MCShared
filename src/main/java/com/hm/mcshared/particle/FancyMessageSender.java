package com.hm.mcshared.particle;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


import static java.lang.Integer.parseInt;

/**
 * Class used to send fancy messages to the player; can be titles, hoverable chat messages or action bar messages. All
 * methods are static and this class cannot be instanciated.
 *
 * @author Pyves
 *
 */
public final class FancyMessageSender {
	private static final int MAJOR_VERSION_NUMBER = parseInt(Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3].split("_")[1]);

	private FancyMessageSender() {
		// Not called.
	}

	/**
	 * Sends a hoverable message to the player. Only supported in Minecraft 1.8+.
	 *
	 * @param player  Online player to send the message to.
	 * @param message The text to display in the chat.
	 * @param hover   The text to display in the hover.
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void sendHoverableMessage(Player player, String message, String hover) throws Exception {
		TextComponent tc = new TextComponent();
		tc.setText(message);
		//tc.setColor(color); This is here as a reminded that it exists if I decide I need it in the future...

		if (MAJOR_VERSION_NUMBER >= 16) {
			tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(hover)));
		} else {
			tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
		}
		player.spigot().sendMessage(tc);
	}

	/**
	 * Sends a clickable and hoverable message to the player. Only supported in Minecraft 1.8+.
	 *
	 * @param player  Online player to send the message to.
	 * @param message The text to display in the chat.
	 * @param command The command that is entered when clicking on the message.
	 * @param hover   The text to display in the hover.
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void sendHoverableCommandMessage(Player player, String message, String command, String hover)
			throws Exception {
		TextComponent tc = new TextComponent();
		tc.setText(message);
		//tc.setColor(color); This is here as a reminder that it exists if I decide I need it in the future...
		tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));

		if (MAJOR_VERSION_NUMBER >= 16) {
			tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(hover)));
		} else {
			tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover).create()));
		}
		player.spigot().sendMessage(tc);
	}

	/**
	 * Sends an action bar chat message to the player. Only supported in Minecraft 1.8+.
	 *
	 * @param player  Online player to send the message to.
	 * @param message The text to display in the action bar.
	 * @throws Exception
	 */
	public static void sendActionBarMessage(Player player, String message) throws Exception {
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
	}

	/**
	 * Sends a title and subtitle to the player. Only supported in Minecraft 1.8+.
	 *
	 * @param player   Online player to send the title and subtitle to.
	 * @param title    The main text that will appear on the player's screen.
	 * @param subtitle The secondary text that will appear on the player's screen.
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void sendTitle(Player player, String title, String subtitle) throws Exception {
		if (MAJOR_VERSION_NUMBER >= 11) {
			player.sendTitle(title, subtitle, 10, 70, 20);
		} else if (MAJOR_VERSION_NUMBER >= 8) {
			player.sendTitle(title, subtitle);
		}
	}
}