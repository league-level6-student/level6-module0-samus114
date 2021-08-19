package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
    	int hourlyWage = 15;
    	int numHours = 40;
    	int expected = 600;
    	//when
    	double received = payroll.calculatePaycheck(hourlyWage, numHours);
        //then
    	assertEquals(expected, received);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
    	int milesTraveled = 10;
    	double expected = 5.75;
        //when
    	double received = payroll.calculateMileageReimbursement(milesTraveled);
        //then
    	assertEquals(expected, received);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
    	String name = "Jack Johnson";
        double hourlyWage = 15.95;
        String expected = "Hello Jack Johnson, We are pleased to offer you an hourly wage of 15.95";
        //when
        String received = payroll.createOfferLetter(name, hourlyWage);
        //then
    	assertEquals(expected, received);
    }

}