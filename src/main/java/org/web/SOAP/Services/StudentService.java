package org.web.SOAP.Services;

import org.web.SOAP.Models.Student;


import java.util.ArrayList;
import java.util.List;


public class StudentService {
    private List<Student> students = new ArrayList<>();
    private static StudentService studentService;

    public StudentService(){
        //añadiendo los estudiantes.
        students.add(new Student(20011136, "Carlos Camacho", "ITT"));
        students.add(new Student(20171086, "Robert Garcia", "ISC"));
        students.add(new Student(20151421, "Juan Carlos", "ISC"));
        students.add(new Student(20185846, "Nicol Guzman", "ISC"));

    }

    public static StudentService getInstance(){
        if(studentService == null){
            studentService = new StudentService();
        }
        return studentService;
    }


    public List<Student> findAll() {
        return students;
    }


    public Student create(Student student) {
        students.add(student);
        return student;
    }

    public Student findByMatricula(int matricula) {
        for (Student aux:students){
            if(aux.getMatricula() == matricula)
                return aux;
        }
        return null;
    }

    public Student update(Student student) {
        Student old = findByMatricula(student.getMatricula());
        if(old == null){
            System.out.println("No existe esa matricula: "+student.getMatricula());
            return null;
        }
        old.setNombre(student.getNombre());
        old.setCarrera(student.getCarrera());
        return old;
    }


    public Boolean deleteByMatricula(int matricula) {
        Student student = findByMatricula(matricula);
        return students.remove(student);
    }
}
