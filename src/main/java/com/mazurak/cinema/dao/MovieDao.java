package com.mazurak.cinema.dao;

import java.util.List;

import com.mazurak.cinema.entity.Movie;
import com.mazurak.cinema.entity.Movie.MovieEntityQueries;

public final class MovieDao extends DaoCRUDAbstract<Movie> {

	private final static String ID_USER_FIELDNAME = "idUser";
	private static final int ID_MOVIE_FIELD = 0;
	private static final int MOVIE_NAME_FIELD = 1;
	private static final int MOVIE_DESCRIPTION_FIELD = 2;
	private static final int MOVIE_AGE_LIMIT_FIELD = 3;
	private static final int MOVIE_YEAR_FIELD = 4;
	private static final int MOVIE_USER_ID_FIELD = 5;
	
	public MovieDao() {
		init();
	}

	@Override
	protected void init() {
		for (MovieEntityQueries movieEntityQueries : MovieEntityQueries.values()) {
			sqlQueries.put(movieEntityQueries.getSqlQueries(), movieEntityQueries);
		}

	}
	
	@Override
	protected Movie createInstance(String[] args) {
		return new Movie(
			Long.parseLong(args[ID_MOVIE_FIELD] == null ? "0" : args[ID_MOVIE_FIELD]),
			args[MOVIE_NAME_FIELD] == null ? new String() : args[MOVIE_NAME_FIELD],
			args[MOVIE_DESCRIPTION_FIELD] == null ? new String() : args[MOVIE_DESCRIPTION_FIELD],
			args[MOVIE_AGE_LIMIT_FIELD] == null ? new String() : args[MOVIE_AGE_LIMIT_FIELD],
			Integer.parseInt(args[MOVIE_YEAR_FIELD] == null ? "0" : args[MOVIE_YEAR_FIELD]),
			Long.parseLong(args[MOVIE_USER_ID_FIELD] == null ? "0" :args[MOVIE_USER_ID_FIELD]));
	}
	
	@Override
	protected String[] getFields(Movie movie) {
		String fields[] = new String[6];
		fields[ID_MOVIE_FIELD] = movie.getId().toString();
		fields[MOVIE_NAME_FIELD] = movie.getFilmName();
		fields[MOVIE_DESCRIPTION_FIELD] = movie.getDescription();
		fields[MOVIE_AGE_LIMIT_FIELD] = movie.getAgeLimit();
		fields[MOVIE_YEAR_FIELD] = movie.getYear().toString();
		fields[MOVIE_USER_ID_FIELD] = movie.getUserId().toString();
		return fields;
	}
	
	@Override
	protected String[] getUpdateFields(Movie movie) {
		String[] result = new String[4];
		String[] allFields = getFields(movie);
		result[0] = allFields[MOVIE_NAME_FIELD];
		result[1] = allFields[MOVIE_DESCRIPTION_FIELD];
		result[2] = allFields[MOVIE_AGE_LIMIT_FIELD];
		result[3] = allFields[ID_MOVIE_FIELD];
		return result;
	}

	public List<Movie> getMovieEntityByIdUser(Long idUser) {
		return getByFieldName(ID_USER_FIELDNAME, idUser.toString());
	}
	
//	protected Movie createInstanceFIRST(List<String> args) {
//		args.add(args.get((int) Long.parseLong(args.get(ID_MOVIE_FIELD)== null ? "0" : args.get(ID_MOVIE_FIELD)))); //index 0
//		args.add(args.get(MOVIE_NAME_FIELD) == null ? new String() : args.get(MOVIE_NAME_FIELD)); //index 1
//		args.add(args.get(MOVIE_DESCRIPTION_FIELD) == null ? new String() : args.get(MOVIE_DESCRIPTION_FIELD));//index 2
//		args.add(args.get(MOVIE_AGE_LIMIT_FIELD) == null ? new String() : args.get(MOVIE_AGE_LIMIT_FIELD)); // index 3
//		args.add(args.get(Integer.parseInt(args.get(MOVIE_YEAR_FIELD) == null ? "0" : args.get(MOVIE_YEAR_FIELD)))); // index 4
//		args.add(args.get((int) Long.parseLong(args.get(MOVIE_USER_ID_FIELD) == null ? "0" : args.get(MOVIE_USER_ID_FIELD))));//index 5
//		
//		return new Movie(Long.parseLong(args.get(ID_MOVIE_FIELD)),args.get(MOVIE_NAME_FIELD),
//				args.get(MOVIE_DESCRIPTION_FIELD), args.get(MOVIE_AGE_LIMIT_FIELD),
//				Integer.parseInt(args.get(MOVIE_YEAR_FIELD)), Long.parseLong(args.get(MOVIE_USER_ID_FIELD)));
//	}
	
//	protected List<String> getFieldsFIRST(Movie movie) {
//	List<String> movieFields = new ArrayList<>(6);
//	movieFields.add(ID_MOVIE_FIELD, movie.getId().toString());
//	movieFields.add(MOVIE_NAME_FIELD, movie.getFilmName());
//	movieFields.add(MOVIE_DESCRIPTION_FIELD, movie.getDescription());
//	movieFields.add(MOVIE_AGE_LIMIT_FIELD, movie.getAgeLimit());
//	movieFields.add(MOVIE_YEAR_FIELD, movie.getYear().toString());
//	movieFields.add(MOVIE_USER_ID_FIELD, movie.getUserId().toString());
//	return movieFields;
//}
	
//	protected List<String> getUpdateFieldsFIRST(Movie movie) {
//	List<String> result = new ArrayList<String>(4);
//	List<String> allFields = getFieldsFIRST(movie);
//	result.add(0, allFields.get(MOVIE_NAME_FIELD));
//	result.add(1,allFields.get(MOVIE_DESCRIPTION_FIELD));
//	result.add(2, allFields.get(MOVIE_AGE_LIMIT_FIELD));
//	result.add(3,allFields.get(ID_MOVIE_FIELD));
//	return result;
//}

}
