public class Customer {
    private int id;
    private String name;
    private String money;
    private int iban;

    public Customer(int id, String name, String money, int iban) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.iban = iban;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getIban() {
        return iban;
    }

    public void setIban(int iban) {
        this.iban = iban;
    }





}
