package terminal;

import java.io.IOException;
import java.util.Scanner;

public class manager_main
{
    public void m_main(database d)
    {
        System.out.println("You are in the Manager's Terminal");
        int again = 1;

        do {
            int menu = manager_menu();
            if (menu == 1)
            {
                System.out.println
                        ("You are in Member Management");
                int choice = manage_member();

                if (choice == 1)
                {
                    System.out.println
                            ("You are trying to add a member");
                    d.add_member();
                }
                else if (choice == 2) {
                    System.out.println
                            ("You are trying to delete a member");
                    d.remove_member();
                }
                else if (choice == 3)
                {
                    System.out.println
                            ("You are trying to update a member");
                    d.update_member();
                }
                else if (choice == 4)
                {
                    System.out.println
                            ("You are trying to print a member's record");
                    d.print_members();
                }
                else return;
            }

            if (menu == 2)
            {
                System.out.println
                        ("You are in Provider Management");
                int choice = manage_provider();

                if (choice == 1)
                {
                    System.out.println
                            ("You are trying to add a provider");
                    d.add_provider();
                }
                else if (choice == 2) {
                    System.out.println
                            ("You are trying to delete a provider");
                    d.remove_provider();
                }
                else if (choice == 3) {
                    System.out.println
                            ("You are trying to update a provider");
                    d.update_provider();
                }
                else if (choice == 4) {
                    System.out.println
                            ("You are trying to print a provider's record");
                    d.print_providers();
                }
                else return;
            }
            else if (menu == 3) return;

            System.out.println("Would you like to manager another? ");
            System.out.println("1-Yes / 2-No :  ");
            do {
                Scanner input = new Scanner(System.in);
                String answer = input.nextLine();
                int parseInt = Integer.parseInt(answer);
                again = parseInt;

                if (again != 1 && again != 2) {
                    System.out.println("Invalid input. Please try again");
                }
                // else if (again == 1) manager_main();
            } while (again != 1 && again != 2);

        }while (again == 1);


        return;
    }

    public static int manager_menu(){
        int choice;
        System.out.println("======================");
        System.out.println("Acme Manager's Menu");
        System.out.println("1. Manage member");
        System.out.println("2. Manager provider");
        System.out.println("3. Quit");
        System.out.println("======================");
        System.out.println("What would you like to do? ");
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        int parseInt = Integer.parseInt(answer);
        choice = parseInt;


        if (   choice != 1 && choice != 2 && choice != 3) {
            System.out.println("Invalid input. Please try again");
            return manager_menu();
        }
        return choice;
    }

    public int manage_member(){
        int choice;
        System.out.println("======================");
        System.out.println("Member's Management");
        System.out.println("1. Add a new member");
        System.out.println("2. Delete a member");
        System.out.println("3. Update a member");
        System.out.println("4. Print a member's record");
        System.out.println("5. Exit");
        System.out.println("======================");
        System.out.println("What would you like to do? ");
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        int parseInt = Integer.parseInt(answer);
        choice = parseInt;


        if (   choice != 1 && choice != 2
                && choice != 3 && choice != 4 && choice != 5) {
            System.out.println("Invalid input. Please try again");
            manage_member();
        }
        return choice;
    }

    public int manage_provider(){
        int choice;
        System.out.println("======================");
        System.out.println("Provider's Management");
        System.out.println("1. Add a new provider");
        System.out.println("2. Delete a provider");
        System.out.println("3. Update a provider");
        System.out.println("4. Print a provider's record");
        System.out.println("5. Exit");
        System.out.println("======================");
        System.out.println("What would you like to do? ");
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        int parseInt = Integer.parseInt(answer);
        choice = parseInt;


        if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5) {
            System.out.println("Invalid input. Please try again");
            manage_provider();
        }
        return choice;
    }
}

