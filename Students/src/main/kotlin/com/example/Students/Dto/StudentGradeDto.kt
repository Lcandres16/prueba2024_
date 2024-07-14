package com.example.Students.Dto

import lombok.Getter

@Getter
class StudentGradeDto {
    var id: Long? = null
    var fullName: String? = null
    var email: String? = null
    var grades: List<GradeDto>? = null
}