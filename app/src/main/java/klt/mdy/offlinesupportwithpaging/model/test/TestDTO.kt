package klt.mdy.offlinesupportwithpaging.model.test

data class TestDTO(
    val success: Boolean,
    val data: TestHeaderDataObj
) {
    data class TestHeaderDataObj(
        val memes: List<TestDataObj>
    ) {
        data class TestDataObj(
            val id: String,
            val name: String,
            val url: String,
            val width: Int,
            val height: Int,
            val box_count: Int
        ) {
            fun toVo(): DataObjVos {
                return DataObjVos(
                    id = id,
                    name = name,
                    url = url,
                    width = width,
                    height = height,
                    box_count = box_count
                )
            }
        }
    }
}