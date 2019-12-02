package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import olympic.business.Sport;
import olympic.data.DBConnector;
import olympic.data.PostgreSQLErrorCodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static olympic.business.ReturnValue.*;

public class Solution {
    public static void main(String[] args) {
//        createTables();
//        clearTables();
//       // dropTables();
    }

    public static void createTables() {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            /*=================Athlete Table=============================== */
            pstmt = connection.prepareStatement("CREATE TABLE Athlete\n" +
                    "(\n" +
                    "    Athlete_ID integer,\n" +
                    "    Athlete_name text NOT NULL,\n" +
                    "    Country text NOT NULL,\n" +
                    "    Active boolean NOT NULL,\n" +
                    "    PRIMARY KEY (Athlete_ID),\n" +
                    "    CHECK (Athlete_ID > 0)\n" +
                    ")");
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }

        try {
            /*=================Sport Table=============================== */
            pstmt = connection.prepareStatement("CREATE TABLE Sport\n" +
                    "(\n" +
                    "    Sport_ID integer,\n" +
                    "    Sport_name text NOT NULL,\n" +
                    "    City text NOT NULL,\n" +
                    "    Athlete_count integer ,\n" +
                    "    PRIMARY KEY (Sport_ID),\n" +
                    "    CHECK (Sport_ID > 0),\n" +
                    "    CHECK (Athlete_count >= 0)\n" +
                    ")");
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }

        try {
            /*=================Goes_to Table=============================== */
            // side note: maybe we should enter first place as 3 in order to make the rank function easier - easy to do 'order by place DESC'
            pstmt = connection.prepareStatement("CREATE TABLE Goes_to\n" +
                    "(\n" +
                    "    Sport_ID integer ,\n" +
                    "    Athlete_ID integer,\n" +
                    "    Fee integer,\n" +
                    "    Place integer,\n" +
                    "    FOREIGN KEY (Sport_ID) REFERENCES Sport(Sport_ID),\n" +
                    "    FOREIGN KEY (Athlete_ID) REFERENCES Athlete(Athlete_ID),\n" +
                    "    CHECK (Place > 0),\n" +
                    "    CHECK (Place < 4),\n" +
                    "    CHECK (fee >= 0)\n" +
                    ")");
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }

        try {

            /*=================Friends Table=============================== */
            pstmt = connection.prepareStatement("CREATE TABLE Friends\n" +
                    "(\n" +
                    "    Athlete2_ID integer,\n" +
                    "    Athlete1_ID integer,\n" +
                    "    FOREIGN KEY (Athlete1_ID) REFERENCES Athlete(Athlete_ID),\n" +
                    "    FOREIGN KEY (Athlete2_ID) REFERENCES Athlete(Athlete_ID),\n" +
                    "    CHECK (Athlete1_ID <> Athlete2_ID)\n" +
                    ")");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }

        try {

            /*=================Medals View=============================== */
            pstmt = connection.prepareStatement("CREATE View Medals(athlete_id, gold, silver, bronze) as" +
                    "SELECT athlete_id," +
                    "COUNT(place=1) gold," +
                    "COUNT(place=2) silver," +
                    "COUNT(place=3) bronze " +
                    "FROM Goes_to" +
                    "GROUP BY athlete_id;");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }

        try {

            /*=================ShareSport View=============================== */
            pstmt = connection.prepareStatement("CREATE View ShareSport(athlete1_id, athlete2_id, sport_id, athlete1_took, athlete2_took) as " +
                    "SELECT a1.athlete_id  , a2.athlete_id , s.sport_id, " +
                    "(SELECT count(*) FROM Goes_to as g1 WHERE g1.athlete_id = a1.athlete_id and s.sport_id = g1.sport_id), " +
                    "(SELECT count(*) FROM Goes_to as g2 WHERE g2.athlete_id = a2.athlete_id and s.sport_id = g2.sport_id) " +
                    "FROM Athlete a1, Athlete a2, Sports s;");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }

        }
    }

    public static void clearTables() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        /*=================== Clear Goes_to ====================*/
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM Goes_to ;");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        /*=================== Clear Friends ====================*/
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM Friends ;");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        /*=================== Clear Sport ====================*/
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM Sport ;");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        /*=================== Clear Athlete ====================*/
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM Athlete ;");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void dropTables() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        /*=================== Drop Goes_to ====================*/
        try {
            pstmt = connection.prepareStatement(
                    "DROP TABLE Goes_to ;");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        /*=================== Drop Friends ====================*/
        try {
            pstmt = connection.prepareStatement(
                    "DROP TABLE Friends ;");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        /*=================== Drop Sport ====================*/
        try {
            pstmt = connection.prepareStatement(
                    "DROP TABLE Sport ;");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        /*=================== Drop Athlete ====================*/
        try {
            pstmt = connection.prepareStatement(
                    "DROP TABLE Athlete ;");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        /*=================== Drop Medals ====================*/
        try {
            pstmt = connection.prepareStatement(
                    "DROP VIEW Medals ;");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        /*=================== Drop ShareSport ====================*/
        try {
            pstmt = connection.prepareStatement(
                    "DROP VIEW ShareSport ;");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static ReturnValue addAthlete(Athlete athlete) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(
                    "INSERT INTO Athlete \n" +
                            "VALUES " +
                            "(?, ?, ?, ?);");
            pstmt.setInt(1, athlete.getId());
            pstmt.setString(2, athlete.getName());
            pstmt.setString(3, athlete.getCountry());
            pstmt.setBoolean(4, athlete.getIsActive());
            pstmt.execute();
        } catch (SQLException e) {
            if (Integer.valueOf(e.getSQLState()) == PostgreSQLErrorCodes.CHECK_VIOLATION.getValue() ||
                    Integer.valueOf(e.getSQLState()) == PostgreSQLErrorCodes.NOT_NULL_VIOLATION.getValue()) {
                return BAD_PARAMS;
            }
            if (Integer.valueOf(e.getSQLState()) == PostgreSQLErrorCodes.UNIQUE_VIOLATION.getValue()) {
                return ALREADY_EXISTS;
            }
            return ERROR;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }

        return OK;
    }

    public static Athlete getAthleteProfile(Integer athleteId) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(
                    "SELECT athlete_id, athlete_name, country, active " +
                            "FROM Athlete \n" +
                            "WHERE athlete_id = ?;");
            pstmt.setInt(1, athleteId);
            ResultSet results = pstmt.executeQuery();
            Athlete a = Athlete.badAthlete();
            while (results.next()) {
                a.setId(results.getInt("athlete_id"));
                a.setName(results.getString("athlete_name"));
                a.setCountry(results.getString("country"));
                a.setIsActive(results.getBoolean("active"));
            }
            return a;
        } catch (SQLException e) {
            return Athlete.badAthlete();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }

    }

    public static ReturnValue deleteAthlete(Athlete athlete) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        Athlete a = getAthleteProfile(athlete.getId());
        if (a.getId() == -1) {
            return NOT_EXISTS;
        }
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM Athlete " +
                            "WHERE athlete_id = ? ;");
            pstmt.setInt(1, athlete.getId());
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
            return ERROR;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue addSport(Sport sport) {

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(
                    "INSERT INTO Sport \n" +
                            "VALUES " +
                            "(?, ?, ?, ?);");
            pstmt.setInt(1, sport.getId());
            pstmt.setString(2, sport.getName());
            pstmt.setString(3, sport.getCity());
            pstmt.setInt(4, sport.getAthletesCount());
            pstmt.execute();
        } catch (SQLException e) {
            if (Integer.valueOf(e.getSQLState()) == PostgreSQLErrorCodes.CHECK_VIOLATION.getValue() ||
                    Integer.valueOf(e.getSQLState()) == PostgreSQLErrorCodes.NOT_NULL_VIOLATION.getValue()) {
                return BAD_PARAMS;
            }
            if (Integer.valueOf(e.getSQLState()) == PostgreSQLErrorCodes.UNIQUE_VIOLATION.getValue()) {
                return ALREADY_EXISTS;
            }
            return ERROR;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }

        return OK;
    }

    public static Sport getSport(Integer sportId) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(
                    "SELECT sport_id, sport_name, city, Athlete_count " +
                            "FROM Sport \n" +
                            "WHERE sport_id = ?;");
            pstmt.setInt(1, sportId);
            ResultSet results = pstmt.executeQuery();
            Sport a = Sport.badSport();
            while (results.next()) {
                a.setId(results.getInt("sport_id"));
                a.setName(results.getString("sport_name"));
                a.setCity(results.getString("city"));
                a.setAthletesCount(results.getInt("athlete_count"));
            }
            return a;
        } catch (SQLException e) {
            return Sport.badSport();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static ReturnValue deleteSport(Sport sport) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        Sport s = getSport(sport.getId());
        if (s.getId() == -1) {
            return NOT_EXISTS;
        }
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM Sport " +
                            "WHERE sport_id = ? ;");
            pstmt.setInt(1, sport.getId());
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
            return ERROR;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue athleteJoinSport(Integer sportId, Integer athleteId) {
        Athlete a = getAthleteProfile(athleteId);
        Sport s = getSport(sportId);
        if (a.getId() == -1 || s.getId() == -1) {
            return NOT_EXISTS;
        }

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
//             check if athlete already joined the sport
            pstmt = connection.prepareStatement("SELECT * FROM Goes_to " +
                    "WHERE sport_id=? AND athlete_id=?;");
            pstmt.setInt(1, sportId);
            pstmt.setInt(2, athleteId);
            ResultSet results = pstmt.executeQuery();
            if (results.next()) {
                return ALREADY_EXISTS;
            }

            // if athlete is active - update athletes’ counter, add hes fee
            int fee = 100;
            if (a.getIsActive()) {
                fee = 0;
                pstmt = connection.prepareStatement("UPDATE Sport " +
                        "SET athlete_count = athlete_count + 1" +
                        "WHERE sport_id=? ;");
                pstmt.setInt(1, sportId);
                pstmt.execute();
            }

            // update the Goes_to table
            pstmt = connection.prepareStatement("INSERT INTO Goes_to(Sport_ID, Athlete_ID, Fee) " +
                    "VALUES (?,?,?);");
            pstmt.setInt(1, sportId);
            pstmt.setInt(2, athleteId);
            pstmt.setInt(3, fee);
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
            return ERROR;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue athleteLeftSport(Integer sportId, Integer athleteId) {
        Athlete a = getAthleteProfile(athleteId);
        Sport s = getSport(sportId);
        if (a.getId() == -1 || s.getId() == -1) {
            return NOT_EXISTS;
        }

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            // check if athlete doesn't participate/observe this sport
            pstmt = connection.prepareStatement("SELECT sport_id FROM Goes_to " +
                    "WHERE sport_id=? AND athlete_id=?;");
            pstmt.setInt(1, sportId);
            pstmt.setInt(2, athleteId);
            ResultSet results = pstmt.executeQuery();
            if (!results.next()) {
                return NOT_EXISTS;
            }

            // if athlete is active - update athletes’ counter
            if (a.getIsActive()) {
                pstmt = connection.prepareStatement("UPDATE Sport " +
                        "SET athlete_count = athlete_count - 1 " +
                        "WHERE sport_id=? ;");
                pstmt.setInt(1, sportId);
                pstmt.execute();
            }

            // update the Goes_to table
            pstmt = connection.prepareStatement("DELETE FROM Goes_to " +
                    "WHERE sport_id=? AND athlete_id=? ;");
            pstmt.setInt(1, sportId);
            pstmt.setInt(2, athleteId);
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
            return ERROR;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue confirmStanding(Integer sportId, Integer athleteId, Integer place) {
        Athlete a = getAthleteProfile(athleteId);
        Sport s = getSport(sportId);
        if (a.getId() == -1 || s.getId() == -1 || a.getIsActive() == false) {
            return NOT_EXISTS;
        }

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            // check if athlete doesn't participate/observe this sport
            pstmt = connection.prepareStatement("SELECT fee FROM Goes_to " +
                    "WHERE sport_id=? AND athlete_id=?;");
            pstmt.setInt(1, sportId);
            pstmt.setInt(2, athleteId);
            ResultSet results = pstmt.executeQuery();
            if (!results.next()) {
                return NOT_EXISTS;
            }

            // update athletes place in sport
            pstmt = connection.prepareStatement("UPDATE Goes_to " +
                    "SET place = ? " +
                    "WHERE sport_id=? AND athlete_id=?;");
            pstmt.setInt(1, place);
            pstmt.setInt(2, sportId);
            pstmt.setInt(3, athleteId);
            pstmt.execute();
        } catch (SQLException e) {
//            getting a check violation at this point means the place is invalid
            if (Integer.valueOf(e.getSQLState()) == PostgreSQLErrorCodes.CHECK_VIOLATION.getValue()) {
                return BAD_PARAMS;
            }
            //e.printStackTrace()();
            return ERROR;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue athleteDisqualified(Integer sportId, Integer athleteId) {
        Athlete a = getAthleteProfile(athleteId);
        Sport s = getSport(sportId);
        ReturnValue ret = OK;
        if (a.getId() == -1 || s.getId() == -1) {
            return NOT_EXISTS;
        }

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            // check if athlete doesn't participate/observe this sport
            pstmt = connection.prepareStatement("SELECT place FROM Goes_to " +
                    "WHERE sport_id=? AND athlete_id=?;");
            pstmt.setInt(1, sportId);
            pstmt.setInt(2, athleteId);
            ResultSet results = pstmt.executeQuery();
            if (!results.next()){
                ret = NOT_EXISTS;
            }

            // disqualifying athlete - nullifying their medal
            pstmt = connection.prepareStatement("UPDATE Goes_to " +
                    "SET place = NULL " +
                    "WHERE sport_id=? AND athlete_id=?;");
            pstmt.setInt(1, sportId);
            pstmt.setInt(2, athleteId);
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
            return ERROR;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return ret;
    }

    public static ReturnValue makeFriends(Integer athleteId1, Integer athleteId2) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            // check if athletes are already friends
            pstmt = connection.prepareStatement("SELECT * FROM Friends " +
                    "WHERE (Athlete1_ID=? AND Athlete2_ID=?) OR (Athlete1_ID=? AND Athlete2_ID=?);");
            pstmt.setInt(1, athleteId1);
            pstmt.setInt(2, athleteId2);
            pstmt.setInt(3, athleteId2);
            pstmt.setInt(4, athleteId1);
            ResultSet results = pstmt.executeQuery();
            if (results.next()) {
//                if the entry exists this means it's with good params and the athlete exist
                return ALREADY_EXISTS;
            }

            // create friendship
            pstmt = connection.prepareStatement("INSERT INTO Friends " +
                    "VALUES (?,?), (?,?);");
            pstmt.setInt(1, athleteId1);
            pstmt.setInt(2, athleteId2);
            pstmt.setInt(3, athleteId2);
            pstmt.setInt(4, athleteId1);
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
//            getting a check violation at this point means the ids are the same
            if (Integer.valueOf(e.getSQLState()) == PostgreSQLErrorCodes.CHECK_VIOLATION.getValue()) {
                return BAD_PARAMS;
            }
            if (Integer.valueOf(e.getSQLState()) == PostgreSQLErrorCodes.FOREIGN_KEY_VIOLATION.getValue()) {
                return NOT_EXISTS;
            }

            return ERROR;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue removeFriendship(Integer athleteId1, Integer athleteId2) {
        Athlete a1 = getAthleteProfile(athleteId1);
        Athlete a2 = getAthleteProfile(athleteId2);
        if (a1.getId() == -1 || a2.getId() == -1) {
            return NOT_EXISTS;
        }

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            // check if athletes are already friends
            pstmt = connection.prepareStatement("SELECT * FROM Friends " +
                    "WHERE (Athlete1_ID=? AND Athlete2_ID=?) OR (Athlete1_ID=? AND Athlete2_ID=?);");
            pstmt.setInt(1, athleteId1);
            pstmt.setInt(2, athleteId2);
            pstmt.setInt(3, athleteId2);
            pstmt.setInt(4, athleteId1);
            ResultSet results = pstmt.executeQuery();
            if (!results.next()) {
                return NOT_EXISTS;
            }

            // delete friendship
            pstmt = connection.prepareStatement("DELETE FROM Friends " +
                    "WHERE (Athlete1_ID=? AND Athlete2_ID=?) OR (Athlete1_ID=? AND Athlete2_ID=?);");
            pstmt.setInt(1, athleteId1);
            pstmt.setInt(2, athleteId2);
            pstmt.setInt(3, athleteId2);
            pstmt.setInt(4, athleteId1);
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
            return ERROR;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue changePayment(Integer athleteId, Integer sportId, Integer payment) {
        Athlete a = getAthleteProfile(athleteId);
        Sport s = getSport(sportId);
//        if an athlete is considered active then he won't observe any sports
        if (a.getId() == -1 || s.getId() == -1 || a.getIsActive()) {
            return NOT_EXISTS;
        }

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            // check if athlete already joined the sport
            pstmt = connection.prepareStatement("SELECT fee FROM Goes_to " +
                    "WHERE sport_id=? AND athlete_id=?;");
            pstmt.setInt(1, sportId);
            pstmt.setInt(2, athleteId);
            ResultSet results = pstmt.executeQuery();
            if (!results.next()) {
                return NOT_EXISTS;
            }

            // update athletes’ payment
            pstmt = connection.prepareStatement("UPDATE Goes_to " +
                    "SET fee = ? " +
                    "WHERE sport_id=? AND athlete_id=?;");
            pstmt.setInt(1, payment);
            pstmt.setInt(2, sportId);
            pstmt.setInt(3, athleteId);
            pstmt.execute();
        } catch (SQLException e) {
//            getting a check violation at this point mean the fee is negative
            if (Integer.valueOf(e.getSQLState()) == PostgreSQLErrorCodes.CHECK_VIOLATION.getValue()) {
                return BAD_PARAMS;
            }
            //e.printStackTrace()();
            return ERROR;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static Boolean isAthletePopular(Integer athleteId) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        boolean ret = false;
        int sport_id;
        try {
            pstmt = connection.prepareStatement(
                    "SELECT sport_id " +
                            "FROM Goes_to " +
                            "WHERE athlete_id IN " +
                            "(SELECT Athlete2_ID FROM Friends WHERE Athlete1_ID = ?) " +
                            "AND sport_id NOT IN " +
                            "(SELECT sport_id FROM Goes_to WHERE athlete_id = ?);");
            pstmt.setInt(1, athleteId);
            pstmt.setInt(2, athleteId);
            ResultSet results = pstmt.executeQuery();
            if (results.next()) {
                sport_id = results.getInt("sport_id");
                if (results.wasNull()) {
                    ret = Boolean.TRUE;
                }
                else{
                    ret = Boolean.FALSE;
                }
            }
            else{
                ret = Boolean.TRUE;
            }


        } catch (SQLException e) {
            ret = Boolean.FALSE;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return ret;
    }

    public static Integer getTotalNumberOfMedalsFromCountry(String country) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        int number_of_medals = 0;
        try {
            pstmt = connection.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM Athlete "  +
                            "LEFT JOIN Goes_to ON Goes_to.athlete_id = Athlete.athlete_id " +
                            "WHERE country = ? AND place IS NOT NULL;");
            pstmt.setString(1, country);
            ResultSet results = pstmt.executeQuery();
            if (results.next()) {
                number_of_medals = results.getInt("COUNT");
            }
        } catch (SQLException e) {
            return 0;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return number_of_medals;
    }

    public static Integer getIncomeFromSport(Integer sportId) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        int income = 0;
        try {
            pstmt = connection.prepareStatement(
                    "SELECT SUM(fee) " +
                            "FROM Goes_to " +
                            "WHERE sport_id = ?;");
            pstmt.setInt(1, sportId);
            ResultSet results = pstmt.executeQuery();
            if (results.next()) {
                income = results.getInt("SUM");
            }
        } catch (SQLException e) {
            return 0;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return income;
    }

    public static String getBestCountry() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        String best_country = null;
        try {
            pstmt = connection.prepareStatement(
                    "SELECT COUNT(a.athlete_id), a.country " +
                            "FROM Athlete as a " +
                            "LEFT JOIN Goes_to as g ON a.athlete_id = g.athlete_id " +
                            "WHERE place IS NOT NULL " +
                            "GROUP BY country " +
                            "ORDER BY count(a.athlete_id) DESC, a.country ASC; ");
            ResultSet results = pstmt.executeQuery();
            best_country = "";
            if (results.next()) {
                best_country = results.getString("country");
            }
        } catch (SQLException e) {
            return "";
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return best_country;
    }

    public static String getMostPopularCity() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        String popular_city = null;
        try {
            pstmt = connection.prepareStatement(
                    "SELECT city " +
                            "FROM Sport as s " +
                            "GROUP BY city HAVING COUNT(distinct s.Sport_id)>0 " +
                            "ORDER BY SUM(athlete_count)*1.0/COUNT(distinct s.Sport_id) DESC, s.city desc;" );
            ResultSet results = pstmt.executeQuery();

            if (results.next()) {
                popular_city = results.getString("city");
            }
            else{
                popular_city = "";
            }
        } catch (SQLException e) {
            return null;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return popular_city;
    }

    public static ArrayList<Integer> getAthleteMedals(Integer athleteId) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        ArrayList<Integer> athlete_medals= new ArrayList<Integer>(3);
        athlete_medals.set(0, 0);
        athlete_medals.set(1, 0);
        athlete_medals.set(2, 0);
        try {
            pstmt = connection.prepareStatement(
                    "SELECT gold, silver, bronze" +
                            "FROM Medals" +
                            "WHERE athlete_id = ?");
            pstmt.setInt(1, athleteId);
            ResultSet results = pstmt.executeQuery();
            if (results.next()) {
                athlete_medals.set(0, results.getInt("gold"));
                athlete_medals.set(1, results.getInt("silver"));
                athlete_medals.set(2, results.getInt("bronze"));
            }
        } catch (SQLException e) {
            return athlete_medals;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return athlete_medals;
    }

    public static ArrayList<Integer> getMostRatedAthletes() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        ArrayList<Integer> most_rated_athletes= new ArrayList<Integer>();
        int num_athletes = 0;
        try {
            pstmt = connection.prepareStatement(
                    "SELECT athlete_id" +
                            "FROM Medals" +
                            "GROUP BY athlete_id" +
                            "ORDER BY SUM(3*gold + 2*silver + 1*bronze) DESC, athlete_id ASC");
            ResultSet results = pstmt.executeQuery();
            while (results.next() && num_athletes<10) {
                most_rated_athletes.add(results.getInt("athlete_id"));
                num_athletes += 1;
            }
        } catch (SQLException e) {
            return most_rated_athletes;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return most_rated_athletes;
    }

    public static ArrayList<Integer> getCloseAthletes(Integer athleteId) {
        ArrayList<Integer> close_athletes= new ArrayList<Integer>();
        Athlete a = getAthleteProfile(athleteId);
        if(a.getId() == -1){
            return close_athletes;
        }
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        int num_athletes = 0;
        try {
            pstmt = connection.prepareStatement(
                    "SELECT athlete1_id FROM" +
                            "FROM ShareSport" +
                            "WHERE athlete2_id = ?" +
                            "GROUP BY athlete1_id" +
                            "HAVING CASE SUM(athlete2_take) <> 0 THEN WHEN SUM(athlete1_take * athlete2_take)/SUM(athlete2_take) >= 0.5" +
                            "THEN athlete1_id > 0"+
                            "ORDER BY athlete_id ASC");
            pstmt.setInt(1, athleteId);
            ResultSet results = pstmt.executeQuery();
            while (results.next() && num_athletes<10) {
                close_athletes.add(results.getInt("athlete_id"));
                num_athletes += 1;
            }
        } catch (SQLException e) {
            return close_athletes;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return close_athletes;
    }

    public static ArrayList<Integer> getSportsRecommendation(Integer athleteId) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        ArrayList<Integer> sports_recommendation= new ArrayList<Integer>();
        int num_sports = 0;
        try {
            pstmt = connection.prepareStatement(
                    "SELECT sport_id" +
                            "FROM Goes_to" +
                            "where athlete_id IN" +
                                "(SELECT athlete_id FROM" +
                                "FROM ShareSport s" +
                                "WHERE athlete2_id = ?" +
                                "GROUP BY athlete1_id" +
                            "HAVING CASE SUM(athlete2_take) <> 0 THEN WHEN SUM(athlete1_take * athlete2_take)/SUM(athlete2_take) >= 0.5" +
                            "THEN athlete1_id > 0)"+
                            "GROUP BY sport_id" +
                            "ORDER BY count(*) DESC, athlete_id ASC");
            pstmt.setInt(1, athleteId);
            ResultSet results = pstmt.executeQuery();
            while (results.next() && num_sports<3) {
                sports_recommendation.add(results.getInt("sport_id"));
                num_sports += 1;
            }
        } catch (SQLException e) {
            return sports_recommendation;
        }
        return sports_recommendation;
    }
}

