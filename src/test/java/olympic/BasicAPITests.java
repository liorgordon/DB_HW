
package olympic;

import org.junit.Test;
import olympic.business.*;
import static org.junit.Assert.assertEquals;
import static olympic.business.ReturnValue.*;

import java.util.ArrayList;

public class BasicAPITests extends AbstractTest {
    @Test
    public void addAthleteToSportTest() {

        ReturnValue res;
        Sport s = new Sport();
        s.setId(1);
        s.setName("Basketball");
        s.setCity("Tel Aviv");
        s.setAthletesCount(0);

        res = Solution.addSport(s);
        assertEquals(OK, res);

        s.setId(5);
        s.setName("Basketball");
        s.setCity("Tel Aviv");
        s.setAthletesCount(0);

        res = Solution.addSport(s);
        assertEquals(OK, res);

        Athlete a = new Athlete();
        a.setId(2);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(true);
        ReturnValue ret = Solution.addAthlete(a);
        assertEquals(OK, ret);

        a.setId(3);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(false);
        ret = Solution.addAthlete(a);
        assertEquals(OK, ret);

        res = Solution.athleteJoinSport(1, 2);
        assertEquals(OK, res);
        res = Solution.athleteJoinSport(5, 2);
        assertEquals(OK, res);
        res = Solution.athleteJoinSport(1, 3);
        assertEquals(OK, res);
        res = Solution.athleteJoinSport(1, 2);
        assertEquals(ALREADY_EXISTS, res);
        res = Solution.athleteJoinSport(1, -19);
        assertEquals(NOT_EXISTS, res);
        res = Solution.athleteJoinSport(91, 2);
        assertEquals(NOT_EXISTS, res);

        res = Solution.athleteLeftSport(91, 2);
        assertEquals(NOT_EXISTS, res);
        res = Solution.athleteLeftSport(5, 20);
        assertEquals(NOT_EXISTS, res);
        res = Solution.athleteLeftSport(5, 3);
        assertEquals(NOT_EXISTS, res);
        res = Solution.athleteLeftSport(1, 2);
        assertEquals(OK, res);
        res = Solution.athleteLeftSport(5, 2);
        assertEquals(OK, res);
    }

    @Test
    public void friendsTest() {

        ReturnValue res;
        Athlete a = new Athlete();
        a.setId(4);
        a.setName("Neymar");
        a.setCountry("Brazil");
        a.setIsActive(true);
        ReturnValue ret = Solution.addAthlete(a);
        assertEquals(OK, ret);


        a.setId(5);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(false);
        ret = Solution.addAthlete(a);
        assertEquals(OK, ret);

        a.setId(6);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(false);
        ret = Solution.addAthlete(a);
        assertEquals(OK, ret);

        res = Solution.makeFriends(4,5);
        assertEquals(OK, res);

        res = Solution.makeFriends(-1, 2000);
        assertEquals(NOT_EXISTS, res);

        res = Solution.makeFriends(10, 2000);
        assertEquals(NOT_EXISTS, res);

        res = Solution.makeFriends(4,4);
        assertEquals(BAD_PARAMS, res);

        res = Solution.makeFriends(4,5);
        assertEquals(ALREADY_EXISTS, res);

        res = Solution.makeFriends(5, 4);
        assertEquals(ALREADY_EXISTS, res);

        res = Solution.makeFriends(6, 4);
        assertEquals(OK, res);

        res = Solution.makeFriends(6, 5);
        assertEquals(OK, res);

        res = Solution.removeFriendship(2,5);
        assertEquals(NOT_EXISTS, res);

    }

    static void BuildDB(){
        ReturnValue res;
        Athlete a = new Athlete();
//      ========================= Athlete =================================
        a.setId(1);
        a.setName("Neymar");
        a.setCountry("Brazil");
        a.setIsActive(true);
        ReturnValue ret = Solution.addAthlete(a);
        assertEquals(OK, ret);


        a.setId(2);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(true);
        ret = Solution.addAthlete(a);
        assertEquals(OK, ret);

        a.setId(3);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(false);
        ret = Solution.addAthlete(a);
        assertEquals(OK, ret);

        a.setId(4);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(false);
        ret = Solution.addAthlete(a);
        assertEquals(OK, ret);

        a.setId(5);
        a.setName("Artur");
        a.setCountry("England");
        a.setIsActive(true);
        ret = Solution.addAthlete(a);
        assertEquals(OK, ret);

        a.setId(6);
        a.setName("Artur");
        a.setCountry("England");
        a.setIsActive(true);
        ret = Solution.addAthlete(a);
        assertEquals(OK, ret);
// ====== Sport =============================================================
        Sport s = new Sport();
        s.setId(1);
        s.setName("Basketball");
        s.setCity("Tel Aviv");
        s.setAthletesCount(0);
        res = Solution.addSport(s);
        assertEquals(OK, res);

        s.setId(2);
        s.setName("Soccer");
        s.setCity("New York");
        s.setAthletesCount(0);
        res = Solution.addSport(s);
        assertEquals(OK, res);

        s.setId(3);
        s.setName("Soccer");
        s.setCity("New York");
        s.setAthletesCount(0);
        res = Solution.addSport(s);
        assertEquals(OK, res);
    }

