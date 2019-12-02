package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import olympic.business.Sport;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class AthleteMedalTest extends AbstractTest{

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
        ArrayList<Integer> res  = Solution.getAthleteMedals(1);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1,0,0));
        assertEquals(expected, res);
    }

    @Test
    public void testConfirm2Medals() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(2,1, 3);
        assertEquals(ReturnValue.OK, ret);
        ArrayList<Integer> res  = Solution.getAthleteMedals(1);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1,0,1));
        assertEquals(expected, res);
    }

    @Test
    public void testDisqualified() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(1,1, 1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.confirmStanding(2,1, 3);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteDisqualified(1,1);
        assertEquals(ReturnValue.OK, ret);
        ArrayList<Integer> res  = Solution.getAthleteMedals(1);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0,0,1));
        assertEquals(expected, res);
    }

    @Test
    public void testNoneMedals() {
        ReturnValue ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ArrayList<Integer> res  = Solution.getAthleteMedals(2);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0,0,0));
        assertEquals(expected, res);
    }

    @Test
    public void testNotExists() {
        ArrayList<Integer> res  = Solution.getAthleteMedals(7);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0,0,0));
        assertEquals(expected, res);
    }

}