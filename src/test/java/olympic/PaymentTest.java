package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import olympic.business.Sport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaymentTest extends AbstractTest{

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
    public void testAthleteJoinSport() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);
    }

    @Test
    public void testAthleteJoinSportNotExists() {
        ReturnValue ret = Solution.athleteJoinSport(1,3);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.athleteJoinSport(3,2);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.athleteJoinSport(4,4);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
    }

    @Test
    public void testAthleteJoinSportAlreadytExists() {
        ReturnValue ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.ALREADY_EXISTS, ret);
        ret = Solution.athleteJoinSport(2,1);
        assertEquals(ReturnValue.ALREADY_EXISTS, ret);
    }


    @Test
    public void testAthleteLeftSport() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteLeftSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
    }

    @Test
    public void testAthleteLeftSportNotExists() {
        ReturnValue ret = Solution.athleteLeftSport(1,3);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.athleteLeftSport(3,2);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.athleteLeftSport(4,4);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteLeftSport(1,2);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.athleteLeftSport(2,1);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
    }

    @Test
    public void testChangePayment() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.changePayment(2,1, 50);
        assertEquals(ReturnValue.OK, ret);
    }

    @Test
    public void testChangePaymentNotExists() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.changePayment(1,1, 50);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.changePayment(1,2, 50);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.changePayment(2,2, 50);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.changePayment(3,3, 50);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
    }
    @Test
    public void testChangePaymentBadParams() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.changePayment(2,1, -1);
        assertEquals(ReturnValue.BAD_PARAMS, ret);
    }

}