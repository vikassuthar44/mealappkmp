import com.russhwolf.settings.Settings

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform