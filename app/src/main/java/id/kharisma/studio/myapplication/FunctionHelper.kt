package id.kharisma.studio.myapplication

import java.text.DecimalFormat


object FunctionHelper {

    fun rupiahFormat(price: Int): String {
        val formatter = DecimalFormat("#,###")
        return "Rp " + formatter.format(price.toLong())
    }
}