package terminal;

public class member_record
{
    private String date;
    private String provider_name;
    private String service_name;

    public member_record()
    {
    }

    public member_record(String date, String provider_name, String service_name)
    {
        this.date = date;
        this.provider_name = provider_name;
        this.service_name = service_name;
    }

    public String getDate()
    {
        return date;
    }

    public String getProvider_name()
    {
        return provider_name;
    }

    public String getService_name()
    {
        return service_name;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setProvider_name(String provider_name)
    {
        this.provider_name = provider_name;
    }

    public void setService_name(String service_name)
    {
        this.service_name = service_name;
    }
}
