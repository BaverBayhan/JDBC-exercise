package com.simple.bank.app.dbutils;

import java.sql.*;

public class DBoperations {
    public static void create_new_customer(Connection connection, int id, String name, float money, int IBAN) throws SQLException {
        String SQL = "INSERT INTO customer(id,name,money_amount,IBAN) VALUES(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setInt(1,id);
        ps.setString(2,name);
        ps.setFloat(3,money);
        ps.setInt(4,IBAN);
        ps.executeUpdate();
        ps.close();
    }
    public static void update_customer_money_by_id(Connection connection, int id,float money) throws SQLException {
        String SQL = "UPDATE customer SET money_amount = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setFloat(1,money);
        ps.setInt(2,id);
        ps.executeUpdate();
        ps.close();
    }
    public static void delete_customer_by_id(Connection connection,int id) throws SQLException{
        String SQL = "DELETE FROM customer WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setInt(1,id);
        ps.executeUpdate();
        ps.close();
    }
    public static void get_customer_by_id(Connection connection,int id) throws SQLException{
        String SQL = "SELECT * FROM customer WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            System.out.println("id : "+rs.getInt(1)+"\n"+"name : "+rs.getString(2)+"\n"+ "money : "+rs.getFloat(3)+"\n"+"IBAN : "+rs.getInt(4)+"\t");

        }
    }
    public static void transfer_money_by_IBAN(Connection connection,int id_withdraw_account,int IBAN_deposit_account, float deposit_amount) throws SQLException{

        float money_of_first_cust=0;
        float money_of_second_cust=0;

        String SQL_to_get_money="SELECT money_amount FROM customer WHERE id = ?";

        PreparedStatement ps1 = connection.prepareStatement(SQL_to_get_money);
        ps1.setInt(1,id_withdraw_account);
        ResultSet rs_firs_cust = ps1.executeQuery();
        if(rs_firs_cust.next())
        {
            money_of_first_cust = rs_firs_cust.getInt("money_amount");
        }
        ps1.close();
        String SQL2_to_get_money="SELECT money_amount FROM customer WHERE IBAN = ?";
        PreparedStatement ps2 = connection.prepareStatement(SQL2_to_get_money);
        ps2.setInt(1,IBAN_deposit_account);
        ResultSet rs_second_cust = ps2.executeQuery();
        if(rs_second_cust.next())
        {
            money_of_second_cust = rs_second_cust.getInt("money_amount");
        }
        ps2.close();
        if(money_of_first_cust>deposit_amount)
        {
            String SQL3_withdraw_money = "UPDATE customer SET money_amount = ? WHERE id = ?";
            String SQL4_deposit_money = "UPDATE customer SET money_amount = ? WHERE IBAN = ?";
            PreparedStatement ps3 = connection.prepareStatement(SQL3_withdraw_money);
            PreparedStatement ps4 = connection.prepareStatement(SQL4_deposit_money);
            ps3.setFloat(1,money_of_first_cust-deposit_amount);
            ps3.setInt(2,id_withdraw_account);
            ps4.setFloat(1,money_of_second_cust+deposit_amount);
            ps4.setInt(2,IBAN_deposit_account);
            try
            {
                connection.setAutoCommit(false);
                ps3.executeUpdate();
                ps4.executeUpdate();
                connection.commit();
                System.out.println("Transfer operation executed successfully .");
            }
            catch (Exception e)
            {
                connection.rollback();
                System.out.println("transfer operation failed.");
            }
            ps3.close();
            ps4.close();
        }
        else
        {
            System.out.println("You don't have that much money in your account !");
        }
    }
}
