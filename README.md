# line-to-telegram

### What?
Tool to convert a set of Line stickers into Telegram stickers.

### Download
Download the [latest JAR][1]

### How to use
1. Go to [Line stickershop][2]
1. Pick the set of stickers that you want ([Dragonball][3] for example)
1. Execute jar with the link as an argument
```
java -jar line-to-telegram.jar https://store.line.me/stickershop/product/1439/en
```
When work is done, a folder with images on it will be created.

Now you can publish your sticker pack, just do it via the Telegram [stickers bot][5]

### Additional Supports
1. Support region-locked sticker pack: Seach for the pack name(無敵鬧4貓二代 for example) then copy the Google cache link. It has the form of (https://webcache.googleusercontent.com/ ...)
1. You can now also download Line Emoji (only non-region-locked versions for now)

### Result example

#### [DRAGONBALL][4]

<img src="https://github.com/kekc42/line-to-telegram/blob/master/Dragonball.PNG?raw=true" width="50%" />

[1]: https://github.com/kekc42/line-to-telegram/releases/download/1.0.0/line-to-telegram.jar
[2]: https://store.line.me/stickershop/showcase/top/en
[3]: https://store.line.me/stickershop/product/1439/en
[4]: https://t.me/addstickers/LineDBStrickers
[5]: https://t.me/Stickers
