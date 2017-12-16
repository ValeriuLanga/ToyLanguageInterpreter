package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.ExecutionStack.ExecutionStack;
import Model.ExecutionStack.ExecutionStackInterface;
import Model.ProgramState;
import Model.SymbolTable.SymbolTableInterface;

public class ForkStatement implements Statement {
    private Statement statement;

    public ForkStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws DivisionByZeroException, UnknownOperationException {

        // create a new program state which we return
        ProgramState forkedProgramState = new ProgramState(
                            new ExecutionStack<>(),
                            currentState.getSymbolTable().clone(),
                            currentState.getOutputList(),
                            currentState.getFileTable(),
                            currentState.getHeap());

        return forkedProgramState;
    }
}
