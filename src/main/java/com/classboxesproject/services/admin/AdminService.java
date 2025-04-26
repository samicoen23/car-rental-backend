package com.classboxesproject.services.admin;

import com.classboxesproject.dto.BookACarDto;
import com.classboxesproject.dto.CarDto;
import com.classboxesproject.dto.CarDtoList;
import com.classboxesproject.dto.SearchCarDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    boolean postCar(CarDto carDto) throws IOException;

    List<CarDto> getAllCars();

    void deleteCar(Long id);

    CarDto getCarById(Long id);

    boolean updateCar(Long carId, CarDto carDto) throws IOException;

    List<BookACarDto> getBookings();

    boolean changeBookingStatus(Long bookingId, String status);

    CarDtoList searchCar(SearchCarDto searchCarDto);
}
