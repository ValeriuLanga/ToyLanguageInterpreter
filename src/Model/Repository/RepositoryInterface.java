package Model.Repository;

import Model.ProgramState;

import java.io.IOException;

public interface RepositoryInterface {
    public void addProgramState(ProgramState programState);
    public ProgramState getCurrentProgramState();
    public void LogProgramState()throws IOException;
    public void setFileName( String fileName );
}
