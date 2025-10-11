package org.example.model;

import org.example.entities.User;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class UserDao {

    private final DataSource dataSource;

    UserDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void save(User user){
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format("insert into users (id, name) values(%d, %s);", user.getId(), user.getUsername()));
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(User user){
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeQuery(String.format("update users set username = %s where id = %d;", user.getUsername(), user.getId()));
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(Long id){
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeQuery(String.format("delete from users where id = %d;", id));
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public User getById(Long id){
        User result = null;
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select * from users where id = %d;", id));
            result = new User(resultSet.getLong("id"), resultSet.getString("username"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
