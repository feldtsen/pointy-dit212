/*
Author: Mattias Oom, Joachim Ørfeldt Pedersen
 */
package game.util;


public class Timer {
    private double time;

    public String getTime(){
        return String.format("%.1f", time);
    }

    public void setTime(double delta) {
        this.time += delta;
    }

    public void resetTimer () {
        time = 0;
    }


}
