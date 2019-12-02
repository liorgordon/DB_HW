package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import olympic.business.Sport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MostPopularCityTest extends AbstractTest{

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
        b.setIsActive(false);
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
        s.setName("Soccer");
        s.setCity("Haifa");
        Solution.addSport(s);
        s = new Sport();
        s.setId(3);
        s.setName("Football");
        s.setCity("TLV");
        Solution.addSport(s);
    }

    @Test
    public void test1() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        Solution.athleteJoinSport(3,3);
        assertEquals(ReturnValue.OK, ret);
        String res = Solution.getMostPopularCity();
        assertEquals("TLV", res);
    }

    @Test
    public void test2() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        Solution.athleteJoinSport(3,3);
        assertEquals(ReturnValue.OK, ret);
        String res = Solution.getMostPopularCity();
        assertEquals("TLV", res);
    }

    @Test
    public void test3() {
        String res = Solution.getMostPopularCity();
        assertEquals("Haifa", res);
    }

    @Test
    public void testEqual() {
        ReturnValue ret = Solution.athleteJoinSport(1, 1);
        assertEquals(ReturnValue.OK, ret);
        Solution.athleteJoinSport(2, 4);
        assertEquals(ReturnValue.OK, ret);
        Solution.athleteJoinSport(3, 3);
        assertEquals(ReturnValue.OK, ret);
        String res = Solution.getMostPopularCity();
        assertEquals("Haifa", res);
    }

    @Test
    public void testNoCities() {
        Sport s = new Sport();
        s.setId(1);
        s.setName("Baseball");
        s.setCity("Haifa");
        ReturnValue ret = Solution.deleteSport(s);
        assertEquals(ReturnValue.OK, ret);
        s = new Sport();
        s.setId(2);
        s.setName("Soccer");
        s.setCity("Haifa");
        ret = Solution.deleteSport(s);
        assertEquals(ReturnValue.OK, ret);
        s = new Sport();
        s.setId(3);
        s.setName("Football");
        s.setCity("TLV");
        ret = Solution.deleteSport(s);
        assertEquals(ReturnValue.OK, ret);
        String res = Solution.getMostPopularCity();
        assertEquals("", res);
    }

}