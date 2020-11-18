package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main
{
    public static Map<String, Integer> commands;
    public static String currentDirectory = "/Users/omara/";
    public static boolean executionTime = true;
    public static void populateCommands() {
        commands = new HashMap<>();

        commands.put("cp", 2);
        commands.put("mv", 2);
        commands.put("cd", 1);
        commands.put("mkdir", 1);
        commands.put("rmdir", 1);
        commands.put("rm", 1);
        commands.put("cat", -1);
        commands.put("ls", 1);
        commands.put("pwd", 0);
        commands.put("clear", 0);
        commands.put("date", 0);
        commands.put("exit", 0);
        commands.put("more", 1);
        commands.put("help", 0);
        commands.put("args", 1);
    }
    public static String cleanInput(String input){
        return input.replaceAll("\\s{2,}", " ").trim();
    }
    public static void parseUserInput(Terminal terminal, String input) throws IOException {
        String[] inputParsedByPipes = input.split(" \\| ");
        boolean parsedCorrectly = false;

        if(inputParsedByPipes.length > 1)
        {
            for (String parsedInput : inputParsedByPipes)
            {
                Parser parser;
                boolean checkSingleRedirection = parsedInput.contains(" > ");
                boolean checkDoubleRedirection = parsedInput.contains(" >> ");
                String filepath;

                if(checkSingleRedirection)
                {
                    parser = new Parser();

                    if(parser.parse(parsedInput.split(" > ")[0])) {
                        parsedCorrectly = true;
                        String outputContent = terminal.callCommand(parser);
                        filepath = parsedInput.split(" > ")[1];
                        Terminal.writeTruncate(filepath, outputContent);
                    }
                }

                else if(checkDoubleRedirection)
                {
                    parser = new Parser();
                    if(parser.parse(parsedInput.split(" >> ")[0])){
                        parsedCorrectly = true;
                        String outputContent = terminal.callCommand(parser);
                        filepath = parsedInput.split(" >> ")[1];
                        Terminal.writeAppend(filepath, outputContent);
                    }
                }

                else {
                    parser = new Parser();
                    if (parser.parse(parsedInput)) {
                        parsedCorrectly = true;
                        terminal.callCommand(parser);
                    }
                }

                if (!parsedCorrectly)
                    break;
            }
        }

        else {
            Parser parser = new Parser();
            boolean checkSingleRedirection = input.contains(" > ");
            boolean checkDoubleRedirection = input.contains(" >> ");

            if(checkSingleRedirection)
            {
                if(parser.parse(input.split(" > ")[0]))
                {
                    String outputContent = terminal.callCommand(parser);
                    String filepath = input.split(" > ")[1];
                    Terminal.writeTruncate(filepath, outputContent);
                }
            }

            else if(checkDoubleRedirection)
            {
                if(parser.parse(input.split(" >> ")[0])){
                    String outputContent = terminal.callCommand(parser);
                    String filepath = input.split(" >> ")[1];
                    Terminal.writeAppend(filepath, outputContent);
                }
            }

            else if (parser.parse(input))
                terminal.callCommand(parser);
        }
    }
    public static void main(String[] args) {
        populateCommands();
        Scanner userInput = new Scanner(System.in);
        Terminal terminal = new Terminal();

        while(executionTime)
        {
            try {
                System.out.print("omara@linux:~$ ");
                String input = cleanInput(userInput.nextLine());
                parseUserInput(terminal, input);
            }

            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
