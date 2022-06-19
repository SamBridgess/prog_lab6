package ilya.lab.client.IO;

import ilya.lab.common.Exceptions.CtrlDException;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * in-out manager
 */
public class IOManager implements AutoCloseable {
    private final String ansiReset = "\u001B[0m";
    private final String ansiRed = "\u001B[31m";
    private final String ansiGreen = "\u001B[32m";
    private BufferedReader reader;
    private PrintWriter writer;
    private boolean continueExecutionFlag;

    private final Stack<File> fileStack = new Stack<>();
    private final Stack<BufferedReader> readers = new Stack<>();
    private final Stack<PrintWriter> writers = new Stack<>();
    private final Stack<ArrayList<String>> executionStack = new Stack<>();

    /**
     * creates new IOManager
     *
     * @param reader    current reader
     * @param writer    current writer
     */
    public IOManager(BufferedReader reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
        this.continueExecutionFlag = true;
    }

    /**
     * closes all readers and writers
     *
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        printConfirmation("Closing everything...");
        while (!readers.isEmpty()) {
            BufferedReader br = readers.pop();
            br.close();
        }
        while (!writers.isEmpty()) {
            PrintWriter pw = writers.pop();
            pw.close();
        }
        reader.close();
        writer.close();
    }

    /**
     * clears all stacks
     *
     */
    public void clearStacks() {
        executionStack.clear();
        fileStack.clear();
    }
    /**
     * @return      returns if last added script was fully executed
     */
    public boolean isLastFileExecuted() {
        if(executionStack.isEmpty()) {
            return false;
        }
        return executionStack.peek().isEmpty();
    }

    /**
     * @return      returns next line from console, or from execution stack if working with file
     * @throws IOException
     * @throws CtrlDException
     */
    public String getNextLine() throws IOException, CtrlDException {
        String s;
        if (getIsFile()) {
            if (executionStack.peek().isEmpty()) {// todo remove?
                return null;
            }
            s = executionStack.peek().get(0);
            executionStack.peek().remove(0);
          /*  if(isLastFileExecuted()) {
                printConfirmation(fileStack.peek().getName() + " executed successfully");
                popFile();
            }*/
        } else {
            s = reader.readLine();
            if (s == null & !getIsFile()) {
                throw new CtrlDException();
            }
        }
        return s;
    }

    /**
     * adds file to stack of opened files
     *
     * @param file  file to add
     * @return      returns whether file was added successfully
     */
    public boolean addFileToFileStack(File file) {
        if (fileStack.contains(file)) {
            return false;
        }
        fileStack.add(file);
        return true;
    }

    /**
     * pops fileStack and executionStack
     */
    public void popStacks() {
        if (!executionStack.isEmpty() & !fileStack.isEmpty()) {
            executionStack.pop();
            fileStack.pop();
        }
    }
    /**
     * adds file to executionStack
     *
     * @param file  File to add
     * @throws IOException
     */
    public void fillExecutionStack(File file) throws IOException {
        readers.add(reader);
        writers.add(writer);

        reader = new BufferedReader(new FileReader(file));

        executionStack.add(new ArrayList<>());
        String s = reader.readLine();
        while (!Objects.equals(s, null)) {
            executionStack.peek().add(s);
            s = reader.readLine();
        }
        reader.close();

        reader = readers.pop();
        writer = writers.pop();
    }

    /**
     * sets continueExecutionFlag to passed parameter
     *
     * @param b     parameter to set ContinueExecutionFlag to
     */
    public void setContinueExecutionFlag(boolean b) {
        continueExecutionFlag = b;
    }

    /**
     * @return      returns value of continueExecutionFlag
     */
    public boolean getContinueExecutionFlag() {
        return continueExecutionFlag;
    }

    public Stack<File> getFileStack() {
        return fileStack;
    }

    /***
     * @return      returns whether manager is working with a file in a given moment to isFile
     */
    public boolean getIsFile() {
        return !executionStack.empty();
    }

    /**
     * prints passed object without new line
     *
     * @param o object to print
     */
    public void print(Object o) {
        writer.printf("%s", o);
    }
    /**
     * prints passed object with new line
     *
     * @param o object to print
     */
    public  void println(Object o) {
        writer.println(o);
    }

    /**
     * prints passed warning message in red
     *
     * @param o object to print
     */
    public void printWarning(Object o) {
        writer.println(ansiRed + o + ansiReset);
    }

    /**
     * prints confirmation warning message in green
     *
     * @param o object to print
     */
    public void printConfirmation(Object o) {
        writer.println(ansiGreen + o + ansiReset);
    }
}
