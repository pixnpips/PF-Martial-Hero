package Controller;

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
    public void insertPlayer(String name, int wins, int loses) {
        try {
            String query = "INSERT INTO players (name, wins, loses) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, wins);
            statement.setInt(3, loses);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Wins eines Spielers aktualisieren
    public void updateWins(String name, int wins) {
        try {
            String query = "UPDATE players SET wins = ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, wins);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Loses eines Spielers aktualisieren
    public void updateLoses(String name, int loses) {
        try {
            String query = "UPDATE players SET loses = ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, loses);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    // Highscore eines Spielers für eine bestimmte Map aktualisieren
    public void updateHighscore(String playerName, String map, int highscore) {
        try {
            // Spieler-ID abrufen
            int playerId = getPlayerIdByName(playerName);

            // Prüfen, ob die Kombination aus Spieler und Map bereits existiert
            int mapId = getMapIdByPlayerAndMap(playerId, map);

            if (mapId != -1) {
                // Die Kombination aus Spieler und Map existiert bereits, Highscore aktualisieren
                String query = "UPDATE maps SET highscore = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, highscore);
                statement.setInt(2, mapId);
                statement.executeUpdate();
            } else {
                // Die Kombination aus Spieler und Map existiert nicht, neuen Eintrag erstellen
                String query = "INSERT INTO maps (map, highscore, player_id) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, map);
                statement.setInt(2, highscore);
                statement.setInt(3, playerId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hilfsmethode: Spieler-ID anhand des Spielernamens abrufen
    private int getPlayerIdByName(String playerName) {
        int playerId = -1;
        try {
            String query = "SELECT id FROM players WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, playerName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                playerId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerId;
    }

    // Hilfsmethode: Map-ID anhand des Spielers und des Map-Namens abrufen
    private int getMapIdByPlayerAndMap(int playerId, String map) {
        int mapId = -1;
        try {
            String query = "SELECT id FROM maps WHERE player_id = ? AND map = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, playerId);
            statement.setString(2, map);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                mapId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mapId;
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

    // Map aus der "maps"-Tabelle löschen
    public void deleteMap(String map) {
        try {
            String query = "DELETE FROM maps WHERE map = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, map);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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