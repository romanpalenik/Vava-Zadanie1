package Model;

public class Client {

    private String name;
    private String address;
    private String PSC;
    private String city;


    public Client(String name, String address, String PSC, String city) {
        this.name = name;
        this.address = address;
        this.PSC = PSC;
        this.city = city;
    }




    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Client(Client client) {
        this.name = client.getName();
        this.address = client.getAddress();
        this.PSC = client.getPSC();
        this.city = client.getCity();

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPSC(String PSC) {
        this.PSC = PSC;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPSC() {
        return PSC;
    }
}
