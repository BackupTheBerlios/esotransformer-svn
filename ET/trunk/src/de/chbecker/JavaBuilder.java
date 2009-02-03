package de.chbecker;


public class JavaBuilder extends CodeContainer implements BFBuilder {
    private String className;
    String language = "Java";

    public JavaBuilder() {
    }

    public void decField() {
        add("		--(mem[ptr]);");
    }

    public void decPointer() {
        add("		--ptr;");

    }

    public void endLoop() {
        add("	} // loop");
    }

    public void incField() {
        add("		++(mem[ptr]);");
    }

    public void incPointer() {
        add("		++ptr;");
    }

    public void printField() {
        add("		System.out.print((char) mem[ptr]);");
    }

    public void readField() {
        add("		readField();");
    }

    public void startLoop() {
        add("		while (mem[ptr] != 0) {");
    }

    public void setName(String name) {
        this.className = name;
    }

    @Override
    String getEndCode() {
        return "	}" + linefeed + "}";
    }

    @Override
    String getStartCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("import java.io.BufferedReader;" + linefeed );
        sb.append("import java.io.InputStreamReader;" + linefeed );
        sb.append("public class " + className + "{" + linefeed );
        sb.append("	int ptr;" + linefeed );
        sb.append("	int[] mem = new int[1000];" + linefeed );

        sb.append("	public static void main(String[] args) {" + linefeed );
        sb.append("		" + className + " bf = new " + className + "();" + linefeed );
        sb.append("		bf.doIt();" + linefeed );
        sb.append("	}"  + linefeed );

        sb.append(" void readField() { " + linefeed);
        sb.append("     try {" + linefeed);
        sb.append("         BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));" + linefeed);
        sb.append("         System.out.println(\"Input: \");" + linefeed);
        sb.append("         mem[ptr] = Integer.parseInt(bin.readLine());" + linefeed);
        sb.append("     }" + linefeed);
        sb.append("   catch (Exception ex) {" + linefeed);
        sb.append("       ex.printStackTrace();" + linefeed);
        sb.append("       System.exit(1);" + linefeed);
        sb.append("   }" + linefeed);
        sb.append(" }" + linefeed);

        sb.append("	public void doIt() {" + linefeed );
        return sb.toString();
    }

    public boolean compile(String filename) {
        // Compile Java Code
        JCompiler compiler = new JCompiler(filename);
        return compiler.compile();
    }

    @Override
    String getFileName(String className) {
        return className + ".java";
    }
}
