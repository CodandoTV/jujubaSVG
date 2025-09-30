const htmlSVGMarker = '        <!-- svg here -->';

const String baseHtml = '''
<!DOCTYPE html>
<html lang="en">
    <script>
      $baseJs
    </script>
    <head>
        <meta name="viewport"  content="width=device-width, initial-scale=1, maximum-scale=1"/>
        <title>Panda</title>
    </head>
    <body onclick="onClickEvent(event)">
      $htmlSVGMarker
    </body>
</html>
''';

const String baseJs = '''
function _getPandaNodeById (elementId) {
  return document.getElementsByTagName('svg')[0].getElementById(elementId)
}

function updateBackgroundColor (elementId, color) {
  const node = _getPandaNodeById(elementId)
  node.style.fill = color
}

function updateStrokeColor (elementId, color) {
  const node = _getPandaNodeById(elementId)
  node.style.stroke = color
}

function updateStrokeWidth (elementId, widthInPx) {
  const node = _getPandaNodeById(elementId)
  node.style.strokeWidth = widthInPx
}

function removeNode (elementId) {
  const node = _getPandaNodeById(elementId)
  node.remove()
}

function updateRootBackgroundColor (color) {
  document.body.style.backgroundColor = color
}

function addRoundedImage (elementId, imageId, url, width, height, x, y) {
  const basePandaSVG = document.getElementsByTagName('svg')[0]
  const targetElement = _getPandaNodeById(elementId)

  if (document.getElementById('roundedShape')) {
    console.log('shape already created')
  } else {
    const circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle')
    circle.setAttributeNS('http://www.w3.org/2000/svg', 'cx', '.5')
    circle.setAttributeNS('http://www.w3.org/2000/svg', 'cy', '.5')
    circle.setAttributeNS('http://www.w3.org/2000/svg', 'r', '.5')

    const clipPath = document.createElementNS('http://www.w3.org/2000/svg', 'clipPath')
    clipPath.setAttributeNS('http://www.w3.org/2000/svg', 'id', 'roundedShape')
    clipPath.setAttributeNS('http://www.w3.org/2000/svg', 'clipPathUnits', 'objectBoundingBox')
    clipPath.appendChild(circle)

    const defs = document.createElementNS('http://www.w3.org/2000/svg', 'defs')
    defs.appendChild(clipPath)

    basePandaSVG.appendChild(defs)
  };

  const svgimg = document.createElementNS('http://www.w3.org/2000/svg', 'image')
  if (targetElement.tagName === 'circle') {
    svgimg.setAttributeNS('http://www.w3.org/2000/svg', 'cx', x)
    svgimg.setAttributeNS('http://www.w3.org/2000/svg', 'cy', y)
  } else {
    svgimg.setAttributeNS('http://www.w3.org/2000/svg', 'x', x)
    svgimg.setAttributeNS('http://www.w3.org/2000/svg', 'y', y)
  }
  svgimg.setAttributeNS('http://www.w3.org/2000/svg', 'width', width)
  svgimg.setAttributeNS('http://www.w3.org/2000/svg', 'height', height)
  svgimg.setAttributeNS('http://www.w3.org/2000/svg', 'clip-path', 'url(#roundedShape)')
  svgimg.setAttributeNS('http://www.w3.org/2000/svg', 'href', url)
  svgimg.setAttributeNS('http://www.w3.org/2000/svg', 'id', imageId)

  const parentNode = targetElement.parentNode
  parentNode.appendChild(svgimg)

  basePandaSVG.innerHTML = basePandaSVG.innerHTML
}

function onClickEvent (event) {
  const id = event.target.id

  const boundingClientRect = event.target.getBoundingClientRect()
  const x = boundingClientRect.x
  const y = boundingClientRect.y

  $pandaFlutterChannelName.postMessage(id + "," + x + "," + y);
  console.log(event.target.id)
}
''';

const pandaFlutterChannelName = 'PandaSVGChannel';
