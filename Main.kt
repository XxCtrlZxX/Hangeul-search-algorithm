import Hangeul.Companion.getFinalSound
import Hangeul.Companion.getInitSound

fun main() {
    // 검색 될 문자열
    val arr = arrayOf("지옥", "월식", "건국대", "페이커", "미세먼지", "ABC마트", "브다샤펄", "대성", "인천 여경", "서울대")
    // 띄어쓰기 없애기
    val arr2 = arr.map { it.replace(" ", "") }
    // 비교할 문자열
    val compareString = "ㄷㅅ"

    val findArr = arr2.filter {
        it.matchHangeul(compareString)
    }
    println(findArr)
}

fun String.matchHangeul(
    compare: String
): Boolean {
    // 입력된 문자열이 모두 자음이면
    if (compare.all { it in 'ㄱ'..'ㅎ' }) {
        val thisInit = this.map { getInitSound(it) }
            .joinToString(separator = "")
            .replace("null", "")
        return compare in thisInit
    }

    // 마지막 글자에 받침이 있으면
    getFinalSound(compare[compare.lastIndex])?.let {
        TODO()
    }
    return false
}