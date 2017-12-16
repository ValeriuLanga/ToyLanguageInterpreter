package Model;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;

public class PrintStatement implements Statement {
    private Expression expression;

    public PrintStatement(Expression e) {
        this.expression = e;
    }

    @Override
    public ProgramState execute (ProgramState currentState) throws DivisionByZeroException, UnknownOperationException {
        OutputListInterface<Integer> list = currentState.getOutputList();
        SymbolTableInterface<String, Integer> symbolTable = currentState.getSymbolTable();

        int res = this.expression.eval(symbolTable);
        list.addElement(res);
        return currentState;
    }

    @Override
    public String toString() {
        return "Print(" + expression.toString() + ")";
    }
}
