package utils

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

object JsoupUtils
{
    private val NEW_LINE = System.getProperty("line.separator")
    private const val LINE_CORP = "Copyright © LINE Corporation"
    private const val RIGHTS = "All Rights Reserved"

    fun getImageLinks(doc: Element): Array<String>
    {
        val imageList = getListElements(doc)

        // From each li element select span that contains img url in it style
        // We get first because li contains only one span
        val imageElements = arrayListOf<Element>()
        imageList.forEach { imageElements.add(it.select("span.mdCMN09Image").first()) }

        println("imageElements process done ...")

        // For each span element get style attr and a clear url from it
        val imageUrls = arrayListOf<String>()
        imageElements.forEach { imageUrls.add(getUrlFromStyle(it.attr("style"))) }

        println("imageUrls process done ...")

        return imageUrls.toTypedArray()
    }

    fun getImageLinksEmoji(doc: Element): Array<String>
    {
        val imageList = getListElements(doc)

        // From each li element select span that contains img url in it style
        // We get first because li contains only one span
        val imageElements = arrayListOf<Element>()
        imageList.forEach { imageElements.add(it.select("span.mdCMN09Image").first()) }

        // For each span element get style attr and a clear url from it
        val imageUrls = arrayListOf<String>()
        imageElements.forEach { imageUrls.add(getUrlFromStyleEmoji(it.attr("style"))) }

        return imageUrls.toTypedArray()
    }

    // Select each li element with image by selecting of div -> ul -> li
    private fun getListElements(doc: Element): Elements
    {
        val imageList = doc.select("div.mdCMN09ImgList").first()
        return imageList.select("ul.mdCMN09Ul").first().select("li.mdCMN09Li")
    }

    // Get a clean url from style, selecting just in the middle of the brackets and without params
    private fun getUrlFromStyle(style: String): String {
        val urlWithArgs = style.substring(style.indexOf("(") + 1, style.indexOf(")"))
        return urlWithArgs.substring(0, urlWithArgs.lastIndexOf(";"))
    }

    // Get a clean url from style, selecting just in the middle of the brackets and without params
    private fun getUrlFromStyleEmoji(style: String): String
    {
        val urlWithArgs = style.substring(style.indexOf("(") + 1, style.indexOf(")"))

        //test print out all image links
        //println("urlWithArgs process done ... $urlWithArgs")

        //urlWithArgs.substring(0, urlWithArgs.lastIndexOf('"')) return statement
        return urlWithArgs
    }

    fun getCopyrightCaption(doc: Element): String {
        val element = doc.select("p.mdCMN09Copy")
        val text = element.text()
        val builder = StringBuilder(text)
        val firstSpace = text.split(" ")[0]
        var offset: Int
        when {
            text == LINE_CORP -> return LINE_CORP
            text.contains("Copyright (C)") -> {
                offset = builder.indexOf("(C)") + 3
                builder.insert(offset, NEW_LINE)
                builder.delete(offset + NEW_LINE.length, offset + NEW_LINE.length + 1)
            }
            firstSpace.matches("©\\d{4}".toRegex()) -> {
                offset = builder.indexOf("©") + firstSpace.length
                builder.insert(offset, NEW_LINE)
                builder.delete(offset + NEW_LINE.length, offset + NEW_LINE.length + 1)
            }
        }
        if (text.contains(RIGHTS)) {
            offset = builder.indexOf("All")
            builder.insert(offset, NEW_LINE)
        }
        return builder.toString()
    }

    fun getPackTitle(doc: Element): String
    {
        val element = doc.select("div.mdBox03Inner01").first().select("h3.mdCMN08Ttl").first()
        return element.text().replace("/?%*:|\"<>".toRegex(),"")
    }
}