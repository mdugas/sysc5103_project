package ca.carleton.sce.sensors;

//	File:			robo.visual.SensorInfo.java
//	Author:		Krzysztof Langner
//	Date:			1997/04/28

//  Modified by:  Paul Marlow, Amir Ghavam, Yoga Selvaraj
//  Course:       Software Agents
//  Date Due:     November 30, 2000 

//  Modified by:  Tarek Hassan
//  Date:         015 June 2001

//  Modified by:	Paul Marlow
//  Date:		February 22, 2004

//  Modified by:	Edgar Acosta
//  Date:		March 5, 2008

import ca.carleton.sce.sensors.hearing.Message;
import ca.carleton.sce.sensors.vision.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SensorInfo implements SensorInput {
    private volatile VisualInfo visualInfo = null;

    // accumulates messages for the current sense_step
    // let's say the sensor is buffered...
    private Collection<Message> auditoryInfo = new ArrayList<>();

    public Collection<BallInfo> getBallList() {
        return this.visualInfo == null ? Collections.EMPTY_LIST : this.visualInfo.getBallList();
    }

    public Collection<PlayerInfo> getPlayerList() {
        return this.visualInfo == null ? Collections.EMPTY_LIST :  this.visualInfo.getPlayerList();
    }

    public Collection<GoalInfo> getGoalList() {
        return this.visualInfo == null ? Collections.EMPTY_LIST :  this.visualInfo.getGoalList();
    }

    public Collection<LineInfo> getLineList() {
        return this.visualInfo == null ? Collections.EMPTY_LIST :  this.visualInfo.getLineList();
    }

    public Collection<FlagInfo> getFlagList() {
        return this.visualInfo == null ? null :  this.visualInfo.getFlagList();
    }

    public int getTime() {
        return this.visualInfo == null ? 0 :  this.visualInfo.getTime();
    }

    public Collection<Message> getMessages() {
        return this.auditoryInfo;
    }

    @Override
    public void see(VisualInfo info) {
        this.visualInfo = info;
    }

    @Override
    public void hear(Message message) {
        this.auditoryInfo.add(message);
    }

    public void clear() {
        this.auditoryInfo.clear();
    }
}

