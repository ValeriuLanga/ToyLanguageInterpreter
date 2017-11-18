package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Expressions.Expression;
import Model.Heap.HeapInterface;
import Model.ProgramState;
import Model.SymbolTable.SymbolTableInterface;
import Utils.FreeAddressGenerator;

public class NewAddressStatement implements Statement {
    private String variableName;
    private Expression expression;

    public NewAddressStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws DivisionByZeroException, UnknownOperationException {

        // in order to add a new address on the heap we need the symbol table ( for the addresses )
        // and the actual heap, for the values stored in the above mentioned addresses
        SymbolTableInterface<String, Integer> symbolTable = currentState.getSymbolTable();
        HeapInterface<Integer, Integer> heap = currentState.getHeap();

        int value = expression.eval(symbolTable, heap);

        // see if the address from the symbol table exists in the heap
        int oldAddress = symbolTable.get(variableName);

        // get a new address
        int newAddress = FreeAddressGenerator.generateFreeAddress(heap);

        if( heap.contains(oldAddress) ){
            // update the address to the new eval() result
            symbolTable.update(variableName, newAddress);
        }
        else{
            // add the entry in the heap
            symbolTable.add(variableName, newAddress);
        }

        // in any case, we update the value 'pointed' to in the heap
        heap.add(newAddress, value);

        return currentState;
    }

    @Override
    public String toString() {
        return "new (" + variableName + " , " + expression + ")";
    }
}
