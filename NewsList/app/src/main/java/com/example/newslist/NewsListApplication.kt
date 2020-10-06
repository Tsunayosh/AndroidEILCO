import android.app.Application

class NewsListApplication : Application() {
    var login: String? = null

    override fun onCreate() {
        super.onCreate()
        login = null
    }
}