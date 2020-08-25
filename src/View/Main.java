package View;

import Controller.Luhn;
import Model.Person;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Message message = new Message();
        Luhn l = new Luhn();
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while(running) {
            try {
                message.print("Please enter Social security number");
                String ssn = input.nextLine();
                Person p = new Person(ssn);
                l.checkSsn(p.getSsn());
            } catch (Exception e) {
                message.print("Ett fel inträffade!");
                message.print(e.getMessage());
            }
        }




    }
}
