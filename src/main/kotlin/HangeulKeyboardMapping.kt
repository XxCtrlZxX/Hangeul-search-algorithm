import Hangeul.InitSound
import Hangeul.MiddleSound
import Hangeul.FinalSound

fun toAlphabets(s: String) : String {
    return s.flatMap { element ->
        val str = splitHangeulLetterNotNull(element)
        str.map { toAlphabet(it) }
    }.joinToString("")
}

// ex: '갇' -> ['ㄱ', 'ㅏ', 'ㄷ']
fun splitHangeulLetterNotNull(c: Char) : List<Char> {
    return splitHangeulLetter(c).filterNotNull()
}

fun splitHangeulLetter(c: Char): List<Char?> {
    return listOf(
        InitSound.of(c), MiddleSound.of(c), FinalSound.of(c)
    )
}

private fun toAlphabet(c: Char): String {
    return when (c) {
        in 'ㄱ'..'ㅎ' -> consonantsToAlphabetMap[c] ?: ""
        in 'ㅏ'..'ㅣ' -> vowelsToAlphabetMap[c] ?: ""
        else -> c.toString()
    }
}