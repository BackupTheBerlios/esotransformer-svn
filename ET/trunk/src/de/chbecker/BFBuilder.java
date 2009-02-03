package de.chbecker;

/**
 * BFBuilder interface defines all Brainfuck commands
 * @author cb
 *
 */
public interface BFBuilder {
    public void incPointer();
    public void decPointer();
    public void incField();
    public void decField();
    public void printField();
    public void readField();
    public void startLoop();
    public void endLoop();

    public String getCode();// return  generated source code
    public void setName(String name);  // "class" name}
}