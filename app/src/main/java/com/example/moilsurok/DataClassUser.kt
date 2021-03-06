package com.example.moilsurok

data class DataClassUser(
    //기수
    val year: String? ="year",
    //이름
    val name: String? = "name",
    //생년월일
    val birthDate: String? =  "birthdate",
    //핸드폰 번호
    val phoneNum: String? = "phoneNum",
    //본인 이메일
    val email: String? = "email",
    //회사
    val company: String? = "company",
    //부서명
    val department: String? = "department",
    //직위
    val comPosition: String? = "comPosition",
    //회사 번호
    val comTel: String? = "comTel",
    //화사 주소
    val comAdr: String? = "comAdr",
    //팩스 번호
    val faxNum: String? = "faxNum"
)
