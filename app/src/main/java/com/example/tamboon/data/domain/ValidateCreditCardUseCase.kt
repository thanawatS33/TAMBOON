package com.example.tamboon.data.domain

import com.example.tamboon.util.State
import java.util.regex.Pattern

class ValidateCreditCardUseCase {

    private val listOfPattern by lazy {
        val listOfPattern = arrayListOf<String>()
        val ptVisa = "^4[0-9]{6,}$"
        listOfPattern.add(ptVisa)
        val ptMasterCard = "^5[1-5][0-9]{5,}$"
        listOfPattern.add(ptMasterCard)
        val ptAmeExp = "^3[47][0-9]{5,}$"
        listOfPattern.add(ptAmeExp)
        val ptDinClb = "^3(?:0[0-5]|[68][0-9])[0-9]{4,}$"
        listOfPattern.add(ptDinClb)
        val ptDiscover = "^6(?:011|5[0-9]{2})[0-9]{3,}$"
        listOfPattern.add(ptDiscover);
        val ptJcb = "^(?:2131|1800|35[0-9]{3})[0-9]{3,}$"
        listOfPattern.add(ptJcb)
        return@lazy listOfPattern
    }

    operator fun invoke(cardNumber: String): State {
        var state = State.INVALID
        for(item in listOfPattern){
            val pattern = Pattern.compile(item)
            if(pattern.matcher(cardNumber).matches()){
                state = State.VALID
            }
        }
        return state
    }

}