package com.classboxesproject.services.customer;

import com.classboxesproject.dto.BookACarDto;
import com.classboxesproject.dto.CarDto;
import com.classboxesproject.dto.CarDtoList;
import com.classboxesproject.dto.SearchCarDto;
import com.classboxesproject.entity.BookACar;
import com.classboxesproject.entity.Car;
import com.classboxesproject.entity.User;
import com.classboxesproject.enums.BookCarStatus;
import com.classboxesproject.repository.BookACarRepository;
import com.classboxesproject.repository.CarRepository;
import com.classboxesproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
 public class CustomerServiceImpl implements CustomerService {

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    private final BookACarRepository bookACarRepository;

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public boolean bookACar(BookACarDto bookACarDto) {
        Optional<Car> optionalCar = carRepository.findById(bookACarDto.getCarId());
        Optional<User> optionalUser = userRepository.findById(bookACarDto.getUserId());
        if (optionalCar.isPresent() && optionalUser.isPresent()) {
            Car existingCar = optionalCar.get();
            BookACar bookACar = new BookACar();
            bookACar.setUser(optionalUser.get());
            bookACar.setCar(existingCar);
            bookACar.setBookCarStatus(BookCarStatus.PENDING);
            long diffInMilliseconds = bookACarDto.getToDate().getTime() - bookACarDto.getFromDate().getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);
            bookACar.setToDate(bookACarDto.getToDate());
            bookACar.setFromDate(bookACarDto.getFromDate());
            bookACar.setDays(days);
            bookACar.setAmount(existingCar.getPrice() * days);
            bookACarRepository.save(bookACar);
            return true;
        }
        return false;
    }

    @Override
    public CarDto getCarById(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        return optionalCar.map(Car::getCarDto).orElse(null);
    }

    @Override
    public List<BookACarDto> getBookingsByUserId(Long userId) {
        return bookACarRepository.findAllByUserId(userId).stream().map(BookACar::getABookCarDto).collect(Collectors.toList()); 
    }

    @Override
    public CarDtoList searchCar(SearchCarDto searchCarDto) {
        Car car = new Car();
        car.setBrand(searchCarDto.getBrand());
        car.setType(searchCarDto.getType());
        car.setTransmission(searchCarDto.getTransmission());
        car.setColor(searchCarDto.getColor());
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Car> carExample = Example.of(car,exampleMatcher);
        List<Car> cars = carRepository.findAll(carExample);
        CarDtoList carDtoList = new CarDtoList();
        carDtoList.setCarDtoList(cars.stream().map(Car::getCarDto).collect(Collectors.toList()));
        return carDtoList;
    }
}
