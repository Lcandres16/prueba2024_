package com.example.Grades.service

import com.example.Grades.client.StudentClient
import com.example.Grades.model.Grade
import com.example.Grades.repository.GradeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GradeService {
    @Autowired
    lateinit var gradeRepository: GradeRepository

    @Autowired
    lateinit var studentClient: StudentClient

    fun list (): List<Grade> {
        return gradeRepository.findAll()
    }

    fun findAllByStudentId(studentsId: Long): List<Grade>{
        return gradeRepository.findAllByStudentId(studentsId)
    }

    fun save(grade: Grade): Grade {
        try {
            studentClient.finById(grade.studentsId)?:
            throw NoSuchElementException("student not found")

            grade.grade?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Grade is null or empty")

            return gradeRepository.save(grade)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }



    fun update(grade:Grade) : Grade {
        try {
            gradeRepository.findById(grade.id)
                ?: throw Exception("Grade id is null")
            return gradeRepository.save(grade)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete (id: Long?) : Boolean? {
        try {
            val response = gradeRepository.findById(id)
                ?: throw Exception("Grade with id not found")
            gradeRepository.deleteById(id!!)
            return true
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
}