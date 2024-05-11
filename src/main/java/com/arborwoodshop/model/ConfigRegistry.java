package com.arborwoodshop.model;




public class ConfigRegistry {

    private Long id;
    private String name;
    private String type;
    private String value;

    public ConfigRegistry() {
    }

    public ConfigRegistry(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
