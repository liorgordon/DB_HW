package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import olympic.business.Sport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IncomeFromSportTest extends AbstractTest{

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
    public void testIncome() {
        ReturnValue ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        int res = Solution.getIncomeFromSport(1);
        assertEquals(100, res);
    }

    @Test
    public void testIncome2Athletes() {
        ReturnValue ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,3);
        assertEquals(ReturnValue.OK, ret);
        int res = Solution.getIncomeFromSport(1);
        assertEquals(200, res);
    }

    @Test
    public void testIncome2AthletesChangePayment() {
        ReturnValue ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,3);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.changePayment(3, 1, 500);
        assertEquals(ReturnValue.OK, ret);
        int res = Solution.getIncomeFromSport(1);
        assertEquals(600, res);
    }

    @Test
    public void testNoSuchSport() {
        ReturnValue ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,3);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.changePayment(3, 1, 500);
        assertEquals(ReturnValue.OK, ret);
        int res = Solution.getIncomeFromSport(3);
        assertEquals(0, res);
    }

    @Test
    public void testEmptySport() {
        ReturnValue ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,3);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.changePayment(3, 1, 500);
        assertEquals(ReturnValue.OK, ret);
        int res = Solution.getIncomeFromSport(2);
        assertEquals(0, res);
    }

    @Test
    public void testEmptySport2() {
        ReturnValue ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,3);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.changePayment(3, 1, 500);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,1);
        assertEquals(ReturnValue.OK, ret);
        int res = Solution.getIncomeFromSport(2);
        assertEquals(0, res);
    }
}