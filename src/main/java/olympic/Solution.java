package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import olympic.business.Sport;
import olympic.data.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static olympic.business.ReturnValue.OK;

public class Solution {
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
            pstmt.execute();
            /*=================Sport Table=============================== */
            pstmt = connection.prepareStatement("CREATE TABLE Sport\n" +
                    "(\n" +
                    "    Sport_ID integer,\n" +
                    "    Sport_name text NOT NULL,\n" +
                    "    City text NOT NULL,\n" +
                    "    Athlete_count integer ,\n" +
                    "    PRIMARY KEY (Sport_ID),\n" +
                    "    CHECK (Sport_ID > 0)\n" +
                    "    CHECK (Athlete_count >= 0)\n" +
                    ")");
            pstmt.execute();
            /*=================Goes_to Table=============================== */
            // side note: maybe we should enter first place as 3 in order to make the rank function easier
            pstmt = connection.prepareStatement("CREATE TABLE Goes_to\n" +
                    "(\n" +
                    "    Sport_ID integer,\n" +
                    "    Athlete_ID integer,\n" +
                    "    Fee integer,\n" +
                    "    Place integer,\n" +
                    "    FOREIGN KEY (Sport_ID) REFERENCES Sport(Sport_ID),\n" +
                    "    FOREIGN KEY (Athlete_ID) REFERENCES Sport(Athlete_ID),\n" +
                    "    CHECK (Place > 0)\n" +
                    "    CHECK (Place < 4)\n" +
                    "    CHECK (fee >= 0)\n" +
                    ")");
            pstmt.execute();
            /*=================Friends Table=============================== */
            pstmt = connection.prepareStatement("CREATE TABLE Friends\n" +
                    "(\n" +
                    "    Athlete2_ID integer,\n" +
                    "    Athlete1_ID integer,\n" +
                    "    FOREIGN KEY (Athlete1_ID) REFERENCES Sport(Sport_ID),\n" +
                    "    FOREIGN KEY (Athlete2_ID) REFERENCES Sport(Athlete_ID),\n" +
                    "    CHECK (Athlete1_ID <> Athlete2_ID)\n" +
                    ")");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
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

    public static void clearTables() {
            Connection connection = DBConnector.getConnection();
            PreparedStatement pstmt = null;
           /*=================== Clear Athlete ====================*/
            try {
                pstmt = connection.prepareStatement(
                        "DELETE FROM Athlete ;");
                pstmt.execute();
            } catch (SQLException e) {
                //e.printStackTrace()();
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

    public static void dropTables() { }

    public static ReturnValue addAthlete(Athlete athlete) { return OK; }

    public static Athlete getAthleteProfile(Integer athleteId) {
        return Athlete.badAthlete();
    }

    public static ReturnValue deleteAthlete(Athlete athlete) {
        return OK;
    }

    public static ReturnValue addSport(Sport sport) {
        return OK;
    }

    public static Sport getSport(Integer sportId) { return Sport.badSport(); }

    public static ReturnValue deleteSport(Sport sport) {
        return OK;
    }

    public static ReturnValue athleteJoinSport(Integer sportId, Integer athleteId) {
        return OK;
    }

    public static ReturnValue athleteLeftSport(Integer sportId, Integer athleteId) {
        return OK;
    }

    public static ReturnValue confirmStanding(Integer sportId, Integer athleteId, Integer place) {
        return OK;
    }

    public static ReturnValue athleteDisqualified(Integer sportId, Integer athleteId) {
        return OK;
    }

    public static ReturnValue makeFriends(Integer athleteId1, Integer athleteId2) {
        return OK;
    }

    public static ReturnValue removeFriendship(Integer athleteId1, Integer athleteId2) {
        return OK;
    }

    public static ReturnValue changePayment(Integer athleteId, Integer sportId, Integer payment) {
        return OK;
    }

    public static Boolean isAthletePopular(Integer athleteId) {
        return true;
    }

    public static Integer getTotalNumberOfMedalsFromCountry(String country) {
        return 0;
    }

    public static Integer getIncomeFromSport(Integer sportId) {
        return 0;
    }

    public static String getBestCountry() {
        return "";
    }

    public static String getMostPopularCity() {
        return "";
    }

    public static ArrayList<Integer> getAthleteMedals(Integer athleteId) {
        return new ArrayList<>();
    }

    public static ArrayList<Integer> getMostRatedAthletes() {
        return new ArrayList<>();
    }

    public static ArrayList<Integer> getCloseAthletes(Integer athleteId) {
        return new ArrayList<>();
    }

    public static ArrayList<Integer> getSportsRecommendation(Integer athleteId) {
        return new ArrayList<>();
    }
}

