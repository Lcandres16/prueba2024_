package com.example.Students.Client

import com.example.Students.Dto.GradeDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("GRADE-MS")
interface GradeClient {
    @GetMapping("/grades/students/{id}")
    fun findAllByStudentId(@PathVariable("id") id: Long?): List<GradeDto>

}