package terminal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main
{
    public static void main(String[] args) throws IOException
    {

        database d = new database();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("======================");
            System.out.println("Acme Main Terminal");
            System.out.println("1. Provider's Terminal");
            System.out.println("2. Manager's Terminal");
            System.out.println("3. Exit");
            System.out.println("======================");
            System.out.println("What would you like to do? ");
            choice = sc.nextInt();
            if (choice != 1 && choice != 2 && choice != 3)
                System.out.println("Invalid input. Please try again");
            if (choice == 1)
            {
                provider_main pm = new provider_main();
                pm.p_main(d);
            }
            else if (choice == 2)
            {
                manager_main mm = new manager_main();
                mm.m_main(d);
            }


        } while (choice != 3);

        //d.lol();
        d.member_record_writer();
        d.shut();


    }


}
