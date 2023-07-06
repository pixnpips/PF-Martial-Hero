package Controller;

import Model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
    private static Connection connection;
    private static DatabaseController instance;

    private DatabaseController() {
        // Verbindung zur Datenbank herstellen
        String url = "jdbc:mysql://localhost:3306/mysql";
        String username = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String createSchemaQuery = "CREATE SCHEMA IF NOT EXISTS martial_hero";
            statement.executeUpdate(createSchemaQuery);

            // Wechsle zur Verwendung des Schemas
            String useSchemaQuery = "USE martial_hero";
            statement.executeUpdate(useSchemaQuery);

            String createPlayersTableQuery = "CREATE TABLE IF NOT EXISTS players (id INT AUTO_INCREMENT, name VARCHAR(255) NOT NULL, wins INT, PRIMARY KEY (id))";
            statement.executeUpdate(createPlayersTableQuery);

            String createMapsTableQuery = "CREATE TABLE IF NOT EXISTS maps (id INT AUTO_INCREMENT, map VARCHAR(255) NOT NULL, PRIMARY KEY (id))";
            statement.executeUpdate(createMapsTableQuery);

            String createHighscoresTableQuery = "CREATE TABLE IF NOT EXISTS highscores (id INT AUTO_INCREMENT, player_id INT, map VARCHAR(255) NOT NULL, highscore INT, PRIMARY KEY (id), FOREIGN KEY (player_id) REFERENCES players(id))";
            statement.executeUpdate(createHighscoresTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseController getInstance() {
        if (instance == null) {
            synchronized (DatabaseController.class) {
                if (instance == null) {
                    instance = new DatabaseController();
                }
            }
        }
        return instance;
    }

    public void insertPlayer(Player player) {
        try {
            String query = "INSERT INTO players (name, wins) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, player.getName());
            statement.setInt(2, player.getWins());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateWins(String name) {
        try {
            int currentWins = getWinsByName(name);
            int newWins = currentWins + 1;
            String query = "UPDATE players SET wins = ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, newWins);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getWinsByName(String name) {
        int wins = 0;
        try {
            String query = "SELECT wins FROM players WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                wins = resultSet.getInt("wins");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wins;
    }

    public void deletePlayer(String name) {
        try {
            String query = "DELETE FROM players WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayerByName(String name) {
        try {
            String query = "SELECT * FROM players WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int wins = resultSet.getInt("wins");
                return new Player(name, wins);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean playerExists(String name) {
        try {
            String query = "SELECT COUNT(*) AS count FROM players WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getAllMaps() {
        List<String> maps = new ArrayList<>();
        try {
            String query = "SELECT map FROM maps";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String map = resultSet.getString("map");
                maps.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maps;
    }

    public List<String> getAllPlayers() {
        List<String> playerNames = new ArrayList<>();
        try {
            String query = "SELECT * FROM players";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                playerNames.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerNames;
    }

    public void insertMap(String map) {
        try {
            String query = "INSERT INTO maps (map) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, map);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateHighscore(String playerName, String map, int highscore) {
        try {
            int playerId = getPlayerId(playerName);
            if (playerId != -1) {
                int currentHighscore = getHighscore(playerId, map);
                if (currentHighscore == -1) {
                    insertHighscore(playerId, map, highscore);
                } else {
                    if (highscore < currentHighscore) {
                        updateHighscore(playerId, map, highscore);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public int getPlayerId(String playerName) throws SQLException {
        String query = "SELECT id FROM players WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, playerName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        }
        return -1;
    }

   public int getHighscore(int playerId, String map) throws SQLException {
        String query = "SELECT highscore FROM highscores WHERE player_id = ? AND map = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, playerId);
        statement.setString(2, map);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("highscore");
        }
        return -1;
    }

    public static void insertHighscore(int playerId, String map, int highscore) throws SQLException {
        String query = "INSERT INTO highscores (player_id, map, highscore) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, playerId);
        statement.setString(2, map);
        statement.setInt(3, highscore);
        statement.executeUpdate();
    }

    public void updateHighscore(int playerId, String map, int highscore) throws SQLException {
        String query = "UPDATE highscores SET highscore = ? WHERE player_id = ? AND map = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, highscore);
        statement.setInt(2, playerId);
        statement.setString(3, map);
        statement.executeUpdate();
    }

    // Datenbankverbindung schließen
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}