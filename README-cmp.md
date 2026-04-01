1.基于https://github.com/jenly1314/UltraSwipeRefresh 修改为cmp跨平台版本

2.其中lottie指示器更换为io.github.ismai117:kottie三方库

使用时： commonMain-composeResources-files中放入json动画文件

```kotlin

  var footerSpec by remember { mutableStateOf<KottieCompositionSpec?>(null) }
    LaunchedEffect(Unit) {
        // 使用 composeApp 的 Res 读取资源
        val bytes = Res.readBytes("files/usr_lottie_rhomb.json")
        footerSpec = KottieCompositionSpec.JsonString(bytes.decodeToString())
    }

 LottieRefreshFooter(
                state = it,
                spec = footerSpec,
            )
```





