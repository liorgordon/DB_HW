package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import olympic.business.Sport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MedalTest extends AbstractTest{

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
    public void testConfirmMedal() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
    }

    @Test
    public void testConfirmMedalBadParams() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 4);
        assertEquals(ReturnValue.BAD_PARAMS, ret);
        ret = Solution.confirmStanding(1,1, 0);
        assertEquals(ReturnValue.BAD_PARAMS, ret);
    }

    @Test
    public void testConfirmMedalNotExists() {
        ReturnValue ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,2, 1);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.confirmStanding(3,2, 1);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
    }

    @Test
    public void testConfirmMedalAlreadytExists() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 2);
        assertEquals(ReturnValue.OK, ret);
    }


    @Test
    public void testDisqualified() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteDisqualified(1,1);
        assertEquals(ReturnValue.OK, ret);
    }

    @Test
    public void testDisqualifiedNotExists() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteDisqualified(2,2);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.athleteDisqualified(2,1);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.athleteDisqualified(1,2);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
    }

    @Test
    public void testLeftSport() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteLeftSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteDisqualified(1,1);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
    }

    @Test
    public void testRemoveAthlete() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        Athlete a = new Athlete();
        a.setId(1);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(true);
        ret = Solution.deleteAthlete(a);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteDisqualified(1,1);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
    }

    @Test
    public void testRemoveSport() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        Sport s = new Sport();
        s.setId(1);
        s.setName("Baseball");
        s.setCity("Haifa");
        ret = Solution.deleteSport(s);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteDisqualified(1,1);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
    }
}