    @Test
    public void StandingsTest(){
        BuildDB();
        ReturnValue res;
        res = Solution.athleteJoinSport(1, 3);
        assertEquals(OK, res);

        res = Solution.athleteJoinSport(1, 1);
        assertEquals(OK, res);

        res = Solution.confirmStanding(5,1,1);
        assertEquals(NOT_EXISTS, res);
        res = Solution.confirmStanding(1,10,1);
        assertEquals(NOT_EXISTS, res);
        res = Solution.confirmStanding(1,3,10);
        assertEquals(NOT_EXISTS, res);
        res = Solution.confirmStanding(1,1,10);
        assertEquals(BAD_PARAMS, res);
        res = Solution.confirmStanding(1,2,1);
        assertEquals(NOT_EXISTS, res);
        res = Solution.confirmStanding(1,1,2);
        assertEquals(OK, res);

        res = Solution.athleteDisqualified(1, 3);
        assertEquals(OK, res);
        res = Solution.athleteDisqualified(1, 100);
        assertEquals(NOT_EXISTS, res);
        res = Solution.athleteDisqualified(2, 3);
        assertEquals(NOT_EXISTS, res);
        res = Solution.athleteDisqualified(-1, 3);
        assertEquals(NOT_EXISTS, res);
        res = Solution.athleteDisqualified(1, 1);
        assertEquals(OK, res);
    }

    @Test
    public void ChangePaymentTest(){
        BuildDB();
        ReturnValue res;
        res = Solution.athleteJoinSport(1, 3);
        assertEquals(OK, res);

        res = Solution.athleteJoinSport(1, 1);
        assertEquals(OK, res);

//        1 is participating in the sport not observing
        res = Solution.changePayment(1, 1, 100);
        assertEquals(NOT_EXISTS, res);

//        no such sport
        res = Solution.changePayment(1, -2, 100);
        assertEquals(NOT_EXISTS, res);

//                no such athlete
        res = Solution.changePayment(10, 2, 100);
        assertEquals(NOT_EXISTS, res);

//        the athlete does not take this sport at all
        res = Solution.changePayment(2, 1, 100);
        assertEquals(NOT_EXISTS, res);

//        invalid payment
        res = Solution.changePayment(3, 1, -100);
        assertEquals(BAD_PARAMS, res);

        res = Solution.changePayment(3, 1, 1000);
        assertEquals(OK, res);

    }

    @Test
    public void IsPopularTest(){
        BuildDB();
        ReturnValue res;
        res = Solution.athleteJoinSport(1, 3);
        assertEquals(OK, res);

        res = Solution.athleteJoinSport(1, 1);
        assertEquals(OK, res);


        res = Solution.makeFriends(1, 3);
        assertEquals(OK, res);

        Boolean b = true;
// his only friend is observing while he is participating
        b = Solution.isAthletePopular(1);
        assertEquals(true, b);

        res = Solution.makeFriends(1, 2);
        assertEquals(OK, res);

//        has a friend that like has no connection to sport id 1 -> he's still popular then
        b = Solution.isAthletePopular(1);
        assertEquals(true, b);

        res = Solution.athleteJoinSport(1, 2);
        assertEquals(OK, res);

        b = Solution.isAthletePopular(1);
        assertEquals(true, b);

        res = Solution.athleteJoinSport(2, 2);
        assertEquals(OK, res);

        b = Solution.isAthletePopular(1);
        assertEquals(false, b);

        res = Solution.removeFriendship(1,2);
        assertEquals(OK, res);

        b = Solution.isAthletePopular(1);
        assertEquals(true, b);

    }

