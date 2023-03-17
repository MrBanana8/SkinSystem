package me.notbanana8.skinsystem.data;

import org.bukkit.Material;

public class Skin {

    private String name;

    private Material material;
    private Integer model;

    public Skin(String name, Material material, Integer model) {
        this.name = name;
        this.material = material;
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }
}
