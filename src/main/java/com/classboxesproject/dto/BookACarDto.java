package com.classboxesproject.dto;

import com.classboxesproject.enums.BookCarStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BookACarDto {

    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long amount;

    private BookCarStatus bookCarStatus;

    private Long carId;

    private Long userId;

    private String username;

    private String email;
}
