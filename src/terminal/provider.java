package terminal;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class provider {

    private provider_data p_data;
    private database d_base;

    public provider()
    {
        d_base = new database();
        d_base.boot();
    }

    public void provider_main() {
        System.out.println("You are in the Provider's Terminal, please enter your Provider ID.");
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        int id = Integer.parseInt(answer);
        p_data = d_base.find_provider(id);
        if(p_data == null)
        {
            System.out.println("Your Provider ID is invalid.");
            return;
        }

        int choice;
        do {
            choice = provider_menu();
            if (choice == 1) {
                System.out.println
                        ("You are trying to print Provider's Directory");
                print_pd();
            }
            else if (choice == 2) {
                System.out.println
                        ("You are trying to bill ChocAn for a service");
                bill_choc();
            }
            else if (choice == 3) {
                System.out.println
                        ("You are trying to print Provider's Report");
                //print_p_report();
            }

        } while (choice != 4);

        try {
            d_base.shut();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public static int provider_menu(){
        int choice;
        System.out.println("======================");
        System.out.println("Health Provider's Menu");
        System.out.println("1. Print Provider's Directory");
        System.out.println("2. Bill ChocAn for a service");
        System.out.println("3. Print Provider's Report");
        System.out.println("4. Quit");
        System.out.println("======================");
        System.out.println("What would you like to do? ");
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        choice = Integer.parseInt(answer);


        if (   choice != 1 && choice != 2
                && choice != 3 && choice != 4) {
            System.out.println("Invalid input. Please try again");
            return provider_menu();
        }
        return choice;
    }

    public void print_pd()
    {
        d_base.print_directory();
    }

    public void bill_choc()
    {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        int id = 0;
        String answer;
        member_data m_data;
        do
        {
            System.out.println("Please enter the member's ID.");
            answer = input.nextLine();
            id = Integer.parseInt(answer);
            int status = d_base.member_verification(id);
            if(status != 1)
            {
                if(status == 0)
                    System.out.println("The member is suspended.");
                else
                    System.out.println("The member is not valid.");
                 System.out.println("Enter 1 to enter the member ID again, enter 2 to quit this mode.");
                 answer = input.nextLine();
                 choice = Integer.parseInt(answer);
            }
            else
            {
                System.out.println("The member is valid.");
                break;
            }
        }while(choice != 2);
        if(choice == 2)
            return;

        m_data = d_base.find_member(id);

        System.out.println("Please enter the date of service provided(MMDDYYYY)");
        answer = input.nextLine();
        int date = Integer.parseInt(answer);
        System.out.println("Type 1 to show the Provider's directory. Type 2 to input the service code directly.");
        answer = input.nextLine();
        choice = Integer.parseInt(answer);
        if(choice == 1)
            d_base.print_directory();
        float fee = 0;
        do
        {
            System.out.println("Please input the 6-digit service code.");
            answer = input.nextLine();
            int s_code = Integer.parseInt(answer);
            fee = d_base.search_code(s_code);
            if(fee == 0)
            {
                System.out.println("Do you want to enter the service code again? Type 1 to enter the code again, type 2 to quit this mode.");
                answer = input.nextLine();
                choice = Integer.parseInt(answer);
                if(choice != 1)
                    return;
            }
            else
            {
                choice = 0;
            }
        }while(choice == 1);
        System.out.println("Please input the the comments about the service provided.");
        String comment = input.nextLine();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        /* Write Provider Report to external file
        write_p_report(p_data, dtf.format(now), date, code, comments);//Or use sth like report_data
        Write Member Report to external file
        write_m_report();//Like above
         */
        System.out.println("This is the fee for this service: " + fee);
        return;
    }

    /*print Provider Report for this provider
    public void print_p_report()
    {
    }
     */


}

