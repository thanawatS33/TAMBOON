package com.example.tamboon.data.domain

import android.util.Log
import co.omise.android.api.Client
import co.omise.android.api.RequestListener
import co.omise.android.models.CardParam
import co.omise.android.models.Token
import com.example.tamboon.data.model.DataHolder
import com.example.tamboon.data.repository.TamBoonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetIdTokenUseCase constructor(private val repository: TamBoonRepository) {

    suspend operator fun invoke(
        name: String,
        _number: String,
        expireDate: String,
        securityCode: String
    ): DataHolder<String> {
        val client = Client(repository.getOmisePublicKey())
        val month = expireDate.split("/")[0].toInt()
        val year = expireDate.split("/")[1].toInt() + 2000
        val number = _number.replace(" ", "")
        val cardParam = CardParam(
            name = name,
            number = number,
            expirationMonth = month,
            expirationYear = year,
            securityCode = securityCode
        )
        val request = Token.CreateTokenRequestBuilder(cardParam).build()
        return suspendCoroutine { continuation ->
            client.send(request, object : RequestListener<Token> {
                override fun onRequestSucceed(model: Token) {
                    if (model.id.isNullOrEmpty()) {
                        continuation.resume(DataHolder.error("nothing id"))
                    } else {
                        continuation.resume(DataHolder.success(model.id))
                    }
                }

                override fun onRequestFailed(throwable: Throwable) {
                    continuation.resume(DataHolder.error(throwable.message))
                }
            })
        }

    }

}