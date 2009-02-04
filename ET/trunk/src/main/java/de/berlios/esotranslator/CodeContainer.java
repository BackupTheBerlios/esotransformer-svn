package de.berlios.esotranslator;


public abstract class CodeContainer {
    StringBuilder code = new StringBuilder();
    protected static String linefeed = System.getProperty("line.separator");
    protected String language = "unknown";
    protected String className;

    abstract public String getStartCode();
    abstract public String getEndCode();
    abstract public String getFileName();
    abstract public String getBinaryFileName();

    public String getCode() {
        return getStartCode() + code.toString() + getEndCode();
    }

    protected void add(String codeline) {
        code.append(codeline + linefeed);
    }

    // child should implement this
    public boolean compile() {
        System.err.println("I currently cannot compile " + getFileName() + " in language '" + language +"'. ");
        return false;
    }
    
    public void setName(String name) {
    	className = name;
    }
    
}
