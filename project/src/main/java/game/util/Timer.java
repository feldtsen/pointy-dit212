/*
 * Author: Mattias Oom, Joachim Ørfeldt Pedersen
 *
 * Used for keeping track of how much time has been spent in a level.
 */
package game.util;


public class Timer {
    private double time;

    public double getTime(){
        return time;
    }

    public void setTime(double delta) {
        this.time += delta;
    }

    public void resetTimer () {
        time = 0;
    }


}
