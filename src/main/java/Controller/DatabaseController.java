package Controller;

import Model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {
    private Connection connection;

    public DatabaseController() {
        // Verbindung zur Datenbank herstellen
        String url = "jdbc:mysql://localhost:3306/MYSQL80";
        String username = "root";
        String password = "%v8yW4*LX6nKec*ryEEqc3Fkxtn#oAwC73uR6bd9V$KaLj!$hNFRg8dw*H85*nJ&";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Spieler in die "players"-Tabelle einfügen
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

    // Wins eines Spielers aktualisieren
    public void updateWins(Player player) {
        try {
            String query = "UPDATE players SET wins = ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, player.getWins());
            statement.setString(2, player.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Spieler aus der "players"-Tabelle löschen
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

    // Spieler in der "players"-Tabelle abrufen
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

    // Überprüfen, ob ein Spieler in der "players"-Tabelle existiert
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

    // Alle Map-Namen aus der "maps"-Tabelle abrufen
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

    // Map in die "maps"-Tabelle einfügen
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

    // Highscore für einen Spieler und eine Map aktualisieren
    public void updateHighscore(String playerName, String map, int highscore) {
        try {
            int playerId = getPlayerId(playerName);

            // Highscore in die "highscores"-Tabelle einfügen oder aktualisieren
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

    // Spieler-ID anhand des Namens abrufen
    private int getPlayerId(String playerName) throws SQLException {
        String query = "SELECT id FROM players WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, playerName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        }
        return -1;
    }

    // Highscore eines Spielers für eine bestimmte Map abrufen
    private int getHighscore(int playerId, String map) throws SQLException {
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

    // Highscore eines Spielers für eine bestimmte Map in die "highscores"-Tabelle einfügen
    private void insertHighscore(int playerId, String map, int highscore) throws SQLException {
        String query = "INSERT INTO highscores (player_id, map, highscore) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, playerId);
        statement.setString(2, map);
        statement.setInt(3, highscore);
        statement.executeUpdate();
    }

    // Highscore eines Spielers für eine bestimmte Map in der "highscores"-Tabelle aktualisieren
    private void updateHighscore(int playerId, String map, int highscore) throws SQLException {
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