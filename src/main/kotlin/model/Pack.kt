package model

import org.jsoup.Jsoup
import utils.JsoupUtils

class Pack(packUrl: String, private var type: String)
{
    private val doc = Jsoup.connect(packUrl).get().body()           //The full source code
    val copyright: String = JsoupUtils.getCopyrightCaption(doc)
    val title: String = JsoupUtils.getPackTitle(doc)

    val stickers: Array<Sticker> = initSticker()

    private fun initSticker(): Array<Sticker>
    {
        val links: Array<String>
        val stickers = arrayListOf<Sticker>()

        if(type == "Sticker")
        {
            links = JsoupUtils.getImageLinks(doc)

            links.forEach { stickers.add(Sticker(it, "Sticker")) }

            return stickers.toTypedArray()
        }

        if(type == "Emoji")
        {
            links = JsoupUtils.getImageLinksEmoji(doc)

            links.forEach { stickers.add(Sticker(it,"Emoji")) }

            return stickers.toTypedArray()
        }

        return stickers.toTypedArray()
    }
}

