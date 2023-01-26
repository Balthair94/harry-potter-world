package com.example.harrypotterworld.util

class FounderImageProvider {
    companion object {
        fun getFounderImage(houseName: String): String {
            val name = houseName.uppercase()
            HouseEnum.values().find { it.name == name }?.let {
                return when(it) {
                    HouseEnum.GRYFFINDOR ->
                        "https://static.wikia.nocookie.net/harrypotter/images/3/31/Founders_gryffindor1.jpg"
                    HouseEnum.HUFFLEPUFF ->
                        "https://static.wikia.nocookie.net/harrypotter/images/d/d6/PM_HelgaHufflepuff_Founders.jpg"
                    HouseEnum.RAVENCLAW ->
                        "https://static.wikia.nocookie.net/esharrypotter/images/6/6d/Rowena.jpg"
                    HouseEnum.SLYTHERIN ->
                        "https://static.wikia.nocookie.net/esharrypotter/images/b/b3/Salazar.jpg"
                }
            }

            return EMPTY_STRING
        }
    }
}
