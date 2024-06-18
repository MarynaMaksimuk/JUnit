package realestateapp;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ApartmentRaterTest {

	@ParameterizedTest
	@CsvSource(value= {"120.0, 50000.0, 0", "75.0, 455000.0, 1", "50, 540000.0, 2"})
	void should_ReturnCorrectRating_When_CorrectApartment(Double area, Double price, int rating) {
//		given
		Apartment apartment = new Apartment(area, new BigDecimal (price));
		int expected = rating;
//		when
		int actual = ApartmentRater.rateApartment(apartment);
//		then
		assertEquals(expected, actual);
				
	}
	
	@ParameterizedTest
	@CsvSource(value= {"0.0, 500000.00"})
	void should_ReturnErrorValue_When_IncorrectApartment(double area, BigDecimal price) {
//		given
		Apartment apartment = new Apartment(area, price);
		int expected = -1;
//		when
		int actual = ApartmentRater.rateApartment(apartment);
//		then
		assertEquals(expected, actual);
	}
	
	@Test
	void should_CalculateAverageRating_When_CorrectApartmentList() {
//		given
		List<Apartment>apartments = new ArrayList<>();
		apartments.add(new Apartment(72.0, new BigDecimal(250000.00)));
		apartments.add(new Apartment (48.0,new BigDecimal (350000.00)));
		apartments.add(new Apartment (30.0,new BigDecimal (600000.00)));
		
		double expected = 1.0;
//		when
		double actual = ApartmentRater.calculateAverageRating(apartments);
//		then
		assertEquals(expected, actual);
	}
	@Test
	void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
//		given
		List<Apartment> apartments = new ArrayList<>();
//		when
		Executable executable = () -> ApartmentRater.calculateAverageRating(apartments);
//		then
		assertThrows(RuntimeException.class, executable);
	}

}
