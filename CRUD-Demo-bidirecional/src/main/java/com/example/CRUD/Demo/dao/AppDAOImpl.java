package com.example.CRUD.Demo.dao;

import com.example.CRUD.Demo.entity.Course;
import com.example.CRUD.Demo.entity.Instructor;
import com.example.CRUD.Demo.entity.InstructorDetail;
import com.example.CRUD.Demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {

        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class,id);

        List<Course> courses = instructor.getCourses();

        for (Course course : courses){
            course.setInstructor(null);
        }

        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructoDetailrById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class,id);
        //breaking bi directional
        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);
        query.setParameter("data",id);
        List<Course> courses  = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i join fetch i.courses " +
                        "join fetch i.instructorDetail " +
                        "where i.id  = :data",Instructor.class);
        query.setParameter("data",id);
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class,id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {

        Course course  = entityManager.find(Course.class,id);

        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {

        entityManager.persist(course);

    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "join fetch c.reviews " +
                        "where c.id = :data",Course.class);
        query.setParameter("data",id);
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "join fetch c.students " +
                        "where c.id = :data",Course.class);

        query.setParameter("data",id);
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentAndCourseByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s " +
                        "join fetch s.courses " +
                        "where s.id = :data",Student.class);

        query.setParameter("data",id);
        Student student = query.getSingleResult();

        return student;
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {

        Student student = entityManager.find(Student.class,id);

        entityManager.remove(student);

    }
}
