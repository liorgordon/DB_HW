
package olympic;

import org.junit.Test;
import olympic.business.*;
import static org.junit.Assert.assertEquals;
import static olympic.business.ReturnValue.*;


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
        a.setId(1);
        a.setName("Neymar");
        a.setCountry("Brazil");
        a.setIsActive(true);
        ReturnValue ret = Solution.addAthlete(a);
        assertEquals(OK, ret);


        a.setId(2);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(false);
        ret = Solution.addAthlete(a);
        assertEquals(OK, ret);

        a.setId(3);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(false);
        ret = Solution.addAthlete(a);
        assertEquals(OK, ret);

        Sport s = new Sport();
        s.setId(1);
        s.setName("Basketball");
        s.setCity("Tel Aviv");
        s.setAthletesCount(0);
        res = Solution.addSport(s);
        assertEquals(OK, res);

        s.setId(2);
        s.setName("Soccer");
        s.setCity("New-York");
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
        assertEquals(NOT_EXISTS, res);
        res = Solution.athleteDisqualified(1, 100);
        assertEquals(NOT_EXISTS, res);
        res = Solution.athleteDisqualified(2, 3);
        assertEquals(NOT_EXISTS, res);
        res = Solution.athleteDisqualified(-1, 3);
        assertEquals(NOT_EXISTS, res);
        res = Solution.athleteDisqualified(1, 1);
        assertEquals(OK, res);
    }

}


