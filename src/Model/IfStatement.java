package Model;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.NotExistingException;
import Model.Exceptions.UnknownOperationException;

public class IfStatement implements Statement {
    private Expression ifStatement;
    private Statement thenStatement;
    private Statement elseStatemnet;

    public IfStatement(Expression ifStm, Statement thenStm, Statement elseStm) {
        ifStatement = ifStm;
        thenStatement = thenStm;
        elseStatemnet = elseStm;
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws DivisionByZeroException, UnknownOperationException {
        SymbolTableInterface<String, Integer> symTable = currentState.getSymbolTable();

        try {
            int result = this.ifStatement.eval(symTable);
            if(result != 0) {
                this.thenStatement.execute(currentState);
            }
            else {
                this.elseStatemnet.execute(currentState);
            }

        }
        catch (DivisionByZeroException | UnknownOperationException | NotExistingException e) {
            System.out.println(e.toString());
        }
        return currentState;
    }

    public String toString() {
        return "IF(" + this.ifStatement + ") THEN(" + this.thenStatement + ")ELSE(" + this.elseStatemnet + ")";
    }
}
