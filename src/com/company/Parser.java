package com.company;

public class Parser {
    private String[] args;
    private String cmd;

    public boolean parse(String input) {
        String[] parsedInput =  input.split(" ");
        cmd = parsedInput[0];

        args = new String[parsedInput.length - 1];

        for (int i = 0, j = 0; i < parsedInput.length; i++) {
            if (i != 0) {
                args[j++] = parsedInput[i];
            }
        }

        return validateCommand(args.length);
    }

    public String getCmd(){
        return cmd;
    }

    public String[] getArguments(){
        return args;
    }

    private boolean validateCommand(int nArgs) {
        boolean isFound = Main.commands.containsKey(cmd);

        if (isFound)
        {
            // ls
            if(cmd.equals("ls") & (nArgs == Main.commands.get(cmd) - 1)){
                return true;
            }

            if(cmd.equals("cat") & ((nArgs > Main.commands.get(cmd)) & (nArgs > 0))){
                return true;
            }

            if(Main.commands.get(cmd) == nArgs)
                return true;

            else {
                System.out.println("Missing " + (Main.commands.get(cmd) - nArgs) +" Positional Argument(s)");
                return false;
            }
        }

        System.out.println("Command '" + cmd + "' not found.");
        return false;
    }
}