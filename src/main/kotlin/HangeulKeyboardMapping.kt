import Hangeul.InitSound
import Hangeul.MiddleSound
import Hangeul.FinalSound

fun toAlphabets(s: String) : String {
    return s.flatMap { element ->
        val str = splitHangeulLetter(element)
        str.map { toAlphabet(it) }
    }.joinToString("")
}

// ex: '갇' -> ['ㄱ', 'ㅏ', 'ㄷ']
fun splitHangeulLetter(c: Char) : List<Char> {
    val charList = mutableListOf(InitSound.of(c), MiddleSound.of(c))
    FinalSound.of(c)?.let { charList.add(it) }
    return charList
}

private fun toAlphabet(c: Char): String {
    return when (c) {
        in 'ㄱ'..'ㅎ' -> consonantsToAlphabetMap[c] ?: ""
        in 'ㅏ'..'ㅣ' -> vowelsToAlphabetMap[c] ?: ""
        else -> c.toString()
    }
}