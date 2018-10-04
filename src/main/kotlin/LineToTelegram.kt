import model.Pack
import utils.Utils

private const val FIVE_SECOND = 5000L
private const val STICKER_SHOP_URL = "https://store.line.me/stickershop"
private const val STICKER_SHOP_URL2 = "https://webcache.googleusercontent.com/"   //For region-locked stickers, google cache
private const val STICKER_SHOP_URL3 = "https://store.line.me/emojishop/product/"  //For emoji packs

fun main(args: Array<String>)
{
    args.forEach {
        if (it.startsWith(STICKER_SHOP_URL) || it.startsWith(STICKER_SHOP_URL2))
        {
            println("Initializing process ...")

            val stickerPack = Pack(it, "Sticker")
            Utils.createDirectory(stickerPack.title)

            stickerPack.stickers.forEach {
                Utils.saveImage(it.url, it.name, stickerPack.title)

                println("Sticker pack title: ${stickerPack.title}")
                println("Sticker name: ${it.name}")
                println("Sticker URL: ${it.url}")
            }

            Utils.writeCaptionImage(stickerPack.copyright, stickerPack.title)
            println("${stickerPack.title} pack is ready")
            Thread.sleep(FIVE_SECOND)
        }

        if (it.startsWith(STICKER_SHOP_URL3))
        {
            println("Initializing Emoji download process ...")

            val stickerPack = Pack(it, "Emoji")
            Utils.createDirectory(stickerPack.title)

            //For each sticker in the "stickers" array in Pack.kt ...
            stickerPack.stickers.forEach {
                Utils.saveImage(it.url, it.name, stickerPack.title)

                println("Sticker pack title: ${stickerPack.title}")
                println("Sticker name: ${it.name}")
                println("Sticker URL: ${it.url}")
            }

            Utils.writeCaptionImage(stickerPack.copyright, stickerPack.title)
            println("${stickerPack.title} pack is ready")
            Thread.sleep(FIVE_SECOND)
        }
    }
    println("Now you can use @stickers at Telegram to create sticker packs")
}