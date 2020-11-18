package com.company;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Testing
{
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
       // String input = "    cd /Users/omara    ";
        //String cleanedInput = Main.cleanInput(input);
        //System.out.println(cleanedInput);
        //String[] parsedCommand = cleanedInput.split(" ");
        //System.out.println(Arrays.toString(parsedCommand));+
        //ArrayList<String> parsedInput = new ArrayList<String>(Arrays.asList(cleanedInput.split(" ")));
        //String cmd = parsedInput.get(0);
        //for(String arg: parsedInput){
        //    System.out.println(arg);
        //}
        //Terminal terminal = new Terminal();
        //String command = "ls";
        //Class<?> c = Class.forName("com.company.Terminal");
        //Method method = c.getDeclaredMethod("ls");
        //method.invoke(terminal);
        //Date date = new Date();
       // SimpleDateFormat formatter= new SimpleDateFormat("E d MMM y HH:mm:ss a z");

        //System.out.println(formatter.format(date));

        //terminal.ls();
        //terminal.pwd();
        //System.out.println("\n");
        //terminal.cd("/Users/omara/data_science_bootcamp");
        //System.out.println("\n");
        //terminal.pwd();
        //System.out.println("\n");
        //terminal.ls();
        // terminal.cp("D:/ME!/Music/Shabjdeed/Aadi.flac", "D:/ME!/Aadi.flac");
        //File src = new File("D:/ME!/Music/Shabjdeed/Aadi.flac");
        //File dest = new File("D:/ME!/Aadi2.flac");
        //Testing.copyFile(src, dest);
        //terminal.cd("..");
        //terminal.pwd();
        //terminal.ls();

        //File directory = new File(Main.currentPath);
        //System.out.println(directory.getAbsolutePath());
        //File a = new File("../");

            // File parentFolder = new File(directory.getParent());
           //  File b = new File(parentFolder, "../some/relative/path");
          //   String absolute = b.getCanonicalPath();

        /*        Main.populateCommands();
        Parser parser = new Parser();
        if(parser.parse("cd users/omara")){
            System.out.println("Valid");
        }
        else{
            System.out.println("Not Valid");
        }
        System.out.println("command: " + parser.getCmd());
        System.out.println("args: " + Arrays.asList(parser.getArguments()).get(0));*/

        //System.out.println(getCanonicalPath("Users/omara/", "data_science_bootcamp"));
        //String input = "cmd1 p1 p2 |";
        //System.out.println(input.split(" \\| ").length);
       // String input = "ls  ";
        //System.out.println(input.split(" > | >> ").length);

       // for(String token:input.split(" > | >> ")){
         //   System.out.println(token);
        //}

        // cd ..
        //System.out.println(System.getProperty("user.dir"));
        // userInput = "../DataCamp";
        //File file = new File(Main.currentDirectory, userInput);
        //printPaths(file);
        //String userInput = "cmd p1 > sample.txt | cmd >> sample2.txt";
        //String[] inputParsedByPipes = userInput.split(" \\| ");
        //for(String parsedInput: inputParsedByPipes){
           // System.out.println(parsedInput);
       // }
        //  String check0 =  inputParsedByPipes[0].contains(" > ") ? "Single Redirection": "No Single Redirection";
        // String check1 =  inputParsedByPipes[0].contains(" >> ") ? "Double Redirection": "No Double Redirection";
        // String check2 = inputParsedByPipes[1].contains(" > ") ? "Single Redirection": "No Single Redirection";
        // String check3 = inputParsedByPipes[1].contains(" >> ") ? "Double Redirection": "No Double Redirection";

        // System.out.println(check0 + "\n" + check1 + "\n" +  check2 +"\n"+ check3);
        //boolean findRedirection = userInput.
        // truncateAndWriteOnFile("usernames.txt", "omarabdelaziz");


        //Terminal terminal = new Terminal();
       // terminal.pwd();
        //File newFile = new File(Main.currentDirectory, "..");
        //i/f(newFile.exists()){
          //  System.out.println(newFile.listFiles());
      // }
        //File file = new File(Main.currentDirectory, "..");
        //printPaths(file);
        //Terminal terminal = new Terminal();
        //System.out.println(terminal.ls(""));
        //String input = "cd .. | ls";
        //String[] command = input.split(" \\| ");
        //System.out.println(command[0]);
        //String srcPathFile = Main.currentDirectory + File.separator + "usernames.txt";
        //String destPathFile = "Users/omara/DataCamp" + File.separator+ "sometext23.txt";

        //File src = new File(srcPathFile);
        //File dest = new File(destPathFile);
        //copyFile(src, dest);
        //copyFileUsingStream(src, dest);
//          Scanner userInput = new Scanner(System.in);
//          String path = userInput.nextLine();
//          System.out.println(path);
//          String handledPath = handlePath(path);
//          System.out.println(handledPath);
    }

    private static void printPaths(File file) throws IOException {
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        System.out.println("Canonical Path: " + file.getCanonicalPath());
        System.out.println("Path: " + file.getPath());
    }

//        private static String handlePath(String givenPath) throws IOException {
//            Path currentDirectory = Paths.get(Main.currentDirectory);
//            Path pathGiven = Paths.get(givenPath);
//            String handledPath = currentDirectory.resolve(pathGiven).toFile().getCanonicalPath();
//
//            if(Files.exists(Paths.get(handledPath)))
//                return handledPath;
//
//            else if(Files.exists(Paths.get(handledPath.substring(0, handledPath.lastIndexOf("\\"))))){
//                return handledPath;
//            }
//
//            return "";
//        }
}

