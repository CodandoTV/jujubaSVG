# Android

## 1. Add the Dependency

You need to add the following line in your desired module/build.gradle.kts:

```kotlin
implementation("io.github.codandotv:jujubaSVG:<library version>")
```

Make sure your application has internet permission. This is required to run javascript commands:

```xml
// AndroidManifest.xml
<uses-permission android:name="android.permission.INTERNET" />
```

## 2. Define your Commander

You need to define at the top level of your composable function the commander (a friend that will help you to send commands to the library):

```kotlin
@Composable
fun YourComposable() {
    val jujubaCommander = rememberJujubaCommander()
    // ...
}
```

## 3. Declare the Widget to render the SVG

Now you need to declare the Widget responsible to render the SVG file:

```kotlin
@Composable
fun YourComposable() {
    val jujubaCommander = rememberJujubaCommander()

    JujubaSVG(
        svgText = svgText,
        onElementClick = { nodeInfo ->
            println("NodeInfo $nodeInfo")
            coroutineScope.launch {
                // commander sample
                jujubaCommander.execute(
                    Command.RemoveNode(
                        nodeInfo.id
                    )
                )
            }
        },
        commander = jujubaCommander,
    )
}
```

Don't forget:

- `svgText` should contain all content of your SVG file.

--

Any problems you are facing, any suggestions you want to add, please feel free to [reach us out](mailto:gabrielbronzattimoro.es@gmail.com).
