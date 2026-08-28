package org.goit.cities.processor;

public class Player {

    private int count;
    private boolean human;

    public Player(int count, boolean human) {
        this.count = count;
        this.human = human;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isHuman() {
        return human;
    }

    public void setHuman(boolean human) {
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
