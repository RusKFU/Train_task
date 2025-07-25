package com.example.train_task.di

import android.annotation.SuppressLint
import android.app.Application
import androidx.room.Room
import com.example.train_task.data.api.ApiConstants
import com.example.train_task.data.api.BinfoApi
import com.example.train_task.data.db.BinfoDatabase
import com.example.train_task.data.repository.BinfoDatabaseRepository
import com.example.train_task.domain.repository.BinfoRepository
import com.example.train_task.domain.usecases.*

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.*

@Module
@InstallIn(SingletonComponent::class)
object BinfoAppModule {
    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): BinfoApi {
        return builder
            .build()
            .create(BinfoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        val logging = HttpLoggingInterceptor()
        logging.setLevel((HttpLoggingInterceptor.Level.BODY))
        val client = getUnsafeOkHttpClient()
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
    }

    @Provides
    @Singleton
    fun provideBinfoDatabase(app: Application): BinfoDatabase {
        return Room.databaseBuilder(
            app,
            BinfoDatabase::class.java,
            BinfoDatabase.DATABASE_NAME
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideBinfoRepository(db: BinfoDatabase): BinfoRepository {
        return BinfoDatabaseRepository(db.binfoDao)
    }

    @Provides
    @Singleton
    fun provideDeleteBinUseCase(repository: BinfoRepository): DeleteBin {
        return DeleteBin(repository)
    }

    @Provides
    @Singleton
    fun provideGetBinUseCase(repository: BinfoRepository): GetBin {
        return GetBin(repository)
    }

    @Provides
    @Singleton
    fun provideGetBinsUseCase(repository: BinfoRepository): GetBins {
        return GetBins(repository)
    }

    @Provides
    @Singleton
    fun provideInsertBinUseCase(repository: BinfoRepository): InsertBin {
        return InsertBin(repository)
    }
}

//fix for ssl
@SuppressLint("TrustAllX509TrustManager")
private fun getUnsafeOkHttpClient(): OkHttpClient {
    val trustAllCerts = arrayOf<TrustManager>(@SuppressLint("CustomX509TrustManager")
    object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        }

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        }

        override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
    })

    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, java.security.SecureRandom())
    val sslSocketFactory = sslContext.socketFactory

    return OkHttpClient.Builder()
        .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }.build()
}