    @Test
    public void getTotalNumberOfMedalsFromCountryTest(){
        BuildDB();
        int medals =0;
        ReturnValue res;
        res = Solution.athleteJoinSport(1, 3);
        assertEquals(OK, res);

        res = Solution.athleteJoinSport(1, 1);
        assertEquals(OK, res);

        res = Solution.athleteJoinSport(2, 1);
        assertEquals(OK, res);

        medals = Solution.getTotalNumberOfMedalsFromCountry("Brazil");
        assertEquals(0, medals);

        medals = Solution.getTotalNumberOfMedalsFromCountry("Barzilai");
        assertEquals(0, medals);

        res = Solution.confirmStanding(1, 1, 1);
        assertEquals(OK, res);

        medals = Solution.getTotalNumberOfMedalsFromCountry("Brazil");
        assertEquals(1, medals);

        res = Solution.confirmStanding(2, 1, 1);
        assertEquals(OK, res);

        medals = Solution.getTotalNumberOfMedalsFromCountry("Brazil");
        assertEquals(2, medals);

        res = Solution.athleteDisqualified(2, 1);
        assertEquals(OK, res);

        medals = Solution.getTotalNumberOfMedalsFromCountry("Brazil");
        assertEquals(1, medals);

        res = Solution.athleteJoinSport(1, 2);
        assertEquals(OK, res);

        res = Solution.confirmStanding(1, 2, 1);
        assertEquals(OK, res);

        medals = Solution.getTotalNumberOfMedalsFromCountry("Brazil");
        assertEquals(2, medals);

        res = Solution.athleteJoinSport(1, 5);
        assertEquals(OK, res);

        res = Solution.confirmStanding(1, 5, 1);
        assertEquals(OK, res);

        medals = Solution.getTotalNumberOfMedalsFromCountry("Brazil");
        assertEquals(2, medals);
    }

    @Test
    public void getIncomeFromSportTest(){
        BuildDB();
        int inc =0;
        ReturnValue res;
        res = Solution.athleteJoinSport(1, 3);
        assertEquals(OK, res);

        res = Solution.athleteJoinSport(1, 1);
        assertEquals(OK, res);

        res = Solution.athleteJoinSport(1, 4);
        assertEquals(OK, res);

        inc = Solution.getIncomeFromSport(1);
        assertEquals(200, inc);

        inc = Solution.getIncomeFromSport(2);
        assertEquals(0, inc);

        inc = Solution.getIncomeFromSport(191);
        assertEquals(0, inc);

        res = Solution.changePayment(3, 1, 17);
        assertEquals(OK, res);

        inc = Solution.getIncomeFromSport(1);
        assertEquals(117, inc);

        res = Solution.athleteLeftSport(1, 3);
        assertEquals(OK, res);

        inc = Solution.getIncomeFromSport(1);
        assertEquals(100, inc);
    }

    @Test
    public void getBestCountryTest(){
        ReturnValue res;
        String s;
        s= Solution.getBestCountry();
        assertEquals("", s);

        BuildDB();
        s= Solution.getBestCountry();
        assertEquals("", s);

        res = Solution.athleteJoinSport(1, 3);
        assertEquals(OK, res);

        res = Solution.athleteJoinSport(1, 1);
        assertEquals(OK, res);

        res = Solution.athleteJoinSport(1, 5);
        assertEquals(OK, res);

        res = Solution.athleteJoinSport(2, 6);
        assertEquals(OK, res);

        res = Solution.confirmStanding(1, 5, 1);
        assertEquals(OK, res);

        s= Solution.getBestCountry();
        assertEquals("England", s);

        res = Solution.confirmStanding(1, 1, 1);
        assertEquals(OK, res);

        s= Solution.getBestCountry();
        assertEquals("Brazil", s);

        res = Solution.athleteDisqualified(1, 1);
        assertEquals(OK, res);

        s= Solution.getBestCountry();
        assertEquals("England", s);

        res = Solution.confirmStanding(1, 1, 1);
        assertEquals(OK, res);
        s= Solution.getBestCountry();
        assertEquals("Brazil", s);

        res = Solution.confirmStanding(2, 6, 1);
        assertEquals(OK, res);
        s= Solution.getBestCountry();
        assertEquals("England", s);

        res = Solution.athleteLeftSport(2, 6);
        assertEquals(OK, res);
        s= Solution.getBestCountry();
        assertEquals("Brazil", s);
    }

