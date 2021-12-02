package terminal;

public class directory_data
{
    private int service_code;
    private float service_fee;
    private String service_name;

    public directory_data()
    {
    }

    public directory_data(int service_code, float service_fee, String service_name)
    {
        this.service_code = service_code;
        this.service_fee = service_fee;
        this.service_name = service_name;
    }

    public int getService_code()
    {
        return service_code;
    }

    public float getService_fee()
    {
        return service_fee;
    }

    public String getService_name()
    {
        return service_name;
    }

    public void setService_code(int service_code)
    {
        this.service_code = service_code;
    }

    public void setService_fee(float service_fee)
    {
        this.service_fee = service_fee;
    }

    public void setService_name(String service_name)
    {
        this.service_name = service_name;
    }
}
