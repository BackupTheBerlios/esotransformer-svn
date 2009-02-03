package de.chbecker;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
// modiefied example from http://www.java2s.com/Code/Java/JDK-6/CompileaJavacode.htm

public class JCompiler {
    private String sourceFile;

    public JCompiler(String sourcefile) {
        this.sourceFile = sourcefile;
    }

    public boolean compile() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int results = compiler.run(null, null, null, sourceFile);
        return (results == 0);
    }
}
