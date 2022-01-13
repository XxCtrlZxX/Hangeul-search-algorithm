import Hangeul.KeyboardMapping
import Hangeul.Util

fun KeyboardMapping.toAlphabets(s: String) : String {
    return s.map { mappingHangeulLetter(it) }.joinToString("")
}

private fun KeyboardMapping.mappingHangeulLetter(c: Char): String {
    val str = Util.splitHangeulLetter(c)
    return str.map { getAlphabet(it) }.joinToString("")
}

private fun KeyboardMapping.getAlphabet(c: Char): String {
    return when (c) {
        in 'ㄱ'..'ㅎ' -> consonantsToAlphabetMap[c] ?: ""
        in 'ㅏ'..'ㅣ' -> vowelsToAlphabetMap[c] ?: ""
        else -> c.toString()
    }
}