    @Test
    public void getMostPopularCityTest(){
        ReturnValue res;
        String s;
        s = Solution.getMostPopularCity();
        assertEquals(s, "");
        BuildDB();
        s = Solution.getMostPopularCity();
        assertEquals("Tel Aviv", s);

        res = Solution.athleteJoinSport(2, 3);
        assertEquals(OK, res);
        s = Solution.getMostPopularCity();
        assertEquals("Tel Aviv", s);

        res = Solution.athleteJoinSport(2, 1);
        assertEquals(OK, res);
        s = Solution.getMostPopularCity();
        assertEquals("New York", s);

        res = Solution.athleteJoinSport(2, 2);
        assertEquals(OK, res);
        s = Solution.getMostPopularCity();
        assertEquals("New York", s);

        res = Solution.athleteJoinSport(3, 5);
        assertEquals(OK, res);
        s = Solution.getMostPopularCity();
        assertEquals("New York", s);

        res = Solution.athleteJoinSport(1, 1);
        assertEquals(OK, res);
        s = Solution.getMostPopularCity();
        assertEquals("New York", s);

        res = Solution.athleteJoinSport(1, 2);
        assertEquals(OK, res);
        s = Solution.getMostPopularCity();
        assertEquals("Tel Aviv", s);

        res = Solution.athleteLeftSport(1, 2);
        assertEquals(OK, res);
        s = Solution.getMostPopularCity();
        assertEquals("New York", s);


    }
    @Test
    public void getAthleteMedalsTest(){
        ReturnValue res;
        ArrayList<Integer> athlete_medals_res;
        ArrayList<Integer> athlete_medals_exp = new ArrayList<Integer>();
        athlete_medals_exp.add(0);
        athlete_medals_exp.add(0);
        athlete_medals_exp.add(0);

        athlete_medals_res = Solution.getAthleteMedals(1);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        BuildDB();
        athlete_medals_res = Solution.getAthleteMedals(1);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(1, 1);
        assertEquals(OK, res);
        res = Solution.confirmStanding(1, 1, 1);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getAthleteMedals(1);
        athlete_medals_exp.set(0,1);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(2, 2);
        assertEquals(OK, res);
        res = Solution.confirmStanding(2, 2, 1);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getAthleteMedals(1);
        assertEquals(athlete_medals_exp, athlete_medals_res);
        athlete_medals_res = Solution.getAthleteMedals(2);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(2, 1);
        assertEquals(OK, res);
        res = Solution.confirmStanding(2, 1, 1);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getAthleteMedals(1);
        athlete_medals_exp.set(0,2);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.confirmStanding(2, 1, 2);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getAthleteMedals(1);
        athlete_medals_exp.set(0,1);
        athlete_medals_exp.set(1,1);
        assertEquals(athlete_medals_exp, athlete_medals_res);
    }
    @Test
    public void getMostRatedAthletesTest(){
        ReturnValue res;
        ArrayList<Integer> athlete_medals_res;
        ArrayList<Integer> athlete_medals_exp = new ArrayList<Integer>();

        athlete_medals_res = Solution.getMostRatedAthletes();
        assertEquals(athlete_medals_exp, athlete_medals_res);

        BuildDB();
        athlete_medals_res = Solution.getMostRatedAthletes();
        athlete_medals_exp.add(1);
        athlete_medals_exp.add(2);
        athlete_medals_exp.add(3);
        athlete_medals_exp.add(4);
        athlete_medals_exp.add(5);
        athlete_medals_exp.add(6);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(1, 2);
        assertEquals(OK, res);
        res = Solution.confirmStanding(1, 2, 3);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getMostRatedAthletes();
        athlete_medals_exp.set(0,2);
        athlete_medals_exp.set(1,1);
        athlete_medals_exp.set(2,3);
        athlete_medals_exp.set(3,4);
        athlete_medals_exp.set(4,5);
        athlete_medals_exp.set(5,6);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(2, 5);
        assertEquals(OK, res);
        res = Solution.confirmStanding(2, 5, 2);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getMostRatedAthletes();
        athlete_medals_exp.set(0,5);
        athlete_medals_exp.set(1,2);
        athlete_medals_exp.set(2,1);
        athlete_medals_exp.set(3,3);
        athlete_medals_exp.set(4,4);
        athlete_medals_exp.set(5,6);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(2, 6);
        assertEquals(OK, res);
        res = Solution.confirmStanding(2, 6, 1);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getMostRatedAthletes();
        athlete_medals_exp.set(0,6);
        athlete_medals_exp.set(1,5);
        athlete_medals_exp.set(2,2);
        athlete_medals_exp.set(3,1);
        athlete_medals_exp.set(4,3);
        athlete_medals_exp.set(5,4);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(2, 2);
        assertEquals(OK, res);
        res = Solution.confirmStanding(2, 2, 2);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getMostRatedAthletes();
        athlete_medals_exp.set(0,2);
        athlete_medals_exp.set(1,6);
        athlete_medals_exp.set(2,5);
        athlete_medals_exp.set(3,1);
        athlete_medals_exp.set(4,3);
        athlete_medals_exp.set(5,4);
        assertEquals(athlete_medals_exp, athlete_medals_res);
    }

