package com.example.Students.Dto

import lombok.Data

@Data
class GradeDto {
    var id: Long? = null
    var studentsId: Long? = null
    var grade: String? = null
}