package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import olympic.business.Sport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MedalPerCountryTest extends AbstractTest{

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
    public void test1() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        int res = Solution.getTotalNumberOfMedalsFromCountry("Brazil");
        assertEquals(1, res);
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
        int res = Solution.getTotalNumberOfMedalsFromCountry("Brazil");
        assertEquals(2, res);
    }

    @Test
    public void testZeroMedals() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(2,2, 1);
        assertEquals(ReturnValue.OK, ret);
        int res = Solution.getTotalNumberOfMedalsFromCountry("Israel");
        assertEquals(0, res);
    }

    @Test
    public void testNotSuchCountry() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(2,2, 1);
        assertEquals(ReturnValue.OK, ret);
        int res = Solution.getTotalNumberOfMedalsFromCountry("USA");
        assertEquals(0, res);
    }

    @Test
    public void testConfirmAndDisqualify() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(2,2, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteDisqualified(1,1);
        assertEquals(ReturnValue.OK, ret);
        int res = Solution.getTotalNumberOfMedalsFromCountry("Brazil");
        assertEquals(1, res);
    }
}