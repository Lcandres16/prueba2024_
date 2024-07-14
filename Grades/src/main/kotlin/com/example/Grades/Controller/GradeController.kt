package com.example.Grades.Controller

import com.example.Grades.model.Grade
import com.example.Grades.service.GradeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/grades")
class GradeController {
    @Autowired
    lateinit var gradeService: GradeService

    @GetMapping
    fun list ():List<Grade> {
        return gradeService.list()
    }

    @GetMapping("/students/{id}")
    fun findAllByStudentId(@PathVariable("id") studentId: Long): List<Grade>{
        return gradeService.findAllByStudentId(studentId)
    }

    @PostMapping
    fun save (@RequestBody grade: Grade): ResponseEntity<Grade> {
        return ResponseEntity(gradeService.save(grade), HttpStatus.OK)
    }

    @PutMapping
    fun update (@RequestBody grade: Grade): ResponseEntity<Grade> {
        return ResponseEntity(gradeService.update(grade), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id : Long ): Boolean? {
        return gradeService.delete(id)
    }
}