package Model;

import Model.ExecutionStack.ExecutionStackInterface;
import Model.FileTable.FileDescriptor;
import Model.FileTable.FileTableInterface;
import Model.Heap.HeapInterface;
import Model.OutputList.OutputListInterface;
import Model.Statements.Statement;
import Model.SymbolTable.SymbolTableInterface;

public class ProgramState {
    private ExecutionStackInterface<Statement> executionStack;
    private SymbolTableInterface<String, Integer> symbolTable;
    private OutputListInterface<Integer> outputList;
    private FileTableInterface<Integer, FileDescriptor> fileTable;
    private HeapInterface<Integer, Integer> heap;

    public ProgramState(ExecutionStackInterface<Statement> stack,
                        SymbolTableInterface<String, Integer> symbolTable,
                        OutputListInterface<Integer> outputList,
                        FileTableInterface<Integer, FileDescriptor> fileTable,
                        HeapInterface<Integer, Integer> heap){

        this.executionStack = stack;
        this.symbolTable = symbolTable;
        this.outputList = outputList;
        this.fileTable = fileTable;
        this.heap = heap;
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

    public HeapInterface<Integer, Integer> getHeap() {
        return heap;
    }

    @Override
    public String toString(){
        return "ExecutionStack: " + executionStack.toString() + "\nSymbolTable: " + symbolTable.toString()
                + "\nOutput: " + outputList.toString()
                +"\nFileTable: " + fileTable.toString()
                + '\n';
    }
}
