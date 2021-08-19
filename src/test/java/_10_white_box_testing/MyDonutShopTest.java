package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Collections;

class MyDonutShopTest {

    MyDonutShop myDonutShop;
    
    @Mock
    BakeryService bakeryService;
    
    @Mock
    DeliveryService deliveryService;
    
    @Mock
    PaymentService paymentService;
    
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);

    	myDonutShop = new MyDonutShop(paymentService, deliveryService, bakeryService);
    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
    	Order order = new Order("John Doe", "012345689", 1, 18.18, "6942 0420 6942 0420", true);
    	when(paymentService.charge(order)).thenReturn(true);
    	when(bakeryService.getDonutsRemaining()).thenReturn(2);
        //when
    	myDonutShop.openForTheDay();
    	myDonutShop.takeOrder(order);
        //then
    	verify(deliveryService, times(1)).scheduleDelivery(order);
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given
    	Order order = new Order("John Doe", "012345689", 100000, 18.18, "6942 0420 6942 0420", true);
        when(bakeryService.getDonutsRemaining()).thenReturn(69420);
    	//when
    	myDonutShop.openForTheDay();
        //then
    	Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(order));
    	assertEquals(exceptionThrown.getMessage(),"Insufficient donuts remaining");
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given
    	Order order = new Order("John Doe", "012345689", 100000, 18.18, "6942 0420 6942 0420", true);
        when(bakeryService.getDonutsRemaining()).thenReturn(69420);
        //when
        myDonutShop.closeForTheDay();
        //then
        Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(order));
    	assertEquals(exceptionThrown.getMessage(),"Sorry we're currently closed");
    }

}