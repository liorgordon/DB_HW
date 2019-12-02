package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import olympic.business.Sport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BestCountryTest extends AbstractTest{

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
        b.setId(3);
        b.setName("Artur");
        b.setCountry("Israel");
        b.setIsActive(true);
        Solution.addAthlete(b);
        b.setId(4);
        b.setName("Artur");
        b.setCountry("Israel");
        b.setIsActive(true);
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
    public void test1() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        String res = Solution.getBestCountry();
        assertEquals("Brazil", res);
    }

    @Test
    public void test2() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(2,2, 1);
        assertEquals(ReturnValue.OK, ret);
        String res = Solution.getBestCountry();
        assertEquals("Brazil", res);
    }
    @Test
    public void test3() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,3);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(2,2, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,3, 2);
        assertEquals(ReturnValue.OK, ret);
        String res = Solution.getBestCountry();
        assertEquals("Brazil", res);
    }

    @Test
    public void test4() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,3);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,4);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,3, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,4, 2);
        assertEquals(ReturnValue.OK, ret);
        String res = Solution.getBestCountry();
        assertEquals("Israel", res);
    }

    @Test
    public void testLexicalOrder() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,3);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(2,3, 1);
        assertEquals(ReturnValue.OK, ret);
        String res = Solution.getBestCountry();
        assertEquals("Brazil", res);
    }

    @Test
    public void testZeroMedals() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);
        String res = Solution.getBestCountry();
        assertEquals("", res);
    }
    @Test
    public void testZeroMedals2() {
        String res = Solution.getBestCountry();
        assertEquals("", res);
    }

    @Test
    public void testConfirmAndDisqualify() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,3);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(2,3, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteDisqualified(1,1);
        assertEquals(ReturnValue.OK, ret);
        String res = Solution.getBestCountry();
        assertEquals("Israel", res);
    }
}