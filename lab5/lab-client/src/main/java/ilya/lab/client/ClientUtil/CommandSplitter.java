package ilya.lab.client.ClientUtil;

import java.util.Arrays;

public class CommandSplitter {
    private String command;
    private String[] args;
    public CommandSplitter(String line) {
        String[] words = line.trim().split("\\s++");

        command = words[0];
        args = Arrays.copyOfRange(words, 1, words.length);
    }

    public String getCommand() {
        return command;
    }
    public String[] getArgs() {
        return args;
    }
}
