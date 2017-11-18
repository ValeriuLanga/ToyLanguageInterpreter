package Model.Expressions;

import Model.SymbolTableInterface;

public class ConstantExpression implements Expression {
    private int constant;

    public ConstantExpression(int value){
        this.constant = value;
    }

    @Override
    public int eval(SymbolTableInterface<String, Integer> SymTable) {
        return this.constant;
    }

    @Override
    public String toString() {
        return String.valueOf(constant);
    }
}
