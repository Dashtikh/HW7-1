package com.company;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CarRepo implements AutoCloseable {
    //public class PersonRepo {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public CarRepo() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "Dashtikh", "dashti1565");
        connection.setAutoCommit(false);
    }

    public void insert(CarEnti carEnti) throws Exception {
        preparedStatement = connection.prepareStatement("INSERT INTO Saipa (model, name,price) VALUES (?,?,?)");
        preparedStatement.setInt(1, carEnti.getmodel());
        preparedStatement.setString(2, carEnti.getName());
        preparedStatement.setInt(3, carEnti.getPrice());
        preparedStatement.executeUpdate();
    }

    public void update(CarEnti personEnti) throws Exception {
        preparedStatement = connection.prepareStatement("UPDATE saipa SET name =?, price=?, WHERE model=? ");
        preparedStatement.setString(1, personEnti.getName());
        preparedStatement.setInt(2, personEnti.getPrice());
        preparedStatement.setLong(4, personEnti.getmodel());
        preparedStatement.executeUpdate();
    }

    public void delete(int model) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM saipa WHERE model=?");
        preparedStatement.setLong(1, model);
        preparedStatement.executeUpdate();
    }

    public List<CarEnti> select() throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM saipa");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<CarEnti> carEntiList = new ArrayList<>();
        while (resultSet.next()) {
            CarEnti carEnti = new CarEnti(); // call by reference
            carEnti.setmodel(resultSet.getInt("model"));
            carEnti.setName(resultSet.getString("name"));
            carEnti.setprice(resultSet.getInt("price"));
            carEntiList.add(carEnti);
        }
        return carEntiList;
    }

    public void commit() throws Exception {
        connection.commit();
    }

    public void rollback() throws Exception {
        connection.rollback();
    }

    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
