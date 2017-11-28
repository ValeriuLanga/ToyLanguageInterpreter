package Controller;

import Model.*;
import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.FileException;
import Model.Exceptions.HeapException;
import Model.Exceptions.UnknownOperationException;
import Model.FileTable.FileTableInterface;
import Model.Repository.RepositoryInterface;
import Model.Statements.Statement;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.Buffer;
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

    public void executeOnce() throws DivisionByZeroException, UnknownOperationException, IOException, HeapException {
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

    public void executeAll() //throws DivisionByZeroException, UnknownOperationException, IOException
    {
        ProgramState programState = repository.getCurrentProgramState();

        while(!programState.getExecutionStack().isEmpty()){

            try {
                executeOnce();

                programState.getHeap().setUnderlyingMap((HashMap<Integer, Integer>)
                        collectGarbage(programState.getSymbolTable().getUnderlyingMap().values(),
                                programState.getHeap().getUnderlyingMap()));
            }
            catch(DivisionByZeroException | UnknownOperationException | IOException | FileException | HeapException exception) {
                System.out.println(exception.getMessage());

                // close any open files, so no handles are left open after the
                // program terminates in case of an exception

                closeFileDescriptors(programState.getFileTable().getUnderlayingContainer());

                System.exit(0);
            }
        }

        // clean the heap after finishing
        /*
        programState.getHeap().setUnderlyingMap((HashMap<Integer, Integer>)
                collectGarbage(programState.getSymbolTable().getUnderlyingMap().values(),
                        programState.getHeap().getUnderlyingMap()));
        */
    }

    public Map<Integer, Integer> collectGarbage(Collection<Integer> symbolTableValues, Map<Integer, Integer> heap){
        return heap.entrySet().stream().filter(e->symbolTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void closeFileDescriptors(Map<Integer, Model.FileTable.FileDescriptor> fileTableMap){
        fileTableMap.forEach((Integer key, Model.FileTable.FileDescriptor value) -> {
            try {
                value.getBufferedReader().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}




