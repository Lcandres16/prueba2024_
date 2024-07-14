package com.example.Grades.repository

import com.example.Grades.model.Grade
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface GradeRepository : JpaRepository<Grade, Long?> {
    fun findById (id:Long?):Grade?

    @Query(nativeQuery = true, value = "SELECT * FROM grades WHERE students_id = ?")
    fun findAllByStudentId(studentsId: Long): List<Grade>
}