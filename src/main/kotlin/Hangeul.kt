object Hangeul {
    private interface ExtractSound {
        fun of(c: Char) : Char?
        fun index(c: Char) : Int
    }

    object Check {
        fun isCompleteHangeul(c: Char) = c in '가'..'힣'

        fun isConsonant(c: Char) = c in 'ㄱ'..'ㅎ'

        fun isVowel(c: Char) = c in 'ㅏ'..'ㅣ'

        fun isHangeul(c: Char): Boolean = isCompleteHangeul(c) || isConsonant(c) || isVowel(c)
    }

    object InitSound : ExtractSound {
        override fun of(c: Char): Char {
            return if (Check.isCompleteHangeul(c)) {
                initSound[index(c)]
            } else c
        }

        override fun index(c: Char) = (c - hangeulInit).code / 28 / 21
    }

    object MiddleSound : ExtractSound {
        override fun of(c: Char): Char {
            return if (Check.isCompleteHangeul(c)) {
                middleSound[index(c)]
            } else c
        }

        override fun index(c: Char) = (c - hangeulInit).code / 28 % 21
    }

    object FinalSound : ExtractSound {
        override fun of(c: Char): Char? {
            return if (Check.isCompleteHangeul(c) && have(c)) {
                finalSound[index(c)]
            } else null
        }

        override fun index(c: Char) = (c - hangeulInit).code % 28

        // 종성이 있는지
        private fun have(c: Char): Boolean = index(c) != 0  // index가 0이면 받침 없는 것임
    }
}

// 초성, 중성, 종성 셋 중 둘이 같으면 true
fun Char.similarHangeulTo(other: Char): Boolean {
    if (this == other)
        return true

    val thisSoundList = splitHangeulLetter(this)
    val otherSoundList = splitHangeulLetter(other)
    val a = thisSoundList[0] == otherSoundList[0]
    val b = thisSoundList[1] == otherSoundList[1]
    val c = thisSoundList[2]?.equalsOrBothNull(otherSoundList[2]) ?: false  // why?

    return (!a && b && c) || (a && !b && c) || (a && b && !c)
}

fun Any?.equalsOrBothNull(other: Any?): Boolean {
    if (this == null)
        return other == null
    return this == other
}