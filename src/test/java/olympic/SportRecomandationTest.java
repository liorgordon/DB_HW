package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import olympic.business.Sport;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SportRecomandationTest extends AbstractTest{

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
        s = new Sport();
        s.setId(3);
        s.setName("Football");
        s.setCity("TLV");
        Solution.addSport(s);
        s.setId(4);
        s.setName("Football");
        s.setCity("TLV");
        Solution.addSport(s);
        s.setId(5);
        s.setName("Football");
        s.setCity("TLV");
        Solution.addSport(s);
    }

    @Test
    public void testOneAthlete() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ArrayList<Integer> res  = Solution.getSportsRecommendation(1);
        ArrayList<Integer> expected = new ArrayList<>();
        assertEquals(expected, res);
    }

    @Test
    public void testTwoAthletes() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);

        ArrayList<Integer> res  = Solution.getSportsRecommendation(1);
        ArrayList<Integer> expected = new ArrayList<>();
        assertEquals(expected, res);
    }

    @Test
    public void testTwoAthletes2() {
        ReturnValue ret = Solution.athleteJoinSport(1,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(3,2);
        assertEquals(ReturnValue.OK, ret);

        ArrayList<Integer> res  = Solution.getSportsRecommendation(1);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(3));
        assertEquals(expected, res);
    }

    @Test
    public void testEmptyWay() {
        ReturnValue ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(2,3);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,3);
        assertEquals(ReturnValue.OK, ret);
        ArrayList<Integer> res  = Solution.getSportsRecommendation(1);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2,1));
        assertEquals(expected, res);
    }

    @Test
    public void testSameCount() {
        ReturnValue ret = Solution.athleteJoinSport(2,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.athleteJoinSport(1,3);
        assertEquals(ReturnValue.OK, ret);
        ArrayList<Integer> res  = Solution.getSportsRecommendation(1);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1,2));
        assertEquals(expected, res);
    }

    @Test
    public void testNotExists() {
        ArrayList<Integer> res  = Solution.getSportsRecommendation(5);
        ArrayList<Integer> expected = new ArrayList<>();
        assertEquals(expected, res);
    }

    @Test
    public void test11Athletes() {
        Athlete a;
        for(int i = 1; i < 15; i++) {
            a = new Athlete();
            a.setId(i);
            a.setName("Artur");
            a.setCountry("Brazil");
            a.setIsActive(true);
            Solution.addAthlete(a);
            Solution.athleteJoinSport(1, i);
            if (i != 1) {
                Solution.athleteJoinSport(5, i);
                if (i % 3 == 0) {
                    Solution.athleteJoinSport(4, i);
                }
                if (i % 4 == 0) {
                    Solution.athleteJoinSport(3, i);
                }
                if (i % 5 == 0) {
                    Solution.athleteJoinSport(2, i);
                }
            }
        }
        ArrayList<Integer> res  = Solution.getSportsRecommendation(1);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(5,4,3));
        assertEquals(expected, res);
    }


}