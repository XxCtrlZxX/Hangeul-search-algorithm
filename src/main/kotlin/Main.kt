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


fun String.superContains(s: String):
        Boolean = contains(s) || compareHangeulInitial(s) || containsHangeulAlphabets(s)


fun String.compareHangeulInitial(s: String): Boolean {
    // 문자열이 모두 초성으로 되어있으면
    if (s.all { it in 'ㄱ'..'ㅎ' }) {
        val targetString = this
        val range = targetString.length - s.length

        // 맞는 조합이 하나라도 있으면 return true
        return (0..range).any { i ->
            var t = i
            return@any s.all {
                when (it) {
                    in 'ㄱ'..'ㅎ' -> { // 초성은 초성끼리만 비교
                        val targetInitial = Hangeul.InitSound.of(targetString[t++])
                        return@all it == targetInitial
                    }
                    else -> return@all it == targetString[t++]
                }
            }
        }
    }
    return false
}

// 한글을 알파벳으로 바꿔서 비교
// TODO: 기능 추가
fun String.containsHangeulAlphabets(s: String): Boolean {
    val targetString = toAlphabets(this)
    val compareString = toAlphabets(s)
    return targetString.contains(compareString)
}