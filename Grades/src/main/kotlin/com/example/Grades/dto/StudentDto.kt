package com.example.Grades.dto

import lombok.Data

@Data
class StudentDto {
    var id: Long? = null
    var fullName: String? = null
    var email: String? = null
}