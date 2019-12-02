package olympic;

import olympic.business.ReturnValue;
import olympic.business.Sport;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SportTest extends AbstractTest{

    @Test
    public void testCreateSport() {
        Sport s = new Sport();
        s.setId(1);
        s.setName("Artur");
        s.setCity("Haifa");
        ReturnValue ret = Solution.addSport(s);
        assertEquals(ReturnValue.OK, ret);
    }

    @Test
    public void testCreateSportNegID() {
        Sport s = new Sport();
        s.setId(0);
        s.setName("Artur");
        s.setCity("Haifa");
        ReturnValue ret = Solution.addSport(s);
        assertEquals(ReturnValue.BAD_PARAMS, ret);
    }

    @Test
    public void testCreateSportNullName() {
        Sport s = new Sport();
        s.setId(1);
        s.setCity("Haifa");
        ReturnValue ret = Solution.addSport(s);
        assertEquals(ReturnValue.BAD_PARAMS, ret);
    }

    @Test
    public void testCreateSportNullCity() {
        Sport s = new Sport();
        s.setId(-1);
        s.setName("Artur");
        ReturnValue ret = Solution.addSport(s);
        assertEquals(ReturnValue.BAD_PARAMS, ret);
    }

    @Test
    public void testCreateSportNegCount() {
        Sport s = new Sport();
        s.setId(1);
        s.setName("Artur");
        s.setCity("Haifa");
        s.setAthletesCount(-4);
        ReturnValue ret = Solution.addSport(s);
        assertEquals(ReturnValue.BAD_PARAMS, ret);
    }

    @Test
    public void getSport() {
        Sport s = new Sport();
        s.setId(1);
        s.setName("Artur");
        s.setCity("Haifa");
        ReturnValue ret = Solution.addSport(s);
        assertEquals(ReturnValue.OK, ret);
        Sport s_clone = Solution.getSport(s.getId());
        assertEquals(s, s_clone);
    }

    @Test
    public void testDeleteSport() {
        Sport s = new Sport();
        s.setId(1);
        s.setName("Artur");
        s.setCity("Haifa");
        ReturnValue ret = Solution.addSport(s);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.deleteSport(s);
        assertEquals(ReturnValue.OK , ret);
        ret = Solution.deleteSport(s);
        assertEquals(ReturnValue.NOT_EXISTS , ret);
    }
}