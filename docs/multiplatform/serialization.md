# Serialization
[from this](https://app.pluralsight.com/player?course=droidcon-nyc-19-life-changing-magic-kotlin-multiplatform-serialization&author=droidcon-nyc&name=2aa960e2-18bc-4923-b3d0-265c86522bcb&clip=0&mode=live)

kotlinx.serialization
* Kotlin Cross-Platform Multi-Format Reflectionless Serialization

```
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

fun main() {
    val s = Json.stringify(Place.serializer(), Place("Microsoft"))
    println(s)

    // if you want to add serializers to Json
    Json {
        serialModule = c
    }
}

@Serializer(forClass = Place::class)
object MySerializer : KSerializer<Place> {
    // can customize Serializer like this
}

@Serializable
data class Place(val name: String? = null)

val a = serializersModule(Place.serializer())
val b = serializersModule(Place.serializer())
val c = a + b
```