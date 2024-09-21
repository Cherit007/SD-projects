package org.parkinglot.commands;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {
    private String commadName;
    private List<String> params;

    public Command(String inputLine){
        List<String> tokens = Arrays.stream(inputLine.trim().split(" "))
                .map(String::trim)
                .filter(token -> (token.length() > 0)).collect(Collectors.toList());
        commadName = tokens.get(0).toLowerCase();
        tokens.remove(0);
        params = tokens;
    }

    public String getCommand(){
        return commadName;
    }
    public List<String> getParams(){
        return params;
    }
}
