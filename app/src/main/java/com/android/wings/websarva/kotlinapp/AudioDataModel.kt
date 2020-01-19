package com.android.wings.websarva.kotlinapp

class AudioDataModel {

    var textNumber : String? = null
    var audioNumber : String? = null

    constructor(text: String, audio: String) {
        this.textNumber = text
        this.audioNumber = audio
    }
}