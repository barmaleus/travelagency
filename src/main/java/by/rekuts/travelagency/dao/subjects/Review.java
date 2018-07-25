package by.rekuts.travelagency.dao.subjects;

import lombok.*;

import java.time.LocalDateTime;


/**
 * Class Review, includes fields <b>id</b>, <b>date</b>, <b>text</b>,
 * <b>userId</b>, <b>tourId</b>
 * @author Aleh_Rekuts
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {
	private int id;
	private LocalDateTime date;
	private String text;
	private int userId;
	private int tourId;
}
