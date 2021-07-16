package com.zy.net

import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create

/**
 *@author:zhangyue
 *@date:2021/6/30
 */
class RetrofitFactory {

    val retrofitBuilder:Retrofit.Builder by lazy { initRetrofitBuilder() }
    val retrofit:Retrofit by lazy { initRetrofit() }



    companion object{
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { RetrofitFactory() }
    }

    private fun initRetrofitBuilder():Retrofit.Builder {
        return Retrofit.Builder()
    }

    private fun initRetrofit():Retrofit {
        return retrofitBuilder
            .build()
    }

    /**
     * 创建服务实例
     */
    fun <T> create(t:Class<*>):T{
       return retrofit.create(t) as T
    }

    inner class Builder{

        fun setClient(client:OkHttpClient):Builder{
            retrofitBuilder.client(client)
            return this
        }

        fun setBaseUrl(url:String):Builder{
            retrofitBuilder.baseUrl(url)
            return this
        }

        fun addCallAdapterFactory(factory:CallAdapter.Factory):Builder{
            retrofitBuilder.addCallAdapterFactory(factory)
            return this
        }

        fun addConverterFactory(factory:Converter.Factory):Builder{
            retrofitBuilder.addConverterFactory(factory)
            return this
        }

        fun build():Retrofit{
            return RetrofitFactory.INSTANCE.retrofit
        }
    }
}