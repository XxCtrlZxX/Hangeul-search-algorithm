import HangeulUtil.Companion.getInitSound
import HangeulReallocateUtil.Companion.reallocateHangeul

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
        Boolean = this.contains(s) || this.containsHangeulOrInitial(s) || this.containsReallocatedHangeul(s)


fun String.containsHangeulOrInitial(s: String): Boolean {
    // 문자열 중 초성이 있으면
    if (s.any { it in 'ㄱ'..'ㅎ' }) {
        val targetString = this
        val range = targetString.length - s.length

        // 맞는 조합이 하나라도 있으면 return true
        return (0..range).any { i ->
            var t = i
            return@any s.all {
                when (it) {
                    in 'ㄱ'..'ㅎ' -> { // 초성은 초성끼리만 비교
                        val targetInitial = getInitSound(targetString[t++])
                        return@all it == targetInitial
                    }
                    else -> return@all it == targetString[t++]
                }
            }
        }
    }
    return false
}

// 한글을 영어로 바꿔서 비교
fun String.containsReallocatedHangeul(s: String): Boolean {
    val targetString = reallocateHangeul(this)
    val compareString = reallocateHangeul(s)
    return targetString.contains(compareString)
}

/*fun String.checkLastHangeulLetter(s: String): Boolean {
    // 마지막 글자에 받침이 있으면
    if (haveFinalSound(s.last())) {
        val convertedString = convertHangeulFinalSound(s)
        return this.containsHangeulOrInitial(convertedString)
    }
    return false
}*/