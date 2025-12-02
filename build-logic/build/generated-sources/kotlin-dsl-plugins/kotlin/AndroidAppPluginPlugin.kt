/**
 * Precompiled [android-app-plugin.gradle.kts][Android_app_plugin_gradle] script plugin.
 *
 * @see Android_app_plugin_gradle
 */
public
class AndroidAppPluginPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Android_app_plugin_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
