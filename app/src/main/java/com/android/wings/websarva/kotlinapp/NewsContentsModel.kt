package com.android.wings.websarva.kotlinapp

class NewsContentsModel(sectionName: String?, webTitle: String?, webUrl: String?) {

    var sectionName: String? = null
    var webTitle: String? = null
    var webUrl: String? = null

    init {
        this.sectionName = sectionName
        this.webTitle = webTitle
        this.webUrl = webUrl
    }

}