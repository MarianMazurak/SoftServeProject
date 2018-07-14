package app;

import com.mazurak.cinema.dao.UserDao;

public class AppDao {

	public static void main(String[] args) {
		
		System.out.println("Start...");
		UserDao userDao = new UserDao(); //DONE WORK
//		RoleDao roleDao = new RoleDao();//DONE WORK
//		MovieDao movieDao = new MovieDao();//DONE WORK
//		movieDao.insert(new Movie
//				(6L, "dad", "dasdas", AgeLimit.LIMIT_2.getLimit(), 31, 12L)); // + фільм якщо ід юзера неіснує
		
//		System.out.println(movieDao.getByFieldName("userId", "51"));
		
//		System.out.println(movieDao.getByFieldName("userId", "50"));

			
			System.out.println(userDao.deleteById(68L));
		
			
			System.out.println(userDao.deleteById(69L));
		System.out.println("Finish");
	}

}


