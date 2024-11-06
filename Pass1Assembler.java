import java.io.*;
import java.util.*;

class Symbol {
    String label;
    int address;

    Symbol(String label, int address) {
        this.label = label;
        this.address = address;
    }
}

class SymbolTable {
    private Map<String, Symbol> symbols = new HashMap<>();

    public void addSymbol(String label, int address) {
        symbols.put(label, new Symbol(label, address));
    }

    public boolean contains(String label) {
        return symbols.containsKey(label);
    }

    public int getAddress(String label) {
        return symbols.get(label).address;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }
}

class Opcode {
    String mnemonic;
    String opcode;
    int length;

    Opcode(String mnemonic, String opcode, int length) {
        this.mnemonic = mnemonic;
        this.opcode = opcode;
        this.length = length;
    }
}

class OpcodeTable {
    private Map<String, Opcode> opcodes = new HashMap<>();

    public void addOpcode(String mnemonic, String opcode, int length) {
        opcodes.put(mnemonic, new Opcode(mnemonic, opcode, length));
    }

    public boolean contains(String mnemonic) {
        return opcodes.containsKey(mnemonic);
    }

    public Opcode getOpcode(String mnemonic) {
        return opcodes.get(mnemonic);
    }
}

class LiteralTable {
    private Map<String, Integer> literals = new HashMap<>();
    private int literalAddress = 0;

    public void addLiteral(String literal) {
        if (!literals.containsKey(literal)) {
            literals.put(literal, literalAddress);
            literalAddress += 1;
        }
    }

    public Map<String, Integer> getLiterals() {
        return literals;
    }
}

class IntermediateFile {
    private List<String> lines = new ArrayList<>();

    public void addLine(String line) {
        lines.add(line);
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public List<String> getLines() {
        return lines;
    }
}

public class Pass1Assembler {
    private SymbolTable symbolTable = new SymbolTable();
    private OpcodeTable opcodeTable = new OpcodeTable();
    private LiteralTable literalTable = new LiteralTable();
    private IntermediateFile intermediateFile = new IntermediateFile();
    private int locationCounter = 0;

    public Pass1Assembler() {
        initializeOpcodes();
    }

    private void initializeOpcodes() {
        opcodeTable.addOpcode("LDA", "00", 1);
        opcodeTable.addOpcode("STA", "01", 1);
        opcodeTable.addOpcode("ADD", "02", 1);
        opcodeTable.addOpcode("SUB", "03", 1);
        opcodeTable.addOpcode("MOV", "04", 2);
    }

    public void processLine(String line) {
        String[] tokens = line.trim().split("\\s+");
        int i = 0;

        // Check for label
        if (!opcodeTable.contains(tokens[i])) {
            String label = tokens[i++];
            if (!symbolTable.contains(label)) {
                symbolTable.addSymbol(label, locationCounter);
            }
        }

        String mnemonic = tokens[i++];
        if (opcodeTable.contains(mnemonic)) {
            Opcode opcode = opcodeTable.getOpcode(mnemonic);
            String operand = (i < tokens.length) ? tokens[i] : "";

            // Add to intermediate file
            intermediateFile.addLine(locationCounter + "\t" + mnemonic + "\t" + operand);

            // Update location counter
            locationCounter += opcode.length;
        } else if (mnemonic.equals("ORG")) {
            locationCounter = Integer.parseInt(tokens[i]);
        } else if (mnemonic.equals("EQU")) {
            symbolTable.addSymbol(tokens[0], Integer.parseInt(tokens[i]));
        } else if (mnemonic.equals("LTORG") || mnemonic.equals("END")) {
            literalTable.addLiteral(tokens[i]);
        }
    }

    public void displayTables() {
        System.out.println("Symbol Table:");
        symbolTable.getSymbols().forEach((label, symbol) -> System.out.println(label + " : " + symbol.address));

        System.out.println("\nLiteral Table:");
        literalTable.getLiterals().forEach((literal, address) -> System.out.println(literal + " : " + address));
    }

    public void generateIntermediateFile(String filename) throws IOException {
        intermediateFile.saveToFile(filename);
    }

    public static void main(String[] args) {
        Pass1Assembler assembler = new Pass1Assembler();
        List<String> sourceProgram = Arrays.asList(
                "START 200",
                "LDA ALPHA",
                "ADD BETA",
                "STA GAMMA",
                "END"
        );

        // Process each line of the source program
        for (String line : sourceProgram) {
            assembler.processLine(line);
        }

        // Display tables and save intermediate file
        assembler.displayTables();
        try {
            assembler.generateIntermediateFile("intermediate.txt");
            System.out.println("\nIntermediate file saved as intermediate.txt");
        } catch (IOException e) {
            System.out.println("Error saving intermediate file: " + e.getMessage());
        }
    }
}
