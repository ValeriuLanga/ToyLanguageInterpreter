package Controller;

import Model.*;
import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.UnknownOperationException;
import Model.Repository.RepositoryInterface;
import Model.Statements.Statement;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

        while(!programState.getExecutionStack().isEmpty()){
            executeOnce();


            programState.getHeap().setUnderlyingMap((HashMap<Integer, Integer>)
                    collectGarbage(programState.getSymbolTable().getUnderlyingMap().values(),
                        programState.getHeap().getUnderlyingMap()));

        }
    }

    public Map<Integer, Integer> collectGarbage(Collection<Integer> symbolTableValues, Map<Integer, Integer> heap){
        return heap.entrySet().stream().filter(e->symbolTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


}




