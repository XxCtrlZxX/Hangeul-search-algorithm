import Hangeul.Companion.convertHangeulFinalSound
import Hangeul.Companion.getInitSound
import Hangeul.Companion.getFinalSound

fun main() {
    // 검색 될 문자열
    val arr = arrayOf("지옥", "월식", "건국대", "페이커", "미세먼지", "ABC마트", "브다샤펄", "대성", "인천 여경", "구구대")
    // 띄어쓰기 없애기
    val arr2 = arr.map { it.replace(" ", "") }

    // 비교할 문자열
    val compareString = "Cㅁ"

    val findArr = arr2.filter {
        it.matchHangeul(compareString)
    }
    println(findArr)

    println(convertHangeulFinalSound('ㄼ', "갋"))
}

fun String.matchHangeul(
    compare: String
): Boolean {
    val targetString = this

    // 입력된 문자열 중 초성이 하나라도 있으면
    if (compare.any { it in 'ㄱ'..'ㅎ' }) {
        val range = targetString.length - compare.length

        // 맞는 조합이 하나라도 있으면 true
        return (0..range).any { i ->
            var t = i
            return@any compare.all {
                when (it) {
                    in 'ㄱ'..'ㅎ' -> { // 초성이면 초성끼리만 비교
                        val targetInit = getInitSound(targetString[t++])
                        return@all it == targetInit
                    }
                    else -> return@all it == targetString[t++]
                }
            }
        }
    }

    // 마지막 글자에 받침이 있으면
    getFinalSound(compare[compare.lastIndex])?.let {
        return compare in targetString || TODO()
    }
    return false
}

fun String.compareInitSound(
    s: String
): Boolean {

    return false
}