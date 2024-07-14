package com.example.Grades.client
import com.example.Grades.dto.StudentDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("STUDENT-MS")
interface StudentClient {
    @GetMapping("/students/{id}")
    fun finById(@PathVariable("id") id: Long?): StudentDto?
}