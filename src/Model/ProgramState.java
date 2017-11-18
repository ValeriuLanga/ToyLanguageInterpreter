package Model;

import Model.FileTable.FileDescriptor;
import Model.FileTable.FileTable;
import Model.FileTable.FileTableInterface;
import Model.Statements.Statement;

public class ProgramState {
    private ExecutionStackInterface<Statement> executionStack;
    private SymbolTableInterface<String, Integer> symbolTable;
    private OutputListInterface<Integer> outputList;
    private FileTableInterface<Integer, FileDescriptor> fileTable;

    public ProgramState(ExecutionStackInterface<Statement> stack,
                        SymbolTableInterface<String, Integer> symbolTable,
                        OutputListInterface<Integer> outputList,
                        FileTableInterface<Integer, FileDescriptor> fileTable){
        this.executionStack = stack;
        this.symbolTable = symbolTable;
        this.outputList = outputList;
        this.fileTable = fileTable;
    }

    public ExecutionStackInterface<Statement> getExecutionStack() {
       return executionStack;
    }

    public OutputListInterface<Integer> getOutputList(){
        return outputList;
    }

    public SymbolTableInterface<String, Integer> getSymbolTable(){
        return symbolTable;
    }

    public FileTableInterface<Integer, FileDescriptor> getFileTable() { return fileTable; }

    @Override
    public String toString(){
        return "ExecutionStack: " + executionStack.toString() + "\nSymbolTable: " + symbolTable.toString()
                + "\nOutput: " + outputList.toString()
                +"\nFileTable: " + fileTable.toString()
                + '\n';
    }
}
