package model

class Sticker(val url: String, private val type: String)
{
    val name:String = initName()

    private fun initName(): String
    {
        if(type == "Sticker")
        {
            val leftIndent = "sticker/"
            val rightIndent = "/ANDROID"

            return url.substring(url.indexOf(leftIndent) + leftIndent.length, url.indexOf(rightIndent))
        }

        if(type == "Emoji")
        {
            val leftIndent = "iphone/"
            val rightIndent = ".png"
            return url.substring(url.indexOf(leftIndent) + leftIndent.length, url.indexOf(rightIndent))
        }

        return ""
    }
}