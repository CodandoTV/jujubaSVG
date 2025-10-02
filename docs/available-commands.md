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

## Remove a node

Remove a node.

```kotlin
jujubaCommander.execute(
    Command.RemoveNode(
        id = nodeInfo.id
    )
)
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

In this case, the current module should depend on any module whose name includes 'util-something'. This rule is useful if you need to enforce that a module depends only on utility modules.
