package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;

    @Mock
    CellPhone cellPhone;
    
    @Mock
    Car car;
    
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	deliveryDriver = new DeliveryDriver("Juan Cena", car, cellPhone);
    }

    @Test
    void itShouldWasteTime() {
        //given
    	boolean expected = true;
    	when(deliveryDriver.wasteTime()).thenReturn(true);
        //when
    	boolean recieved = deliveryDriver.wasteTime();
        //then
    	assertEquals(expected, recieved);
    }

    @Test
    void itShouldRefuel() {
        //given
    	int octaneGrade = 92;
    	boolean expected = true;
        //when
    	boolean recieved = deliveryDriver.refuel(octaneGrade);
        //then
    	assertEquals(expected, recieved);
    }

    @Test
    void itShouldContactCustomer() {
        //given
    	String phoneNum = "0123456789";
    	boolean expected = true;
        //when
    	boolean recieved = deliveryDriver.contactCustomer(phoneNum);
        //then
    	assertEquals(expected, recieved);
    }

}