package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import olympic.business.Sport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AthletePopularTest extends AbstractTest{

    @Before
    public void createData() {
        Athlete a = new Athlete();
        a.setId(1);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(true);
        Solution.addAthlete(a);
        Athlete b = new Athlete();
        b.setId(2);
        b.setName("Artur");
        b.setCountry("Brazil");
        b.setIsActive(true);
        Solution.addAthlete(b);
        b = new Athlete();
        b.setId(3);
        b.setName("Artur");
        b.setCountry("Brazil");
        b.setIsActive(false);
        Solution.addAthlete(b);
        Sport s = new Sport();
        s.setId(1);
        s.setName("Baseball");
        s.setCity("Haifa");
        Solution.addSport(s);
        s = new Sport();
        s.setId(2);
        s.setName("Football");
        s.setCity("TLV");
        Solution.addSport(s);
    }

    @Test
    public void testIsPopular() {
        ReturnValue ret = Solution.makeFriends(1,2);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,1);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);

        boolean res = Solution.isAthletePopular(1);
        assertTrue(res);
    }

    @Test
    public void testEmptyIntersection() {
        ReturnValue ret = Solution.makeFriends(1,2);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);

        boolean res = Solution.isAthletePopular(1);
        assertFalse(res);
    }

    @Test
    public void testSubGroup() {
        ReturnValue ret = Solution.makeFriends(1,2);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);

        boolean res = Solution.isAthletePopular(1);
        assertFalse(res);
    }

    @Test
    public void test2Friends() {
        ReturnValue ret = Solution.makeFriends(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.makeFriends(1,3);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,1);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(1,3);
        assertEquals(ReturnValue.OK, ret);
        boolean res = Solution.isAthletePopular(1);
        assertTrue(res);
    }

    @Test
    public void testEmptyIntersection2Friends() {
        ReturnValue ret = Solution.makeFriends(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.makeFriends(1,3);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(1,3);
        assertEquals(ReturnValue.OK, ret);
        boolean res = Solution.isAthletePopular(1);
        assertFalse(res);
    }

    @Test
    public void testAthleteNotExists() {
        ReturnValue ret = Solution.makeFriends(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.makeFriends(1,3);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(1,3);
        assertEquals(ReturnValue.OK, ret);
        boolean res = Solution.isAthletePopular(4);
        assertFalse(res);
    }

    @Test
    public void testAthleteHasNoFriends() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);

        ret = Solution.athleteJoinSport(2,3);
        assertEquals(ReturnValue.OK, ret);
        boolean res = Solution.isAthletePopular(1);
        assertTrue(res);
    }

}