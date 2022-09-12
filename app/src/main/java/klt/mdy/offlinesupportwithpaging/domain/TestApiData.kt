package klt.mdy.offlinesupportwithpaging.domain

import klt.mdy.offlinesupportwithpaging.common.Resource
import klt.mdy.offlinesupportwithpaging.model.test.MemeVos

data class TestApiData(
    val languages: Resource<List<MemeVos>> = Resource.Loading(),
    )
