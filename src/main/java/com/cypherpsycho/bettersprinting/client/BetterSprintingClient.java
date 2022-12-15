package com.cypherpsycho.bettersprinting.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class BetterSprintingClient implements ClientModInitializer {
    Map<KeyBinding, Long> lastPressedTime = new HashMap<>();
    private static KeyBinding fowardBind;
    private static KeyBinding backBind;
    private static KeyBinding leftBind;
    private static KeyBinding rightBind;


    @Override
    public void onInitializeClient() {
        fowardBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.examplemod.forward", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_Y, // The keycode of the key
                "category.examplemod.sprinting" // The translation key of the keybinding's category.
        ));
        leftBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.examplemod.left", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_G, // The keycode of the key
                "category.examplemod.sprinting" // The translation key of the keybinding's category.
        ));
        backBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.examplemod.back", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_H, // The keycode of the key
                "category.examplemod.sprinting" // The translation key of the keybinding's category.
        ));
        rightBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.examplemod.right", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_J, // The keycode of the key
                "category.examplemod.sprinting" // The translation key of the keybinding's category.
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (fowardBind.wasPressed()) {
                lastPressedTime.put(fowardBind, System.currentTimeMillis());
                boolean sprinting = checkSprinting(fowardBind);
                if (sprinting) {
                    client.player.setSprinting(true);

                }
            } else if (backBind.wasPressed()) {
                lastPressedTime.put(backBind, System.currentTimeMillis());
                boolean sprinting = checkSprinting(backBind);
                if (sprinting) {
                    client.player.setSprinting(true);
                }
            } else if (leftBind.wasPressed()) {
                lastPressedTime.put(leftBind, System.currentTimeMillis());
                boolean sprinting = checkSprinting(leftBind);
                if (sprinting) {
                    client.player.setSprinting(true);
                }
            } else if (rightBind.wasPressed()) {
                lastPressedTime.put(rightBind, System.currentTimeMillis());
                boolean sprinting = checkSprinting(rightBind);
                if (sprinting) {
                    client.player.setSprinting(true);
                }
            }
        });
    }

    private boolean checkSprinting(KeyBinding bind) {
        Long lastPressed = lastPressedTime.get(bind);
        if (lastPressed != null) {
            long timeSinceLastPress = System.currentTimeMillis() - lastPressed;
            if (timeSinceLastPress <= 500) {
                return true;
            }
        }
        return false;
    }
}
