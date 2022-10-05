import com.simple.bank.app.dbutils.DBUtil;
import com.simple.bank.app.dbutils.DBoperations;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Connection conn = DBUtil.getConnection();
        UI(conn);
        conn.close();
    }
    public static void create_customer(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type customer id : ");
        int id = scanner.nextInt();
        System.out.print("Type customer name : ");
        String name = scanner.next();
        System.out.print("Type customer money : ");
        float money = scanner.nextFloat();
        System.out.print("Type customer IBAN : ");
        int IBAN = scanner.nextInt();
        DBoperations.create_new_customer(connection,id,name,money,IBAN);

    }
    public static void update_customer_money(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type customer id : ");
        int id = scanner.nextInt();
        System.out.print("Type customer money : ");
        float money = scanner.nextFloat();
        DBoperations.update_customer_money_by_id(connection,id,money);
    }
    public static void delete_customer(Connection connection) throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type customer id : ");
        int id = scanner.nextInt();
        DBoperations.delete_customer_by_id(connection,id);
    }
    public static void get_customer(Connection connection) throws  SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type customer id : ");
        int id = scanner.nextInt();
        DBoperations.get_customer_by_id(connection,id);
    }
    public static void transfer_money(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type your id : ");
        int id1 =scanner.nextInt();
        System.out.print("Type money amount to transfer : ");
        float amount = scanner.nextFloat();
        System.out.print("Type the IBAN you want to transfer the money : ");
        int IBAN=scanner.nextInt();
        DBoperations.transfer_money_by_IBAN(connection,id1, IBAN, amount);
    }
    public static void UI(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("PRESS C TO CREATE NEW CUSTOMER");
        System.out.println("PRESS U TO UPDATE A CUSTOMER");
        System.out.println("PRESS D TO DELETE A CUSTOMER");
        System.out.println("PRESS G TO GET CUSTOMER INFO");
        System.out.println("PRESS T TO TRANSFER MONEY");
        System.out.println("PRESS Q TO QUIT");
        System.out.println("********************************************");
        while (true)
        {
            System.out.println("********************************************");
            System.out.println("Type the operation you want to do : ");
            String operation = scanner.next();
            if (operation.equals("Q"))
            {
                break;
            }
            else
            {
                switch (operation) {
                    case "C" -> create_customer(connection);
                    case "U" -> update_customer_money(connection);
                    case "D" -> delete_customer(connection);
                    case "G" -> get_customer(connection);
                    case "T" -> transfer_money(connection);
                    default -> System.out.println("Type an appropriate operation !");
                }
            }

        }
    }
}
