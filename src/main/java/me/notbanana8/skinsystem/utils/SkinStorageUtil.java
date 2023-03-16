package me.notbanana8.skinsystem.utils;

import com.google.gson.Gson;
import me.notbanana8.skinsystem.SkinSystem;
import me.notbanana8.skinsystem.data.Skin;
import org.bukkit.Material;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkinStorageUtil {

    private static ArrayList<Skin> skins = new ArrayList<>();
    public static Skin createSkin(String name, Material material, Integer modelData){
        Skin skin = new Skin(name,material ,modelData);
        skins.add(skin);
        try {
            saveSkins();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return skin;
    }

    public static Skin findSkin(String name){
        for (Skin skin: skins){
            if (skin.getName().equalsIgnoreCase(name)){
                return skin;
            }
        }
        return null;
    }

    public static void deleteSkin(String name){

        //Linear search
        for (Skin skin: skins){
            if (skin.getName().equalsIgnoreCase(name)){
                skins.remove(skin);
                break;
            }
        }
        try {
            saveSkins();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Skin updateSkin(String name, Skin newSkin){
        for(Skin skin: skins){
            if(skin.getName().equalsIgnoreCase(name)){
                skin.setName(newSkin.getName());
                skin.setMaterial(newSkin.getMaterial());
                skin.setModel(newSkin.getModel());
                return newSkin;
            }
        }
        return null;
    }

    public static List<Skin> findSkins(){
        return skins;
    }

    public static void saveSkins() throws IOException {
        Gson gson = new Gson();
        File file = new File(SkinSystem.getPlugin().getDataFolder().getAbsolutePath() + "/skins.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writer = new FileWriter(file, false);
        gson.toJson(skins, writer);
        writer.flush();
        writer.close();
        System.out.println("skins saved");
    }

    public static void loadSkins() throws IOException{
        Gson gson = new Gson();
        File file = new File(SkinSystem.getPlugin().getDataFolder().getAbsolutePath() + "/skins.json");
        if(file.exists()){
            Reader reader = new FileReader(file);
            Skin[] s = gson.fromJson(reader,Skin[].class);
            skins = new ArrayList<>(Arrays.asList(s));
            System.out.println("loaded saved");
        }
    }
}
