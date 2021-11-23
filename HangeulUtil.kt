class HangeulException(msg: String = "Invalid Hangeul"): Exception(msg)

class HangeulUtil {
    companion object {
        private const val init = 0xAC00 // 한글 시작점

        private val InitSound = arrayOf('ㄱ','ㄲ','ㄴ','ㄷ','ㄸ','ㄹ','ㅁ','ㅂ','ㅃ','ㅅ','ㅆ','ㅇ','ㅈ','ㅉ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ' )
        private val MiddleSound = arrayOf('ㅏ','ㅐ','ㅑ','ㅒ','ㅓ','ㅔ','ㅕ','ㅖ','ㅗ','ㅘ','ㅙ','ㅚ','ㅛ','ㅜ','ㅝ','ㅞ','ㅟ','ㅠ','ㅡ','ㅢ','ㅣ')
        private val FinalSound = arrayOf(null, 'ㄱ','ㄲ','ㄳ','ㄴ','ㄵ','ㄶ','ㄷ','ㄹ','ㄺ','ㄻ','ㄼ','ㄽ','ㄾ','ㄿ','ㅀ','ㅁ','ㅂ','ㅄ','ㅅ','ㅆ','ㅇ','ㅈ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ')
        private val splittedMap = mapOf(
            'ㄳ' to Pair('ㄱ', 'ㅅ'),
            'ㄵ' to Pair('ㄴ', 'ㅈ'),
            'ㄶ' to Pair('ㄴ', 'ㅎ'),
            'ㄺ' to Pair('ㄹ', 'ㄱ'),
            'ㄻ' to Pair('ㄹ', 'ㅁ'),
            'ㄼ' to Pair('ㄹ', 'ㅂ'),
            'ㄽ' to Pair('ㄹ', 'ㅅ'),
            'ㄾ' to Pair('ㄹ', 'ㅌ'),
            'ㄿ' to Pair('ㄹ', 'ㅍ'),
            'ㅀ' to Pair('ㄹ', 'ㅎ'),
            'ㅄ' to Pair('ㅂ', 'ㅅ')
        )

        private fun initIndex(c: Char) = (c - init).code / 28 / 21
        private fun middleIndex(c: Char) = (c - init).code / 28 % 21
        private fun finalIndex(c: Char) = (c - init).code % 28

        private fun splitSound(consonant: Char):
                Pair<Char, Char>? = splittedMap.getOrDefault(consonant, null)

        private fun isCompleteHangeul(c: Char):
                Boolean = c in '가'..'힣'

        private fun isHangeul(c: Char):
                Boolean = isCompleteHangeul(c) || c in 'ㄱ'..'ㅎ' || c in 'ㅏ'..'ㅣ'

        fun getInitSound(c: Char):
                Char? = if (isCompleteHangeul(c)) InitSound[initIndex(c)] else null

        fun getMiddleSound(c: Char):
                Char? = if (isCompleteHangeul(c)) MiddleSound[middleIndex(c)] else null

        fun getFinalSound(c: Char):
                Char? = if (isCompleteHangeul(c)) FinalSound[finalIndex(c)] else null


        // 종성 연음된 문자열로 변환
        // ex: "가낙" -> "가나ㄱ", "가낣" -> "가날ㅂ"
        fun convertHangeulFinalSound(s: String): String {
            val frontString = s.substring(0 until s.lastIndex)
            val lastChar = s.last()
            val finalSound = getFinalSound(lastChar)
            val excludedLastChar = deleteFinalSound(lastChar)

            return when (finalSound) {
                'ㄳ','ㄵ','ㄶ','ㄺ','ㄻ','ㄼ','ㄽ','ㄾ','ㄿ','ㅀ','ㅄ' ->
                {
                    val pair = splitSound(finalSound)?.toList() ?: throw HangeulException()
                    val newLastChar = addFinalSound(excludedLastChar, pair[0]) // 종성 하나만 추가
                    frontString + newLastChar + pair[1]
                }
                else -> frontString + excludedLastChar + finalSound
            }
        }

        // 종성 제외
        // ex: '각' -> '가'
        private fun deleteFinalSound(c: Char):
                Char = getFinalSound(c)?.let { c - finalIndex(c) }
            ?: throw HangeulException()

        // 종성 추가
        private fun addFinalSound(c: Char, finalSound: Char): Char {
            getFinalSound(c)?.let {
                throw HangeulException("The Hangeul character '$c' already have finalSound")
            }
            return (c.code + FinalSound.indexOf(finalSound)).toChar()
        }
    }
}