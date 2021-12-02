package terminal;

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



    public member_data()
    {
        consults = 0;
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

    /*
    public void print_personal_report()
    {
        if(consults == 0)
            return;

    }
    
     */
}
