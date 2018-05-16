package com.gstmadeeasy.Model;

/**
 * Created by Dharam on 4/10/2018.
 */

   public class State {

     int StateID;
     String StateName;

    public State() {}

    public State(String StateName) {
        this.StateName = StateName;
    }

    public int getStateID() {
        return StateID;
    }

    public void setStateID(int stateID) {
        StateID = stateID;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    @Override
    public String toString() {
        return getStateName();
    }
}
