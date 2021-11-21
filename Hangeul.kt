class Hangeul {
    companion object {
        private const val init = 0xAC00 // 한글 시작점

        private val InitSound = arrayOf('ㄱ','ㄲ','ㄴ','ㄷ','ㄸ','ㄹ','ㅁ','ㅂ','ㅃ','ㅅ','ㅆ','ㅇ','ㅈ','ㅉ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ' )
        private val MiddleSound = arrayOf('ㅏ','ㅐ','ㅑ','ㅒ','ㅓ','ㅔ','ㅕ','ㅖ','ㅗ','ㅘ','ㅙ','ㅚ','ㅛ','ㅜ','ㅝ','ㅞ','ㅟ','ㅠ','ㅡ','ㅢ','ㅣ')
        private val FinalSound = arrayOf(null, 'ㄱ','ㄲ','ㄳ','ㄴ','ㄵ','ㄶ','ㄷ','ㄹ','ㄺ','ㄻ','ㄼ','ㄽ','ㄾ','ㄿ','ㅀ','ㅁ','ㅂ','ㅄ','ㅅ','ㅆ','ㅇ','ㅈ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ')
        private fun initIndex(c: Char) = (c - init).code / 28 / 21
        private fun middleIndex(c: Char) = (c - init).code / 28 % 21
        private fun finalIndex(c: Char) = (c - init).code % 28

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
        private fun splitSound(consonant: Char) = splittedMap.getOrDefault(consonant, null)

        fun getInitSound(
            c: Char
        ): Char? {
            if (c !in '가'..'힣') return null // 한글이 아니면 종료
            return InitSound[initIndex(c)]
        }

        fun getMiddleSound(
            c: Char
        ): Char {
            return MiddleSound[middleIndex(c)]
        }

        fun getFinalSound(
            c: Char
        ): Char? {
            if (c !in '가'..'힣') return null
            return FinalSound[finalIndex(c)]
        }

        // 종성 연음된 문자열로 변환
        // ex: "각" -> "가ㄱ", "갋" -> "갈ㅂ"
        fun convertHangeulFinalSound(
            finalSound: Char,
            s: String
        ): String {
            val frontString = s.substring(0 until s.lastIndex)
            val excludedLastChar = excludeFinalSound(s.last())

            return when (finalSound) {
                'ㄳ','ㄵ','ㄶ','ㄺ','ㄻ','ㄼ','ㄽ','ㄾ','ㄿ','ㅀ','ㅄ' -> {
                    val pair = splitSound(finalSound)?.toList() ?: return s
                    println(pair)
                    val newLastChar = addFinalSound(excludedLastChar, pair[0]) // 종성 하나만 추가
                    frontString + newLastChar + pair[1]
                }
                else -> frontString + excludedLastChar + finalSound
            }
        }

        // 종성 제외
        // ex: '각' -> '가'
        private fun excludeFinalSound(
            c: Char
        ) = (c.code - finalIndex(c)).toChar()

        // 종성 추가
        // * c에는 종성이 없는 한글이 와야 함
        private fun addFinalSound(
            c: Char,
            finalSound: Char
        ): Char? {
            getFinalSound(c)?.let { return null }
            return (c.code + FinalSound.indexOf(finalSound)).toChar()
        }
    }
}