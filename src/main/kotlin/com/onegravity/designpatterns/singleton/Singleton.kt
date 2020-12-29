package com.onegravity.designpatterns.singleton

/**
 * Singleton's are part of the Kotlin language. It can't get any simpler than this
 */
object Singleton

/**
 * The "Java equivalent" version of an Singleton design pattern using a private constructor and lazy initialization for the instance.
 */
class Singleton2 private constructor() {
    companion object {
        val instance by lazy { Singleton2() }
    }
}