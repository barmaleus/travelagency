package by.rekuts.travelagency.domain;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CsvTour {
    @CsvBindByPosition(position = 0, required = true)
    private String photo;

    @CsvBindByPosition(position = 1, required = true)
    @CsvDate("dd-MM-yyyy")
    private Date date;

    @CsvBindByPosition(position = 2, required = true)
    private int duration;

    @CsvBindByPosition(position = 3, required = true)
    private String description;

    @CsvBindByPosition(position = 4, required = true)
    private BigDecimal cost;

    @CsvBindByPosition(position = 5, required = true)
    private int tourTypeId;

    @CsvBindByPosition(position = 6, required = true)
    private int hotelId;

    @CsvBindByPosition(position = 7, required = true)
    private int countryId;
}
