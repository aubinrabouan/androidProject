package com.example.aubin.lights;

public class LightContextState {

    private int id;
    private String status;
    private int level;
    private float roomId;
    private int color;

    public LightContextState(int id, String status, int level, float roomId) {
        super();
        this.id = id;
        this.status = status;
        this.level = level;
        this.roomId = roomId;
    }



    public LightContextState(int id, String status, int level, float roomId, int color) {
        super();
        this.id = id;
        this.status = status;
        this.level = level;
        this.roomId = roomId;
        this.color = color;

    }

    public int getId() {
        return this.id;
    }

    public String getStatus() {
        return this.status;
    }

    public int getLevel() {
        return this.level;
    }

    public float getRoomId() {
        return this.roomId;
    }

    public int getColor() {
        return color;
    }
}