package chat.rocket.android.main.viewmodel

import chat.rocket.android.helper.UrlHelper
import chat.rocket.android.server.domain.*
import chat.rocket.core.model.Myself
import javax.inject.Inject

class NavHeaderViewModelMapper @Inject constructor(
    serverInteractor: GetCurrentServerInteractor,
    getSettingsInteractor: GetSettingsInteractor
) {
    private val currentServer = serverInteractor.get()!!
    private var settings: PublicSettings = getSettingsInteractor.get(currentServer)

    fun mapToViewModel(me: Myself): NavHeaderViewModel {
        val displayName = mapDisplayName(me)
        val status = me.status
        val avatar = me.username?.let { UrlHelper.getAvatarUrl(currentServer, it) }
        val image = settings.wideTile() ?: settings.faviconLarge()
        val logo = image?.let { UrlHelper.getServerLogoUrl(currentServer, it) }

        return NavHeaderViewModel(displayName, status, avatar, currentServer, logo)
    }

    private fun mapDisplayName(me: Myself): String {
        val username = me.username
        val realName = me.name
        val senderName = if (settings.useRealName()) realName else username
        return senderName ?: username.toString()
    }
}