    @Test
    public void getCloseAthletesTest(){
        ReturnValue res;
        ArrayList<Integer> athlete_medals_res;
        ArrayList<Integer> athlete_medals_exp = new ArrayList<Integer>();

        athlete_medals_res = Solution.getCloseAthletes(1);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        BuildDB();
        athlete_medals_res = Solution.getCloseAthletes(1);
        athlete_medals_exp.add(2);
        athlete_medals_exp.add(3);
        athlete_medals_exp.add(4);
        athlete_medals_exp.add(5);
        athlete_medals_exp.add(6);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(1, 3);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getCloseAthletes(1);
        assertEquals(athlete_medals_exp, athlete_medals_res);
        athlete_medals_res = Solution.getCloseAthletes(3);
        athlete_medals_exp.removeAll(athlete_medals_exp);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(1, 1);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getCloseAthletes(1);
        athlete_medals_exp.add(3);
        assertEquals(athlete_medals_exp, athlete_medals_res);
        athlete_medals_res = Solution.getCloseAthletes(3);
        athlete_medals_exp.set(0,1);
        assertEquals(athlete_medals_exp, athlete_medals_res);


        res = Solution.athleteJoinSport(1, 5);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getCloseAthletes(3);
        athlete_medals_exp.add(5);
        assertEquals(athlete_medals_exp, athlete_medals_res);
        athlete_medals_res = Solution.getCloseAthletes(1);
        athlete_medals_exp.set(0,3);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(2, 5);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getCloseAthletes(5);
        athlete_medals_exp.set(0,1);
        athlete_medals_exp.set(1,3);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(3, 5);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getCloseAthletes(5);
        athlete_medals_exp.removeAll(athlete_medals_exp);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(1, 6);
        assertEquals(OK, res);
        res = Solution.athleteJoinSport(3, 6);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getCloseAthletes(5);
        athlete_medals_exp.add(6);
        assertEquals(athlete_medals_exp, athlete_medals_res);
        athlete_medals_res = Solution.getCloseAthletes(6);
        athlete_medals_exp.set(0,1);
        athlete_medals_exp.add(3);
        athlete_medals_exp.add(5);
        assertEquals(athlete_medals_exp, athlete_medals_res);

    }


    @Test
    public void getSportsRecommendationTest(){
        ReturnValue res;
        ArrayList<Integer> athlete_medals_res;
        ArrayList<Integer> athlete_medals_exp = new ArrayList<Integer>();

        athlete_medals_res = Solution.getSportsRecommendation(1);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        BuildDB();
        athlete_medals_res = Solution.getSportsRecommendation(1);
        athlete_medals_exp.add(1);
        athlete_medals_exp.add(2);
        athlete_medals_exp.add(3);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(1, 3);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getSportsRecommendation(1);
        assertEquals(athlete_medals_exp, athlete_medals_res);
        athlete_medals_res = Solution.getSportsRecommendation(3);
        athlete_medals_exp.removeAll(athlete_medals_exp);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(1, 1);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getSportsRecommendation(1);
        athlete_medals_exp.add(2);
        athlete_medals_exp.add(3);
        assertEquals(athlete_medals_exp, athlete_medals_res);
        athlete_medals_res = Solution.getSportsRecommendation(3);
        assertEquals(athlete_medals_exp, athlete_medals_res);


        res = Solution.athleteJoinSport(1, 5);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getSportsRecommendation(3);
        assertEquals(athlete_medals_exp, athlete_medals_res);
        athlete_medals_res = Solution.getSportsRecommendation(1);
        assertEquals(athlete_medals_exp, athlete_medals_res);
        athlete_medals_res = Solution.getSportsRecommendation(5);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(2, 5);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getSportsRecommendation(5);
        athlete_medals_exp.removeAll(athlete_medals_exp);
        athlete_medals_exp.add(3);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(3, 5);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getSportsRecommendation(5);
        athlete_medals_exp.removeAll(athlete_medals_exp);
        assertEquals(athlete_medals_exp, athlete_medals_res);

        res = Solution.athleteJoinSport(1, 6);
        assertEquals(OK, res);
        res = Solution.athleteJoinSport(3, 6);
        assertEquals(OK, res);
        athlete_medals_res = Solution.getSportsRecommendation(6);
        athlete_medals_exp.add(2);
        assertEquals(athlete_medals_exp, athlete_medals_res);

    }
}


