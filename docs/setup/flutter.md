# Flutter

## 1. Add the Dependency

You need to add jujuba_svg library in your `pubspec.yaml`:

```yaml
dependencies:
  jujuba_svg: ^1.0.0
```

## 2. Define your Commander

Let's say you want to use the library in your `my_beatiful_screen.dart`. You need to initialize the controller:

```dart
class MyBeautifulScreen extends StatelessWidget {
  final JujubaCommander commander = JujubaCommander();

//...
```

## 3. Declare the Widget to render the SVG

The library provides a widget called `JujubaSVGWidget`, there you can the `commander`, `svgText`, and `onElementClick`.

- `commander`: allow you to send commands to manipulate the SVG;

- `svgText`: your SVG;

- `onElementClick`: callback to intercept click events in your SVG.

```dart
class MyBeautifulScreen extends StatelessWidget {
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
  // ...
```

Don't forget:

- `svgText` should contain all content of your SVG file.
