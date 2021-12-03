package terminal;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class database
{

    private static ArrayList<provider_data> provider_list;
    private static ArrayList<member_data> member_list;
    private ArrayList<directory_data> directory_list;


    public database()
    {
        boot();
    }

    public void boot()       //remember to boot() before use the database
    {
        provider_list = new ArrayList<>();
        member_list = new ArrayList<>();
        directory_list = new ArrayList<>();

        provider_reader();
        member_reader();
        pd_reader();
    }

    public void shut() throws IOException    //remember to shut() after use the database
    {
        provider_writer();
        member_writer();
        //pd_writer();

        //member_record_writer();

        //print_providers();
    }


    public void bill_choc(provider_data pd)
    {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        int id = 0;
        String answer;

        do
        {
            System.out.println("Please enter the member's ID.");
            answer = input.nextLine();
            id = Integer.parseInt(answer);
            int status = member_verification(id);
            if (status != 1)
            {
                if (status == 0)
                    System.out.println("The member is suspended.");
                else
                    System.out.println("The member is not valid.");
                System.out.println("Enter 1 to enter the member ID again, enter 2 to quit this mode.");
                answer = input.nextLine();
                choice = Integer.parseInt(answer);
            } else
            {
                System.out.println("The member is valid.");
                break;
            }
        } while (choice != 2);
        if (choice == 2)
            return;

        member_data md = find_member(id);

        System.out.println("Please enter the date of service provided(MMDDYYYY)");
        answer = input.nextLine();
        //int date = Integer.parseInt(answer);
        String date = answer;
        System.out.println("Type 1 to show the Provider's directory. Type 2 to input the service code directly.");
        answer = input.nextLine();
        choice = Integer.parseInt(answer);
        if (choice == 1)
            print_directory();
        float fee = 0;
        do
        {
            System.out.println("Please input the 6-digit service code.");
            answer = input.nextLine();
            int s_code = Integer.parseInt(answer);
            fee = search_code(s_code);
            String service_name = search_sname(s_code);
            if (fee == 0)
            {
                System.out.println("Do you want to enter the service code again? Type 1 to enter the code again, type 2 to quit this mode.");
                answer = input.nextLine();
                choice = Integer.parseInt(answer);
                if (choice != 1)
                    return;
            } else
            {
                choice = 0;
            }
            //md.add_report(date, provider_name, service_name);////////////////////
            //member_record mr = new member_record(date, provider_name, service_name);


            for(member_data m_data :member_list)
            {
                if(id == m_data.getID())
                {
                   // m_date.setMember_records_list();
                    //m_date.getMember_records_list().add(mr);
                    md.add_report(date, pd.getProvider_name(), service_name);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();

                    pd.add_report(date,now.toString(), md.getMember_name(), Integer.toString(md.getID()),Integer.toString(s_code),Float.toString(fee));
                }
            }
        } while (choice == 1);





        //md.getMember_records_list().get(0).getDate();
        //md.print_report();
        /* Write Provider Report to external file
        write_p_report(p_data, dtf.format(now), date, code, comments);//Or use sth like report_data
        Write Member Report to external file
        write_m_report();//Like above
         */
        System.out.println("This is the fee for this service: " + fee);
        return;
    }


    public void provider_reader()
    {
        File file = new File("src//inputFiles//provider_list.txt");
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int count = 0;
            String[] a_provider = new String[7];
            while ((tempString = reader.readLine()) != null)
            {
                a_provider[count] = tempString;
                count++;
                if (count == 7)
                {
                    provider_data pd = new provider_data();
                    pd.setNum(a_provider[0]);
                    pd.setProvider_name(a_provider[1]);
                    pd.setID(Integer.parseInt(a_provider[2]));
                    pd.setStreet_name(a_provider[3]);
                    pd.setCity(a_provider[4]);
                    pd.setState(a_provider[5]);
                    pd.setZip(Integer.parseInt(a_provider[6]));

                    provider_list.add(pd);

                    count = 0;
                }
            }
            reader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                } catch (IOException e1)
                {
                }
            }
        }
    }

    public void provider_writer() throws IOException
    {
        FileWriter fw = new FileWriter("src//inputFiles//provider_list.txt");

        BufferedWriter writer = new BufferedWriter(fw);
        int index = 0;
        int order = 1;
        String num;
        for (provider_data pd : provider_list)
        {
            num = Integer.toString(order);
            num += ")";
            writer.write(num);
            writer.newLine();
            writer.write(pd.getProvider_name());
            writer.newLine();
            writer.write(Integer.toString(pd.getID()));
            writer.newLine();
            writer.write(pd.getStreet_name());
            writer.newLine();
            writer.write(pd.getCity());
            writer.newLine();
            writer.write(pd.getState());
            writer.newLine();
            writer.write(Integer.toString(pd.getZip()));
            if (index != provider_list.size() - 1)
            {
                writer.newLine();
            }
            writer.flush();
            index++;
            order++;
        }
        //writer.flush();
        writer.close();

    }

    public void add_provider()
    {
        Scanner sc = new Scanner(System.in);
        provider_data pd = new provider_data();
        String string_input;
        int integer_input;
        System.out.println("Enter provider's name: ");
        string_input = sc.next();
        pd.setProvider_name(string_input);
        System.out.println("Enter provider's ID: ");
        integer_input = sc.nextInt();
        pd.setID(integer_input);
        System.out.println("Enter street's name: ");
        string_input = sc.next();
        pd.setStreet_name(string_input);
        System.out.println("Enter city: ");
        string_input = sc.next();
        pd.setCity(string_input);
        System.out.println("Enter state: ");
        string_input = sc.next();
        pd.setState(string_input);
        System.out.println("Enter zip code: ");
        integer_input = sc.nextInt();
        pd.setZip(integer_input);

        provider_list.add(pd);
    }

    public void remove_provider()
    {
        Scanner sc = new Scanner(System.in);
        int ID;
        int index = 0;
        System.out.println("Enter the provider ID to remove: ");
        ID = sc.nextInt();
        for (provider_data pd : provider_list)
        {
            if (ID == pd.getID())
            {
                provider_list.remove(index);
                System.out.println("Removed");
                return;
            }
            index++;
        }
        System.out.println("ID not found");
        return;

    }

    public void update_provider()
    {
        Scanner sc = new Scanner(System.in);
        int ID;
        String choice;
        System.out.println("Enter the provider ID to update: ");
        ID = sc.nextInt();
        for (provider_data pd : provider_list)
        {
            if (ID == pd.getID())
            {
                System.out.println("provider name: " + pd.getProvider_name());
                System.out.println("provider ID: " + pd.getID());
                System.out.println("provider street name: " + pd.getStreet_name());
                System.out.println("provider city: " + pd.getCity());
                System.out.println("provider state: " + pd.getState());
                System.out.println("provider zip code: " + pd.getZip());
                System.out.println("Do you want to update the information of this provider?\n1: YES\n2: NO");
                choice = sc.next();
                if (choice.equals("1") || choice.equals("YES") || choice.equals("yes") || choice.equals("Yes") || choice.equals("y") || choice.equals("Y"))
                {
                    String string_input;
                    int integer_input;
                    System.out.println("Enter provider's name: ");
                    string_input = sc.next();
                    pd.setProvider_name(string_input);
                    System.out.println("Enter provider's ID: ");
                    integer_input = sc.nextInt();
                    pd.setID(integer_input);
                    System.out.println("Enter street's name: ");
                    string_input = sc.next();
                    pd.setStreet_name(string_input);
                    System.out.println("Enter city: ");
                    string_input = sc.next();
                    pd.setCity(string_input);
                    System.out.println("Enter state: ");
                    string_input = sc.next();
                    pd.setState(string_input);
                    System.out.println("Enter zip code: ");
                    integer_input = sc.nextInt();
                    pd.setZip(integer_input);
                    System.out.println("Updated");
                    return;
                }
                if (choice.equals("2") || choice.equals("NO") || choice.equals("No") || choice.equals("no") || !choice.equals("n") || choice.equals("N"))
                {
                    System.out.println("Error");
                }
                return;
            }
        }
        System.out.println("ID not found");
    }

    public provider_data find_provider(int ID)
    {
        for (provider_data pd : provider_list)
        {
            if (ID == pd.getID())
            {
                return pd;
            }
        }
        System.out.println("ID not found");
        return null;
    }

    public void print_providers()
    {
        int i = 1;
        for (provider_data pd : provider_list)
        {
            System.out.println(i++);
            System.out.println("provider name: " + pd.getProvider_name());
            System.out.println("provider ID: " + pd.getID());
            System.out.println("provider street name: " + pd.getStreet_name());
            System.out.println("provider city: " + pd.getCity());
            System.out.println("provider state: " + pd.getState());
            System.out.println("provider zip code: " + pd.getZip());
            System.out.println();
        }
    }


    public String check_status(boolean status)
    {
        if (status)
            return "active";
        else
            return "suspended";
    }

    public void member_record_reader()
    {
        for(member_data md:member_list)
        {
            String path = md.getMember_name() + ".txt";
            File filename = new File(path);
            if (!filename.exists())
            {
                continue;
            }
            else
            {

            }
            if(md.getMember_records_list() != null)
            {
                for (int i = 0; i < md.getMember_records_list().size(); i++)
                {
                    System.out.println(md.getMember_records_list().get(i).getProvider_name());
                }
            }
        }

    }

    public void member_record_writer(File f,member_data md) throws IOException
    {
        /*
        FileWriter fw = new FileWriter(f);

        BufferedWriter writer = new BufferedWriter(fw);


        writer.write(md.getMember_name());
        writer.newLine();
        writer.write(Integer.toString(md.getID()));
        writer.newLine();
        writer.write(md.getStreet_name());
        writer.newLine();
        writer.write(md.getCity());
        writer.newLine();
        writer.write(md.getState());
        writer.newLine();
        writer.write(Integer.toString(md.getZip()));
        writer.newLine();



        int index = 0;

        for (member_record mr : md.getMember_records_list())
        {

            writer.write(mr.getDate());
            writer.newLine();
            writer.write(mr.getProvider_name());
            writer.newLine();
            writer.write(mr.getService_name());
            if (index != member_list.size() - 1)
            {
                writer.newLine();
            }
            writer.flush();
            index++;
        }
        writer.close();

         */
        try (FileWriter fw = new FileWriter(f, true))
        {

            for (member_record mr : md.getMember_records_list())
            {
                fw.append("\n");

                fw.append(mr.getDate()+"\n");

                fw.append(mr.getProvider_name()+"\n");

                fw.append(mr.getService_name());

            }

        }


    }




    public void member_record_writer() throws IOException
    {
        label:
        for (member_data md : member_list)
        {
            if (md.getMember_records_list() != null)
            {
                String directory = "src\\reports\\members\\";
                String target_file = "src\\reports\\members\\" + md.getMember_name() + ".txt";

                File files = new File(directory);
                File[] fs_list = files.listFiles();

                String file_toCompare;

                for(File f:fs_list)
                {
                    if(!f.isDirectory())
                    {
                        file_toCompare = f.toString();

                        file_toCompare = file_toCompare.replaceAll("\\d+","");
                        System.out.println(file_toCompare+"\t"+target_file);
                        if(file_toCompare.equals(target_file))
                        {
                            member_record_writer(f,md);
                            continue label;
                        }
                    }
                }


                String path = "src\\reports\\members\\" + md.getMember_name() + md.getMember_records_list().get(0).getDate() + ".txt";
                File filename = new File(path);

                filename.createNewFile();


                FileWriter fw = new FileWriter(path);

                BufferedWriter writer = new BufferedWriter(fw);

                writer.write(md.getMember_name());
                writer.newLine();
                writer.write(Integer.toString(md.getID()));
                writer.newLine();
                writer.write(md.getStreet_name());
                writer.newLine();
                writer.write(md.getCity());
                writer.newLine();
                writer.write(md.getState());
                writer.newLine();
                writer.write(Integer.toString(md.getZip()));
                writer.newLine();

                int index = 0;

                for (member_record mr : md.getMember_records_list())
                {

                    writer.write(mr.getDate());
                    writer.newLine();
                    writer.write(mr.getProvider_name());
                    writer.newLine();
                    writer.write(mr.getService_name());
                    if (index != member_list.size() - 1)
                    {
                        writer.newLine();
                    }
                    writer.flush();
                    index++;
                }
                writer.close();
            }
        }
    }


    public void member_reader()
    {
        File file = new File("src//inputFiles//member_list.txt");
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int count = 0;
            String[] a_member = new String[8];
            while ((tempString = reader.readLine()) != null)
            {
                a_member[count] = tempString;
                count++;
                if (count == 8)
                {
                    member_data md = new member_data();
                    md.setNum(a_member[0]);
                    md.setMember_name(a_member[1]);
                    md.setID(Integer.parseInt(a_member[2]));
                    md.setStreet_name(a_member[3]);
                    md.setCity(a_member[4]);
                    md.setState(a_member[5]);
                    md.setZip(Integer.parseInt(a_member[6]));
                    if (a_member[7].equals("active"))
                        md.setStatus(true);
                    else
                        md.setStatus(false);
                    member_list.add(md);
                    count = 0;
                }
            }
            reader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                } catch (IOException e1)
                {
                }
            }
        }
    }

    public void member_writer() throws IOException
    {
        FileWriter fw = new FileWriter("src//inputFiles//member_list.txt");

        BufferedWriter writer = new BufferedWriter(fw);
        int order = 1;
        int index = 0;
        String num;
        for (member_data md : member_list)
        {
            num = Integer.toString(order);
            num += ")";
            writer.write(num);
            writer.newLine();
            writer.write(md.getMember_name());
            writer.newLine();
            writer.write(Integer.toString(md.getID()));
            writer.newLine();
            writer.write(md.getStreet_name());
            writer.newLine();
            writer.write(md.getCity());
            writer.newLine();
            writer.write(md.getState());
            writer.newLine();
            writer.write(Integer.toString(md.getZip()));
            writer.newLine();
            writer.write(check_status(md.isStatus()));
            if (index != member_list.size() - 1)
            {
                writer.newLine();
            }
            writer.flush();
            index++;
            order++;
        }
        writer.close();

    }

    public void print_members()
    {
        String status;
        int i = 1;
        for (member_data md : member_list)
        {
            if (md.getMember_records_list() != null)
            {
                System.out.println("lol");
            }
            System.out.println(i++);
            System.out.println("member name: " + md.getMember_name());
            System.out.println("member ID: " + md.getID());
            System.out.println("member street name: " + md.getStreet_name());
            System.out.println("member city: " + md.getCity());
            System.out.println("member state: " + md.getState());
            System.out.println("member zip code: " + md.getZip());
            System.out.println("member status: " + check_status(md.isStatus()));
            System.out.println();
        }
    }


    public void add_member()
    {
        Scanner sc = new Scanner(System.in);
        member_data md = new member_data();
        String string_input;
        int integer_input;
        System.out.println("Enter member's name: ");
        string_input = sc.next();
        md.setMember_name(string_input);
        System.out.println("Enter member's ID: ");
        integer_input = sc.nextInt();
        md.setID(integer_input);
        System.out.println("Enter member's name: ");
        string_input = sc.next();
        md.setStreet_name(string_input);
        System.out.println("Enter city: ");
        string_input = sc.next();
        md.setCity(string_input);
        System.out.println("Enter state: ");
        string_input = sc.next();
        md.setState(string_input);
        System.out.println("Enter zip code: ");
        integer_input = sc.nextInt();
        md.setZip(integer_input);
        System.out.println("Enter status: ");
        string_input = sc.next();
        if (string_input.equals("active"))
            md.setStatus(true);
        else if (string_input.equals("suspended"))
            md.setStatus(false);
        else
        {
            md.setStatus(true);
            System.out.println("error, set status to active. May need to update");
        }

        member_list.add(md);
    }


    public void remove_member()
    {
        Scanner sc = new Scanner(System.in);
        int ID;
        int index = 0;
        System.out.println("Enter the member ID to remove: ");
        ID = sc.nextInt();
        for (member_data md : member_list)
        {
            if (ID == md.getID())
            {
                member_list.remove(index);
                System.out.println("Removed");
                return;
            }
            index++;
        }
        System.out.println("ID not found");
        return;

    }

    public void update_member()
    {
        Scanner sc = new Scanner(System.in);
        int ID;
        String choice;
        System.out.println("Enter the member ID to update: ");
        ID = sc.nextInt();
        for (member_data md : member_list)
        {
            if (ID == md.getID())
            {
                System.out.println("provider name: " + md.getMember_name());
                System.out.println("provider ID: " + md.getID());
                System.out.println("provider street name: " + md.getStreet_name());
                System.out.println("provider city: " + md.getCity());
                System.out.println("provider state: " + md.getState());
                System.out.println("provider zip code: " + md.getZip());
                System.out.println("Do you want to update the information of this member?\n1: YES\n2: NO");
                choice = sc.next();
                if (choice.equals("1") || choice.equals("YES") || choice.equals("yes") || choice.equals("Yes") || choice.equals("y") || choice.equals("Y"))
                {
                    String string_input;
                    int integer_input;
                    System.out.println("Enter member's name: ");
                    string_input = sc.next();
                    md.setMember_name(string_input);
                    System.out.println("Enter member's ID: ");
                    integer_input = sc.nextInt();
                    md.setID(integer_input);
                    System.out.println("Enter street's name: ");
                    string_input = sc.next();
                    md.setStreet_name(string_input);
                    System.out.println("Enter city: ");
                    string_input = sc.next();
                    md.setCity(string_input);
                    System.out.println("Enter state: ");
                    string_input = sc.next();
                    md.setState(string_input);
                    System.out.println("Enter zip code: ");
                    integer_input = sc.nextInt();
                    md.setZip(integer_input);
                    System.out.println("Enter status: ");
                    string_input = sc.next();
                    if (string_input.equals("active"))
                        md.setStatus(true);
                    else if (string_input.equals("suspended"))
                        md.setStatus(false);
                    else
                    {
                        md.setStatus(true);
                        System.out.println("error, set status to active. May need to update again");
                        return;
                    }
                    System.out.println("Updated");

                    return;
                }
                if (choice.equals("2") || choice.equals("NO") || choice.equals("No") || choice.equals("no") || choice.equals("n") || choice.equals("N"))
                {
                    return;
                } else
                {
                    System.out.println("Error");
                    return;
                }
            }
        }
        System.out.println("ID not found");
    }

    public member_data find_member(int ID)
    {
        for (member_data md : member_list)
        {
            if (ID == md.getID())
            {
                return md;
            }
        }
        System.out.println("ID not found");
        return null;
    }

    public int member_verification(int ID)   //1: active; 0: suspended; -1: invalid
    {
        for (member_data md : member_list)
        {
            if (ID == md.getID())
            {
                if (md.isStatus())
                    return 1;
                else
                    return 0;
            }
        }
        return -1;
    }



























    public void pd_reader()
    {
        File file = new File("src/inputFiles/provider_directory.txt");
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int count = 0;
            String[] a_service = new String[4];
            while ((tempString = reader.readLine()) != null)
            {
                a_service[count] = tempString;
                count++;
                if (count == 4)
                {
                    directory_data pd = new directory_data();
                    pd.setService_name(a_service[1]);
                    pd.setService_fee(Float.parseFloat(a_service[2]));
                    pd.setService_code(Integer.parseInt(a_service[3]));
                    directory_list.add(pd);
                    count = 0;
                }
            }
            reader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                } catch (IOException e1)
                {
                }
            }
        }
    }






    public void print_directory()
    {
        int i = 1;
        for (directory_data dd : directory_list)
        {
            System.out.println(i++);
            System.out.println("service name: " + dd.getService_name());
            System.out.println("service code: " + dd.getService_code());
            System.out.println("service fee: " + dd.getService_fee());
            System.out.println();
        }
    }

    public float search_code(int code)
    {
        Scanner sc = new Scanner(System.in);
        String choice;
        for (directory_data dd : directory_list)
        {
            if (code == dd.getService_code())
            {
                System.out.println("service name: " + dd.getService_name());
                System.out.println("service code: " + dd.getService_code());
                System.out.println("service fee: " + dd.getService_fee());
                System.out.println("Correct?\n1: YES\n2: NO");
                choice = sc.next();
                if (choice.equals("1") || choice.equals("YES") || choice.equals("yes") || choice.equals("Yes") || choice.equals("y") || choice.equals("Y"))
                {
                    return dd.getService_fee();
                }
                if (choice.equals("2") || choice.equals("NO") || choice.equals("No") || choice.equals("no") || choice.equals("n") || choice.equals("N"))
                {
                    return 0;
                } else
                {
                    System.out.println("Error");
                    return 0;
                }
            }
        }
        System.out.println("code not found");
        return 0;
    }

    public String search_sname(int code)
    {
        for (directory_data dd : directory_list)
        {
            if (code == dd.getService_code())
            {
                return dd.getService_name();
            }
        }
        return null;
    }




