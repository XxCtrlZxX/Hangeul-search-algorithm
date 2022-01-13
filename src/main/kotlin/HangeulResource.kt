const val hangeulInit = 0xAC00 // 한글 시작점

val initSound =
    arrayOf('ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ')

val middleSound =
    arrayOf('ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ')

val finalSound = arrayOf(
    null,
    'ㄱ',
    'ㄲ',
    'ㄳ',
    'ㄴ',
    'ㄵ',
    'ㄶ',
    'ㄷ',
    'ㄹ',
    'ㄺ',
    'ㄻ',
    'ㄼ',
    'ㄽ',
    'ㄾ',
    'ㄿ',
    'ㅀ',
    'ㅁ',
    'ㅂ',
    'ㅄ',
    'ㅅ',
    'ㅆ',
    'ㅇ',
    'ㅈ',
    'ㅊ',
    'ㅋ',
    'ㅌ',
    'ㅍ',
    'ㅎ'
)

val consonantsToAlphabetMap = mapOf(
    'ㄱ' to "r", 'ㄲ' to "R", 'ㄴ' to "s", 'ㄷ' to "e", 'ㄸ' to "E", 'ㄹ' to "f", 'ㅁ' to "a", 'ㅂ' to "q",
    'ㅃ' to "Q", 'ㅅ' to "t", 'ㅆ' to "T", 'ㅇ' to "d", 'ㅈ' to "w", 'ㅉ' to "W", 'ㅊ' to "c", 'ㅋ' to "z",
    'ㅌ' to "x", 'ㅍ' to "v", 'ㅎ' to "g",
    'ㄳ' to "rt", 'ㄵ' to "sw", 'ㄶ' to "sg", 'ㄺ' to "fr", 'ㄻ' to "fa", 'ㄼ' to "fq", 'ㄽ' to "ft", 'ㄾ' to "fx",
    'ㄿ' to "fv", 'ㅀ' to "fg", 'ㅄ' to "qt"
)

val vowelsToAlphabetMap = mapOf(
    'ㅏ' to "k", 'ㅐ' to "o", 'ㅑ' to "i", 'ㅒ' to "O", 'ㅓ' to "j", 'ㅔ' to "p", 'ㅕ' to "u", 'ㅖ' to "P",
    'ㅗ' to "h", 'ㅘ' to "hk", 'ㅙ' to "ho", 'ㅚ' to "hl", 'ㅛ' to "y", 'ㅜ' to "n", 'ㅝ' to "nj", 'ㅞ' to "np",
    'ㅟ' to "nl", 'ㅠ' to "b", 'ㅡ' to "m", 'ㅢ' to "ml", 'ㅣ' to "l"
)

val doubleConsonantsMap = mapOf(
    'ㄳ' to "ㄱㅅ", 'ㄵ' to "ㄴㅈ",
    'ㄶ' to "ㄴㅎ", 'ㄺ' to "ㄹㄱ",
    'ㄻ' to "ㄹㅁ", 'ㄼ' to "ㄹㅂ",
    'ㄽ' to "ㄹㅅ", 'ㄾ' to "ㄹㅌ",
    'ㄿ' to "ㄹㅍ", 'ㅀ' to "ㄹㅎ",
    'ㅄ' to "ㅂㅅ"
)