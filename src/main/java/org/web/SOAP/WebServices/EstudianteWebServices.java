package org.web.SOAP.WebServices;

import org.web.SOAP.Models.Student;
import org.web.SOAP.Services.StudentService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class EstudianteWebServices {
    public StudentService studentService = StudentService.getInstance();

    @WebMethod
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @WebMethod
    public Student create(Student student) {

        return studentService.create(student);
    }

    @WebMethod
    public Student findByMatricula(int matricula) {

        return studentService.findByMatricula(matricula);
    }

    @WebMethod
    public Student update(Student student) {

        return studentService.update(student);
    }

    @WebMethod
    public Boolean deleteByMatricula(int matricula) {

        return studentService.deleteByMatricula(matricula);
    }
}
