package Model;

import java.util.ArrayList;

public class Repository implements RepositoryInterface {
    private ArrayList<ProgramState> arrayList;

    public Repository()    {
        arrayList = new ArrayList<ProgramState>();
    }

    public void addProgramState(ProgramState programState)    {
        arrayList.add(programState);
    }

    @Override
    public ProgramState getCurrentProgramState() {
         return arrayList.get(0);
    }
}
