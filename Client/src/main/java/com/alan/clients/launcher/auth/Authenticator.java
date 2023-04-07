package com.alan.clients.launcher.auth;

import com.alan.clients.launcher.Launcher;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class Authenticator {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static boolean isValid() {
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
        } catch (final Exception e) {
            for (; ; ) {
                Minecraft.getMinecraft().mouseHelper = null;
            }
        }

        return true;
    }

    public static String getId() {
        try {
            final HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:3000/api/id").openConnection();

            // hard coded temporarily + use https or I am actually gonna commit sudoku
            connection.addRequestProperty("captcha", ("viaDGUenAthi"));
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
