package de.berlios.esotranslator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class BrainfunckParser {

    int ptr;
    int[] mem ;
    BFBuilder builder;
    CodeContainer container;
    private String filename;

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String className = "Brainfucker";

        BrainfunckParser bp = new BrainfunckParser(15, className);
        bp.parseBF("++++++++++[>+++++++>++++++++++>++++++++++++>+<<<<-]>++++.>---.>--.<.>>." +
                ">++++++++++[>+++++++>++++++++++>++++++++++++>+<<<<-]>+++++.>---.>--.<.>>.");
        bp.writeCode();
        bp.tryCompile();
    }

    public BrainfunckParser(int size, String className) {
        mem = new int[size];

        builder = new JavaBuilder();
        builder.setName(className);

        container = (CodeContainer) builder;

        filename = container.getFileName(className);
    }

    public void tryCompile() {
        container.compile(filename);
    }

    public void writeCode() {
        // Write Java Source
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(filename));
            bw.write(container.getCode());
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void dumpMem() {
        for(int i = 0; i < mem.length; i++) {
            System.out.printf("%03d ",mem[i]);
        }
        System.out.print("\n");
    }

    void parseBF(String bf) {
//        System.out.println("Parsing " + bf);
        char[] code = bf.toCharArray();
        int pos = 0;

        while (pos < code.length) {
//            System.out.print("Position: " + pos + " Code: " + code[pos] + " MEM: ");
            switch (code[pos]) {
                case '>': incPointer(); break;
                case '<': decPointer(); break;
                case '+': incField(); break;
                case '-': decField(); break;
                case ',': readField(); break;
                case '.': printField(); break;
                case '[': pos = doLoop(bf, pos); break;
            }
//            dumpMem();
            pos++;
        }

    }

    void incPointer()
    {
        ++ptr;
        builder.incPointer();
    }

    void decPointer() {
        --ptr;
        builder.decPointer();
    }

    void incField() {
        ++(mem[ptr]);
        builder.incField();
    }

    void decField() {
        --(mem[ptr]);
        builder.decField();
    }

    void printField() {
        System.out.print((char) mem[ptr]);
        builder.printField();
    }

    void readField() {
        try {
            BufferedReader bin = new BufferedReader(new InputStreamReader(
                    System.in));
            System.out.println("Input: ");
            mem[ptr] = Integer.parseInt(bin.readLine());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        builder.readField();
    }

    // returns loop end + 1
    int doLoop(String bf, int pos) {
        builder.startLoop();
        // Springt nach vorne, hinter den passenden ]-Befehl, wenn der aktuelle
        // Zellenwert null ist
        // finde ende
        int endPos = bf.indexOf(']', pos);
        String loopCode = bf.substring(pos+1, endPos);

        while (mem[ptr] != 0) {
//            System.out.println("field " +ptr + " is " + mem[ptr] + " which is != 0 - looping");
            parseBF(loopCode);
        }
        builder.endLoop();
        return endPos;
    }
}
