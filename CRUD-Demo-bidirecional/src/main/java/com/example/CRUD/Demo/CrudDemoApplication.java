package com.example.CRUD.Demo;

import com.example.CRUD.Demo.dao.AppDAO;
import com.example.CRUD.Demo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner ->{
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
//			deleteInstructorDetail(appDAO);
//			createInstructorWithCourses(appDAO);
//			findInstructorWithCourses(appDAO);
//			findCoursesForInstructor(appDAO);
//			findInstructorWithCoursesJoinFetch(appDAO);
//			updateInstructor(appDAO);
//			updateCourse(appDAO);
//			deleteInstructor(appDAO);
//			deleteCourse(appDAO);

//			createCourseAndReviews(appDAO);
//			findCourseAndReviews(appDAO);

//			deleteCourseAndReviews(appDAO);

			createCourseAndStudent(appDAO);
//			findCourseAndStudents(appDAO);
//			findStudentAndCourses(appDAO);

//			addMoreCoursesForStudent(appDAO);

//			deleteCourse(appDAO);

//			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int id =1;
		appDAO.deleteStudentById(id);
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int id = 2;
		Student student = appDAO.findStudentAndCourseByStudentId(id);

		student.addCourse(new Course("Pathology"));
		student.addCourse(new Course("IT-200"));

		appDAO.updateStudent(student);
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int id = 2;

		Student student = appDAO.findStudentAndCourseByStudentId(id);
		System.out.println(student);
		System.out.println(student.getCourses());
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int id = 13;
		Course course = appDAO.findCourseAndStudentByCourseId(id);
		System.out.println(course);
		System.out.println(course.getStudents());
	}

	private void createCourseAndStudent(AppDAO appDAO) {

		Course course = new Course("Medicine");
		Student student1 = new Student("test","test","test@test");
		Student student2 = new Student("ali","ali","ali@test");

		course.addStudent(student1);
		course.addStudent(student2);

		appDAO.saveCourse(course);

	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id =12;

		//cascade all
		appDAO.deleteCourseById(id);
		System.out.println("Done");
	}

	private void findCourseAndReviews(AppDAO appDAO) {
		int id = 12 ;
		Course course=appDAO.findCourseAndReviewsByCourseId(id);
		System.out.println(course);
		System.out.println(course.getReviews());

	}

	private void createCourseAndReviews(AppDAO appDAO) {

		Course course = new Course("DataBase");
		course.add(new Review("Very Strong !!"));
		course.add(new Review("cool"));
		course.add(new Review("not bad"));

		appDAO.saveCourse(course);


	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 13;
		appDAO.deleteCourseById(id);
		System.out.println("Done");
	}

	private void updateCourse(AppDAO appDAO) {
		int id =10;
		Course course=appDAO.findCourseById(id);
		course.setTitle("PL");

		appDAO.updateCourse(course);
		System.out.println("Done");
	}

	private void updateInstructor(AppDAO appDAO) {
		int id  = 4;
		Instructor instructor = appDAO.findInstructorById(id);

		instructor.setLastName("tester");

		appDAO.updateInstructor(instructor);

	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 4;
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println(instructor);
		System.out.println(instructor.getCourses() );
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id =4;
		Instructor instructor  = appDAO.findInstructorById(id);

		System.out.println(instructor);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		instructor.setCourses(courses);
		System.out.println(instructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id =4;
		Instructor instructor  = appDAO.findInstructorById(id);

		System.out.println(instructor);
		System.out.println(instructor.getCourses());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor = new Instructor("nada","nada","test@test");

		InstructorDetail instructorDetail = new InstructorDetail("youtube1","vedio");

		instructor.setInstructorDetail(instructorDetail);

		Course course1= new Course("AI");
		Course course2= new Course("ML");

		instructor.add(course1);
		instructor.add(course2);

		appDAO.save(instructor);

		System.out.println("Done !!!!!!!!!!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 3;
		appDAO.deleteInstructoDetailrById(id);
		System.out.println("done");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		int id = 2;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println(instructorDetail);

		System.out.println(instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 4;
		appDAO.deleteInstructorById(id);
		System.out.println("done");
	}

	private void findInstructor(AppDAO appDAO) {
		int id  = 1;
		System.out.println("finding id "+ id);
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println(instructor);
		System.out.println(instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor instructor = new Instructor("ahmed","ali","test@test");

		InstructorDetail instructorDetail = new InstructorDetail("youtube1","vedio");

		instructor.setInstructorDetail(instructorDetail);

		appDAO.save(instructor);

		System.out.println("Done !!!!!!!!!!");
	}

}
