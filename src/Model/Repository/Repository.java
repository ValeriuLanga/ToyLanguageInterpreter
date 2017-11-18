package Model.Repository;

import Model.ProgramState;
import sun.rmi.runtime.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Repository implements RepositoryInterface {
    private ArrayList<ProgramState> arrayList;
    private String LogFilePath;


    public Repository(String FilePath)    {
        arrayList = new ArrayList<ProgramState>();
        LogFilePath = FilePath;
    }

    public void addProgramState(ProgramState programState)    {
        arrayList.add(programState);
    }

    @Override
    public ProgramState getCurrentProgramState() {
         return arrayList.get(0);
    }

    @Override
    public void LogProgramState() throws IOException{
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(LogFilePath, true)));
        printWriter.print("\n");
        printWriter.print(getCurrentProgramState());
        printWriter.close();
    }

    @Override
    public void setFileName( String fileName ){
        this.LogFilePath = fileName;
    }
}
