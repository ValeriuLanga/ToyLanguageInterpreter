package Model;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;

public interface Expression {
    public int eval(SymbolTableInterface<String, Integer> SymbolTable) throws DivisionByZeroException, UnknownOperationException;
}
