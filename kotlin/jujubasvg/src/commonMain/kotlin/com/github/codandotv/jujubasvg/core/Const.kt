package com.github.codandotv.jujubasvg.core

public object Const {
    internal const val SVG_CODE_SIGN = "        <!-- svg here -->"
    internal const val JS_CODE_SIGN = "        // baseJS here"
    internal const val JS_CLICK_EVENT = """
    function onClickEvent (event) {
        const id = event.target.id

        const boundingClientRect = event.target.getBoundingClientRect()
        const x = boundingClientRect.x
        const y = boundingClientRect.y

        window.kmpJsBridge.callNative("onElementClicked",JSON.stringify({paramId: id, paramX: x, paramY: y}),
            function (data) {
                console.log(event.target.id)
            }
        );        
    }
    """;
}
