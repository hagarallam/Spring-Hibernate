package com.example.CRUD.Demo.dao;

import com.example.CRUD.Demo.entity.Course;
import com.example.CRUD.Demo.entity.Instructor;
import com.example.CRUD.Demo.entity.InstructorDetail;
import com.example.CRUD.Demo.entity.Student;

import java.util.List;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructoDetailrById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void updateInstructor(Instructor instructor);

    void updateCourse(Course course);

    Course findCourseById(int id);

    void deleteCourseById(int id);

    void saveCourse(Course course);

    Course findCourseAndReviewsByCourseId(int id);

    Course findCourseAndStudentByCourseId(int id);

    Student findStudentAndCourseByStudentId(int id);

    void updateStudent(Student student);


    void deleteStudentById(int id);

}
