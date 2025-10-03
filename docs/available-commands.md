# Available Commands 📐

jujubaSVG already provides some commands. This page we will demonstrate how to use them in Kotlin and Dart (Flutter).


## Update background color

Update the background color of a node. For example:

```kotlin
// KOTLIN
jujubaCommander.execute(
    Command.UpdateBackgroundColor(
        id = nodeInfo.id,
        color = getRainbowColor()
    )
)
```

```dart
// DART
commander.execute(
    UpdateBackgroundColor(
        id: nodeInfo.id, 
        colorHex: getRainbowColorInHex()
    ),
);
```

## Update stroke color

Update the stroke color of a node.

```kotlin
// KOTLIN
jujubaCommander.execute(
    Command.UpdateStrokeColor(
        id = nodeInfo.id,
        color = getRainbowColor()
    )
)
```

```dart
// DART
commander.execute(
    UpdateStrokeColor(
        id: nodeInfo.id, 
        colorHex: getRainbowColorInHex(),
    ),
);
```

## Update stroke width

Update the stroke width of a node.

```kotlin
// KOTLIN
jujubaCommander.execute(
    Command.UpdateStrokeWidth(
        id = nodeInfo.id,
        widthInPx = 20
    )
)
```

```dart
// DART
commander.execute(
    UpdateStrokeWidth(
        id: nodeInfo.id, 
        widthInPx: 20,
    ),
);
```

## Remove a node

Remove a node.

```kotlin
// KOTLIN
jujubaCommander.execute(
    Command.RemoveNode(
        id = nodeInfo.id
    )
)
```

```dart
// DART
commander.execute(
    RemoveNode(
        id: nodeInfo.id,
    )
);
```

## Update root background color

Update the root background color.

```kotlin
// KOTLIN
jujubaCommander.execute(
    Command.UpdateRootBackgroundColor(
        color = Color.White
    )
)
```

```dart
// DART
commander.execute(
    UpdateRootBackgroundColor(
        colorInHex: '#FFFFFF',
    ),
);
```

## Add Rounded Image

Add a rounded image into the same parent of the elementId.

```kotlin
// KOTLIN
jujubaCommander.execute(
    AddRoundedImage(
        elementId = nodeInfo.id,
        imageId = "imageId",
        imageUrl = "https://i.imgur.com/LQIsf.jpeg",
        widthInPx = 56,
        heightInPx = 56,
        coordinate = NodeCoordinate(0f, 120f)
    )
)
```

```dart
// DART
commander.execute(
    AddRoundedImage(
        elementId: nodeInfo.id,
        imageId: 'imageId',
        imageUrl: 'https://i.imgur.com/LQIsf.jpeg',
        widthInPx: 56,
        heightInPx: 56,
        coordinate: NodeCoordinate(
            x: 0.0,
            y: 120.0,
        ),
    ),
);
```
