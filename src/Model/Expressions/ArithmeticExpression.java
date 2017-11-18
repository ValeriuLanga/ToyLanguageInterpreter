package Model.Expressions;

import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.SymbolTableInterface;

public class ArithmeticExpression implements Expression {
    private char operator;
    private Expression left, right;

    public ArithmeticExpression(char op, Expression left, Expression right) {
        this.operator = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval(SymbolTableInterface<String, Integer> SymTable) throws DivisionByZeroException, UnknownOperationException {
        int left = 0, right = 0;

        left = this.left.eval(SymTable);
        right = this.right.eval(SymTable);

        switch (this.operator) {
            case '+': {
                return left + right;
            }
            case '-': {
                return left - right;
            }
            case '*': {
                return left * right;
            }
            case '/': {
                if (right == 0) {
                    throw new DivisionByZeroException();
                }
                return left / right;
            }
            default: {
                throw new UnknownOperationException(this.operator);
            }
        }
    }

    @Override
    public String toString() {
        return this.left.toString() + this.operator + this.right.toString();
    }
}
