package com.classboxesproject.services.customer;

import com.classboxesproject.dto.BookACarDto;
import com.classboxesproject.dto.CarDto;
import com.classboxesproject.dto.CarDtoList;
import com.classboxesproject.dto.SearchCarDto;

import java.util.List;

public interface CustomerService {

    List<CarDto> getAllCars();

    boolean bookACar(BookACarDto bookACarDto);

    CarDto getCarById(Long carId);

    List<BookACarDto> getBookingsByUserId(Long userId);

    CarDtoList   searchCar(SearchCarDto searchCarDto);
}
