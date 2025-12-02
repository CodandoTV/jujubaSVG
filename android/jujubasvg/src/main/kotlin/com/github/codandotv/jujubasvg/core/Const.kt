package com.github.codandotv.jujubasvg.core

public object Const {
    internal const val SVG_CODE_SIGN = "        <!-- svg here -->"
    internal const val JS_CODE_SIGN = "        // baseJS here"
    internal const val MIME_TYPE = "text/html"
    internal const val ENCONDING = "utf-8"
    internal const val BASE_INTERFACE_NAME = "JujubaInterface"
    internal const val TAG = "JujubaSVG"
    internal const val JS_CLICK_EVENT = """
    function onClickEvent (event) {
        const id = event.target.id

        const boundingClientRect = event.target.getBoundingClientRect()
        const x = boundingClientRect.x
        const y = boundingClientRect.y

        $BASE_INTERFACE_NAME.onElementClicked(id, x, y)
        console.log(event.target.id)
    }
    """;
}
