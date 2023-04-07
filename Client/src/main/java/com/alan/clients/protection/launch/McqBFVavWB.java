package com.alan.clients.protection.launch;

import com.alan.clients.Client;
import com.alan.clients.component.impl.player.RotationComponent;
import com.alan.clients.component.impl.player.rotationcomponent.MovementFix;
import com.alan.clients.launcher.Launcher;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class McqBFVavWB {

    public static final List<Byte> BYTES = new ArrayList<>();

    private static final byte BYTE = (byte) -9;

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void validate() {
        if (Client.DEVELOPMENT_SWITCH) return;

        if (Client.INSTANCE.isValidated()) { // Checking if the client is already validated even though it hasn't been validated yet.
            for (; ; ) {
                Minecraft.getMinecraft().currentScreen = new GuiScreenHorseInventory(null, null, null);

                final Minecraft mc = Minecraft.getMinecraft();
                mc.getTextureManager().bindTexture(null);
            }
        }

        try {
            final HttpURLConnection connection = (HttpURLConnection)
                    new URL(Launcher.BASE + ("/api/validate")).openConnection();

            connection.addRequestProperty(("id"), getId());
            connection.addRequestProperty(("client"), ("rise"));

            connection.setRequestMethod(("POST"));

            final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String currentln;
            final StringBuilder response = new StringBuilder();

            while ((currentln = in.readLine()) != null) {
                response.append(currentln);
            }

            final JsonObject json = GSON.fromJson(response.toString(), JsonObject.class);

            if (!json.get(("success")).getAsBoolean()) {
                if (!response.toString().contains(("error"))) {
                    for (; ; ) {
                    }
                }

                for (; ; ) {
                    Minecraft.getMinecraft().entityRenderer = null;
                }
            }

            if (response.toString().contains(("error"))) {
                for (; ; ) {
                }
            }

            if (BYTES.isEmpty()) {
                BYTES.add(BYTE);
            }
        } catch (final Exception e) {
            for (; ; ) {
                Minecraft.getMinecraft().mouseHelper = null;
            }
        }

        for (final byte b : BYTES) {
            if (b != -9) {
                RotationComponent.setRotations(null, -1, MovementFix.OFF);
                return;
            }

            Client.INSTANCE.setValidated(true);
            break;
        }
    }

    public static String getId() {
        try {
            final HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3000/api/id").openConnection();

            // hard coded temporarily + use https or I am actually gonna commit sudoku
            connection.addRequestProperty("captcha", "viaDGUenAthi");
            connection.addRequestProperty("username", "Tecnio");
            connection.addRequestProperty("password", "YourMomGay");

            connection.setRequestMethod("POST");

            final JsonObject json = GSON.fromJson(IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8), JsonObject.class);

            System.out.println(json.toString());

            if (json.get("success").getAsBoolean()) {
                return json.get("id").getAsString();
            }
        } catch (final Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }
}