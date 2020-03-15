package com.madproject.spaceinvaders.models.components;

public class PlayerScore {
    private String name;
    private int wave;
    private int score;

    public PlayerScore(String name, int wave, int score){
        this.name = name;
        this.wave = wave;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "PlayerScore{" +
                ", name='" + name + '\'' +
                ", wave='" + wave + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
