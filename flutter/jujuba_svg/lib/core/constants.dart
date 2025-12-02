const htmlSVGMarker = '        <!-- svg here -->';
const htmlBaseJSMarker = '      //base_js_here';

const String baseHtml = '''
<!DOCTYPE html>
<html lang="en">
    <script>
      $htmlBaseJSMarker
      
      $onClickEventJS
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

const onClickEventJS = '''
function onClickEvent (event) {
  const id = event.target.id

  const boundingClientRect = event.target.getBoundingClientRect()
  const x = boundingClientRect.x
  const y = boundingClientRect.y

  $jujubaFlutterChannelName.postMessage(id + "," + x + "," + y);
  console.log(event.target.id)
}
''';

const jujubaFlutterChannelName = 'JujubaSVGChannel';
