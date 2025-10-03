# Available Commands 📐

We have some commands already provided by the library.

## Update background color

Update the background color of a node. For example:

```kotlin
jujubaCommander.execute(
    Command.UpdateBackgroundColor(
        id = nodeInfo.id,
        color = getRainbowColor()
    )
)
```

```dart
commander.execute(
    UpdateBackgroundColor(id: nodeInfo.id, colorHex: '#000000')
);
```

## Update stroke color

Update the stroke color of a node.

```kotlin
jujubaCommander.execute(
    Command.UpdateStrokeColor(
        id = nodeInfo.id,
        color = getRainbowColor()
    )
)
```

```dart
commander.execute(
    UpdateStrokeColor(id: nodeInfo.id, colorHex: '#000000')
);
```

## Update stroke width

Update the stroke width of a node.

```kotlin
jujubaCommander.execute(
    Command.UpdateStrokeWidth(
        id = nodeInfo.id,
        widthInPx = 20
    )
)
```

```dart
commander.execute(
    UpdateStrokeWidth(id: nodeInfo.id, widthInPx: 12)
);
```

## Remove a node

Remove a node.

```kotlin
jujubaCommander.execute(
    Command.RemoveNode(
        id = nodeInfo.id
    )
)
```

```dart
commander.execute(
    RemoveNode(
        id: nodeInfo.id,
    )
);
```

## Update root background color

Update the root background color.

```kotlin
jujubaCommander.execute(
    Command.UpdateRootBackgroundColor(
        color = Color.White
    )
)
```

```dart
commander.execute(
    UpdateRootBackgroundColor(colorInHex: '#000000')
);
```

## Add Rounded Image

Add a rounded image into the same parent of the elementId.

```kotlin
jujubaCommander.execute(
    AddRoundedImage(
        elementId = nodeInfo.id,
        imageId = "imageId",
        imageUrl = "www.myimage.com",
        widthInPx = 56,
        heightInPx = 56,
        coordinate = NodeCoordinate(0f, 120f)
    )
)
```

```dart
commander.execute(
    AddRoundedImage(
        elementId: nodeInfo.id,
        imageId: 'nasa',
        imageUrl: 'https://i.imgur.com/LQIsf.jpeg',
        widthInPx: 100,
        heightInPx: 100,
        coordinate: nodeInfo.coordinate,
    )
);
```

In this case, the current module should depend on any module whose name includes 'util-something'. This rule is useful if you need to enforce that a module depends only on utility modules.
