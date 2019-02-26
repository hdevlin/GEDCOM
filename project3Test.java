



import org.junit.Test;
import static org.junit.Assert.assertTrue;

import MyPackage.Date;
import MyPackage.family;
import MyPackage.person;
public class project3Test {

	@Test
	public void test() {
		Date d1 = new Date("24 08 2000");
		Date d2 = new Date( "24 01 2019");
		Date d3 = new Date("24 08 2000");
		Date d4 = new Date("23 08 2000");
		assertTrue(Date.before(d1, d2) == true);
		assertTrue(Date.before(d1, d3) == false);
		assertTrue(Date.before(d1, d4) == false);
		
		person p1 = new person();
		assertTrue(p1.birthBeforeDeath() == true);
		p1.setDeathdate("30 12 200");
		p1.setBirthday("30 12 200");
		assertTrue(p1.birthBeforeDeath() == false);
		
		family f1 = new family();
		assertTrue(f1.marryBeforeDivorce());
		f1.setMarrdate("1 1 100");
		assertTrue(f1.marryBeforeDivorce());

		f1.setDivdate("1 1 0");
		assertTrue(f1.marryBeforeDivorce() == false);

	}

}
