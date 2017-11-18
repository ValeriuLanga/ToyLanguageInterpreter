package Controller;

import Model.*;
import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Repository.RepositoryInterface;
import Model.Statements.Statement;

import java.io.IOException;

public class Controller
{
    private RepositoryInterface repository;

    public Controller(RepositoryInterface repository){
        this.repository = repository;
    }

    public void executeOnce() throws DivisionByZeroException, UnknownOperationException, IOException {
        ProgramState programState = repository.getCurrentProgramState();

        if(!programState.getExecutionStack().isEmpty())
        {
            Statement statement = programState.getExecutionStack().pop();
            statement.execute(programState);
        }

        System.out.println(programState);
        repository.LogProgramState();

        repository.addProgramState(programState);
    }

    public void executeAll() throws DivisionByZeroException, UnknownOperationException, IOException {
        ProgramState programState = repository.getCurrentProgramState();

        while(!programState.getExecutionStack().isEmpty())
            executeOnce();
    }
    public void setFile(String fileName){
        repository.setFileName(fileName);
    }


}




