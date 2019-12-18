package View;

import Controller.Controller;

public class RunExample extends Command {
    private Controller ctr;

    RunExample(String key, String desc, Controller ctr) {
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute() {
        try {
            ctr.allStep();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}