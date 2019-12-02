package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AthleteTest extends AbstractTest{

    @Test
    public void testCreateAthlete() {
        Athlete a = new Athlete();
        a.setId(1);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(true);
        ReturnValue ret = Solution.addAthlete(a);
        assertEquals(ReturnValue.OK, ret);
    }

    @Test
    public void testCreateAthleteNegID() {
        Athlete a = new Athlete();
        a.setId(0);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(true);
        ReturnValue ret = Solution.addAthlete(a);
        assertEquals(ReturnValue.BAD_PARAMS, ret);
    }

    @Test
    public void testCreateAthleteNullName() {
        Athlete a = new Athlete();
        a.setId(1);
        a.setCountry("Brazil");
        a.setIsActive(true);
        ReturnValue ret = Solution.addAthlete(a);
        assertEquals(ReturnValue.BAD_PARAMS, ret);
    }

    @Test
    public void testCreateAthleteNullCountry() {
        Athlete a = new Athlete();
        a.setId(1);
        a.setName("Artur");
        a.setIsActive(true);
        ReturnValue ret = Solution.addAthlete(a);
        assertEquals(ReturnValue.BAD_PARAMS, ret);
    }

    @Test
    public void getAthlete() {
        Athlete a = new Athlete();
        a.setId(1);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(true);
        ReturnValue ret = Solution.addAthlete(a);
        assertEquals(ReturnValue.OK, ret);
        Athlete a_clone = Solution.getAthleteProfile(a.getId());
        assertEquals(a, a_clone);
    }

    @Test
    public void testDeleteAthlete() {
        Athlete a = new Athlete();
        a.setId(1);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(true);
        ReturnValue ret = Solution.addAthlete(a);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.deleteAthlete(a);
        assertEquals(ReturnValue.OK , ret);
        ret = Solution.deleteAthlete(a);
        assertEquals(ReturnValue.NOT_EXISTS , ret);
    }
}