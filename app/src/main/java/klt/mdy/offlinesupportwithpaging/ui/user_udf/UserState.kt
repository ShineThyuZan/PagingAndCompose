package klt.mdy.offlinesupportwithpaging.ui.user_udf

import klt.mdy.offlinesupportwithpaging.common.Resource
import klt.mdy.offlinesupportwithpaging.model.user.LanguageVo

data class UserState(
    val form: SettingsForm = SettingsForm(),
    val languages: Resource<List<LanguageVo>> = Resource.Loading(),
)