package klt.mdy.offlinesupportwithpaging.domain

import klt.mdy.offlinesupportwithpaging.common.Resource
import klt.mdy.offlinesupportwithpaging.model.test.DataObjVos

data class TestApiData(
    val languages: Resource<List<DataObjVos>> = Resource.Loading(),
    )
