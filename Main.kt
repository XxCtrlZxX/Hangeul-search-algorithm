import HangeulUtil.Companion.convertHangeulFinalSound
import HangeulUtil.Companion.deleteFinalSound
import HangeulUtil.Companion.getInitSound
import HangeulUtil.Companion.haveFinalSound
import HangeulUtil.Companion.isHangeul

fun main() {
    // 검색 될 문자열
    val arr = arrayOf("지옥", "월식", "건국대", "페이커", "미세먼지", "ABC마트", "브다샤펄", "대성", "인천 여경", "구구대")

    // 비교할 문자열 (입력된 검색어)
    val compareString = "미세머"

    val searchedArr = arr.map {
        it.replace(" ", "") // 띄어쓰기 없애기
    }.filter {
        it.upgradedContains(compareString)
    }

    println(searchedArr)
}


fun String.upgradedContains(s: String):
        Boolean = this.contains(s) || this.containsHangeulOrInitial(s) || this.checkLastHangeulLetter(s)


fun String.containsHangeulOrInitial(s: String): Boolean {

    // 문자열 중 한글이 있으면
    if (s.any { isHangeul(it) }) {
        val targetString = this
        val range = targetString.length - s.length

        // 맞는 조합이 하나라도 있으면 return true
        return (0..range).any { i ->
            var t = i
            return@any s.all {
                when (it) {
                    in 'ㄱ'..'ㅎ' -> { // 초성이면 초성끼리만 비교
                        val targetInitial = getInitSound(targetString[t++])
                        return@all it == targetInitial
                    }
                    else -> {
                        if (it == s.last() && !haveFinalSound(it)) {
                            // it이 s의 마지막 글자이고 종성이 없을 때
                            val deletedTargetFinalSound = deleteFinalSound(targetString[t++])
                            return@all it == deletedTargetFinalSound
                        }
                        return@all it == targetString[t++]
                    }
                }
            }
        }
    }
    return false
}

fun String.checkLastHangeulLetter(s: String): Boolean {
    // 마지막 글자에 받침이 있으면
    if (haveFinalSound(s.last())) {
        val convertedString = convertHangeulFinalSound(s)
        return this.containsHangeulOrInitial(convertedString)
    }
    return false
}

// TODO: 그냥 한글을 영어로 바꿔서 비교하는 방식은..?
// ex: 가나다 -> rkskek