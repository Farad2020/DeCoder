package com.example.decoder.coderClasses

class CeaserCyphere{

    //encode with step 1
    //abcdefghijklmnopqrstuvwxyz
    //zabcdefghijklmnopqrstuvwxy
    fun encodeCaesarCipher(str: String, shift: Int): String {
        val aCode = 'a'.toInt()
        val zCode = 'z'.toInt()
        val realShift = shift % (zCode - aCode + 1)

        return str.map {
            var code = it.toInt() // asci code
            code += realShift

            if (code > zCode) {
                code = aCode + (code % zCode) - 1
            }

            code.toChar()
        }.joinToString(separator = "")
    }

    fun decodeCaesarCipher(str: String, user_shift: Int): String {
        val shift = 26 - user_shift
        val aCode = 'a'.toInt()
        val zCode = 'z'.toInt()
        val realShift = shift % (zCode - aCode + 1)

        return str.map {
            var code = it.toInt() // asci code
            code += realShift

            if (code > zCode) {
                code = aCode + (code % zCode) - 1
            }

            code.toChar()
        }.joinToString(separator = "")
    }
}

