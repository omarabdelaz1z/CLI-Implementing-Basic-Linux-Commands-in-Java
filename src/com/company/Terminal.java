package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Terminal
{
    public void cp(String sourcePath, String destinationPath) throws IOException {
        //automatically overwrite to the new file, another command cp -i file1 file2
        //allows you to choose either overwrite or append, not asked in the assignment

        //if the 2 strings given are files in same directory
        File srcFile;
        File destFile;

        srcFile = new File(handlePath(sourcePath));
        destFile = new File(handlePath(destinationPath));
        destFile.createNewFile();

        if(srcFile.isFile() & destFile.isFile()) {
            InputStream input_Stream = null;
            OutputStream output_Stream = null;
            input_Stream = new FileInputStream(srcFile);
            output_Stream = new FileOutputStream(destFile);

            byte[] temporary_buffer = new byte[1024];
            int length = 0;
            while ((length = input_Stream.read(temporary_buffer)) > 0) {
                output_Stream.write(temporary_buffer, 0, length);

            }

            input_Stream.close();
            output_Stream.close();
        }

        //to copy file to directory
        //////////directory must be full paths
        else if(srcFile.isFile() && destFile.isDirectory())
        {
            String srcPath = handlePath(sourcePath);
            String destPath = destinationPath + File.separator + sourcePath;
            destPath = handlePath(destPath);

            //Path fromPath = Paths.get(handlePath(sourcePath));
            //Path targetPath = Paths.get(handlePath(destinationPath + File.separator + srcFile.getPath()));

            cp(srcPath, destPath);
        }

        //////////////copy content of directory to other directory
        else
        {
            File[] elements = new File(sourcePath).listFiles();
            if (elements != null && elements.length > 0)
            {
                for (File element : elements)
                {
                    if (element.isDirectory())
                    {
                        // if the content is directory
                        File newDir = new File(destinationPath, element.getName());
                        mkdir(newDir.getName());

                        cp(element.getName(), newDir.getName()); //recursive
                    }

                    else  //if the content is file
                    {
                        File destiFile = new File(destinationPath, element.getName());
                        cp(element.getName(), destiFile.getName());
                    }

                }
            }
        }
    }
    public void rm(String sourcePath) throws IOException {
        //To remove directory rm -d directory name
        //not asked in the assignment
        File fd = new File(handlePath(sourcePath));
        fd.delete();
    }
    public String cat(String[] paths) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (String path : paths)
        {
            Scanner input = new Scanner(new File(handlePath(path)));

            while (input.hasNextLine())
            {
                String line = input.nextLine();
                builder.append(line);
                System.out.println(line);
            }
        }
        return builder.toString();
    }
    public void mv(String sourcePath, String destinationPath) throws IOException {
        // if moving file to other file in same directory, it's like rename

            String handledSourcePath = handlePath(sourcePath);
            String handledDestinationPath = handlePath(destinationPath);

            File srcFile = new File(handledSourcePath);
            File destFile = new File(handledDestinationPath);
            destFile.createNewFile();

            if(srcFile.isFile() & destFile.isFile())
            {
                cp(handledSourcePath, handledDestinationPath);
                rm(handledSourcePath);
            }

            //move file to directory
            else if(srcFile.isFile() && destFile.isDirectory())
            {
                cp(handledSourcePath, handledDestinationPath);
                rm(handledSourcePath);
            }

            ////////////move content of directory to directory
            else
            {
                cp(handledSourcePath, handledDestinationPath);
                File source = new File(handledSourcePath);
                rmdir(source.getName());
            }
    }
    public void mkdir(String givenPath) throws IOException {
        File file;

        if(!givenPath.contains("/"))
            file = new File(Main.currentDirectory, givenPath);

        else
            file = new File(handlePath(givenPath));

        // true if the directory was created, false otherwise
        file.mkdir();
    }
    public void rmdir(String givenPath) throws IOException {
        int noOfFiles = 0;

        File directory;

        if(!givenPath.contains("/"))
            directory = new File(Main.currentDirectory, givenPath);

        else
            directory = new File(givenPath);

        if(directory.isDirectory()) {
            File[] files = directory.listFiles();
            for(File file : files) {
                noOfFiles++;
            }

            if (noOfFiles == 0 )
            {
                directory.delete();
            }
            else {
                System.out.println("Directory not empty.");
            }
        }
        else {
            System.out.println("Directory doesn't exist.");
        }

    }
    public String args (String command) {
        switch(command) {
            case "args":{
                System.out.println("arg: command");
                return "arg: command";
            }
            case "cat":{
                System.out.println("args: filepath1, filepath2, ...., filepathN");
                return "args: filepath1, filepath2, ...., filepathN";
            }
            case "more":{
                System.out.println("args: filePath");
                return "args: filePath";
            }
            case "pwd":
            case "date":
            case "help":
            case "clear":
            case "exit": {
                System.out.println("Takes no arguments");
                return "Takes no arguments";
            }
            case "cp":
            case "mv": {
                System.out.println("arg1: SourcePath\targ2: DestinationPath");
                return "arg1: SourcePath\targ2: DestinationPath";
            }
            case "mkdir":
            case "rmdir":
            case "ls":
            case "rm": {
                System.out.println("arg1: givenPath");
                return "arg1: givenPath";
            }
        }
        return null;
    }
    public void cd(String givenPath) throws IOException {
        if(givenPath == null)
            System.out.println(Main.currentDirectory);

        String handledPath = handlePath(givenPath);
        if(handledPath.equals(""))
            System.out.println("System cannot find the path.");

        else
            Main.currentDirectory = handledPath;
    }
    public String pwd() throws IOException {
        String currentWorkingDirectory = new File(Main.currentDirectory).getCanonicalPath();
        System.out.println(currentWorkingDirectory);
        return currentWorkingDirectory;
    }
    public void clear(){
        for(int i = 0 ; i < 100; i++ )
            System.out.println("\b");
    }
    public String date(){
        SimpleDateFormat linuxDateFormat= new SimpleDateFormat("E d MMM y HH:mm:ss a z");
        System.out.println(linuxDateFormat.format(new java.util.Date()));
        return linuxDateFormat.format(new java.util.Date());
    }
    public void exit(){ Main.executionTime = false; }
    /*
    public String lsa(String givenPath) throws IOException {
        String path = givenPath == null ? Main.currentDirectory : handlePath(givenPath);

        if(path.equals(""))
            return "Path doesn't exist.";

        File directory = new File(path);
        String[] directories = directory.list();

        if(directories == null)
            return "Empty Directory.";

        StringBuilder concatenatedOutput = new StringBuilder();

        int fileDirectory = 0;
        Arrays.sort(directories);

        for (String pathname : directories) {
            fileDirectory++;
            System.out.print(pathname + "\t");
            concatenatedOutput.append(pathname).append(" ");

            if(fileDirectory >= 3) {
                System.out.print("\n");
                concatenatedOutput.append("\n");
                fileDirectory = 0;
            }
        }

        System.out.println();
        return concatenatedOutput.toString();
    }
*/
    public String ls(String givenPath) throws IOException {
        String path = givenPath == null ? Main.currentDirectory : handlePath(givenPath);

        if(path.equals(""))
            return "Path doesn't exist.";

        File directory = new File(path);
        File[] filesInDirectory = directory.listFiles();
        StringBuilder concatenatedOutput = new StringBuilder();

        int fileDirectory = 0;
        Arrays.sort(filesInDirectory);

        for(File file: filesInDirectory){
            fileDirectory++;
            System.out.print(file.getName() + "\t");
            concatenatedOutput.append(file.getName()).append(" ");

            if(fileDirectory >= 3) {
                System.out.print("\n");
                concatenatedOutput.append("\n");
                fileDirectory = 0;
            }
        }
        return concatenatedOutput.toString();
    }
    public String more(String givenPath) throws IOException {
        File file;

        if(!givenPath.contains("/"))
            file = new File(Main.currentDirectory, givenPath);

        else
            file = new File(handlePath(givenPath));

        if (file.exists())
        {
            try {
                FileInputStream a = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(a));
                StringBuilder builder = new StringBuilder();
                String line;
                int i = 0;
                int choice;
                Scanner in = new Scanner(System.in);
                while ((line = br.readLine()) != null)
                {
                    System.out.println(line);
                    builder.append(line).append("\n");
                    i++;
                    if (i % 10 == 0)
                    {
                        System.out.println("..MORE..(press 0 for quitting and press 1 for more)");
                        choice= in.nextInt();
                        if (choice == 0) {
                            return builder.toString();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("File doesnt exist");
        return null;
    }
    public String help(){
        try {
            Scanner reader = new Scanner(new File(System.getProperty("user.dir"),"HELP.txt"));
            StringBuilder builder = new StringBuilder();
            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                builder.append(line).append("\n");
                System.out.println(line);
            }
            reader.close();
            return builder.toString();
        }

        catch(FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }
        return null;
    }
    public String callCommand(Parser parse) throws IOException {
            switch (parse.getCmd().toLowerCase()) {
                case "cp": {
                    String srcPath = parse.getArguments()[0];
                    String destPath = parse.getArguments()[1];
                    cp(srcPath, destPath);
                    break;
                }
                case "mv": {
                    String srcPath = parse.getArguments()[0];
                    String destPath = parse.getArguments()[1];
                    mv(srcPath, destPath);
                    break;
                }
                case "cd": {
                    String path = parse.getArguments()[0];
                    cd(path);
                    break;
                }
                case "mkdir": {
                    String path = parse.getArguments()[0];
                    mkdir(path);
                    break;
                }
                case "rmdir": {
                    String path = parse.getArguments()[0];
                    rmdir(path);
                    break;
                }
                case "rm": {
                    String path = parse.getArguments()[0];
                    rm(path);
                    break;
                }
                case "cat":
                    return cat(parse.getArguments());
                case "ls":
                    if (parse.getArguments().length == 0)
                        return ls(Main.currentDirectory);

                    return ls(parse.getArguments()[0]);
                case "pwd":
                    return pwd();
                case "clear":
                    clear();
                    break;
                case "date":
                    return date();
                case "exit":
                    exit();
                    break;
                case "more":
                    return more(parse.getArguments()[0]);
                case "help":
                    return help();
                case "args":
                    return args(parse.getArguments()[0]);
            }
        return "\n";
    }
    private static String handlePath(String givenPath) throws IOException {
        Path currentDirectory = Paths.get(Main.currentDirectory);
        Path pathGiven = Paths.get(givenPath);
        String handledPath = currentDirectory.resolve(pathGiven).toFile().getCanonicalPath();

        if(Files.exists(Paths.get(handledPath)))
            return handledPath;

        else if(Files.exists(Paths.get(handledPath.substring(0, handledPath.lastIndexOf("\\"))))){
            return handledPath;
        }

        return "";
    }
    public static void writeAppend(String file, String content) throws IOException {
        File fileCreated = new File(Main.currentDirectory, file);
        FileWriter fileWriter = new FileWriter(fileCreated, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(content);
        bufferedWriter.close();
        fileWriter.close();
    }
    public static void writeTruncate(String file, String content) throws IOException {
        File fileCreated = new File(Main.currentDirectory, file);
        FileWriter fileWriter = new FileWriter(fileCreated, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(content);
        bufferedWriter.close();
        fileWriter.close();
    }
}