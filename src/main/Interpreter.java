package main;

import Model.*;
import Model.ExecutionStack.ExecutionStack;
import Model.ExecutionStack.ExecutionStackInterface;
import Model.Expressions.ConstantExpression;
import Model.Expressions.VariableExpression;
import Model.FileTable.FileDescriptor;
import Model.FileTable.FileTable;
import Model.OutputList.OutputList;
import Model.OutputList.OutputListInterface;
import Model.Repository.Repository;
import Model.Repository.RepositoryInterface;
import Model.Statements.*;
import Model.SymbolTable.SymbolTable;
import Model.SymbolTable.SymbolTableInterface;
import View.ExitCommand;
import View.RunExample;
import View.TextMenu;
import Controller.Controller;



public class Interpreter {

    public static void main(String[] args){
        TextMenu menu = new TextMenu();

        menu.addCommand(new ExitCommand("0", "Exit!"));

        Statement statement1 = new CompoundStatement(
                new AssignStatement("a", new ConstantExpression(10)),
                new PrintStatement(new VariableExpression("a")));

        ExecutionStackInterface<Statement> executionStack1  = new ExecutionStack<>();
        executionStack1.push(statement1);

        SymbolTableInterface<String, Integer> symbolTable1  = new SymbolTable<>();
        OutputListInterface<Integer> outputList1            = new OutputList<>();
        FileTable<Integer, FileDescriptor> fileTable1       = new FileTable<>();
        ProgramState programState1                          = new ProgramState(executionStack1, symbolTable1, outputList1, fileTable1);

        RepositoryInterface repository1                     = new Repository("LogFile1.txt");
        repository1.addProgramState(programState1);
        Controller controller1 = new Controller(repository1);
        // comment here
        menu.addCommand(new RunExample("1", programState1.toString(), controller1));


        // 2nd statement below
        Statement statement2 = new CompoundStatement(
                        new CompoundStatement(
                                new CompoundStatement(
                                        new CompoundStatement(
                                                new OpenReadFileStatement("exampl.txt", "f"),
                                                new ReadFileStatement("f", "c")),
                                        new PrintStatement(new VariableExpression("c"))),
                                new IfStatement(new VariableExpression("c"),
                                        new CompoundStatement(
                                                new ReadFileStatement("f", "c"),
                                                new PrintStatement(new VariableExpression("c"))),
                                        new PrintStatement(new ConstantExpression(0)))),
                        new CloseReadFileStatement("f"));

        ExecutionStackInterface<Statement> executionStack2  = new ExecutionStack<>();
        executionStack2.push(statement2);

        SymbolTableInterface<String, Integer> symbolTable2  = new SymbolTable<>();
        OutputListInterface<Integer> outputList2            = new OutputList<>();
        FileTable<Integer, FileDescriptor> fileTable2       = new FileTable<>();
        ProgramState programState2                          = new ProgramState(executionStack2, symbolTable2, outputList2, fileTable2);

        RepositoryInterface repository2                     = new Repository("LogFile2.txt");
        repository2.addProgramState(programState2);
        Controller controller2 = new Controller(repository2);

        menu.addCommand(new RunExample("2", statement2.toString(), controller2));

        menu.show();
    }
}
