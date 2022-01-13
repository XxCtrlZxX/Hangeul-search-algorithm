import Hangeul.InitSound
import Hangeul.MiddleSound
import Hangeul.FinalSound
import Hangeul.Util

// ex: '가' -> "ㄱㅏ"
fun Util.splitHangeulLetter(c: Char) : String {
    return "" + InitSound.of(c) + MiddleSound.of(c) +
            if (FinalSound.have(c)) FinalSound.of(c) else ""
}

fun Util.splitDoubleConsonant(doubleConsonant: Char):
        String = doubleConsonantsMap[doubleConsonant] ?: throw Exception("The argument must be Double Consonant")

// 종성 제외
// ex: '각' -> '가'
fun Util.deleteFinalSound(c: Char):
        Char = if (FinalSound.have(c)) c - FinalSound.index(c) else c

// 종성 추가
fun Util.addFinalSound(c: Char, finalSound: Char): Char {
    if (FinalSound.have(c))
        throw Exception("The Hangeul character '$c' already have finalSound")
    return (c.code + FinalSound.index(finalSound)).toChar()
}

// 종성 연음된 문자열로 변환
// ex: "가낙" -> "가나ㄱ", "가낣" -> "가날ㅂ"
fun Util.convertHangeulFinalSound(s: String): String {
    val frontString = s.substring(0 until s.lastIndex)
    val lastLetter = s.last()
    val finalSound = FinalSound.of(lastLetter)
    val excludedLastChar = deleteFinalSound(lastLetter)

    return when (finalSound) {
        'ㄳ','ㄵ','ㄶ','ㄺ','ㄻ','ㄼ','ㄽ','ㄾ','ㄿ','ㅀ','ㅄ' ->
        {
            val pair = splitDoubleConsonant(finalSound)
            val newLastChar = addFinalSound(excludedLastChar, pair[0]) // 종성 하나만 추가
            frontString + newLastChar + pair[1]
        }
        else -> frontString + excludedLastChar + finalSound
    }
}