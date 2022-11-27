package lotto.domain

class Lotto(val lotto:List<LottoBall>) {

    init {
        require(lotto.size == LOTTO_SIZE) {"비정상적인 로또입니다."}

    }

    companion object {
        const val LOTTO_SIZE = 6
    }

}
