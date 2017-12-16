package Model;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;

public interface Statement {
    public ProgramState execute(ProgramState currentState) throws DivisionByZeroException, UnknownOperationException;
}
