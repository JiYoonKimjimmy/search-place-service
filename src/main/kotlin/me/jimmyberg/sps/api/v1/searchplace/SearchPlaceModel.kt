package me.jimmyberg.sps.api.v1.searchplace

import kotlin.reflect.KProperty

class SearchPlaceModel {
    var title: String by Delegate()
}

class Delegate {
    private var value: String = ""
    operator fun getValue(searchPlaceModel: SearchPlaceModel, property: KProperty<*>): String = value
    operator fun setValue(searchPlaceModel: SearchPlaceModel, property: KProperty<*>, s: String) {
        value = s.replace("<.*?>".toRegex(), "")
    }
}

data class SearchPlaceResponse(
    val places: List<SearchPlaceModel>,
    val size: Int
)