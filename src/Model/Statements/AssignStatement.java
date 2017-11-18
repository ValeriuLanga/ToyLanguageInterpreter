package Model.Statements;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.SymbolTable.SymbolTableInterface;

public class AssignStatement implements Statement {
    private String varName;
    private Expression expression;

    public AssignStatement(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws DivisionByZeroException, UnknownOperationException {
        //ExecutionStackInterface<Statement> executionStack = currentState.getExecutionStack();
        SymbolTableInterface<String, Integer> symbolTable = currentState.getSymbolTable();

        int result = this.expression.eval(symbolTable);
        if (symbolTable.contains(varName)) {
            symbolTable.replace(varName, result);
        }
        else {
            symbolTable.put(varName, result);
        }

        return currentState;
    }

    @Override
    public String toString() {
        return this.varName + '=' + this.expression.toString();
    }
}
