package org.goit.cities.processor;

public class Player {

    private Integer count;
    private Boolean human;

    public Player(Integer count, Boolean human) {
        this.count = count;
        this.human = human;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getHuman() {
        return human;
    }

    public void setHuman(Boolean human) {
        this.human = human;
    }

    @Override
    public String toString() {
        return "Player{" +
                "count=" + count +
                ", human=" + human +
                '}';
    }
}
