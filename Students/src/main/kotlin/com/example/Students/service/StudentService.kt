package com.example.Students.service

import com.example.Students.Client.GradeClient
import com.example.Students.Dto.StudentGradeDto
import com.example.Students.model.Student
import com.example.Students.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class StudentService {

    @Autowired
    lateinit var studentRepository: StudentRepository

    @Autowired
    lateinit var gradeClient: GradeClient

    fun list(): List<StudentGradeDto> {
        val students = studentRepository.findAll()
        val studentsGrades: MutableList<StudentGradeDto> = mutableListOf()

        for(student in students){
            val grades = gradeClient.findAllByStudentId(student.id)
            val studentWithGrades = StudentGradeDto()
            studentWithGrades.id = student.id
            studentWithGrades.email = student.email
            studentWithGrades.fullName = student.fullName
            studentWithGrades.grades = grades

            studentsGrades.add(studentWithGrades)
        }

        return studentsGrades
    }


    fun findById(id: Long?): Student{
        val student = studentRepository.findById(id)?:
        throw NoSuchElementException("student not found")
        return student
    }

    fun save(student: Student): Student {
        try {
            student.fullName?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Student name is null or empty")
            student.email?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Student email is null or empty")

            return studentRepository.save(student)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }


    fun update(student: Student): Student {
        try {
            studentRepository.findById(student.id)
                ?: throw  Exception("Student not found")
            return studentRepository.save(student)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete (id: Long?): Boolean? {
        try {
            val response = studentRepository.findById(id)
                ?: throw  Exception("Student id not found")
            studentRepository.deleteById(id!!)
            return true
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
}