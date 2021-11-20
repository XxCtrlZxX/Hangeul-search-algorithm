class Hangeul {
    companion object {
        private const val init = 0xAC00 // 한글 시작점

        private val InitSound = arrayOf('ㄱ','ㄲ','ㄴ','ㄷ','ㄸ','ㄹ','ㅁ','ㅂ','ㅃ','ㅅ','ㅆ','ㅇ','ㅈ','ㅉ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ' )
        private val MiddleSound = arrayOf('ㅏ','ㅐ','ㅑ','ㅒ','ㅓ','ㅔ','ㅕ','ㅖ','ㅗ','ㅘ','ㅙ','ㅚ','ㅛ','ㅜ','ㅝ','ㅞ','ㅟ','ㅠ','ㅡ','ㅢ','ㅣ')
        private val FinalSound = arrayOf(null, 'ㄱ','ㄲ','ㄳ','ㄴ','ㄵ','ㄶ','ㄷ','ㄹ','ㄺ','ㄻ','ㄼ','ㄽ','ㄾ','ㄿ','ㅀ','ㅁ','ㅂ','ㅄ','ㅅ','ㅆ','ㅇ','ㅈ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ')

//        fun finalRange(
//            s: String,
//            finalSound: Char
//        ):

        fun getInitSound(
            c: Char
        ): Char? {
            if (c !in '가'..'힣') return null // 한글이 아니면 종료
            return InitSound[(c - init).code / 28 / 21]
        }

        fun getMiddleSound(
            c: Char
        ): Char {
            return MiddleSound[(c - init).code / 28 % 21]
        }

        fun getFinalSound(
            c: Char
        ): Char? {
            if (c !in '가'..'힣') return null
            return FinalSound[(c - init).code % 28]
        }
    }
}