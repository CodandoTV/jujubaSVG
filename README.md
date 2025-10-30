[![Kotlin](https://img.shields.io/badge/kotlin-2.1.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Flutter](https://img.shields.io/badge/Flutter-%252302569B.svg?style=flat&logo=Flutter&logoColor=white&labelColor=%2301579b&color=%2303a9f4)](https://docs.flutter.dev/)
![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/CodandoTV/jujubaSVG/issues)
[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.codandotv/jujubaSVG)](https://central.sonatype.com/artifact/io.github.codandotv/jujubaSVG)
[![Pub Version](https://img.shields.io/pub/v/jujuba_svg?style=flat)](https://pub.dev/packages/jujuba_svg)

![Logo do CodandoTV](file_readme/codandotv.png)

# Welcome to the JujubaSVG library

Welcome to the jujubaSVG library! A CodandoTV library 👋

jujubaSVG is a user-friendly library for handling SVG files in Android and Flutter applications. It enables granular manipulation of SVG elements - you can access any element by its ID to modify properties like background color, stroke, and other attributes.

<img src="img/jujuba-icon.svg" />


📚 Our documentation is available [here](https://codandotv.github.io/jujubaSVG/).

---


## How to use? 🤔

### Android

You need to add the following line in your desired `module/build.gradle.kts`:

```kotlin
// ...
dependencies {
    implementation("io.github.codandotv:jujubaSVG:<library version>")
    // ...
}
    // ...
```

After that, you need to define at the top level of your composable function the
`commander` (a friend that will help you to send commands to the library):

```kotlin
@Composable
fun YourComposable() {
    val jujubaCommander = rememberJujubaCommander()
    // ...
}
```

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

### Flutter

You need to add jujuba_svg library in your `pubspec.yaml`:

```yaml
dependencies:
  jujuba_svg: ^1.0.0
```

Let's say you want to use the library in your `my_beatiful_screen.dart`. You need to initialize the controller:

```dart
class MyBeautifulScreen extends StatelessWidget {
  final JujubaCommander commander = JujubaCommander();

  //...

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: JujubaSVGWidget(
            commander: commander,
            svgText: svgText,
            onElementClick: (nodeInfo) => {},
        ),
    )
//...
```

The library provides a widget called `JujubaSVGWidget`, there you can the `commander`, `svgText`, and `onElementClick`.

- `commander`: allow you to send commands to manipulate the SVG;

- `svgText`: your SVG;

- `onElementClick`: callback to intercept click events in your SVG.

## Sample project

### Android

More details you can check at our [sample project](android/sampleApp)

<img src="img/teaser.gif" height="500"  alt="teaser showing the app working with SVG image, where onClick deletes a State from Brazil."/>

### Flutter

- Android

<img src="img/jujuba-svg-android-teaser.gif" height="500"  alt="teaser showing the app working on Android with SVG image."/>

- iOS

<img src="img/jujuba-svg-ios-teaser.gif" height="500"  alt="teaser showing the app working on Android with SVG image."/>

--- 

### How to contribute?

- Fork this repository;

- Solve an issue, or do any improvement you want;

- Open a PR to the origin repository.

As soon the PR is reviewed and merged, your update will be available.
