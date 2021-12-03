package terminal;

import java.util.ArrayList;

public class member_data
{
    private int ID;
    private int zip;
    private int consults;
    private boolean status;
    private String num;
    private String member_name;
    private String street_name;
    private String city;
    private String state;


    private  ArrayList<member_record> member_records_list;


    public ArrayList<member_record> getMember_records_list()
    {
        return member_records_list;
    }

    public member_data()
    {
        //consults = 0;
        //member_records_list = new ArrayList<>();
    }

    public member_data(int ID, int zip, int consults, boolean status, String num, String member_name, String street_name, String city, String state)
    {
        this.ID = ID;
        this.zip = zip;
        this.consults = consults;
        this.status = status;
        this.num = num;
        this.member_name = member_name;
        this.street_name = street_name;
        this.city = city;
        this.state = state;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public void setZip(int zip)
    {
        this.zip = zip;
    }

    public void setConsults(int consults)
    {
        this.consults = consults;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public void setNum(String num)
    {
        this.num = num;
    }

    public void setMember_name(String member_name)
    {
        this.member_name = member_name;
    }

    public void setStreet_name(String street_name)
    {
        this.street_name = street_name;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setState(String state)
    {
        this.state = state;
    }


    public int getID()
    {
        return ID;
    }

    public int getZip()
    {
        return zip;
    }

    public int getConsults()
    {
        return consults;
    }

    public boolean isStatus()
    {
        return status;
    }

    public String getNum()
    {
        return num;
    }

    public String getMember_name()
    {
        return member_name;
    }

    public String getStreet_name()
    {
        return street_name;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }



    public void add_report(String date, String provider_name, String service_name)
    {
        if(member_records_list == null)
        {
            member_records_list = new ArrayList<>();
        }
        member_record mr = new member_record(date,provider_name,service_name);
        member_records_list.add(mr);
        //System.out.println(member_records_list.get(0).getDate());
    }

    public void print_report()
    {
        int i = 0;
        for(member_record mr : member_records_list)
        {
            System.out.println(++i);
            System.out.println(mr.getDate());
            System.out.println(mr.getProvider_name());
            System.out.println(mr.getService_name());
            System.out.println();
        }
    }



}
