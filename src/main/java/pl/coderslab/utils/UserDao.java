package pl.coderslab.utils;

import java.sql.*;
import java.util.Arrays;

public class UserDao extends User{
    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?,?,?)";
    private static final String allFromUsers = "SELECT * FROM users";
    private static final String READ_SINGLE_USER_QUERY = "SELECT * FROM users WHERE username = ?";
    private static final String READ_SINGLE_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";

    public UserDao() {
    }

    public User addUserToDB(User user) {

        if (isExist(user)) {
            getExistingUser(user);
            System.out.println("Uzytkownik o id = " + user.getId() + " juz znajduje sie w bazie.");
        } else {
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, hashPassword(user.getPassword()));
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
                System.out.println("Dodano uzytkownika o id = " + user.getId());
                return user;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }

    private void getExistingUser(User user) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement stm = connection.prepareStatement(READ_SINGLE_USER_QUERY);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                if (user.getEmail().equals(resultSet.getString("email"))) {
                    user.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User read(int userID) {
        User user = new User();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement stm = connection.prepareStatement(READ_SINGLE_USER_BY_ID_QUERY);
            stm.setInt(1,userID);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                String index = resultSet.getString("id");
                if (index.equals(String.valueOf(userID))) {
                    user.setId(Integer.parseInt(index));
                    user.setUserName(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.toString();
                    return user;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public void update(User user) {
        int id = user.getId();
        if (isExist(id)) {
            String username = user.getUserName();
            String email = user.getEmail();
            String password = user.getPassword();
            try (Connection connection = DbUtil.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.setInt(4, id);
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Taki uzytwnik nie istnieje w bazie");
        }
    }

    public void delete(int userId) {
        if (isExist(userId)) {
            try (Connection connection = DbUtil.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY);
                preparedStatement.setInt(1, userId);
                preparedStatement.executeUpdate();
                System.out.println("Usunieto rekord z bazy");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Taki rekord nie istnieje w bazie!");
        }
    }

    public User[] findAll() {
        User user = new User();
        User[] usersArr = new User[0];
        try (Connection connection = DbUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(allFromUsers);
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                usersArr = addToArray(user, usersArr);
                user = new User();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersArr;
    }

    private User[] addToArray(User user, User[] users) {
        User[] usersTmp = Arrays.copyOf(users, users.length + 1);
        usersTmp[usersTmp.length - 1] = user;
        return usersTmp;
    }

    public Boolean isExist(User user) {
        boolean result = false;
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement stm = connection.prepareStatement(READ_SINGLE_USER_QUERY);
            stm.setString(1,user.getUserName());
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                if (user.getEmail().equals(resultSet.getString("email"))) {
                    result = true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Boolean isExist(int id) {
        boolean result = false;
        try (Connection connection = DbUtil.getConnection()) {
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(allFromUsers);
            while (resultSet.next()) {
                if (id == (resultSet.getInt("id"))) {
                    result = true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
