package com.mjc.school.controller.operation;


// The Command interface
@FunctionalInterface
public interface Command {

    public void execute();
}