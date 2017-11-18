package Model.Expressions;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.SymbolTableInterface;

public interface Expression {
    public int eval(SymbolTableInterface<String, Integer> SymbolTable) throws DivisionByZeroException, UnknownOperationException;
}
