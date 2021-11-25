import HangeulUtil.Companion.splitHangeulLetter

class HangeulReallocateUtil {
    companion object {
        private val consonantsMap = mapOf(
            'ㄱ' to "r", 'ㄲ' to "R", 'ㄴ' to "s", 'ㄷ' to "e", 'ㄸ' to "E", 'ㄹ' to "f", 'ㅁ' to "a", 'ㅂ' to "q",
            'ㅃ' to "Q", 'ㅅ' to "t", 'ㅆ' to "T", 'ㅇ' to "d", 'ㅈ' to "w", 'ㅉ' to "W", 'ㅊ' to "c", 'ㅋ' to "z",
            'ㅌ' to "x", 'ㅍ' to "v", 'ㅎ' to "g",
            'ㄳ' to "rt", 'ㄵ' to "sw", 'ㄶ' to "sg", 'ㄺ' to "fr", 'ㄻ' to "fa", 'ㄼ' to "fq", 'ㄽ' to "ft", 'ㄾ' to "fx",
            'ㄿ' to "fv", 'ㅀ' to "fg", 'ㅄ' to "qt"
        )
        private val vowelsMap = mapOf(
            'ㅏ' to "k", 'ㅐ' to "o", 'ㅑ' to "i", 'ㅒ' to "O", 'ㅓ' to "j", 'ㅔ' to "p", 'ㅕ' to "u", 'ㅖ' to "P",
            'ㅗ' to "h", 'ㅘ' to "hk", 'ㅙ' to "ho", 'ㅚ' to "hl", 'ㅛ' to "y", 'ㅜ' to "n", 'ㅝ' to "nj", 'ㅞ' to "np",
            'ㅟ' to "nl", 'ㅠ' to "b", 'ㅡ' to "m", 'ㅢ' to "ml", 'ㅣ' to "l"
        )

        fun reallocateHangeul(s: String): String {
            return s.map { reallocateHangeulLetter(it) }.joinToString("")
        }

        private fun reallocateHangeulLetter(c: Char): String {
            val str = splitHangeulLetter(c)
            return str.map { reallocateConsonantOrVowel(it) }.joinToString("")
        }

        private fun reallocateConsonantOrVowel(c: Char): String {
            return when (c) {
                in 'ㄱ'..'ㅎ' -> consonantsMap[c] ?: ""
                else -> vowelsMap[c] ?: ""
            }
        }
    }
}