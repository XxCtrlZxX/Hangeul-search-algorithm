fun main() {
    // 검색 될 문자열
    val arr = arrayOf("지옥", "월식", "건국대", "페이커", "미세먼지", "ABC마트", "브다샤펄", "대성", "인천 여경", "구구대")

    // 비교할 문자열 (입력된 검색어)
    val compareString = "미세머"

    val searchedArr = arr.map {
        it.replace(" ", "") // 띄어쓰기 없애기
    }.filter {
        it.superContains(compareString)
    }

    println(searchedArr)
}


fun String.superContains(other: String):
        Boolean = contains(other) || containsHangeul(other) || containsHangeulAlphabets(other)


fun String.containsHangeul(other: String): Boolean {
    val range = this.length - other.length

    // 맞는 조합이 하나라도 있으면 return true
    return (0..range).any { i ->
        var t = i
        return@any other.all {
            when (it) {
                in 'ㄱ'..'ㅎ' -> { // 초성은 초성끼리만 비교
                    val targetInitial = Hangeul.InitSound.of(this[t++])
                    return@all it == targetInitial
                }
                in '가'..'힣' -> return@all it.similarHangeulTo(this[t++])
                else -> return@all it == this[t++]
            }
        }
    }
}

// 한글을 알파벳으로 바꿔서 비교
// TODO: 기능 추가
fun String.containsHangeulAlphabets(other: String): Boolean {
    val targetString = toAlphabets(this)
    val compareString = toAlphabets(other)
    return targetString.contains(compareString)
}