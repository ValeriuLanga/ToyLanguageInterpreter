package Model;

import Model.Exceptions.NotExistingException;

public class VariableExpression implements Expression {
    private String varName;

    public VariableExpression(String name) {
        this.varName = name;
    }

    @Override
    public int eval(SymbolTableInterface<String, Integer> SymTable) throws NotExistingException {
        if (SymTable.contains(this.varName))
            return SymTable.get(this.varName);
        else {
            throw new NotExistingException("Not found!");
        }
    }

    @Override
    public String toString(){
        return this.varName;
    }
}
