package klt.mdy.offlinesupportwithpaging.domain

import klt.mdy.offlinesupportwithpaging.common.Resource
import klt.mdy.offlinesupportwithpaging.model.DataObjVos

data class TestApiData(
    val languages: Resource<List<DataObjVos>> = Resource.Loading(),
    )
