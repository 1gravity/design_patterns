package com.onegravity.designpatterns.lazyinit

/**
 * The "Java equivalent" version of an Singleton using a private constructor and lazy initialization for the instance.
 */
class SingletonWithLazyInit private constructor() {

    companion object {
        val instance by lazy { SingletonWithLazyInit() }
    }

}
