package View;

import Controller.Controller;
import Model.Exceptions.DivisionByZeroException;
import Model.Exceptions.FileException;
import Model.Exceptions.UnknownOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunExample extends Command{
    private Controller controller;

    public RunExample(String option, String description, Controller controller)
    {
        super(option, description);
        this.controller = controller;
    }

    @Override
    public void execute()
    {
        try{
            controller.executeAll();
        }
        catch(DivisionByZeroException | UnknownOperationException | IOException | FileException exception) {
            System.out.println(exception.getMessage());
            System.exit(0);
        }
    }

    @Override
    public String toString(){
        return "Run All : " + super.toString();
    }
}
