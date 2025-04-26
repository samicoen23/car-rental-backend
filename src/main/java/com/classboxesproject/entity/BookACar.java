package com.classboxesproject.entity;

import com.classboxesproject.dto.BookACarDto;
import com.classboxesproject.enums.BookCarStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class BookACar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long amount;

    private BookCarStatus bookCarStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;

    public BookACarDto getABookCarDto(){
        BookACarDto bookACarDto = new BookACarDto();
        bookACarDto.setId(id);
        bookACarDto.setDays(days);
        bookACarDto.setBookCarStatus(bookCarStatus);
        bookACarDto.setAmount(amount);
        bookACarDto.setFromDate(fromDate);
        bookACarDto.setToDate(toDate);
        bookACarDto.setEmail(user.getEmail());
        bookACarDto.setUsername(user.getUsername());
        bookACarDto.setUserId(user.getId());
        bookACarDto.setCarId(car.getId());
        return bookACarDto;
    }
}

