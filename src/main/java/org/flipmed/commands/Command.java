package org.flipmed.commands;

interface Command {
    boolean matches(String input);
    void execute(String input);
}