package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-07-08T21:01:06.416+0100")
@StaticMetamodel(HorseEvent.class)
public class HorseEvent_ {
	public static volatile SingularAttribute<HorseEvent, Double> id;
	public static volatile SingularAttribute<HorseEvent, Date> actualOff;
	public static volatile SingularAttribute<HorseEvent, String> country;
	public static volatile SingularAttribute<HorseEvent, String> course;
	public static volatile SingularAttribute<HorseEvent, String> event;
	public static volatile SingularAttribute<HorseEvent, Double> event_ID;
	public static volatile SingularAttribute<HorseEvent, Date> firstTaken;
	public static volatile SingularAttribute<HorseEvent, String> fullDescription;
	public static volatile SingularAttribute<HorseEvent, String> inPlay;
	public static volatile SingularAttribute<HorseEvent, Date> latestTaken;
	public static volatile SingularAttribute<HorseEvent, Double> numberBets;
	public static volatile SingularAttribute<HorseEvent, Double> odds;
	public static volatile SingularAttribute<HorseEvent, Date> scheduledOff;
	public static volatile SingularAttribute<HorseEvent, String> selection;
	public static volatile SingularAttribute<HorseEvent, Integer> selectionId;
	public static volatile SingularAttribute<HorseEvent, Date> settledDate;
	public static volatile SingularAttribute<HorseEvent, Integer> sportsId;
	public static volatile SingularAttribute<HorseEvent, Float> volumeMatched;
	public static volatile SingularAttribute<HorseEvent, Integer> winFlag;
}
