package View;

import java.io.IOException;
import java.util.Scanner;

public class UI {

    static int askWhichProgram() throws IOException {
        System.out.println("Select a program from the following:\n" +
                "\t1  -  int v; v=2; Print(v)\n" +
                "\t2  -  int a; int b; a=2+3*5; b=a+1; Print(b)\n" +
                "\t3  -  bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)\n" +
                "\t0  -  cancel");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    static int askMenuOption() throws IOException {
        System.out.println("Choose an option:\n" +
                "\t1 - input a program\n" +
                "\t2 - complete execution of a program\n" +
                "\t0 - exit");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

}
