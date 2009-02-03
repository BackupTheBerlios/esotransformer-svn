package de.chbecker;


public abstract class CodeContainer {
    StringBuilder code = new StringBuilder();
    static String linefeed = System.getProperty("line.separator");
    protected String language = "unknown";

    abstract String getStartCode();
    abstract String getEndCode();


    public String getCode() {
        return getStartCode() + code.toString() + getEndCode();
    }

    void add(String codeline) {
        code.append(codeline + linefeed);
    }

    public boolean compile(String filename) {
        System.out.println("I currently cannot compile " + filename + " in " + language);
        return false;
    }

    abstract String getFileName(String className);
}
